package com.example.go_with_dog.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.example.go_with_dog.R

class Indicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var itemCount: Int = 0
    private var primaryDrawable: Int = R.drawable.ic_indicator_unselect
    private var accentDrawable: Int = R.drawable.ic_indicator_select

    init {
        initAttr(attrs)
    }

    private fun initAttr(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.Indicator) {
            itemCount = getInteger(R.styleable.Indicator_itemCount, 0)
            primaryDrawable = getResourceId(R.styleable.Indicator_primaryDrawable, primaryDrawable)
            accentDrawable = getResourceId(R.styleable.Indicator_accentDrawable, accentDrawable)
        }
    }
}