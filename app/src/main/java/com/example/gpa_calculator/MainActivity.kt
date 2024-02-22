package com.example.gpa_calculator


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dbmyapp.DBHelper


class MainActivity : AppCompatActivity() {

    var idGlobal = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get variable values
        val course_1: TextView = findViewById(R.id.editTextCourse1)
        val course_2: TextView = findViewById(R.id.editTextCourse2)
        val course_3: TextView = findViewById(R.id.editTextCourse3)

        val credit_1 : TextView = findViewById(R.id.editTextCredit1)
        val credit_2 : TextView = findViewById(R.id.editTextCredit2)
        val credit_3 : TextView = findViewById(R.id.editTextCredit3)

        val grade_1  : TextView = findViewById(R.id.editTextGrade1)
        val grade_2  : TextView = findViewById(R.id.editTextGrade2)
        val grade_3  : TextView = findViewById(R.id.editTextGrade3)

        var helper = DBHelper(applicationContext)


        val btnGotoNextScreen: Button = findViewById(R.id.btnViewNextScreen)

        btnGotoNextScreen.setOnClickListener {

            val id = ++idGlobal

            helper.insertData(
                id.toString(),
                course_1.text?.toString(),
                credit_1.text?.toString()?.toInt() ?: 0,
                grade_1.text?.toString(),
                course_2.text?.toString(),
                credit_2.text?.toString()?.toInt() ?: 0,
                grade_2.text?.toString(),
                course_3.text?.toString(),
                credit_3.text?.toString()?.toInt() ?: 0,
                grade_3.text?.toString()
            )

            val bundle = Bundle()
            bundle.putString("id_para", id.toString())

            val gotoNextScreen = Intent(applicationContext,SecondScreen::class.java)
            gotoNextScreen.putExtras(bundle)
            startActivity(gotoNextScreen)
        }
    }
}

