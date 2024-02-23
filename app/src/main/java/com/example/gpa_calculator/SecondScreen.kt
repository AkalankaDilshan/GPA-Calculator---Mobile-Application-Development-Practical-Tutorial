package com.example.gpa_calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.dbmyapp.DBHelper

class SecondScreen : AppCompatActivity(){

    private fun calculateGPA(
        credit1: Int?,
        credit2: Int?,
        credit3: Int?,
        grade1: String?,
        grade2: String?,
        grade3: String?
    ): Any {
        // Assuming the standard GPA scale
        val gradeToGPA = mapOf(
            "A" to 4.0,
            "A-" to 3.7,
            "B+" to 3.3,
            "B" to 3.0,
            "B-" to 2.7,
            "C+" to 2.3,
            "C" to 2.0,
            "C-" to 1.7,
            "D+" to 1.3,
            "D" to 1.0,
            "F" to 0.0
        )

        // Check for null values and use default values if null
        val safeCredit1 = credit1 ?: 0
        val safeCredit2 = credit2 ?: 0
        val safeCredit3 = credit3 ?: 0

        val safeGrade1 = grade1 ?: "F"
        val safeGrade2 = grade2 ?: "F"
        val safeGrade3 = grade3 ?: "F"

        // Calculate total grade points
        val totalGradePoints =
            safeCredit1 * (gradeToGPA[safeGrade1] ?: 0.0) + safeCredit2 * (gradeToGPA[safeGrade2] ?: 0.0) + safeCredit3 * (gradeToGPA[safeGrade3] ?: 0.0)

        // Calculate total credit hours
        val totalCreditHours = safeCredit1 + safeCredit2 + safeCredit3

        // Calculate GPA
        return if (totalCreditHours > 0) totalGradePoints / totalCreditHours else 0.0
    }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        val course_1: TextView = findViewById(R.id.txtCourse1View)
        val course_2: TextView = findViewById(R.id.txtCourse2View)
        val course_3: TextView = findViewById(R.id.txtCourse3View)

        val credit_1 : TextView = findViewById(R.id.txtCredits1View)
        val credit_2 : TextView = findViewById(R.id.txtCredits2View)
        val credit_3 : TextView = findViewById(R.id.txtCredits3View)

        val grade_1  : TextView = findViewById(R.id.txtGrade1View)
        val grade_2  : TextView = findViewById(R.id.txtGrade2View)
        val grade_3  : TextView = findViewById(R.id.txtGrade3View)

        val gpa      : TextView = findViewById(R.id.textViewGPA)

        val i = intent
        val idValue = i.getStringExtra("id_para")

        var helper = DBHelper(applicationContext)
        val db = helper.readableDatabase
        val res = db.rawQuery("SELECT * FROM Results WHERE id='$idValue'", null)

        if (res != null && res.moveToFirst()) {
            while (res.moveToNext()) {
                val course1Value = res.getString(res.getColumnIndex("course_1"))
                val course2Value = res.getString(res.getColumnIndex("course_2"))
                val course3Value = res.getString(res.getColumnIndex("course_3"))

                val credit1Value = res.getInt(res.getColumnIndex("credit_1"))
                val credit2Value = res.getInt(res.getColumnIndex("credit_2"))
                val credit3Value = res.getInt(res.getColumnIndex("credit_3"))

                val grade1Value = res.getString(res.getColumnIndex("grade_1"))
                val grade2Value = res.getString(res.getColumnIndex("grade_2"))
                val grade3Value = res.getString(res.getColumnIndex("grade_3"))

                // Set values to TextViews
                course_1.text = course1Value
                course_2.text = course2Value
                course_3.text = course3Value

                credit_1.text = credit1Value.toString()
                credit_2.text = credit2Value.toString()
                credit_3.text = credit3Value.toString()

                grade_1.text = grade1Value
                grade_2.text = grade2Value
                grade_3.text = grade3Value

                 //Call  GPA Calculate function
                val calculatedGPA = calculateGPA(
                    credit_1.text.toString().toInt(),
                    credit_2.text.toString().toInt(),
                    credit_3.text.toString().toInt(),
                    grade_1.text.toString(),
                    grade_2.text.toString(),
                    grade_3.text.toString()
                )

                // Set the calculated GPA to the TextView
                gpa.text = "${calculatedGPA}"





            }
        } else {
            Log.e("SecondScreen", "Error retrieving data from the database.")
        }
    }
}