package com.example.projet_mobile
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Inscription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connect = findViewById<Button>(R.id.submit)
        val db = DatabaseHelper(applicationContext).writableDatabase
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val repassword = findViewById<EditText>(R.id.repassword)
        val error = findViewById<TextView>(R.id.erreur)
        val ok = findViewById<TextView>(R.id.success)
        val seconnecter = findViewById<TextView>(R.id.seconnecter)

        seconnecter.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        connect.setOnClickListener {
            error.visibility = View.GONE
            ok.visibility = View.GONE
            var compteur = 0
            val txtemail = email.text.toString()
            val txtusername = username.text.toString()
            val txtpassword = password.text.toString()
            val txtrepassword = repassword.text.toString()

            if (txtemail.isEmpty() || txtpassword.isEmpty() || txtrepassword.isEmpty() || txtusername.isEmpty()) {
                error.text = "Aucuns des champs ne doit être vide !!"
                error.visibility = View.VISIBLE
            } else {
                if (txtpassword != txtrepassword) {
                    error.text = "Désolé, les mots de passe ne correspondent pas. Veuillez les vérifier et réessayer."
                    error.visibility = View.VISIBLE
                }
                else
                {
                val projection = arrayOf(DatabaseHelper.COLUMN_EMAIL)
                val cursor = db.query(DatabaseHelper.TABLE_NAME, projection, null, null, null, null, null)
                with(cursor) {
                    while (moveToNext()) {

                        val mail = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
                        if(txtemail == mail)
                        {
                            compteur++
                            break
                        }
                    }
                }
                 if(compteur > 0)
                {
                    error.text = "Désolé, cet e-mail est déjà utilisé. Veuillez en choisir un autre."
                    error.visibility = View.VISIBLE
                }
                    else
                 {
                     val contentValues = ContentValues().apply {
                         put(DatabaseHelper.COLUMN_EMAIL, txtemail)
                         put(DatabaseHelper.COLUMN_USERNAME, txtusername)
                         put(DatabaseHelper.COLUMN_PASSWORD, txtpassword)
                     }

                     db.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
                     ok.text = "Félicitations, vous êtes inscrit avec succès."
                     ok.visibility= View.VISIBLE

                 }
                }
            }
            }
        }
    }

