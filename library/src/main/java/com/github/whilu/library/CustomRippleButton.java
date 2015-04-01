package com.github.whilu.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lujun on 2015/3/30.
 */
public class CustomRippleButton extends Button {

    private int mWidth, mHeight;
    private int mUnPressedColor;
    private int mRoundRadius, mBtnRadius;
    private int mShapeType;
    private int mRippleColor;
    private int mRippleDuration;
    private int mRippleRadius;
    private float pointX, pointY;

    private Paint mPaint, mRipplePaint;
    private RectF mRectF;
    private Path mPath;
    private Timer mTimer;
    private TimerTask mTask;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_DRAW_COMPLETE){
                invalidate();
            }
        }
    };

    private int mRippleAlpha;
    private final static int HALF_ALPHA = 127;
    private final static int RIPPLR_ALPHE = 47;
    private final static int MSG_DRAW_COMPLETE = 101;

    public CustomRippleButton(Context context){
        super(context);
    }

    public CustomRippleButton(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context, attrs);
    }

    public CustomRippleButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs){
        if (isInEditMode()){
            return;
        }
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        mUnPressedColor = typedArray.getColor(R.styleable.CustomButton_unpressed_color,
                getResources().getColor(R.color.unpressed_color));
        mRippleColor = typedArray.getColor(R.styleable.CustomButton_ripple_color,
                getResources().getColor(R.color.ripple_color));
        mRippleAlpha = typedArray.getInteger(R.styleable.CustomButton_ripple_alpha,
                RIPPLR_ALPHE);
        mRippleDuration = typedArray.getInteger(R.styleable.CustomButton_ripple_duration,
                1000);
        mShapeType = typedArray.getInt(R.styleable.CustomButton_shape_type, 1);
        mRoundRadius = typedArray.getDimensionPixelSize(R.styleable.CustomButton_round_radius,
                getResources().getDimensionPixelSize(R.dimen.round_radius));
        /*mBtnRadius = typedArray.getDimensionPixelSize(R.styleable.CustomButton_btn_radius,
                getResources().getDimensionPixelSize(R.dimen.btn_radius));*/
        typedArray.recycle();
        mRipplePaint = new Paint();
        mRipplePaint.setColor(mRippleColor);
        mRipplePaint.setAlpha(mRippleAlpha);
        mRipplePaint.setStyle(Paint.Style.FILL);
        mRipplePaint.setAntiAlias(true);
        mPaint = new Paint();
        mPaint.setColor(mUnPressedColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        this.setWillNotDraw(false);
        this.setDrawingCacheEnabled(true);
        this.setClickable(true);
        this.setBackgroundColor(getResources().getColor(R.color.transparent));
        mPath = new Path();
        mRectF = new RectF();
        pointY = pointX = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBtnRadius = mWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null){
            return;
        }
        mPaint.setAlpha(HALF_ALPHA);
        if (mShapeType == 0){// draw cirle button
            canvas.drawCircle(mWidth / 2, mHeight / 2, mBtnRadius, mPaint);
        }else{// draw rectangle button
            mRectF.set(0, 0, mWidth, mHeight);
            canvas.drawRoundRect(mRectF, mRoundRadius, mRoundRadius, mPaint);
        }
        drawFillCircle(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            pointX = event.getX();
            pointY = event.getY();
            onStartDrawRipple();
        }
        return super.onTouchEvent(event);
    }

    /** Draw ripple effect*/
    private void drawFillCircle(Canvas canvas){
        if (canvas != null && pointX >= 0 && pointY >= 0){
            int rbX = canvas.getWidth();
            int rbY = canvas.getHeight();
            float longDis = Math.max(pointX, pointY);
            longDis = Math.max(longDis, Math.abs(rbX - pointX));
            longDis = Math.max(longDis, Math.abs(rbY - pointY));
            if (mRippleRadius > longDis) {
                onCompleteDrawRipple();
                return;
            }
            final float drawSpeed = longDis / mRippleDuration * 35;
            mRippleRadius += drawSpeed;

            canvas.save();
//            canvas.translate(0, 0);//保持原点
            mPath.reset();
            canvas.clipPath(mPath);
            if (mShapeType == 0){
                mPath.addCircle(rbX / 2, rbY / 2, mBtnRadius, Path.Direction.CCW);
            }else {
                mPath.addRoundRect(mRectF, mRoundRadius, mRoundRadius, Path.Direction.CCW);
            }
            canvas.clipPath(mPath, Region.Op.REPLACE);
            canvas.drawCircle(pointX, pointY, mRippleRadius, mRipplePaint);
            canvas.restore();
        }
    }

    /** Start draw ripple effect*/
    private void onStartDrawRipple(){
        onCompleteDrawRipple();
        mTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_DRAW_COMPLETE);
            }
        };
        mTimer = new Timer();
        mTimer.schedule(mTask, 0, 30);
    }

    /** Stop draw ripple effect*/
    private void onCompleteDrawRipple(){
        mHandler.removeMessages(MSG_DRAW_COMPLETE);
        if (mTimer != null){
            if (mTask != null){
                mTask.cancel();
            }
            mTimer.cancel();
        }
        mRippleRadius = 0;
    }
}
