package pl.dtokarzewski.cockpit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.dtokarzewski.cockpit.databinding.ActivityCockpitBinding

class CockpitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCockpitBinding

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
            Pair("5 OneNote", R.drawable.ic_note),
            Pair("6 Climate", R.drawable.ic_air),
            Pair("7 Phone", R.drawable.ic_phone),
            Pair("8 Message", R.drawable.ic_message),
            Pair("9 Booking", R.drawable.ic_home),
            Pair("10 Waze", R.drawable.ic_map),
            Pair("11 YT Music", R.drawable.ic_play),
            Pair("12 Car", R.drawable.ic_car),
            Pair("13 Youtube", R.drawable.ic_play),
            Pair("14 Radio", R.drawable.ic_radio),
            Pair("15 Zoom", R.drawable.ic_message),
            Pair("16 Settings", R.drawable.ic_settings),
            Pair("17 Podcasts", R.drawable.ic_play),
            Pair("18 Notion", R.drawable.ic_note),
            Pair("19 Voice assistant", R.drawable.ic_assistant),
            Pair("20 Weather", R.drawable.ic_weather),
            Pair("21 Google Play", R.drawable.ic_play),
            Pair("22 Signal", R.drawable.ic_message),
            Pair("23 Wallet", R.drawable.ic_card),
            Pair("24 Instagram", R.drawable.ic_air),
            Pair("25 Traffic info", R.drawable.ic_car),
            Pair("26 Tasks", R.drawable.ic_note),
            Pair("27 Video recorder", R.drawable.ic_camera),
            Pair("28 Home", R.drawable.ic_home),
            Pair("29 SmartThings", R.drawable.ic_smart),
            Pair("30 Teams", R.drawable.ic_calendar),
            Pair("31 Alexa", R.drawable.ic_assistant),
            Pair("32 Keep", R.drawable.ic_note),
        )

        val adapter = CockpitAdapter().apply { submitList(menuItems) }
        val snapHelper = CockpitSnapHelper(
            rowsPerSnap = 2,
            columnsPerSnap = 5,
        )
        val itemDecoration = CockpitOffsetItemDecoration(
            rows = 2,
            columns = 5,
            orientation = CockpitLayoutManager.HORIZONTAL,
        )
        val layoutManager = CockpitLayoutManager(
            context = this@CockpitActivity,
            rows = 2,
            columns = 5,
            orientation = CockpitLayoutManager.HORIZONTAL,
            reverseLayout = false,
        )

        binding.grid.apply {
            setLayoutManager(layoutManager)
            snapHelper.attachToRecyclerView(this)
            addItemDecoration(itemDecoration)
            setAdapter(adapter)
            adapter.itemTouchHelper.attachToRecyclerView(this)
        }
    }
}
