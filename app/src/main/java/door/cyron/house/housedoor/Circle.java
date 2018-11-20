package door.cyron.house.housedoor;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import door.cyron.house.housedoor.review.EachView;
import org.jetbrains.annotations.NotNull;

public class Circle extends View {
    private int scrWidth;
    private int scrHeight;
    private Paint linePaint;
    private Paint circlePaint;
    private Paint circlePaintActive;
    private Paint linePaintActive;

    public int dotCount = 5;
    private int startX, startY;
    private int selectedPoint = 0;
    private EachView eachObj;
    private boolean movementDown;

    public int getSelectedPoint() {
        return selectedPoint;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int x) {
        startX = x;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int y) {
        startY = y;
    }

    public void increaseSelectedPoint() {
        this.selectedPoint++;
    }

    public void decreaseSelectedPoint() {
        selectedPoint--;
    }

    public Circle(Context context) {
        super(context);
        init(context, null);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(4);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.GRAY);
        circlePaint.setStyle(Paint.Style.FILL);

        circlePaintActive = new Paint();
        circlePaintActive.setAntiAlias(true);
        circlePaintActive.setColor(Color.GREEN);
        circlePaintActive.setStyle(Paint.Style.FILL);


        linePaintActive = new Paint();
        linePaintActive.setColor(Color.GREEN);
        linePaintActive.setAntiAlias(true);
        linePaintActive.setStyle(Paint.Style.STROKE);
        linePaintActive.setStrokeWidth(4);

        selectedPoint = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (eachObj != null) {
            if (movementDown) {
             /*
           active line from topmost point to previous active point
            */
                canvas.drawLine(scrWidth - scrWidth / 7, 0, scrWidth - scrWidth / 7, eachObj.getYMidLine(), linePaintActive);

           /*
           active line from previous active point to recent active point
            */
                canvas.drawLine(eachObj.getXStartLine(), eachObj.getYStartLine(), eachObj.getXMidLine(), eachObj.getYMidLine(), linePaintActive);
              /*
           inactive line from  active point to bottom most point
            */
                canvas.drawLine(eachObj.getXMidLine(), eachObj.getYMidLine(), eachObj.getXEndLine(), eachObj.getYEndLine(), linePaint);
             /*
           top most active checkpoints
            */
                if (eachObj.isActiveCircle())
                    canvas.drawCircle(eachObj.getXStartLine(), eachObj.getYMidLine(), 20, circlePaintActive);

            /*
            active and in active circle checkpoints
            */
                for (int j = 1; j <= dotCount; j++) {
                    if (j < selectedPoint) {
                        canvas.drawCircle(scrWidth - scrWidth / 7, (scrHeight / dotCount - 20) * j, 20, circlePaintActive);
                    } else
                        canvas.drawCircle(scrWidth - scrWidth / 7, (scrHeight / dotCount - 20) * j, 20, circlePaint);
                }
            } else {

                 /*
           inactive line from  inactive point to bottom most point
            */
                canvas.drawLine(eachObj.getXStartLine(), eachObj.getYStartLine(), eachObj.getXEndLine(), getEndY(), linePaint);
                /*
           Inactive line from previous Inactive point to recent Inactive point
            */
                canvas.drawLine(eachObj.getXStartLine(), eachObj.getYStartLine(), eachObj.getXMidLine(), eachObj.getYMidLine(), linePaint);
                /*
           active line from topmost point to recent Inactive point
            */
                canvas.drawLine(eachObj.getXMidLine(), eachObj.getYMidLine(), eachObj.getXEndLine(), 0, linePaintActive);

            /*
            active and in active circle checkpoints
            */
                for (int j = 1; j <= dotCount; j++) {
                    if (j < selectedPoint + 1) {
                        canvas.drawCircle(scrWidth - scrWidth / 7, (scrHeight / dotCount - 20) * j, 20, circlePaintActive);
                    } else
                        canvas.drawCircle(scrWidth - scrWidth / 7, (scrHeight / dotCount - 20) * j, 20, circlePaint);
                }
                 /*
           Inactive top most checkpoints
            */
                if (eachObj.isActiveCircle())
                    canvas.drawCircle(eachObj.getXStartLine(), eachObj.getYMidLine(), 20, circlePaint);

            }

        } else {
            canvas.drawLine(scrWidth - scrWidth / 7, 0, scrWidth - scrWidth / 7, (scrHeight / dotCount - 20) * dotCount, linePaint);

            for (int j = 1; j <= dotCount; j++) {
                canvas.drawCircle(scrWidth - scrWidth / 7, (scrHeight / dotCount - 20) * j, 20, circlePaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        scrWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        scrHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setStartY(0);
        setStartX(scrWidth - scrWidth / 7);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.getParent().requestDisallowInterceptTouchEvent(true);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                setPressed(false);
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                setPressed(false);
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return true;
    }


    public int getCircleMidPoint() {
        return (scrHeight / dotCount - 20) * getSelectedPoint();
    }

    public int getPrevCircleMidPoint() {
        return ((scrHeight / dotCount - 20) * getSelectedPoint()) + (scrHeight / dotCount - 20);
    }

    public int getEndY() {
        return (scrHeight / dotCount - 20) * dotCount;
    }

    public int getTopStartY() {
        return (scrHeight / dotCount - 20);
    }

    public void setObj(@NotNull EachView each) {
        eachObj = each;
        invalidate();
    }

    public void setMovement(boolean movementDown) {
        this.movementDown = movementDown;
    }


}