package pl.dtokarzewski.cockpit

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * SnapHelper that snaps fixed number of items at once. It works both for horizontal and vertical grids.
 *
 * In conjunction with CockpitLayoutManager, when same number of rows and columns set in both, SnapHelper
 * will snap entire page at once.
 * @param rowsPerSnap number of rows that should be snapped at once.
 * @param columnsPerSnap number of columns that should be snapped at once.
 */
class CockpitSnapHelper(
    private val rowsPerSnap: Int = 1,
    private val columnsPerSnap: Int = 1,
) : PagerSnapHelper() {

    private var verticalHelper: OrientationHelper? = null
    private var horizontalHelper: OrientationHelper? = null

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray {
        val out = IntArray(2)

        if (layoutManager.canScrollHorizontally()) {
            out[0] = closestDistanceToEdge(
                targetView = targetView,
                layoutManager = layoutManager,
                helper = getHorizontalHelper(layoutManager),
            )
        } else {
            out[0] = 0
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = closestDistanceToEdge(
                targetView = targetView,
                layoutManager = layoutManager,
                helper = getVerticalHelper(layoutManager),
            )
        } else {
            out[1] = 0
        }

        Log.d(
            "CockpitSnapHelper",
            "Calculated distance to final snap: x = ${out[0]}, y = ${out[1]}, index: ${
                layoutManager.getPosition(targetView)
            }"
        )
        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        val visibleCornerPosition = findVisibleCornerPosition(layoutManager = layoutManager)
        return layoutManager.findViewByPosition(visibleCornerPosition)
    }

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        return findVisibleCornerPosition(layoutManager = layoutManager)
    }

    // Position of first visible item that should start current of next page
    private fun findVisibleCornerPosition(layoutManager: RecyclerView.LayoutManager): Int {
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return RecyclerView.NO_POSITION
        }

        for (i in 0 until childCount) {
            layoutManager.getChildAt(i)?.let { child ->
                val position = layoutManager.getPosition(child)
                if (position % (rowsPerSnap * columnsPerSnap) == 0) {
                    Log.d("CockpitSnapHelper", "Visible corner position: ** $position **")
                    return position
                }
            }
        }
        return RecyclerView.NO_POSITION
    }

    private fun closestDistanceToEdge(
        targetView: View,
        layoutManager: RecyclerView.LayoutManager,
        helper: OrientationHelper
    ): Int {
        val position = layoutManager.getPosition(targetView)
        val distanceToStart = distanceToStart(targetView = targetView, helper = helper)
        val distanceToEnd = distanceToEnd(targetView = targetView, helper = helper)
        Log.d(
            "CockpitSnapHelper",
            "TargetView distanceToStart = $distanceToStart, distanceToEnd = $distanceToEnd, index: $position"
        )
        return when {
            abs(distanceToStart) < abs(distanceToEnd) -> distanceToStart // this view should be start of the page
            position == 0 -> distanceToStart // this view has closer to the end but it's first item in the list
            else -> distanceToEnd // this view has closer to the end - snap to previous page corner
        }
    }

    private fun distanceToStart(targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    private fun distanceToEnd(targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.endAfterPadding
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (verticalHelper == null || verticalHelper?.layoutManager != layoutManager) {
            verticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }
        return verticalHelper!!
    }

    private fun getHorizontalHelper(
        layoutManager: RecyclerView.LayoutManager
    ): OrientationHelper {
        if (horizontalHelper == null || horizontalHelper?.layoutManager != layoutManager) {
            horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return horizontalHelper!!
    }
}