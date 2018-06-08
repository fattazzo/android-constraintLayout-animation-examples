/*
 * Project: android-constraintLayout-animation-examples
 * File: ZoomCarouselValueAnimatorActivity.kt
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

package com.gmail.fattazzo.android.constraintlayout.animation.examples.zoomcarouselvalueanimator

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.gmail.fattazzo.android.constraintlayout.animation.examples.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById


@EActivity(R.layout.activity_zoom_carousel_valueanimator)
open class ZoomCarouselValueAnimatorActivity : AppCompatActivity() {

    private var currentMode: AnimationMode = AnimationMode.DEFAULT

    @ViewById
    internal lateinit var leftGuideline: Guideline

    @ViewById
    internal lateinit var rightGuideline: Guideline

    @ViewById
    internal lateinit var rootLayout: ConstraintLayout

    @ViewById
    internal lateinit var mercuryDescription: TextView

    @ViewById
    internal lateinit var venusDescription: TextView

    @ViewById
    internal lateinit var earthDescription: TextView


    @AfterViews
    fun initViews() {
        currentMode = AnimationMode.DEFAULT
    }

    @Click
    fun mercuryImageViewClicked() {
        playAnimation(AnimationMode.MERCURY)
    }

    @Click
    fun venusImageViewClicked() {
        playAnimation(AnimationMode.VENUS)
    }

    @Click
    fun earthImageViewClicked() {
        playAnimation(AnimationMode.EARTH)
    }

    @Click
    fun rootLayoutClicked() {
        playDefaultAnimation()
    }

    private fun playDefaultAnimation() {
        if (currentMode != AnimationMode.DEFAULT) {
            val leftGuidelineAnimator = getLeftGuidelineAnimatorToDefault()
            val rightGuidelineAnimator = getRightGuidelineAnimatorToDefault()
            val descriptionAnimator = getDescriptionAnimatorToDefault()

            val buttonAnimator = AnimatorSet()
            buttonAnimator.play(leftGuidelineAnimator).with(rightGuidelineAnimator).after(descriptionAnimator)
            buttonAnimator.start()
            currentMode = AnimationMode.DEFAULT
        }
    }

    private fun playAnimation(animationMode: AnimationMode) {
        if (currentMode == animationMode) {
            playDefaultAnimation()
            return
        }
        mercuryDescription.text = ""
        mercuryDescription.requestLayout()
        venusDescription.text = ""
        venusDescription.requestLayout()
        earthDescription.text = ""
        earthDescription.requestLayout()

        val leftGuidelineAnimator = getLeftGuidelineAnimator(animationMode)
        val rightGuidelineAnimator = getRightGuidelineAnimator(animationMode)
        val descriptionAnimator = getDescriptionAnimator(animationMode)

        val buttonAnimator = AnimatorSet()
        buttonAnimator.play(leftGuidelineAnimator).with(rightGuidelineAnimator).before(descriptionAnimator)
        buttonAnimator.start()
        currentMode = animationMode
    }

    /**
     * ValueAnimator to update leftGuideline to default position
     *
     * @return animator
     */
    private fun getLeftGuidelineAnimatorToDefault(): ValueAnimator {
        val leftPercentage = (leftGuideline.layoutParams as ConstraintLayout.LayoutParams).guidePercent
        val leftGuidelineAnimator = ValueAnimator.ofFloat(leftPercentage, AnimationMode.DEFAULT.leftGuidelinePos)
        leftGuidelineAnimator.addUpdateListener { animation ->
            leftGuideline.setGuidelinePercent(animation.animatedValue as Float)
            leftGuideline.requestLayout()
        }
        leftGuidelineAnimator.duration = ANIMATION_DURATION
        return leftGuidelineAnimator
    }

    /**
     * ValueAnimator to update rightGuideline to default position
     *
     * @return animator
     */
    private fun getRightGuidelineAnimatorToDefault(): ValueAnimator {
        val rightPercentage = (rightGuideline.layoutParams as ConstraintLayout.LayoutParams).guidePercent
        val rightGuidelineAnimator = ValueAnimator.ofFloat(rightPercentage, AnimationMode.DEFAULT.rightGuidelinePos)
        rightGuidelineAnimator.addUpdateListener { animation ->
            rightGuideline.setGuidelinePercent(animation.animatedValue as Float)
            rightGuideline.requestLayout()
        }
        rightGuidelineAnimator.duration = ANIMATION_DURATION
        return rightGuidelineAnimator
    }

    /**
     * ValueAnimator to update all description to default
     *
     * @return animator
     */
    private fun getDescriptionAnimatorToDefault(): ValueAnimator {
        val descriptionAnimator = ValueAnimator.ofFloat(1f, 0f)
        descriptionAnimator.addUpdateListener { animation ->
            mercuryDescription.alpha = animation.animatedValue as Float
            venusDescription.alpha = animation.animatedValue as Float
            rightGuideline.alpha = animation.animatedValue as Float
            if (0f == (animation.animatedValue as Float)) {
                mercuryDescription.text = ""
                mercuryDescription.requestLayout()
                venusDescription.text = ""
                venusDescription.requestLayout()
                earthDescription.text = ""
                earthDescription.requestLayout()
            }
        }
        descriptionAnimator.duration = ANIMATION_DURATION
        return descriptionAnimator
    }

    /**
     * ValueAnimator to update leftGuideline to animation mode position
     *
     * @param animationMode mode
     * @return animator
     */
    private fun getLeftGuidelineAnimator(animationMode: AnimationMode): ValueAnimator {
        val leftPercentage = (leftGuideline.layoutParams as ConstraintLayout.LayoutParams).guidePercent
        val leftGuidelineAnimator = ValueAnimator.ofFloat(leftPercentage, animationMode.leftGuidelinePos)
        leftGuidelineAnimator.addUpdateListener { animation ->
            leftGuideline.setGuidelinePercent(animation.animatedValue as Float)
            leftGuideline.requestLayout()
        }
        leftGuidelineAnimator.duration = ANIMATION_DURATION
        return leftGuidelineAnimator
    }

    /**
     * ValueAnimator to update rightGuideline to animation mode position
     *
     * @param animationMode mode
     * @return animator
     */
    private fun getRightGuidelineAnimator(animationMode: AnimationMode): ValueAnimator {
        val rightPercentage = (rightGuideline.layoutParams as ConstraintLayout.LayoutParams).guidePercent
        val rightGuidelineAnimator = ValueAnimator.ofFloat(rightPercentage, animationMode.rightGuidelinePos)
        rightGuidelineAnimator.addUpdateListener { animation ->
            rightGuideline.setGuidelinePercent(animation.animatedValue as Float)
            rightGuideline.requestLayout()
        }
        rightGuidelineAnimator.duration = ANIMATION_DURATION
        return rightGuidelineAnimator
    }

    /**
     * ValueAnimator to update all description to animation mode position
     *
     * @param animationMode mode
     * @return animator
     */
    private fun getDescriptionAnimator(animationMode: AnimationMode): ValueAnimator {
        val descriptionAnimator = ValueAnimator.ofFloat(0f, 1f)
        descriptionAnimator.addUpdateListener { animation ->
            if (0f == (animation.animatedValue as Float)) {
                when (animationMode) {
                    AnimationMode.MERCURY -> {
                        mercuryDescription.setText(animationMode.description!!)
                        mercuryDescription.requestLayout()
                    }
                    AnimationMode.VENUS -> {
                        venusDescription.setText(animationMode.description!!)
                        venusDescription.requestLayout()
                    }
                    AnimationMode.EARTH -> {
                        earthDescription.setText(animationMode.description!!)
                        earthDescription.requestLayout()
                    }
                    else -> {
                        // Do nothing
                    }
                }
            }
            mercuryDescription.alpha = animation.animatedValue as Float
            venusDescription.alpha = animation.animatedValue as Float
            earthDescription.alpha = animation.animatedValue as Float
        }
        descriptionAnimator.duration = ANIMATION_DURATION
        return descriptionAnimator
    }

    enum class AnimationMode(val leftGuidelinePos: Float, val rightGuidelinePos: Float, val description: Int?) {
        MERCURY(0.6f, 0.8f, R.string.planet_mercury_description_short),
        VENUS(0.2f, 0.8f, R.string.planet_venus_description_short),
        EARTH(0.2f, 0.4f, R.string.planet_earth_description_short),
        DEFAULT(0.33f, 0.66f, null)
    }

    companion object {

        const val ANIMATION_DURATION = 300L
    }
}
