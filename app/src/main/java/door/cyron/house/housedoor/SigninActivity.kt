package door.cyron.house.housedoor

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView


class SigninActivity : AppCompatActivity() {

    lateinit var idTittle: TextView
    lateinit var etEmail: EditText
    lateinit var etRePassword: EditText
    lateinit var tvRegister: TextView
    lateinit var tvForget: TextView
    lateinit var linearLayout: LinearLayout
    lateinit var linView: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        idTittle = findViewById(R.id.idTittle)
        tvRegister = findViewById(R.id.tvRegister)
        tvForget = findViewById(R.id.tvForget)
        etEmail = findViewById(R.id.etEmail)
        etRePassword = findViewById(R.id.etRePassword)
        linearLayout = findViewById(R.id.linearLayout)
        linView = findViewById(R.id.linView)
        floatingActionButton = findViewById(R.id.floatingActionButton)
        idTittle.text = "Login"
        tvRegister.text = "Register"

        tvRegister.setOnClickListener() {
            if (idTittle.text.equals("Login"))
                signinGoing() else
                registerGoing()

        }


    }

    private fun signinGoing() {

        etRePassword.visibility = View.GONE
        linView.visibility = View.GONE

        val linearLayoutAnimOut = ObjectAnimator.ofFloat(linearLayout, "translationX", -1000f)
        linearLayoutAnimOut.repeatCount = 0
        linearLayoutAnimOut.duration = 500
        val tvRegAnimOut = ObjectAnimator.ofFloat(tvRegister, "translationX", 1000f)
        tvRegAnimOut.repeatCount = 0
        tvRegAnimOut.duration = 500
        val tvForgetAnimOut = ObjectAnimator.ofFloat(tvForget, "translationX", 1000f)
        tvForgetAnimOut.repeatCount = 0
        tvForgetAnimOut.duration = 500
        val idTitleAnimHide = ObjectAnimator.ofFloat(idTittle, "alpha", 1f, 0f)
        idTitleAnimHide.repeatCount = 0
        idTitleAnimHide.duration = 500
        floatingActionButton.hide()
        val set = AnimatorSet()
        set.play(tvRegAnimOut).with(tvForgetAnimOut).with(idTitleAnimHide).with(linearLayoutAnimOut)
        set.start()

        set.addListener(object : AnimatorListener {

            override fun onAnimationStart(animation: Animator) {
                // ...
            }

            override fun onAnimationRepeat(animation: Animator) {
                // ...
            }

            override fun onAnimationEnd(animation: Animator) {
                registerComing()
            }

            override fun onAnimationCancel(animation: Animator) {
                // ...
            }
        })

    }

    private fun registerComing() {

        idTittle.text = "Register"
        tvRegister.text = "Login"
        floatingActionButton.show()

        etRePassword.visibility = View.VISIBLE
        linView.visibility = View.VISIBLE

//        val etEmailExpand = ObjectAnimator.ofFloat(etEmail, "translationY", 0f)
//        etEmailExpand.repeatCount = 0
//        etEmailExpand.duration = 1000

        val linearLayoutAnimIn = ObjectAnimator.ofFloat(linearLayout, "translationX", 0f)
        linearLayoutAnimIn.repeatCount = 0
        linearLayoutAnimIn.duration = 500
        val tvRegAnimIn = ObjectAnimator.ofFloat(tvRegister, "translationX", 0f)
        tvRegAnimIn.repeatCount = 0
        tvRegAnimIn.duration = 500
        val idTitleAnimshow = ObjectAnimator.ofFloat(idTittle, "alpha", 0f, 1f)
        idTitleAnimshow.repeatCount = 0
        idTitleAnimshow.duration = 500
        val set = AnimatorSet()
        set.play(tvRegAnimIn).with(idTitleAnimshow).with(linearLayoutAnimIn)
        set.start()
    }

    private fun registerGoing() {

        etRePassword.visibility = View.VISIBLE
        linView.visibility = View.VISIBLE

        val linearLayoutAnimOut = ObjectAnimator.ofFloat(linearLayout, "translationX", -1000f)
        linearLayoutAnimOut.repeatCount = 0
        linearLayoutAnimOut.duration = 500

        val tvRegAnimOut = ObjectAnimator.ofFloat(tvRegister, "translationX", 1000f)
        tvRegAnimOut.repeatCount = 0
        tvRegAnimOut.duration = 500
        val tvForgetAnimOut = ObjectAnimator.ofFloat(tvForget, "translationX", 1000f)
        tvForgetAnimOut.repeatCount = 0
        tvForgetAnimOut.duration = 500
        val idTitleAnimHide = ObjectAnimator.ofFloat(idTittle, "alpha", 1f, 0f)
        idTitleAnimHide.repeatCount = 0
        idTitleAnimHide.duration = 500
        floatingActionButton.hide()
        val set = AnimatorSet()
        set.play(tvRegAnimOut).with(tvForgetAnimOut).with(idTitleAnimHide).with(linearLayoutAnimOut)
        set.start()

        set.addListener(object : AnimatorListener {

            override fun onAnimationStart(animation: Animator) {
                // ...
            }

            override fun onAnimationRepeat(animation: Animator) {
                // ...
            }

            override fun onAnimationEnd(animation: Animator) {
                signinComing()

            }

            override fun onAnimationCancel(animation: Animator) {
                // ...
            }
        });
    }

    private fun signinComing() {
        idTittle.text = "Login"
        tvRegister.text = "Register"
        floatingActionButton.show()

        etRePassword.visibility = View.GONE
        linView.visibility = View.GONE

        val linearLayoutAnimIn = ObjectAnimator.ofFloat(linearLayout, "translationX", 0f)
        linearLayoutAnimIn.repeatCount = 0
        linearLayoutAnimIn.duration = 500

        val tvRegAnimIn = ObjectAnimator.ofFloat(tvRegister, "translationX", 0f)
        tvRegAnimIn.repeatCount = 0
        tvRegAnimIn.duration = 500
        val tvForgetAnimIn = ObjectAnimator.ofFloat(tvForget, "translationX", 0f)
        tvForgetAnimIn.repeatCount = 0
        tvForgetAnimIn.duration = 500
        val idTitleAnimshow = ObjectAnimator.ofFloat(idTittle, "alpha", 0f, 1f)
        idTitleAnimshow.repeatCount = 0
        idTitleAnimshow.duration = 500
        val set = AnimatorSet()
        set.play(tvRegAnimIn).with(tvForgetAnimIn).with(idTitleAnimshow).with(linearLayoutAnimIn)
        set.start()
    }

}

