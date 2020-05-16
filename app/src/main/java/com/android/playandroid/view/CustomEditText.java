package com.android.playandroid.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.android.playandroid.R;


/**
 * 带清除功能编辑框
 */
public class CustomEditText extends androidx.appcompat.widget.AppCompatEditText {

    private static final String TAG = CustomEditText.class.getSimpleName();

    private Context mContext;
    private Bitmap clearBut;
    private Bitmap passwordEye;
    private Paint bitmapPaint;
    private Bitmap passwordEyeInvisible;
    private Bitmap passwordEyeVisible;
    private int rightOffset;
    private int rightPadding;
    private Bitmap clearButShow;
    private int initPaddingRight;
    private boolean useLookPwdDrawable = false;

    //按钮显示方式
    private ClearButtonMode mClearButtonMode;
    private boolean mClearStatus;

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    public CustomEditText(Context context) {
        super(context);
        this.mContext = context;
        init(null);
    }

    /**
     * 按钮显示方式
     * NEVER   不显示清空按钮
     * ALWAYS  始终显示清空按钮
     * WHILEEDITING   输入框内容不为空且有获得焦点
     * UNLESSEDITING  输入框内容不为空且没有获得焦点
     */
    public enum ClearButtonMode {
        NEVER,
        ALWAYS,
        WHILEEDITING,
        UNLESSEDITING
    }
    private void init(AttributeSet attributeSet) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.CustomEditText);
        int clearButton = typedArray.getResourceId(R.styleable.CustomEditText_clearButtonDrawable, R.mipmap.clear_button);
        useLookPwdDrawable = typedArray.getBoolean(R.styleable.CustomEditText_useLookPwdDrawable, false);
        int clearButtonMode = typedArray.getInteger(R.styleable.CustomEditText_clearButtonMode, 0);
        typedArray.recycle();
        switch (clearButtonMode) {
            case 1:
                mClearButtonMode = ClearButtonMode.ALWAYS;
                break;
            case 2:
                mClearButtonMode = ClearButtonMode.WHILEEDITING;
                break;
            case 3:
                mClearButtonMode = ClearButtonMode.UNLESSEDITING;
                break;
            default:
                mClearButtonMode = ClearButtonMode.NEVER;
                break;
        }

        rightOffset = dp2px(20);

        clearButShow = ((BitmapDrawable) getDrawableCompat(clearButton)).getBitmap();
        if (useLookPwdDrawable) {
            passwordEyeInvisible = ((BitmapDrawable) getDrawableCompat(R.drawable.eye_close)).getBitmap();
            passwordEyeVisible = ((BitmapDrawable) getDrawableCompat(R.drawable.eye_open)).getBitmap();
            passwordEye = passwordEyeInvisible;
        }
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);

        initPaddingRight = getPaddingRight();
        if (TextUtils.isEmpty(getText().toString())) {
            clearBut = null;
            if (useLookPwdDrawable) {
                rightPadding = initPaddingRight + passwordEye.getWidth() + rightOffset;
            } else {
                rightPadding = initPaddingRight + rightOffset;
            }
        } else {
            clearBut = clearButShow;
            if (useLookPwdDrawable) {
                rightPadding = initPaddingRight + clearBut.getWidth() + passwordEye.getWidth() + rightOffset + rightOffset;
            } else {
                rightPadding = initPaddingRight + clearBut.getWidth() + rightOffset + rightOffset;
            }
        }
        setPadding(getPaddingLeft(), getPaddingTop(), rightPadding, getPaddingBottom());
//		setOnTouchListener(this);
        initTextChange();
    }

    /**
     * 获取Drawable
     *
     * @param resourseId 资源ID
     */
    private Drawable getDrawableCompat(int resourseId) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            return getResources().getDrawable(resourseId, mContext.getTheme());
        } else {
            return getResources().getDrawable(resourseId);
        }
    }

    private void initTextChange() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    clearBut = clearButShow;
                    if (useLookPwdDrawable) {
                        rightPadding = initPaddingRight + clearBut.getWidth() + passwordEye.getWidth() + rightOffset + rightOffset;
                    } else {
                        rightPadding = initPaddingRight + clearBut.getWidth() + rightOffset + rightOffset;
                    }
                    invalidate();
                } else {
                    clearBut = null;
                    if (useLookPwdDrawable) {
                        rightPadding = initPaddingRight + passwordEye.getWidth() + rightOffset;
                    } else {
                        rightPadding = initPaddingRight + rightOffset;
                    }
                    invalidate();
                }
                setPadding(getPaddingLeft(), getPaddingTop(), rightPadding, getPaddingBottom());
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                float distanceToDrawLeft = event.getX() - (getMeasuredWidth() - rightPadding);
                if (distanceToDrawLeft >= 0) {
                    if (distanceToDrawLeft >= (rightPadding - ((useLookPwdDrawable ? passwordEye.getWidth() : 0) + rightOffset))) {
                        //眼睛区域被点击了
                        if (useLookPwdDrawable) {
                            if (getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                                setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                setSelection(getText().length());
                                passwordEye = passwordEyeVisible;
                                invalidate();
                            } else {
                                setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                setSelection(getText().length());
                                passwordEye = passwordEyeInvisible;
                                invalidate();
                            }
                        }
                    } else {
                        if (mClearStatus) {
                            //清除区域被点击了
                            setText("");
                            clearBut = null;
                            if (useLookPwdDrawable) {
                                rightPadding = initPaddingRight + passwordEye.getWidth() + rightOffset;
                            } else {
                                rightPadding = initPaddingRight + rightOffset;
                            }
                            setPadding(getPaddingLeft(), getPaddingTop(), rightPadding, getPaddingBottom());
                            invalidate();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 按钮状态管理
     *
     * @param canvas onDraw的Canvas
     */
    private void buttonManager(Canvas canvas, int eyeLeft) {
        switch (mClearButtonMode) {
            case ALWAYS:
                // 始终显示清空按钮
                drawBitmap(canvas, getRect(true, eyeLeft));
                break;
            case WHILEEDITING:
                // 输入框内容不为空且有获得焦点
                drawBitmap(canvas, getRect(hasFocus() && getText().length() > 0, eyeLeft));
                break;
            case UNLESSEDITING:
                // 输入框内容不为空且没有获得焦点
                drawBitmap(canvas, getRect(getText().length() > 0, eyeLeft));
                break;
            default:
                drawBitmap(canvas, getRect(false, eyeLeft));
                break;
        }
    }

    /**
     * 取得显示按钮与不显示按钮时的Rect
     *
     * @param isShow  是否显示按钮
     * @param eyeLeft
     */
    private Rect getRect(boolean isShow, int eyeLeft) {
        mClearStatus = isShow;
        int left, top, bottom;
//        right = isShow ? getMeasuredWidth() + getScrollX() - mButtonPadding - mButtonPadding : 0;
//        left = isShow ? right - clearBut.getWidth() : 0;
        left = isShow ? eyeLeft - clearBut.getWidth() : 0;
        top = isShow ? (getMeasuredHeight() - clearBut.getHeight()) / 2 : 0;
        bottom = isShow ? top + clearBut.getHeight() : 0;

        return new Rect(left, top, eyeLeft, bottom);
    }

    /**
     * 绘制按钮图片
     *
     * @param canvas onDraw的Canvas
     * @param rect   图片位置
     */
    private void drawBitmap(Canvas canvas, Rect rect) {
        if (rect != null) {
//            canvas.drawBitmap(clearBut, eyeLeft - clearBut.getWidth() - rightOffset, (getMeasuredHeight() - clearBut.getHeight()) / 2, bitmapPaint);
            canvas.drawBitmap(clearBut, null, rect, bitmapPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getScrollX(), 0);
        int eyeLeft = getMeasuredWidth() - (useLookPwdDrawable ? passwordEye.getWidth() : 0) - rightOffset;
        if (useLookPwdDrawable) {
            canvas.drawBitmap(passwordEye, eyeLeft, (getMeasuredHeight() - passwordEye.getHeight()) / 2, bitmapPaint);
        }
        if (clearBut != null) {
            buttonManager(canvas, eyeLeft);
        }
        canvas.restore();
    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
