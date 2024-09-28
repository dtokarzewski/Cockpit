package pl.dtokarzewski.cockpit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.dtokarzewski.cockpit.databinding.ViewCockpitIconBinding

class CockpitAdapter(
    private val menuItems: List<Pair<String, Int>>
) : RecyclerView.Adapter<CockpitAdapter.MenuItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        val binding = ViewCockpitIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuItemHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    override fun getItemCount(): Int = menuItems.count()

    class MenuItemHolder(private val itemBinding: ViewCockpitIconBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(menuItem: Pair<String, Int>) {
            itemBinding.label.text = menuItem.first
            itemBinding.icon.setImageResource(menuItem.second)
        }
    }
}