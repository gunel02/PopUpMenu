package com.example.test_ui

import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test_ui.databinding.CustomMenuItemBinding


class ArrayAdapter(
    private val activity: MainActivity,
    private val searchList: List<ItemList>,
    private var fixedItemPosition: Int,
    private var selectedItemPosition: Int,
    private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<ArrayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val itemCount = itemCount
        val current = searchList[position]
        holder.binding.menuItem1.text = holder.itemView.context.getString(current.item)
        holder.binding.menuItem1.text = activity.getString(current.item)

        if (position == fixedItemPosition) {
            holder.itemView.isEnabled = false
            holder.binding.menuItem1.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.fixed_color
                )
            )
            holder.binding.menuItem1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
            holder.binding.iconSelect.visibility = View.INVISIBLE

        } else {
            holder.itemView.isEnabled = true
            holder.binding.menuItem1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            holder.binding.iconSelect.visibility =
                if (position == selectedItemPosition) View.VISIBLE else View.INVISIBLE

            holder.itemView.setOnClickListener {
                if (position != fixedItemPosition) {
                    selectedItemPosition = position
                    notifyDataSetChanged()
                    onItemSelected(position)
                }
            }
        }

        if (itemCount == 1) {
            holder.itemView.background =
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.top_item_corner)
        }

        when (position) {
            0 -> {
                holder.itemView.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.top_item_corner)
            }

            itemCount - 1 -> {
                holder.itemView.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.bottom_item_corner
                )
            }

            else -> {
                holder.itemView.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.middle_item_corner
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class ViewHolder(val binding: CustomMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}