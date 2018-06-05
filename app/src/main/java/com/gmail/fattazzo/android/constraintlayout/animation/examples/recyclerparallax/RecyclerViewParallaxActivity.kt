/*
 * Project: android-constraintLayout-animation-examples
 * File: RecyclerViewParallaxActivity.kt
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

package com.gmail.fattazzo.android.constraintlayout.animation.examples.recyclerparallax

import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import com.gmail.fattazzo.android.constraintlayout.animation.examples.R
import com.gmail.fattazzo.android.constraintlayout.animation.examples.planet.Planets
import kotlinx.android.synthetic.main.activity_recycler_parallax.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import kotlin.math.absoluteValue

/**
 * @author fattazzo
 *         <p/>
 *         date: 05/06/18
 */
@EActivity(R.layout.activity_recycler_parallax)
open class RecyclerViewParallaxActivity : AppCompatActivity() {

    @AfterViews
    fun initViews() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewPlanets)

        recyclerViewPlanets.adapter = PlanetsAdapter(Planets.get().toTypedArray())
        recyclerViewPlanets.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewPlanets.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView!!.layoutManager as LinearLayoutManager
                val firstPosition = manager.findFirstVisibleItemPosition()
                val lastPosition = manager.findLastVisibleItemPosition()
                val offSet = recyclerView.computeHorizontalScrollOffset()
                for (i in 0..lastPosition - firstPosition) {
                    val layout = manager.findViewByPosition(firstPosition + i) as ConstraintLayout
                    val guidelineImage = layout.findViewById<Guideline>(R.id.guidelineImage)
                    val paramsImage = guidelineImage.layoutParams as ConstraintLayout.LayoutParams
                    val w = recyclerView.width
                    val deltaPos = offSet - (firstPosition + i) * w
                    val percent = deltaPos / w.toFloat()
                    paramsImage.guidePercent = Math.max(0.3f, Math.min(0.7f, 0.5f - percent))
                    guidelineImage.layoutParams = paramsImage
                    println(percent)

                    val layoutName = layout.findViewById<ConstraintLayout>(R.id.nameLayout)
                    val guidelineTitle = layoutName.findViewById<Guideline>(R.id.guidelineTitle)
                    val paramsTitle = guidelineTitle.layoutParams as ConstraintLayout.LayoutParams
                    paramsTitle.guidePercent = 1f + percent.absoluteValue
                    guidelineTitle.layoutParams = paramsTitle
                }
            }
        })
    }
}