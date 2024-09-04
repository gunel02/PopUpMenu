package com.example.test_ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.test_ui.databinding.ActivityMainBinding
import com.example.test_ui.databinding.CustomMenuItemBinding
import com.example.test_ui.databinding.LayoutRecyclerViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconVideoQuality.setOnClickListener {
            showPopupMenu(binding.iconVideoQuality)
        }

    }

    private fun showPopupMenu(anchorView: View) {
        val popupView = LayoutInflater.from(this).inflate(R.layout.layout_recycler_view, null)
        val recyclerView: RecyclerView = popupView.findViewById(R.id.item_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)



        val itemList = getItemList()
        val fixedItemPosition = 0
        val defaultSelectedPosition = itemList.indexOfFirst { it.item == R.string.off }
        recyclerView.adapter =
            ArrayAdapter(this, itemList, fixedItemPosition, defaultSelectedPosition) { position ->
            }
        recyclerView.addItemDecoration(DividerItemDecoration(this))
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        popupWindow.isFocusable = true
        popupWindow.isOutsideTouchable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        popupWindow.showAsDropDown(anchorView)

//        popupWindow.showAtLocation(
//            anchorView,
//            Gravity.CENTER,
//            0,
//            0
//        )

        // Calculate position for left-top alignment
        val anchorViewLocation = IntArray(2)
        anchorView.getLocationOnScreen(anchorViewLocation)
        val xOffset = -popupWindow.contentView.measuredWidth
        val yOffset = 0

        // Show PopupWindow at calculated position
        popupWindow.showAsDropDown(anchorView, xOffset, yOffset)

        // Apply animations
        applyPopupWindowAnimations(popupWindow, anchorView)
    }

    private fun applyPopupWindowAnimations(popupWindow: PopupWindow, anchorView: View) {
        val showAnimation = AnimationUtils.loadAnimation(this, R.anim.popup_enter)
        val hideAnimation = AnimationUtils.loadAnimation(this, R.anim.popup_exit)

        popupWindow.contentView.startAnimation(showAnimation)

        popupWindow.setOnDismissListener {
            popupWindow.contentView.startAnimation(hideAnimation)
        }
    }



    private fun getItemList(): List<ItemList> {
        val itemList = mutableListOf<ItemList>()
        itemList.add(ItemList(R.string.subtitles))
        itemList.add(ItemList(R.string.off))
        itemList.add(ItemList(R.string.english))
        itemList.add(ItemList(R.string.russian))
        itemList.add(ItemList(R.string.turkish))
        return itemList
    }
}