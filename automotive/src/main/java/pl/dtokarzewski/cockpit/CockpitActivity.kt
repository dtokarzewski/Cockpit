package pl.dtokarzewski.cockpit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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
            Pair("Waze", R.drawable.ic_map),
            Pair("YT Music", R.drawable.ic_play),
            Pair("News", R.drawable.ic_news),
            Pair("Calendar", R.drawable.ic_calendar),
            Pair("Climate", R.drawable.ic_air),
            Pair("Phone", R.drawable.ic_phone),
            Pair("Car", R.drawable.ic_car),
            Pair("Message", R.drawable.ic_message),
            Pair("Radio", R.drawable.ic_radio),
            Pair("Settings", R.drawable.ic_settings),
            Pair("Messenger", R.drawable.ic_message),
            Pair("Podcasts", R.drawable.ic_play),
            Pair("Zoom", R.drawable.ic_message),
            Pair("Voice assistant", R.drawable.ic_assistant)
        )
        binding.root.apply {
            layoutManager = GridLayoutManager(
                this@CockpitActivity,
                2,
                GridLayoutManager.HORIZONTAL,
                false
            )
            adapter = CockpitAdapter(menuItems)

        }
    }
}