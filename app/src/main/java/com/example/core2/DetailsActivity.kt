package com.example.core2
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {
    var selected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Declaration of variables to be used to update new data in activity details
        val vname = findViewById<TextInputEditText>(R.id.tv_input_name)
        val vdate = findViewById<TextView>(R.id.tv_input_date)
        val vcuisine = findViewById<TextView>(R.id.tv_input_cuisine)
        val vpicture = findViewById<ImageView>(R.id.imageView)
        val vrating = findViewById<RatingBar>(R.id.ratingbar_changed)
        val calendar = Calendar.getInstance()
        val myFormat = "dd/MM/yyyy"
        val date_format = SimpleDateFormat(myFormat, Locale.UK)
        val data_name = intent.getParcelableExtra<C_DATA>("EXTRA_Data")!!

        //Calender declaration as function
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                vdate.text = date_format.format(calendar.time)
            }
        vrating.rating = data_name.cratingbar

        //if else loop to be used to recognised selected pictures using ID
        selected = data_name.pictureclicked
        when (selected) {
            1 -> {
                vpicture.setImageResource(R.drawable.sushi)
            }
            2 -> {
                vpicture.setImageResource(R.drawable.burger)
            }
            3 -> {
                vpicture.setImageResource(R.drawable.noodles)
            }
            4 -> {
                vpicture.setImageResource(R.drawable.pizza)
            }
        }
        //update new data
        vname.setText(data_name.cname)
        vdate.setText(data_name.cdate)
        vcuisine.setText(data_name.cfoodtype)
        vdate.setOnClickListener {
            DatePickerDialog(
                this@DetailsActivity, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        vrating.rating = data_name.cratingbar
    }

    //validation for valid input for new data
    fun validated():Boolean{
        val vname = findViewById<TextInputEditText>(R.id.tv_input_name).text
        val inputLayout = findViewById<TextInputLayout>(R.id.tv_input_layout)
        if(vname.isNullOrEmpty()) {
            inputLayout.error = "Please Enter Valid Input"
            return false
        }else{
            return true
        }
    }


    //function to update and replace data on inputs
    override fun onBackPressed() {
        val vname = findViewById<TextInputEditText>(R.id.tv_input_name)
        val vdate = findViewById<TextView>(R.id.tv_input_date)
        val vcuisine = findViewById<TextView>(R.id.tv_input_cuisine)
        val vrating = findViewById<RatingBar>(R.id.ratingbar_changed)
        val Mdata = C_DATA(vname.text.toString(), vdate.text.toString(), vcuisine.text.toString(), vrating.rating,selected)
        if (!validated()){
            return
        }else{
            val data = Intent(this, MainActivity::class.java).apply{
                putExtra("extra_new_name", Mdata)
            }
            setResult(RESULT_OK,data)
        }

        super.onBackPressed()
    }
}