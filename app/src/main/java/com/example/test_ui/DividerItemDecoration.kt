package com.example.test_ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        strokeWidth = 2f
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val left = parent.paddingLeft.toFloat()
        val right = parent.width.toFloat() - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + paint.strokeWidth

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}
