package com.example.projet_mobile
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val db = DatabaseHelper(applicationContext).writableDatabase
        val connect = findViewById<Button>(R.id.submit)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.erreur)
        val inscription = findViewById<TextView>(R.id.inscription)
        inscription.setOnClickListener{
            val intent = Intent(this, Inscription::class.java)
            startActivity(intent)
        }
        connect.setOnClickListener{
            var compteur = 0
            error.visibility = View.GONE
            val txtemail = email.text.toString()
            val txtpassword = password.text.toString()
            val projection = arrayOf(DatabaseHelper.COLUMN_EMAIL, DatabaseHelper.COLUMN_PASSWORD)
            val cursor = db.query(DatabaseHelper.TABLE_NAME, projection, null, null, null, null, null)
            with(cursor) {
                while (moveToNext()) {
                    val mail = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
                    val pass = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD))
                    if(txtemail == mail && txtpassword == pass)
                    {
                        compteur++
                        break
                    }

                }
            }
            if(compteur == 0)
            {
                error.text = "Email ou mot de passe invalide !"
                error.visibility = View.VISIBLE
            }
            else
            {
                val intent = Intent(this, Index::class.java)
                startActivity(intent)
            }
        }

    }
}