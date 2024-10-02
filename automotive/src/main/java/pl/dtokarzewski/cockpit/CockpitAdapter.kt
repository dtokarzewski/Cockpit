package pl.dtokarzewski.cockpit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.dtokarzewski.cockpit.databinding.ViewCockpitIconBinding

class CockpitAdapter : ListAdapter<Pair<String, Int>, CockpitAdapter.MenuItemHolder>(CockpitAdapterDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        val binding = ViewCockpitIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuItemHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MenuItemHolder(private val itemBinding: ViewCockpitIconBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(menuItem: Pair<String, Int>) {
            itemBinding.label.text = menuItem.first
            itemBinding.icon.setImageResource(menuItem.second)
        }
    }

    val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, 0
    ) {
        var oldPosition = -1
        var newPosition = -1

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            newPosition = target.getBindingAdapterPosition()
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            when (actionState) {
                ItemTouchHelper.ACTION_STATE_DRAG -> {
                    viewHolder?.bindingAdapterPosition?.let { oldPosition = it }
                }
                ItemTouchHelper.ACTION_STATE_IDLE -> {
                    if (oldPosition != -1 && newPosition != -1 && oldPosition != newPosition) {
                        val updateList = currentList.toMutableList()
                        val item = updateList[oldPosition]
                        updateList.removeAt(oldPosition)
                        updateList.add(newPosition, item)
                        submitList(updateList)
                    }
                }
            }
        }
    })

    object CockpitAdapterDiffUtilCallback : DiffUtil.ItemCallback<Pair<String, Int>>() {
        override fun areItemsTheSame(
            oldItem: Pair<String, Int>,
            newItem: Pair<String, Int>
        ): Boolean {
            return oldItem.first == newItem.first && oldItem.second == newItem.second
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, Int>,
            newItem: Pair<String, Int>
        ): Boolean {
            return  oldItem == newItem
        }
    }
}