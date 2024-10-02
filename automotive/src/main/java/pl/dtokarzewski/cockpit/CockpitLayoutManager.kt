package pl.dtokarzewski.cockpit

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/** Layout Manager based on GridLayoutManager, with added configuration of number of rows and columns.
 * It sets span size to take entire space with given number of rows and columns.
 * @param context Current context, will be used to access resources.
 * @param rows number of rows in the grid.
 * @param columns number of columns in the grid.
 * @param orientation orientation of the grid, either HORIZONTAL or VERTICAL.
 * @param reverseLayout when set to true, layouts from end to start.
 */
class CockpitLayoutManager(
    context: Context,
    private val rows: Int,
    private val columns: Int,
    orientation: Int,
    reverseLayout: Boolean,
) : GridLayoutManager(
    context,
    if (orientation == HORIZONTAL) rows else columns,
    orientation,
    reverseLayout
) {
    companion object {
        const val HORIZONTAL: Int = LinearLayoutManager.HORIZONTAL
        const val VERTICAL: Int = LinearLayoutManager.VERTICAL
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        val defaultParams = super.generateDefaultLayoutParams()
        return spanLayoutSize(defaultParams)
    }

    override fun generateLayoutParams(
        c: Context?,
        attrs: AttributeSet?
    ): RecyclerView.LayoutParams {
        val defaultParams = super.generateLayoutParams(c, attrs)
        return spanLayoutSize(defaultParams)
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        val defaultParams = super.generateLayoutParams(lp)
        return spanLayoutSize(defaultParams)
    }

    private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
        layoutParams.width = Math.round(getAvailableWidth() / columns.toDouble()).toInt()
        layoutParams.height = Math.round(getAvailableHeight() / rows.toDouble()).toInt()
        return layoutParams
    }

    private fun getAvailableWidth(): Int {
        return width - (paddingRight) - (paddingLeft)
    }

    private fun getAvailableHeight(): Int {
        return height - paddingBottom - paddingTop
    }
}
