package com.example.uiandanimations.swipeItemOnRecyclerView

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uiandanimations.R
import com.google.android.material.snackbar.Snackbar


class RecyclerViewItemSwipeActivity : AppCompatActivity() {

    private lateinit var rv :RecyclerView
    private lateinit var adapter : RecyclerViewAdapter
    private var list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_item_swipe)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){
        rv = findViewById(R.id.rv)

        for(i in 1..40)
            list.add("Lorem Ipsum$i")
        adapter = RecyclerViewAdapter(list)
        val layoutManager = LinearLayoutManager(this)
        val divItemDecorator = DividerItemDecoration(rv.context, layoutManager.orientation)
        rv.layoutManager = layoutManager
        rv.addItemDecoration(divItemDecorator)
        rv.adapter = adapter
        swipeFunctionalityRv()
    }

    private fun swipeFunctionalityRv(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved/ drag & dropped.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedItem: String =
                    list[viewHolder.adapterPosition]

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                list.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                rv.adapter?.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.


                Snackbar.make(rv, "Deleted $deletedItem", Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            list.add(position, deletedItem)

                            // below line is to notify item is
                            // added to our adapter class.
                            rv.adapter?.notifyItemInserted(position)
                        }).show()


            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(rv)
    }
}