package door.cyron.house.housedoor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish();
        this.overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
}
