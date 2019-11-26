package com.fxn.huddle

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.getResourceIdOrThrow


class Huddle : FrameLayout {
    companion object {
        val HORIZONTAL = 0
        val VERTICAL = 1
    }

    private var childId: Int = -1
    var spacing: Int = 0
        set(value) {
            field = value
            setupChildViews(huddleAdapter)
            requestLayout()

        }
    var orientation: Int = 0
        set(value) {
            field = value
            setupChildViews(huddleAdapter)
            requestLayout()

        }

    var huddleAdapter: HuddleAdapter? = null
        set(value) {
            field = value
            setupChildViews(value)

        }

    private fun setupChildViews(value: HuddleAdapter?) {
        if (value != null) {
            removeAllViews()
            for (i in 0 until value!!.getItemCount()) {
                var v = LayoutInflater.from(context).inflate(childId, this, false)
                if (v.layoutParams is MarginLayoutParams) {
                    var space = spacing * i
                    val p = v.layoutParams as MarginLayoutParams
                    Log.e("spacing", "=> " + space)
                    when (orientation) {
                        0 -> p.setMargins(space, 0, 0, 0)
                        1 -> p.setMargins(0, space, 0, 0)
                    }
                    v.layoutParams = p
                }
                v.setOnClickListener {
                    value.onclickItem(v, i)
                }

                addView(v)
                value!!.getBindData(v, i)
            }
        }
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.Huddle, 0, 0)
            try {
                orientation = attributes.getInt(R.styleable.Huddle_huddle_orientation, 0)
                spacing = attributes.getDimension(R.styleable.Huddle_huddle_spacing, 0f).toInt()
                childId = attributes.getResourceIdOrThrow(R.styleable.Huddle_huddle_childview)
            } finally {
                attributes.recycle()
                setupChildViews(huddleAdapter)
            }
        }
    }

    interface HuddleAdapter {
        fun getItemCount(): Int
        fun getBindData(view: View, pos: Int)
        fun onclickItem(view: View, pos: Int)
    }
}