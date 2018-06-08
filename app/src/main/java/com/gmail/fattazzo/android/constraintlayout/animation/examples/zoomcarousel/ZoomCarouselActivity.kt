/*
 * Project: android-constraintLayout-animation-examples
 * File: ZoomCarouselActivity.kt
 *
 * Created by fattazzo
 * Copyright © 2018 Gianluca Fattarsi. All rights reserved.
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gmail.fattazzo.android.constraintlayout.animation.examples.zoomcarousel

import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import com.gmail.fattazzo.android.constraintlayout.animation.examples.R
import kotlinx.android.synthetic.main.activity_zoom_carousel.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_zoom_carousel)
open class ZoomCarouselActivity : AppCompatActivity() {

    private var currentMode: PlanetMode = PlanetMode.NONE

    @AfterViews
    fun initViews() {
        currentMode = PlanetMode.NONE
    }

    @Click
    fun mercuryImageViewClicked() {
        updateConstraints(PlanetMode.MERCURY)
    }

    @Click
    fun venusImageViewClicked() {
        updateConstraints(PlanetMode.VENUS)
    }

    @Click
    fun earthImageViewClicked() {
        updateConstraints(PlanetMode.EARTH)
    }

    @Click
    fun rootLayoutClicked() {
        updateConstraints(PlanetMode.NONE)
    }

    private fun updateConstraints(planetMode: PlanetMode) {
        val newConstraintSet = ConstraintSet()
        currentMode = if (currentMode != planetMode) {
            newConstraintSet.clone(this, planetMode.layoutResId)
            planetMode
        } else {
            newConstraintSet.clone(this, PlanetMode.NONE.layoutResId)
            PlanetMode.NONE
        }
        newConstraintSet.applyTo(rootLayout)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        transition.duration = 500
        TransitionManager.beginDelayedTransition(rootLayout, transition)
    }

    enum class PlanetMode(val layoutResId: Int) {
        NONE(R.layout.activity_zoom_carousel),
        MERCURY(R.layout.activity_zoom_carousel_mercury),
        VENUS(R.layout.activity_zoom_carousel_venus),
        EARTH(R.layout.activity_zoom_carousel_earth);
    }
}
