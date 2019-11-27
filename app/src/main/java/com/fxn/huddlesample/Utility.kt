package com.fxn.huddlesample

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape


class Utility {
    companion object {
        fun getRoundRect(color: String): Drawable? {
            var shape = 150f
            val rectShape =
                RoundRectShape(
                    floatArrayOf(shape, shape, shape, shape, shape, shape, 30f, 30f), null,
                    null
                )
            val shapeDrawable = ShapeDrawable(rectShape)
            shapeDrawable.paint.color = Color.parseColor(color)
            shapeDrawable.paint.style = Paint.Style.FILL
            shapeDrawable.paint.isAntiAlias = true
            shapeDrawable.paint.flags = Paint.ANTI_ALIAS_FLAG
            return shapeDrawable
        }
    }
}