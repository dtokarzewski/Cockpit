package pl.dtokarzewski.cockpit

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.dtokarzewski.cockpit.databinding.ViewCockpitIconBinding

class CockpitAdapter :
    ListAdapter<Pair<String, Int>, CockpitAdapter.MenuItemHolder>(CockpitAdapterDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        val binding =
            ViewCockpitIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuItemHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MenuItemHolder(private val itemBinding: ViewCockpitIconBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
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
                        if (newPosition > oldPosition) {
                            updateList.removeAt(oldPosition)
                            updateList.add(newPosition, item)
                        } else {
                            updateList.removeAt(oldPosition)
                            updateList.add(newPosition, item)
                        }
                        submitList(updateList)
                    }
                }
            }
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            Log.d("ItemTouchHelper", "ClearView")
            /* Drag and drop scrolls screen using recyclerView.scrollBy(). This doesn't
            trigger SnapHelper, which listens on recyclerView.smoothScrollBy() and recyclerView.fling().
            Adding tiny smoothScroll action causes SnapHelper to trigger, and snap view to correct page position.

            Delay is added for animation to look smother. Items need to be rearranged after drop
            and then, view should be scrolled to proper position. Scrolling and rearrangement at the
            same time makes it look like random movement caused by an error.
             */

            Handler(Looper.getMainLooper()).postDelayed({
                recyclerView.smoothScrollBy(1, 0)
            }, 200)
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
            return oldItem == newItem
        }
    }
}