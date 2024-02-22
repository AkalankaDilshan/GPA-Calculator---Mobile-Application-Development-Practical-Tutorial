package com.example.gpa_calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gpa_calculator.ui.theme.GPA_CalculatorTheme

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


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


        val btnView: Button = findViewById(R.id.btnViewGpa)
        btnView.setOnClickListener {

            //next screen
            val gotoNextScreen = Intent(applicationContext,SecondScreen::class.java)
            startActivity(gotoNextScreen)
    }

    }


}

