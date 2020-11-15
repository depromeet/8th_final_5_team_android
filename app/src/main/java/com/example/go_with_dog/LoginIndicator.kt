package com.example.go_with_dog

import android.animation.AnimatorInflater
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes

class LoginIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var itemCount: Int = 0
    private var primaryDrawable: Int = R.drawable.ic_indicator_unselect
    private var accentDrawable: Int = R.drawable.ic_indicator_select

    init {
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.LoginIndicator) {
            itemCount = getInteger(R.styleable.LoginIndicator_itemCount, 0)
            primaryDrawable =
                getResourceId(R.styleable.LoginIndicator_primaryDrawable, primaryDrawable)
            accentDrawable =
                getResourceId(R.styleable.LoginIndicator_accentDrawable, accentDrawable)
        }
    }

    fun createIndicators(count: Int, initialPosition: Int = 0) {
        when {
            count < childCount -> removeViews(count, childCount - count)
            count > childCount -> repeat(count - childCount) { addIndicator() }
        }
        (0 until count)
            .map { getChildAt(it) }
            .forEachIndexed { i, indicator ->
                indicator.isSelected = (i == initialPosition)
                indicator.isEnabled = this.isEnabled
            }
        lastPosition = initialPosition
    }

    private fun addIndicator() {
        val indicator = View(context).apply {
            setBackgroundResource(indicatorDrawableResId)
            if (indicatorAnimatorResId != 0) {
                stateListAnimator =
                    AnimatorInflater.loadStateListAnimator(context, indicatorAnimatorResId)
            }
        }
        val params = generateDefaultLayoutParams().apply {
            width = indicatorWidth.toInt()
            height = indicatorHeight.toInt()
            leftMargin = indicatorMargin.toInt()
            rightMargin = indicatorMargin.toInt()
        }
        addView(indicator, params)
    }
}