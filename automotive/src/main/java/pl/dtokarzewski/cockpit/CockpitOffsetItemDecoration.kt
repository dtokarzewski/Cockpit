package pl.dtokarzewski.cockpit

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import pl.dtokarzewski.cockpit.CockpitLayoutManager.Companion.VERTICAL

class CockpitOffsetItemDecoration(
    private val rows: Int,
    private val columns: Int,
    private val orientation: Int = HORIZONTAL,
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        val lastPageItemsCount = itemCount % (columns * rows)
        if (lastPageItemsCount == 0) {
            return super.getItemOffsets(outRect, view, parent, state)
        }
        val lastRowOrColumnStartPosition = when {
            orientation == HORIZONTAL && lastPageItemsCount % rows == 0 -> {
                itemCount - rows
            }
            orientation == HORIZONTAL && lastPageItemsCount % rows != 0 -> {
                itemCount - itemCount % rows
            }
            orientation == VERTICAL && lastPageItemsCount % columns == 0 -> {
                itemCount - columns
            }
            orientation == VERTICAL && lastPageItemsCount % columns != 0 -> {
                itemCount - itemCount % columns
            }
            else -> itemCount - 1
        }
        if (position >= lastRowOrColumnStartPosition) {
            outRect.right = ((columns * rows) - lastPageItemsCount) / rows * view.width
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}