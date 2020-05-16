package com.android.playandroid.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.android.playandroid.R;

import static com.util.ktx.ext.CommonExtKt.dp2px;

/**
 * 自定义toolbar
 */
public class CustomToolBar extends Toolbar {
    private TextView mCenterTitle;//中心标题
    private ImageView mCenterIcon; //中心icon
    private TextView mLeftText;//左侧文字
    private ImageButton mLeftIcon; //左侧按钮
    private TextView mSettingText;//右侧文字
    private ImageButton mSettingIcon; //右侧按钮

    public CustomToolBar(Context context) {
        super(context);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 左侧文字
     *
     * @param id 文字id
     */
    public void setLeftText(@StringRes int id) {
        setLeftText(this.getContext().getText(id));
    }

    /**
     * ToolBar左侧有contentInsetStart 16Dp的空白，若需要可自己定义style修改
     *
     * @param text
     */
    public CustomToolBar setLeftText(CharSequence text) {
        Context context = this.getContext();
        if (this.mLeftText == null) {
            this.mLeftText = new TextView(context);
            this.mLeftText.setGravity(Gravity.CENTER_VERTICAL);
            this.mLeftText.setSingleLine();
//            this.mLeftText.setEllipsize(TextUtils.TruncateAt.END);
            setLeftTextAppearance(getContext(), R.style.TextAppearance_TitleBar_subTitle);
            //textView in left
//            this.addMyView(this.mLeftText, Gravity.START);
            int i = dp2px(this, 16);
            this.addMyView(this.mLeftText, Gravity.START, 0, 0, i, 0, 48);
        }
        mLeftText.setText(text);
        return this;
    }

    public void setLeftTextAppearance(Context context, @StyleRes int resId) {
        if (this.mLeftText != null) {
            this.mLeftText.setTextAppearance(context, resId);
        }
    }

    public void setLeftTextColor(@ColorInt int color) {
        if (this.mLeftText != null) {
            this.mLeftText.setTextColor(color);
        }
    }

    public void setLeftTextOnClickListener(OnClickListener listener) {
        if (mLeftText != null) {
            mLeftText.setOnClickListener(listener);
        }
    }

    /**
     * 左侧图标
     *
     * @param resId
     */
    public CustomToolBar setLeftIcon(@DrawableRes int resId) {
        return setLeftIcon(ContextCompat.getDrawable(this.getContext(), resId));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public CustomToolBar setLeftIcon(Drawable drawable) {
        Context context = this.getContext();
        if (this.mLeftIcon == null) {
            this.mLeftIcon = new ImageButton(context);
            this.mLeftIcon.setBackground(null);
            //保持点击区域
            int padding = (int) this.getContext().getResources().getDimension(R.dimen.title_right_margin);
            this.mLeftIcon.setPadding(padding, 0, padding, 0);
            this.mLeftIcon.setScaleType(ImageView.ScaleType.CENTER);
            //textView in center
            this.addMyView(this.mLeftIcon, Gravity.START);
        } else {
            if (mLeftIcon.getVisibility() != VISIBLE) {
                mLeftIcon.setVisibility(VISIBLE);
            }
        }
        if (mLeftText != null && mLeftText.getVisibility() != GONE) {
            mLeftText.setVisibility(GONE);
        }
        mLeftIcon.setImageDrawable(drawable);
        return this;
    }

    public void setLeftIconOnClickListener(OnClickListener listener) {
        if (mLeftIcon != null) {
            mLeftIcon.setOnClickListener(listener);
        }
    }

    public void setLeftIconVisibility(int visibility) {
        if (mLeftIcon != null) {
            mLeftIcon.setVisibility(visibility);
        }
    }

    /**
     * 居中标题
     *
     * @param id 文字id
     */
    public CustomToolBar setMyCenterTitle(@StringRes int id, boolean center) {
        return setMyCenterTitle(this.getContext().getText(id), center);
    }

    /**
     * 居中标题
     */
      /*
    public void setMyCenterTitle(@StringRes int Rid) {
        setMyCenterTitle(this.getContext().getText(Rid));
    }

   public void setMyCenterTitle(CharSequence text) {
        Context context = this.getContext();
        if (this.mCenterTitle == null) {
            this.mCenterTitle = new TextView(context);
            this.mCenterTitle.setGravity(Gravity.CENTER);
            this.mCenterTitle.setSingleLine();
            this.mCenterTitle.setEllipsize(TextUtils.TruncateAt.END);
            setMyCenterTextAppearance(getContext(), R.style.TextAppearance_TitleBar_Title);
            //textView in center
            this.addMyView(this.mCenterTitle, Gravity.CENTER);
        } else {
            if (this.mCenterTitle.getVisibility() != VISIBLE) {
                mCenterTitle.setVisibility(VISIBLE);
            }
        }
        if (mCenterIcon != null && mCenterIcon.getVisibility() != GONE) {
            mCenterIcon.setVisibility(GONE);
        }
        //隐藏toolbar自带的标题
        setTitle("");
        mCenterTitle.setText(text);
        mCenterTitle.setTextColor(getResources().getColor(R.color.white));
        //Utils2App.dip2px(20.0)
        mCenterTitle.setTextSize(20);
    }*/
    public CustomToolBar setMyCenterTitle(CharSequence text, boolean center) {
        Context context = this.getContext();
        if (this.mCenterTitle == null) {
            this.mCenterTitle = new TextView(context);
            this.mCenterTitle.setGravity(Gravity.CENTER);
            this.mCenterTitle.setSingleLine();
            this.mCenterTitle.setEllipsize(TextUtils.TruncateAt.END);
            setMyCenterTextAppearance(getContext(), R.style.TextAppearance_TitleBar_Title);
            //textView in center
//            this.addMyView(this.mCenterTitle, Gravity.CENTER);
            this.addSimpleView(this.mCenterTitle, Gravity.CENTER);
        } else {
            if (this.mCenterTitle.getVisibility() != VISIBLE) {
                mCenterTitle.setVisibility(VISIBLE);
            }
        }
        if (mCenterIcon != null && mCenterIcon.getVisibility() != GONE) {
            mCenterIcon.setVisibility(GONE);
        }
        //隐藏toolbar自带的标题
        if (!center) {
            setTitle(text);
            setTitleTextColor(getResources().getColor(R.color.black));
        } else {
            mCenterTitle.setText(text);
            mCenterTitle.setTextColor(getResources().getColor(R.color.black));
            mCenterTitle.setTextSize(18);
        }
        return this;
    }

    public void setMyCenterTextAppearance(Context context, @StyleRes int resId) {
        if (this.mCenterTitle != null) {
            this.mCenterTitle.setTextAppearance(context, resId);
        }
    }

    public void setMyCenterTextColor(@ColorInt int color) {
        if (this.mCenterTitle != null) {
            this.mCenterTitle.setTextColor(color);
        }
    }

    public void setMyCenterTextOnClickListener(OnClickListener listener) {
        if (mCenterTitle != null) {
            mCenterTitle.setOnClickListener(listener);
        }
    }

    /**
     * 居中图标
     *
     * @param resId
     */
    public void setMyCenterIcon(@DrawableRes int resId) {
        setMyCenterIcon(ContextCompat.getDrawable(this.getContext(), resId));
    }

    public void setMyCenterIcon(Drawable drawable) {
        Context context = this.getContext();
        if (this.mCenterIcon == null) {
            this.mCenterIcon = new ImageView(context);
            this.mCenterIcon.setScaleType(ImageView.ScaleType.CENTER);
            //textView in center
            this.addMyView(this.mCenterIcon, Gravity.CENTER);
        } else {
            if (mCenterIcon.getVisibility() != VISIBLE) {
                mCenterIcon.setVisibility(VISIBLE);
            }
        }
        if (mCenterTitle != null && mCenterTitle.getVisibility() != GONE) {
            mCenterTitle.setVisibility(GONE);
        }
        //隐藏toolbar自带的标题
        setTitle("");
        mCenterIcon.setImageDrawable(drawable);
    }

    /**
     * 右侧文字
     *
     * @param Rid
     */
    public void setMySettingText(@StringRes int Rid) {
        setMySettingText(this.getContext().getText(Rid));
    }

    public void setMySettingText(CharSequence text) {
        Context context = this.getContext();
        if (this.mSettingText == null) {
            this.mSettingText = new TextView(context);
            this.mSettingText.setGravity(Gravity.CENTER);
            this.mSettingText.setSingleLine();
            this.mSettingText.setEllipsize(TextUtils.TruncateAt.END);
            setMySettingTextAppearance(getContext(), R.style.TextAppearance_TitleBar_subTitle);
            //textView in center
            int padding = (int) this.getContext().getResources().getDimension(R.dimen.title_right_margin);
            this.mSettingText.setPadding(padding, 0, padding, 0);
            //this.addMyView(this.mSettingText, Gravity.END);
            this.addSimpleView(this.mSettingText, Gravity.END);
        } else {
            if (mSettingText.getVisibility() != VISIBLE) {
                mSettingText.setVisibility(VISIBLE);
            }
        }
        if (mSettingIcon != null && mSettingIcon.getVisibility() != GONE) {
            mSettingIcon.setVisibility(GONE);
        }
        mSettingText.setText(text);
        mSettingText.setTextSize(18);
        mSettingText.setTextColor(getResources().getColor(R.color.colorAccent));

    }

    public void setMySettingTextAppearance(Context context, @StyleRes int resId) {
        if (mSettingText != null) {
            mSettingText.setTextAppearance(context, resId);
        }
    }

    public void setMySettingTextColor(@ColorInt int color) {
        if (mSettingText != null) {
            mSettingText.setTextColor(color);
        }
    }

    public void setSettingTextOnClickListener(OnClickListener listener) {
        if (mSettingText != null) {
            mSettingText.setOnClickListener(listener);
        }
    }

    /**
     * 右侧图标
     *
     * @param resId
     */
    public CustomToolBar setMySettingIcon(@DrawableRes int resId) {
        return setMySettingIcon(ContextCompat.getDrawable(this.getContext(), resId));
//        ViewConfiguration.get(this.getContext()).getScaledTouchSlop();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public CustomToolBar setMySettingIcon(Drawable drawable) {
        Context context = this.getContext();
        if (this.mSettingIcon == null) {
            this.mSettingIcon = new ImageButton(context);
            this.mSettingIcon.setBackground(null);
            //保持点击区域
            int padding = (int) this.getContext().getResources().getDimension(R.dimen.title_right_margin);
            this.mSettingIcon.setPadding(padding, 0, padding, 0);
            this.mSettingIcon.setScaleType(ImageView.ScaleType.CENTER);
            //textView in center
            this.addMyView(this.mSettingIcon, Gravity.END);
        } else {
            if (mSettingIcon.getVisibility() != VISIBLE) {
                mSettingIcon.setVisibility(VISIBLE);
            }
        }
        if (mSettingText != null && mSettingText.getVisibility() != GONE) {
            mSettingText.setVisibility(GONE);
        }
        mSettingIcon.setImageDrawable(drawable);
        return this;
    }

    public void setSettingIconOnClickListener(OnClickListener listener) {
        if (mSettingIcon != null) {
            mSettingIcon.setOnClickListener(listener);
        }
    }

    /**
     * 添加普通view
     *
     * @param v
     * @param gravity
     */
    private void addSimpleView(View v, int gravity) {
        addSimpleView(v, gravity, 0, 0, 0, 0);
    }

    private void addSimpleView(View v, int gravity, int left, int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, gravity);
        lp.setMargins(left, top, right, bottom);
        this.addView(v, lp);
    }

    /**
     * 添加标准view
     *
     * @param v
     * @param gravity
     */
    private void addMyView(View v, int gravity) {

        addMyView(v, gravity, 0, 0, dp2px(this, 16), 0);
    }

    private void addMyView(View v, int gravity, int left, int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(dp2px(this, 20),
                dp2px(this, 20), gravity);
        lp.setMargins(left, top, right, bottom);
        this.addView(v, lp);
    }

    private void addMyView(View v, int gravity, int left, int top, int right, int bottom, int width) {
        LayoutParams lp = new LayoutParams(dp2px(this, width),
                20, gravity);
        lp.setMargins(left, top, right, bottom);
        this.addView(v, lp);
    }


    /**
     * 居中标题（带icon）
     */
    public CustomToolBar setCenterTitleWithImg(CharSequence text, Drawable drawable, boolean center) {
        Context context = this.getContext();
        if (this.mCenterTitle == null) {
            this.mCenterTitle = new TextView(context);
            this.mCenterTitle.setGravity(Gravity.CENTER);
            this.mCenterTitle.setSingleLine();
            this.mCenterTitle.setEllipsize(TextUtils.TruncateAt.END);
            setMyCenterTextAppearance(getContext(), R.style.TextAppearance_TitleBar_Title);
//            this.addSimpleView(this.mCenterTitle, Gravity.CENTER);
        } else {
            if (this.mCenterTitle.getVisibility() != VISIBLE) {
                mCenterTitle.setVisibility(VISIBLE);
            }
        }

        if (this.mCenterIcon == null) {
            this.mCenterIcon = new ImageView(context);
            this.mCenterIcon.setScaleType(ImageView.ScaleType.CENTER);
//            this.addMyView(this.mCenterIcon, Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.addView(mCenterTitle, layoutParams);
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams1.setMargins(8, 0, 0, 0);
            linearLayout.addView(mCenterIcon, layoutParams1);
            this.addSimpleView(linearLayout, Gravity.CENTER);
        } else {
            if (mCenterIcon.getVisibility() != VISIBLE) {
                mCenterIcon.setVisibility(VISIBLE);
            }
        }

        mCenterTitle.setTextSize(18);
        mCenterTitle.setTextColor(getResources().getColor(R.color.black));
        mCenterTitle.setText(text);
        mCenterIcon.setImageDrawable(drawable);

        return this;
    }

    public void setCenterTitleWithImgOnClickListener(OnClickListener listener) {
        if (mCenterTitle != null) {
            ((View) mCenterTitle.getParent()).setOnClickListener(listener);
        }
    }
}
