package com.mno.msnackbar;

import android.app.Activity;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.ViewGroup;

/**
 * MSnackBar is a lightweight library for showing a brief message at the top or bottom of the
 * screen. <p>
 * <pre>
 * new MSnackBar
 *      .Builder(MainActivity.this)
 *      .setTitle("TITLE")
 *      .setMessage("MESSAGE")
 *      .setAction("ACTION", new OnActionClickListener() {})
 *      .show();
 * </pre>
 * <p> Created by Eric on 2017/3/2.
 */
public class MSnackBar {

    private static final String TAG = "cookie";

    private Snack snackView;
    private Activity context;
    private Boolean showing;

    private MSnackBar() {
    }

    private MSnackBar(Activity context, Params params) {
        this.context = context;
        snackView = new Snack(context);
        snackView.setParams(params);
    }

    public void show() {
        if (snackView != null) {
            final ViewGroup decorView = (ViewGroup) context.getWindow().getDecorView();
            final ViewGroup content = (ViewGroup) decorView.findViewById(android.R.id.content);
            if (snackView.getParent() == null) {
                if (snackView.getLayoutGravity() == Gravity.BOTTOM) {
                    show(content);
                } else {
                    show(decorView);
                }
            }
            snackView.show();
        }
    }

    public boolean isShowing() {
        if (snackView != null) {
            if (snackView.getParent() == null) {
                return false;
            } else
                return true;
        } else return false;
    }
    public void show(ViewGroup view) {
        if (snackView != null) {
            if (snackView.getParent() == null) {
                view.addView(snackView);
            }
            snackView.show();
        }
    }
    public void hide() {
        if (snackView != null&&isShowing()) {
            snackView.dismiss();
        }
    }
    public static class Builder {

        private Params params = new Params();

        public Activity context;

        /**
         * Create a builder for an cookie.
         */
        public Builder(Activity activity) {
            this.context = activity;
        }

        public Builder setIcon(@DrawableRes int iconResId) {
            params.iconResId = iconResId;
            return this;
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int resId) {
            params.title = context.getString(resId);
            return this;
        }

        public Builder setMessage(String message) {
            params.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int resId) {
            params.message = context.getString(resId);
            return this;
        }

        public Builder setDuration(long duration) {
            params.duration = duration;
            return this;
        }

        public Builder setTitleColor(@ColorRes int titleColor) {
            params.titleColor = titleColor;
            return this;
        }

        public Builder setMessageColor(@ColorRes int messageColor) {
            params.messageColor = messageColor;
            return this;
        }

        public Builder setBackgroundColor(@ColorRes int backgroundColor) {
            params.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setActionColor(@ColorRes int actionColor) {
            params.actionColor = actionColor;
            return this;
        }

        public Builder setTitleSizeTextSize(int titleSize) {
            params.titleTextSize = titleSize;
            return this;
        }

        public Builder setMessageTextSize(int messageTextSize) {
            params.messageTextSize = messageTextSize;
            return this;
        }

        public Builder setActionTextSize(int actionTextSize) {
            params.actionTextSize = actionTextSize;
            return this;
        }

        public Builder setAction(String action, OnActionClickListener onActionClickListener) {
            params.action = action;
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setAction(@StringRes int resId, OnActionClickListener onActionClickListener) {
            params.action = context.getString(resId);
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setLayoutGravity(int layoutGravity) {
            params.layoutGravity = layoutGravity;
            return this;
        }

        public MSnackBar create() {
            MSnackBar cookie = new MSnackBar(context, params);
            return cookie;
        }

        public MSnackBar show() {
            final MSnackBar cookie = create();
            cookie.show();
            return cookie;
        }

        public MSnackBar show(ViewGroup view) {
            final MSnackBar cookie = create();
            cookie.show(view);
            return cookie;
        }
    }

    final static class Params {

        public String title;

        public String message;

        public String action;

        public OnActionClickListener onActionClickListener;

        public int iconResId;

        public int backgroundColor;

        public int titleColor;

        public int messageColor;

        public int actionColor;

        public long duration = -1;

        public int layoutGravity = Gravity.TOP;

        public int titleTextSize;

        public int messageTextSize;

        public int actionTextSize;
    }

}
