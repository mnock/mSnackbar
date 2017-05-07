package com.mno.msnackbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Eric on 2017/3/2.
 */
final class Snack extends LinearLayout {
    private LinearLayout layoutCookie;
    private TextView tvTitle;
    private TextView tvMessage;
    private ImageView ivIcon;
    private TextView btnAction;
    private long duration = 2000;
    private int layoutGravity = Gravity.BOTTOM;

    public Snack(@NonNull final Context context) {
        this(context, null);
    }

    public Snack(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Snack(@NonNull final Context context, @Nullable final AttributeSet attrs,
                 final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public int getLayoutGravity() {
        return layoutGravity;
    }

    SlideUp slideUp;

    private void initViews(Context context) {
        inflate(getContext(), R.layout.layout_cookie, this);
        layoutCookie = (LinearLayout) findViewById(R.id.cookie);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        btnAction = (TextView) findViewById(R.id.btn_action);
        initDefaultStyle(context);
    }

    /**
     * Init the default text color or background color. You can change the default style by set the
     * Theme's attributes.
     * <p>
     * <pre>
     *  <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
     *          <item name="cookieTitleColor">@color/default_title_color</item>
     *          <item name="cookieMessageColor">@color/default_message_color</item>
     *          <item name="cookieActionColor">@color/default_action_color</item>
     *          <item name="cookieBackgroundColor">@color/default_bg_color</item>
     *  </style>
     * </pre>
     */
    private void initDefaultStyle(Context context) {
        //Custom the default style of a cookie
        int titleColor = ThemeResolver.getColor(context, R.attr.cookieTitleColor, Color.WHITE);
        int messageColor = ThemeResolver.getColor(context, R.attr.cookieMessageColor, Color.WHITE);
        int actionColor = ThemeResolver.getColor(context, R.attr.cookieActionColor, Color.WHITE);
        int backgroundColor = ThemeResolver.getColor(context, R.attr.cookieBackgroundColor,
                ContextCompat.getColor(context, R.color.default_bg_color));

        tvTitle.setTextColor(titleColor);
        tvMessage.setTextColor(messageColor);
        btnAction.setTextColor(actionColor);
        layoutCookie.setBackgroundColor(backgroundColor);
    }

    public void setParams(final MSnackBar.Params params) {
        if (params != null) {
            duration = params.duration;
            layoutGravity = params.layoutGravity;

            //Icon
            if (params.iconResId != 0) {
                ivIcon.setVisibility(VISIBLE);
                ivIcon.setBackgroundResource(params.iconResId);
            }

            //Title
            if (!TextUtils.isEmpty(params.title)) {
                tvTitle.setVisibility(VISIBLE);
                tvTitle.setText(params.title);
                if (params.titleTextSize != 0)
                    tvTitle.setTextSize(params.titleTextSize);
                if (params.titleColor != 0) {
                    tvTitle.setTextColor(ContextCompat.getColor(getContext(), params.titleColor));
                }
            }

            //Message
            if (!TextUtils.isEmpty(params.message)) {
                tvMessage.setVisibility(VISIBLE);
                tvMessage.setText(params.message);
                if (params.messageTextSize != 0)
                    tvMessage.setTextSize(params.messageTextSize);
                if (params.messageColor != 0) {
                    tvMessage.setTextColor(ContextCompat.getColor(getContext(), params.messageColor));
                }
            }

            //Action
            if (!TextUtils.isEmpty(params.action) && params.onActionClickListener != null) {
                btnAction.setVisibility(VISIBLE);
                btnAction.setText(params.action);
                if (params.actionTextSize != 0)
                    btnAction.setTextSize(params.actionTextSize);
                btnAction.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        params.onActionClickListener.onClick();
                        dismiss();
                    }
                });

                //Action Color
                if (params.actionColor != 0) {
                    btnAction.setTextColor(ContextCompat.getColor(getContext(), params.actionColor));
                }
            }

            //Background
            if (params.backgroundColor != 0) {
                layoutCookie
                        .setBackgroundColor(ContextCompat.getColor(getContext(), params.backgroundColor));
            }

            final int padding = getContext().getResources().getDimensionPixelSize(R.dimen.default_padding);
            if (layoutGravity == Gravity.BOTTOM) {
                layoutCookie.setPadding(padding, padding, padding, padding);
            }

            int type = Gravity.TOP;
            if (layoutGravity == Gravity.TOP) {
                type = Gravity.TOP;
            } else if (layoutGravity == Gravity.BOTTOM) {
                type = Gravity.BOTTOM;
            }
            slideUp = new SlideUp.Builder(layoutCookie)
                    .withListeners(new SlideUp.Listener() {
                        @Override
                        public void onSlide(float percent) {
                            Log.e("mno", "" + percent);
                            layoutCookie.setAlpha(1 - (percent / 100));
                        }

                        @Override
                        public void onVisibilityChanged(int visibility) {
                            if (visibility == GONE) {
                                destroy();
                            } else if (duration > 0) {
                                mhander.removeMessages(1000);
                                mhander.start(1000, duration);
                            }

                        }
                    })
                    .withStartGravity(type)
                    .withLoggingEnabled(true)
                    .withStartState(SlideUp.State.HIDDEN)
                    .build();
        }
    }

    WeakRefHander mhander = new WeakRefHander(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1000)
                dismiss();
            return false;
        }
    });

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (layoutGravity == Gravity.TOP) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.TOP;
            layoutCookie.setLayoutParams(layoutParams);
        } else {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.BOTTOM;
            layoutCookie.setLayoutParams(layoutParams);
        }
        super.onLayout(changed, l, t, r, b);
    }

    private void dismiss() {
        slideUp.hide();
    }

    private void destroy() {
        mhander.stop();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewParent parent = getParent();
                if (parent != null) {
                    //fl_back.setReBack();
                    Snack.this.clearAnimation();
                    ((ViewGroup) parent).removeView(Snack.this);
                }
            }
        }, 200);
    }

    public void show() {
        slideUp.show();
    }

}
