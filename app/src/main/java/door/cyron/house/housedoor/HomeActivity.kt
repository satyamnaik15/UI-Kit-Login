package door.cyron.house.housedoor

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.DecelerateInterpolator
import android.widget.EditText
import android.animation.ObjectAnimator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import door.cyron.house.housedoor.utility.expandablelayout.ExpandableLayout
import door.cyron.house.housedoor.utility.expandablelayout.ExpandableLayout.State.*


class HomeActivity : AppCompatActivity(), ExpandableLayout.OnExpansionUpdateListener {

    lateinit var floatingActionButton: FloatingActionButton
    lateinit var view: View
    lateinit var exp: ExpandableLayout
    lateinit var exp_one:ExpandableLayout
    lateinit var btn:Button
    lateinit var txtClose:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        floatingActionButton = findViewById(R.id.floatingActionButton2)
        view = findViewById(R.id.view)
        exp=findViewById(R.id.exp)
        exp_one=findViewById(R.id.exp_one)
        btn=findViewById(R.id.button)
        txtClose=findViewById(R.id.txtClose)

        val vto = view.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                startAnim(view)

            }
        })

        btn.setOnClickListener(){
            if(!exp.isExpanded){
                exp.expand()
            }
        }
        txtClose.setOnClickListener(){
            if(exp_one.isExpanded){
                exp_one.collapse()
            }
        }

        exp.setOnExpansionUpdateListener(this)
        exp_one.setOnExpansionUpdateListener(this)

    }

    private fun startAnim(view: View) {
        val parentWidth = (view.parent as View).measuredWidth

        val widthAnimator = ValueAnimator.ofInt(this.view.getWidth(), parentWidth)
        widthAnimator.duration = 2000
        widthAnimator.interpolator = DecelerateInterpolator()
        widthAnimator.addUpdateListener { animation ->
            view.getLayoutParams().width = animation.animatedValue as Int
            view.requestLayout()
        }
        widthAnimator.start()
        widthAnimator.addListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator) {
                // ...
            }

            override fun onAnimationRepeat(animation: Animator) {
                // ...
            }

            override fun onAnimationEnd(animation: Animator) {
            }

            override fun onAnimationCancel(animation: Animator) {
                // ...
            }
        })
    }
    override fun onExpansionUpdate(expansionFraction: Float, state: Int) {

        if(state==EXPANDED){
            if(!exp_one.isExpanded())
            exp_one.expand()
        }else if(state== COLLAPSED){
            if(exp.isExpanded())
                exp.collapse()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish();
//        this.overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
}

