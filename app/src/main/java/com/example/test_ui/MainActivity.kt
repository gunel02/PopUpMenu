package com.example.test_ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        val anchorX = location[0]
        val anchorY = location[1]

        val popupWidth = popupView.measuredWidth
        val popupHeight = popupView.measuredHeight
        val screenHeight = resources.displayMetrics.heightPixels
        val offsetX = 0
        val offsetY = -(popupHeight + anchorView.height)

        popupWindow.showAtLocation(
            anchorView,
            Gravity.NO_GRAVITY,
            anchorX + offsetX,
            anchorY + offsetY
        )

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