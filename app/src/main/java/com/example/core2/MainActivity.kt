package com.example.core2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaration of variables for Main Activity to be used to pass to Details Activity
        val sushiimg = findViewById<ImageView>(R.id.sushi_image)
        val burgerimg = findViewById<ImageView>(R.id.burger_image)
        val noodlesimg = findViewById<ImageView>(R.id.noodles_image)
        val pizzaimg = findViewById<ImageView>(R.id.pizza_image)
        val sushiModel = C_DATA(findViewById<TextView>(R.id.sushi_name).text.toString(), (findViewById<TextView>(R.id.sushi_date).text.toString()), "Japanese",  findViewById<RatingBar>(R.id.sushi_ratingbar).rating,1)
        val burgerModel = C_DATA(findViewById<TextView>(R.id.burger_name).text.toString(), (findViewById<TextView>(R.id.burger_date).text.toString()),"American",  findViewById<RatingBar>(R.id.burger_ratingbar).rating,2)
        val noodlesModel = C_DATA(findViewById<TextView>(R.id.noodles_name).text.toString(), (findViewById<TextView>(R.id.noodles_date).text.toString()),"Chinese",  findViewById<RatingBar>(R.id.noodles_ratingbar).rating,3)
        val pizzaModel = C_DATA(findViewById<TextView>(R.id.pizza_name).text.toString(), (findViewById<TextView>(R.id.pizza_date).text.toString()), "Italian", findViewById<RatingBar>(R.id.pizza_ratingbar).rating,4)

        //Function to pass variables to details activity using C_DATA class as Parcelable Object
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val newdata = data?.getParcelableExtra<C_DATA>("extra_new_name")
                when (newdata?.pictureclicked) {
                    1 -> {
                        sushiModel.cname = newdata.cname
                        sushiModel.cdate = newdata.cdate
                        sushiModel.cratingbar = newdata.cratingbar
                        findViewById<TextView>(R.id.sushi_name).text = sushiModel.cname
                        findViewById<TextView>(R.id.sushi_date).text = sushiModel.cdate
                        findViewById<RatingBar>(R.id.sushi_ratingbar).rating = sushiModel.cratingbar
                    }
                    2 -> {
                        burgerModel.cname = newdata.cname
                        burgerModel.cdate = newdata.cdate
                        burgerModel.cratingbar = newdata.cratingbar
                        findViewById<TextView>(R.id.burger_name).text = burgerModel.cname
                        findViewById<TextView>(R.id.burger_date).text = burgerModel.cdate
                        findViewById<RatingBar>(R.id.burger_ratingbar).rating = burgerModel.cratingbar
                    }
                    3 -> {
                        noodlesModel.cname = newdata.cname
                        noodlesModel.cdate = newdata.cdate
                        noodlesModel.cratingbar = newdata.cratingbar
                        findViewById<TextView>(R.id.noodles_name).text = noodlesModel.cname
                        findViewById<TextView>(R.id.noodles_date).text = noodlesModel.cdate
                        findViewById<RatingBar>(R.id.noodles_ratingbar).rating = noodlesModel.cratingbar
                    }
                    4 -> {
                        pizzaModel.cname = newdata.cname
                        pizzaModel.cdate = newdata.cdate
                        pizzaModel.cratingbar = newdata.cratingbar
                        findViewById<TextView>(R.id.pizza_name).text = pizzaModel.cname
                        findViewById<TextView>(R.id.pizza_date).text = pizzaModel.cdate
                        findViewById<RatingBar>(R.id.pizza_ratingbar).rating = pizzaModel.cratingbar
                    }
                }
            }
        }

        //OnClickListeners on images to link to individual pictures of cuisine to change inputs
        sushiimg.setOnClickListener {
            sushiModel.cratingbar = findViewById<RatingBar>(R.id.sushi_ratingbar).rating
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_Data", sushiModel)
            resultLauncher.launch(intent)
        }
        burgerimg.setOnClickListener {
            burgerModel.cratingbar = findViewById<RatingBar>(R.id.burger_ratingbar).rating
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_Data", burgerModel)
            resultLauncher.launch(intent)
        }
        noodlesimg.setOnClickListener {
            noodlesModel.cratingbar = findViewById<RatingBar>(R.id.noodles_ratingbar).rating
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_Data", noodlesModel)
            resultLauncher.launch(intent)
        }
        pizzaimg.setOnClickListener {
            pizzaModel.cratingbar = findViewById<RatingBar>(R.id.pizza_ratingbar).rating
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_Data", pizzaModel)
            resultLauncher.launch(intent)
        }
    }

}