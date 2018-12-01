package door.cyron.house.housedoor

import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator


class HomeActivity : AppCompatActivity() {


    lateinit var floatingActionButton: FloatingActionButton
    lateinit var viewColor: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        floatingActionButton = findViewById(R.id.btnClick)
        viewColor = findViewById(R.id.viewColor)

        val vto = viewColor.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewColor.viewTreeObserver.removeOnGlobalLayoutListener(this)
                startAnim(viewColor)

            }
        })


    }

    private fun startAnim(viewColor: View?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = viewColor!!.height

            // create the animator for this view (the start radius is zero)
            val circularReveal = ViewAnimationUtils.createCircularReveal(viewColor, viewColor.width/2, viewColor.height, 0f, finalRadius.toFloat())
            circularReveal.duration = 1000
            circularReveal.interpolator = AccelerateInterpolator()

            // make the view visible and start the animation
            viewColor!!.setVisibility(View.VISIBLE)
            circularReveal.start()
        } else {

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish();
    }
}

