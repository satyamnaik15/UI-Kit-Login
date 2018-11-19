package door.cyron.house.housedoor;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Circleline extends View {
    private int scrWidth;
    private int scrHeight;
    private Paint linePaint;
    private Paint circlePaint;
    private Paint circlePaintActive;
    private Paint linePaintActive;

    private int dotCount = 5;
    private int step = 0;

    public Circleline(Context context) {
        super(context);
        init(context, null);
    }

    public Circleline(Context context, AttributeSet attrs) {
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


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = scrWidth - scrWidth / 7;
        int circleGap = scrHeight / dotCount - 20;
        circlePaint.setColor(Color.RED);
        canvas.drawLine(x, 0, x, scrHeight, linePaint);
        for (int j = 1; j <= dotCount; j++) {
            if (j <= step) {
                canvas.drawLine(x, 0, x, circleGap * j, linePaintActive);
                canvas.drawCircle(x, circleGap * j, 20, circlePaintActive);
            } else {
                canvas.drawCircle(x, circleGap * j, 20, circlePaint);
            }
        }


    }

    public void stepUp() {
        if (step < dotCount) {
            step++;
            invalidate();
        }
    }
    public void stepDown() {
        if (step > 0) {
            step--;
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        scrWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        scrHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
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


}