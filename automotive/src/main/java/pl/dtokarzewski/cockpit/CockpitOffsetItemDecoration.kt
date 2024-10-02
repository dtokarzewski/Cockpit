package pl.dtokarzewski.cockpit

import android.graphics.Rect
import android.view.View
import androidx.core.text.layoutDirection
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.LAYOUT_DIRECTION_LTR
import pl.dtokarzewski.cockpit.CockpitLayoutManager.Companion.VERTICAL
import java.util.Locale

/**
 * This item decoration adds offset to the last row or column of the grid in case when not all spans
 * are occupied. This scenario happens at the end of the list.
 * @param rows number of rows in the grid
 * @param columns number of columns in the grid
 * @param orientation orientation of the grid, either HORIZONTAL or VERTICAL
 * */
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

        /*
        Entire last row/column needs to be offset by the same amount, so first items that applies to
        following scheme needs to be found.

        Horizontal:     Horizontal full row:     Vertical:       Vertical full row:
        ___________     ___________              __________      __________
        |* * x    |     |* * x    |              |* * * * |      |* * * * |
        |* * x    |     |* * x    |              |x x     |      |x x x x |
        |* *      |     |* * x    |              |        |      |        |
        ----------      ----------               ----------      ----------
         */
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

            if (orientation == HORIZONTAL) {
                val horizontalOffset = ((columns * rows) - lastPageItemsCount) / rows * view.width
                if (Locale.getDefault().layoutDirection == LAYOUT_DIRECTION_LTR) {
                    outRect.right = horizontalOffset
                } else {
                    outRect.left = horizontalOffset
                }
            } else {
                outRect.bottom = ((columns * rows) - lastPageItemsCount) / columns * view.height
            }
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}