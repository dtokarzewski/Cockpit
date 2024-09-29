package pl.dtokarzewski.cockpit

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
