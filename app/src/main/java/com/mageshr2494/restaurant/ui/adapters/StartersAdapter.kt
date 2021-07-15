package com.mageshr2494.restaurant.ui.adapters

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mageshr2494.restaurant.CartHandle
import com.mageshr2494.restaurant.databinding.LayoutStarterItemBinding
import com.mageshr2494.restaurant.datasource.entities.StartersItem

class StartersAdapter(
    val context: Context,
    private val listener: ViewCartListener,
    private val myCartScreen: Boolean
) :
    ListAdapter<StartersItem, StartersAdapter.StarterViewHolder>(DiffCallback()) {

    interface ViewCartListener {
        fun onClickedCount(count: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarterViewHolder {
        val binding: LayoutStarterItemBinding =
            LayoutStarterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StarterViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: StarterViewHolder, position: Int) {
        val item = getItem(position)

        holder.apply {
            bind(item, context, myCartScreen)
        }
    }

    class StarterViewHolder(
        private val itemBinding: LayoutStarterItemBinding,
        private val listener: ViewCartListener
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: StartersItem?, context: Context, myCartScreen: Boolean) {

            var count: Int = 0
            itemBinding.starterName.text = item?.itemName
            itemBinding.description.text = item?.description
            itemBinding.price.text = "\u20ac" + item?.price

            if (item!!.day) itemBinding.dayAvailable.visibility =
                View.VISIBLE else itemBinding.dayAvailable.visibility = View.INVISIBLE

            if (item!!.night) itemBinding.nightAvailable.visibility =
                View.VISIBLE else itemBinding.nightAvailable.visibility = View.INVISIBLE

            if (myCartScreen) itemBinding.chat.visibility =
                View.VISIBLE else itemBinding.chat.visibility = View.INVISIBLE

            count = CartHandle.getStarterItemCount(context, item?.id)

            if (count != 0) {
                itemBinding.addButton.visibility = View.INVISIBLE
                itemBinding.linerLayoutAddRemove.visibility = View.VISIBLE
                itemBinding.count.text = "" + count
            } else {
                itemBinding.addButton.visibility = View.VISIBLE
                itemBinding.linerLayoutAddRemove.visibility = View.INVISIBLE
            }

            itemBinding.addButton.setOnClickListener {
                if (count == 0) {
                    count++
                    itemBinding.addButton.visibility = View.INVISIBLE
                    itemBinding.linerLayoutAddRemove.visibility = View.VISIBLE
                    itemBinding.count.text = "" + count

                    val startersItem = StartersItem(
                        item?.id,
                        item?.day,
                        item?.description,
                        item?.itemName,
                        item?.night,
                        item?.price,
                        count
                    )
                    CartHandle.addStarterItem(context, startersItem)
                }
                listener.onClickedCount(count)
            }

            itemBinding.remove.setOnClickListener {

                if (count != 1) {
                    count = count - 1
                    itemBinding.addButton.visibility = View.INVISIBLE
                    itemBinding.linerLayoutAddRemove.visibility = View.VISIBLE
                    itemBinding.count.text = "" + count
                    CartHandle.updateStarterItem(context, item?.id, count)
                } else {
                    count = count - 1

                    if (!myCartScreen) {
                        itemBinding.addButton.visibility = View.VISIBLE
                        itemBinding.linerLayoutAddRemove.visibility = View.INVISIBLE
                    }

                    CartHandle.deleteStarterItem(context, item?.id)
                }
                listener.onClickedCount(count)
            }

            itemBinding.add.setOnClickListener {
                if (count == 20) {
                    return@setOnClickListener
                }
                count++
                itemBinding.count.text = "" + count
                CartHandle.updateStarterItem(context, item?.id, count)
                listener.onClickedCount(count)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<StartersItem>() {

    override fun areItemsTheSame(oldItem: StartersItem, newItem: StartersItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StartersItem, newItem: StartersItem): Boolean {
        return oldItem == newItem
    }
}
