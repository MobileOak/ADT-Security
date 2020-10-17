package com.mobileoak.rickandmorty

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CharacterAdapter (var dataset: List<Result>, val context: Context) :
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CharacterAdapter.MyViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_view, parent, false) as LinearLayout



        return MyViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = "Name: " + dataset[position].name
        val status = "Status: " + dataset[position].status
        val species = "Species: " + dataset[position].species

        holder.layout.findViewById<TextView>(R.id.character_name).text = name
        holder.layout.findViewById<TextView>(R.id.character_status).text = status
        holder.layout.findViewById<TextView>(R.id.character_species).text = species

        holder.layout.setOnClickListener {
            val location = dataset[position].location?.name
            AlertDialog.Builder(context).setTitle("Location").setMessage(location).show()
        }

        val imageView = holder.layout.findViewById<ImageView>(R.id.character_image)
        val url = dataset[position].image
        Glide.with(context).load(url).into(imageView)
    }

    fun updateData(dataset: List<Result>) {
        this.dataset = dataset
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}
