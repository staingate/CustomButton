package com.github.whilu.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

/**
 * Created by lujun on 2015/3/30.
 */
public class CustomImageButton extends ImageButton {

    private int mPressedSrc, mUnPressedSrc;

    public CustomImageButton(Context context){
        super(context);
    }

    public CustomImageButton(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context, attrs);
    }

    public CustomImageButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs){
        if (isInEditMode()){
            return;
        }
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageButton);
        mPressedSrc = typedArray.getResourceId(R.styleable.CustomImageButton_pressed_src, 0);
        mUnPressedSrc = typedArray.getResourceId(R.styleable.CustomImageButton_unpressed_src, 0);
        typedArray.recycle();
        this.setBackground(null);
        if (mUnPressedSrc != 0){
            this.setImageResource(mUnPressedSrc);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (mPressedSrc != 0){
                    this.setImageResource(mPressedSrc);
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mUnPressedSrc != 0) {
                    this.setImageResource(mUnPressedSrc);
                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
