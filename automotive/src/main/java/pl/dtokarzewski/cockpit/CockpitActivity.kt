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
            Pair("Maps", R.drawable.ic_map),
            Pair("Spotify", R.drawable.ic_play),
            Pair("News", R.drawable.ic_news),
            Pair("Calendar", R.drawable.ic_calendar),
            Pair("Climate", R.drawable.ic_air),
            Pair("Phone", R.drawable.ic_phone),
            Pair("Message", R.drawable.ic_message),
            Pair("Waze", R.drawable.ic_map),
            Pair("YT Music", R.drawable.ic_play),
            Pair("Car", R.drawable.ic_car),
            Pair("Radio", R.drawable.ic_radio),
            Pair("Zoom", R.drawable.ic_message),
            Pair("Settings", R.drawable.ic_settings),
            Pair("Podcasts", R.drawable.ic_play),
            Pair("Voice assistant", R.drawable.ic_assistant),
            Pair("Messenger", R.drawable.ic_message)
        )
        binding.root.apply {
            layoutManager = CockpitLayoutManager(
                context = this@CockpitActivity,
                rows = 2,
                columns = 5,
                orientation = CockpitLayoutManager.HORIZONTAL,
                reverseLayout = false
            )
            adapter = CockpitAdapter(menuItems)
        }
    }
}
