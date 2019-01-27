package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

var currentTheme = R.style.redTheme
const val TEST_LOGIN = "test"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener{view -> login(view)}

        setSpinnerSelected()
        themeSelctor.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                setNewTheme(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


    private fun setSpinnerSelected() {
        themeSelctor.setSelection(when{
            currentTheme == R.style.AppTheme -> 0
            currentTheme == R.style.redTheme -> 1
            else -> 0
        })
    }


    fun setNewTheme(itemId: Int) {
        val prevTheme = currentTheme
        when(itemId) {
            0 -> currentTheme = R.style.AppTheme
            1 -> currentTheme = R.style.redTheme
        }
        if(prevTheme == currentTheme)
            return

        finish()
        startActivity(intent)
    }


    private fun login(view: View): Any {

        if(loginInput.text.toString() == TEST_LOGIN && passInput.text.toString() == TEST_LOGIN) {
            startActivity(Intent(this, Main2Activity::class.java))
        } else
            if(loginInput.text.isEmpty() && passInput.text.isEmpty())
                Snackbar.make(view, getString(R.string.emptyLoginAndPass), Snackbar.LENGTH_SHORT).show()
            else if(loginInput.text.isEmpty())
                Snackbar.make(view, getString(R.string.emptyLogin), Snackbar.LENGTH_SHORT).show()
            else if(passInput.text.isEmpty())
                Snackbar.make(view, getString(R.string.emptyPass), Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(view, getString(R.string.passErr), Snackbar.LENGTH_SHORT).show()
        return true
    }

}
