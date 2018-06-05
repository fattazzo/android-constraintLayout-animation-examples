/*
 * Project: android-constraintLayout-animation-examples
 * File: PlanetsAdapter.kt
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

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.fattazzo.android.constraintlayout.animation.examples.R
import com.gmail.fattazzo.android.constraintlayout.animation.examples.planet.Planet
import kotlinx.android.synthetic.main.recycler_parallax_item_planet.view.*


/**
 * @author fattazzo
 *         <p/>
 *         date: 05/06/18
 */
class PlanetsAdapter(var planets: Array<Planet>) : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = planets.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_parallax_item_planet, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planet = planets[position]
        holder.nameTV.setText(planet.nameResId)
        holder.imageView.setImageResource(planet.imageResId)
        holder.descriptionTV.setText(planet.descriptionResId)
        holder.numberTV.text = planet.number.toString()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.planetImageView!!
        var descriptionTV = itemView.descriptionTV!!
        var nameTV = itemView.nameTV!!
        var numberTV = itemView.numberTV!!
    }
}