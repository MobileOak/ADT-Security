package com.adt.rickandmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter (private val myDataset: Array<Result>) :
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CharacterAdapter.MyViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_view, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.layout.findViewById<TextView>(R.id.character_name).text = "test"
    }

    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount() = myDataset.size
    override fun getItemCount() = 10
}
