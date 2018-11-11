package door.cyron.house.housedoor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView


class RegisterActivity : AppCompatActivity() {

    lateinit var tvlogin:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        tvlogin=findViewById(R.id.tvlogin)


    }
}
