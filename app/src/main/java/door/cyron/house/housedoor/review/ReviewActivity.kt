package door.cyron.house.housedoor.review

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import door.cyron.house.housedoor.Circle
import door.cyron.house.housedoor.R
import java.util.Collections.rotate
import android.R.attr.radius
import android.animation.ValueAnimator
import android.animation.PropertyValuesHolder
import android.util.Log


class ReviewActivity : AppCompatActivity() {

    lateinit var btnCLick:FloatingActionButton
    lateinit var btnMin:Button
    lateinit var cusView:Circle
     var isfromPlus=false
     var isFromMinus=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        btnCLick=findViewById(R.id.btnClick)
        cusView=findViewById(R.id.cusView)
        btnMin=findViewById(R.id.btnMin)

        btnCLick.setOnClickListener(){

            if(cusView.dotCount>=cusView.selectedPoint) {
                isfromPlus=true
                if(isFromMinus){
                    cusView.increaseSelectedPoint()
                    isFromMinus=false
                }
                val propertyRadius =
                    PropertyValuesHolder.ofInt("PROPERTY_RADIUS", cusView.startY, cusView.getCircleMidPoint())
                cusView.setMovement(true)

                var animator = ValueAnimator()
                animator.setValues(propertyRadius)
                animator.setDuration(2000)
                animator.addUpdateListener { animation ->
                    var yMidValue = animation.getAnimatedValue("PROPERTY_RADIUS") as Int
                    var isActiveCircle=false
                    isActiveCircle = yMidValue==cusView.getCircleMidPoint()
                    val each= EachView(cusView.startX,cusView.startY,cusView.startX,yMidValue,cusView.startX,cusView.getEndY(),isActiveCircle)
                    cusView.setObj(each)
//                    Log.e("LOG","11111---> "+(yMidValue))

                    if(isActiveCircle) {
                        cusView.increaseSelectedPoint()
                        cusView.setStartY(yMidValue)

                    }
                }
                animator.start()
            }
        }
        btnMin.setOnClickListener(){

            if(cusView.selectedPoint>0) {
                cusView.setMovement(false)
                isFromMinus=true
                cusView.decreaseSelectedPoint()
                if(isfromPlus) {
                    cusView.decreaseSelectedPoint()
                    isfromPlus=false
                }
                val propertyRadius =PropertyValuesHolder.ofInt("PROPERTY_Y_RADIUS", cusView.getCircleMidPoint(), cusView.getPrevCircleMidPoint())
                val temp=cusView.getCircleMidPoint()
                val tempEnd=cusView.getPrevCircleMidPoint()

                var animator = ValueAnimator()
                animator.setValues(propertyRadius)
                animator.setDuration(2000)
                animator.addUpdateListener { animation ->
                    var yMidValue = (animation.getAnimatedValue("PROPERTY_Y_RADIUS") as Int)

                    yMidValue=(tempEnd-(yMidValue-temp))
                    cusView.startY=yMidValue

                    var isActiveCircle=false
                    isActiveCircle = (temp==yMidValue)&&(cusView.selectedPoint>1)
                    val each= EachView(cusView.startX,tempEnd,cusView.startX,yMidValue,cusView.startX,cusView.getTopStartY(),isActiveCircle)
                    cusView.setObj(each)

                }
                animator.start()
            }


        }
    }
}
