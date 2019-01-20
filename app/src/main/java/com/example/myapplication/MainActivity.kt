package com.example.myapplication

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener{view -> login(view)}
    }



    private fun login(view: View): Boolean {
        if(loginInput.text!!.equals("test") && passInput.text!!.equals("test")) {
            startActivity(Intent(this, Main2Activity::class.java))
        } else
            if(loginInput.text!!.isEmpty() && passInput.text!!.isEmpty())
                Snackbar.make(view, getString(R.string.emptyLoginAndPass), Snackbar.LENGTH_SHORT).show()
            else if(loginInput.text.isEmpty())
                Snackbar.make(view, getString(R.string.emptyLogin), Snackbar.LENGTH_SHORT).show()
            else if(passInput.text!!.isEmpty())
                Snackbar.make(view, getString(R.string.emptyPass), Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(view, getString(R.string.passErr), Snackbar.LENGTH_SHORT).show()
        return true
    }

}
