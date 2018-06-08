/*
 * Project: android-constraintLayout-animation-examples
 * File: MainActivity.kt
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

package com.gmail.fattazzo.android.constraintlayout.animation.examples

import android.support.v7.app.AppCompatActivity
import com.gmail.fattazzo.android.constraintlayout.animation.examples.recyclerparallax.RecyclerViewParallaxActivity_
import com.gmail.fattazzo.android.constraintlayout.animation.examples.zoomcarousel.ZoomCarouselActivity_
import com.gmail.fattazzo.android.constraintlayout.animation.examples.zoomcarouselvalueanimator.ZoomCarouselValueAnimatorActivity_
import com.gmail.fattazzo.android.constraintlayout.animation.examples.zoomdetails.ZoomDetailActivity_
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {

    @Click
    fun zoomDetailButtonClicked() {
        ZoomDetailActivity_.intent(this).start()
    }

    @Click
    fun recyclerviewParallaxButtonClicked() {
        RecyclerViewParallaxActivity_.intent(this).start()
    }

    @Click
    fun zoomCarouselButtonClicked() {
        ZoomCarouselActivity_.intent(this).start()
    }

    @Click
    fun zoomCarouselValueAnimatorButtonClicked() {
        ZoomCarouselValueAnimatorActivity_.intent(this).start()
    }
}
