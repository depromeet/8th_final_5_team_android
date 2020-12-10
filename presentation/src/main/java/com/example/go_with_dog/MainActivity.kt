package com.example.go_with_dog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.example.go_with_dog.base.BaseActivity
import com.example.go_with_dog.databinding.ActivityMainBinding
import com.example.go_with_dog.model.SearchUiModel
import com.example.go_with_dog.view.adapter.SearchCardStackAdapter
import com.yuyakaido.android.cardstackview.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), CardStackListener {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { SearchCardStackAdapter(getMockData()) }

    override fun onWillAttachViewModel() {
        super.onWillAttachViewModel()

        initialize()
        dataBinding.spSearch.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
        listOf("영등포구 여의도동"))
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.Top)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        dataBinding.rvSearch.layoutManager = manager
        dataBinding.rvSearch.adapter = adapter
        dataBinding.rvSearch.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onCardDisappeared(view: View, position: Int) {
//        val textView = view.findViewById<TextView>(com.yuyakaido.android.cardstackview.R.id.item_name)
//        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (direction == Direction.Left) {
            startActivity(Intent(this, MatchActivity::class.java))
        }
    }

//    private fun paginate() {
//        val old = adapter.getSpots()
//        val new = old.plus(createSpots())
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
//    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {}

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    private fun getMockData(): List<SearchUiModel> {
        return listOf(
            SearchUiModel(
                id = 0,
                name = "콩콩이",
                dog = "말티즈 | 3살 | 남아",
                old = "3살",
                gender = "남아",
                category = "애교만땅",
                background = getDrawable(R.drawable.ic_dog1)!!
            ),
            SearchUiModel(
                id = 0,
                name = "산책이",
                dog = "말티즈 | 2살 | 여아",
                old = "2살",
                gender = "여아",
                category = "중성화 O",
                background = getDrawable(R.drawable.ic_dog2)!!
            )
        )
    }
}
