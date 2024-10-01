package pl.dtokarzewski.cockpit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.SnapHelper
import pl.dtokarzewski.cockpit.databinding.ActivityCockpitBinding

class CockpitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCockpitBinding
    private lateinit var adapter: CockpitAdapter
    private lateinit var snapHelper: SnapHelper
    private var scrollToIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCockpitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupGrid()
    }

    private fun setupGrid() {
        val menuItems = arrayListOf(
            Pair("0 Messenger", R.drawable.ic_message),
            Pair("1 Maps", R.drawable.ic_map),
            Pair("2 Spotify", R.drawable.ic_play),
            Pair("3 News", R.drawable.ic_news),
            Pair("4 Calendar", R.drawable.ic_calendar),
            Pair("5 Climate", R.drawable.ic_air),
            Pair("6 Phone", R.drawable.ic_phone),
            Pair("7 Message", R.drawable.ic_message),
            Pair("8 Waze", R.drawable.ic_map),
            Pair("9 YT Music", R.drawable.ic_play),
            Pair("10 Car", R.drawable.ic_car),
            Pair("11 Radio", R.drawable.ic_radio),
            Pair("12 Zoom", R.drawable.ic_message),
            Pair("13 Settings", R.drawable.ic_settings),
            Pair("14 Podcasts", R.drawable.ic_play),
            Pair("15 Voice assistant", R.drawable.ic_assistant),
            Pair("16 Messenger", R.drawable.ic_message),
            Pair("17 Maps", R.drawable.ic_map),
            Pair("18 Spotify", R.drawable.ic_play),
            Pair("19 News", R.drawable.ic_news),
            Pair("20 Calendar", R.drawable.ic_calendar),
            Pair("21 Climate", R.drawable.ic_air),
            Pair("22 Phone", R.drawable.ic_phone),
            Pair("23 Message", R.drawable.ic_message),
            Pair("24 Waze", R.drawable.ic_map),
            Pair("25 YT Music", R.drawable.ic_play),
            Pair("26 Car", R.drawable.ic_car),
            Pair("27 Radio", R.drawable.ic_radio),
            Pair("28 Zoom", R.drawable.ic_message),
            Pair("29 Settings", R.drawable.ic_settings),
            Pair("30 Podcasts", R.drawable.ic_play),
            Pair("31 Voice assistant", R.drawable.ic_assistant),
        )

        adapter = CockpitAdapter(menuItems)
        snapHelper = CockpitSnapHelper(rowsPerSnap = 2, columnsPerSnap = 5)

        binding.grid.apply {
            layoutManager = CockpitLayoutManager(
                context = this@CockpitActivity,
                rows = 2,
                columns = 5,
                orientation = CockpitLayoutManager.HORIZONTAL,
                reverseLayout = false
            )
            adapter = this@CockpitActivity.adapter
            snapHelper.attachToRecyclerView(this)
        }

        // Used for smooth scrolling test
        binding.fab.setOnClickListener {
            scrollToIndex = scrollToIndex xor menuItems.size
            binding.grid.smoothScrollToPosition(scrollToIndex)
        }
    }
}
