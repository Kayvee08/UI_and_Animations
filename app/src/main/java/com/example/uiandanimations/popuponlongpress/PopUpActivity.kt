package com.example.uiandanimations.popuponlongpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import com.example.uiandanimations.R

class PopUpActivity : AppCompatActivity() {
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up)
        button = findViewById(R.id.pop_up_button)
        setButtonListener()
    }
    private fun setButtonListener(){
        button.setOnLongClickListener {
            popUpButton(view = it)
            true
        }
    }
    private fun popUpButton(view: View) {
        val popUpMenu = PopupMenu(this, button).apply{
            menuInflater.inflate(R.menu.pop_up_menu, menu)
            setOnMenuItemClickListener { item ->
                Toast.makeText(view.context, "Hi", Toast.LENGTH_SHORT).show()
                true
            }
        }
        popUpMenu.show()
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

    }
}