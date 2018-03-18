package vacomba.com.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dex on 15/03/2018.
 */

public class DialogCustom extends Dialog {

    DialogInterface.OnClickListener positiveListener;
    DialogInterface.OnClickListener negativeListener;


    public DialogCustom(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_custom);
        TextView tvYes = findViewById(R.id.yes);
        TextView tvNo = findViewById(R.id.no);

        tvYes.setOnClickListener(noListener);
        tvNo.setOnClickListener(yesListener);
    }


    protected DialogCustom(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, Builder builder) {
        super(context, cancelable, cancelListener);
        setContentView(R.layout.dialog_custom);
        TextView tvYes = findViewById(R.id.yes);
        TextView tvNo = findViewById(R.id.no);
        TextView tvTitle = findViewById(R.id.title);
        TextView tvMessage = findViewById(R.id.message);
        ImageView ivIcon = findViewById(R.id.icon);

        tvYes.setOnClickListener(noListener);
        tvNo.setOnClickListener(yesListener);

        Builder.setText(tvTitle, builder.title);
        Builder.setText(tvMessage, builder.message);
        Builder.setText(tvYes, builder.ok);
        Builder.setText(tvNo, builder.cancel);
        Builder.setImage(ivIcon, builder.icon);

    }
    private View.OnClickListener noListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hide();
            Builder.doOnclick(negativeListener, DialogCustom.this, DialogInterface.BUTTON_NEGATIVE);
        }
    };


    private View.OnClickListener yesListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hide();
            Builder.doOnclick(positiveListener, DialogCustom.this, DialogInterface.BUTTON_NEGATIVE);
        }
    };


    static public class Builder {
        Context context;
        Resources resources;
        Drawable icon;
        DialogInterface.OnClickListener positiveClickListener;
        DialogInterface.OnClickListener negativeClickListener;
        String title;
        String message;
        String cancel;
        String ok;

        OnCancelListener cancelListener;
        boolean cancelable = true;

        static void setText(TextView textView, String text) {
            if (text != null) {
                textView.setText(text);
            }
        }

        static void setImage(ImageView imageView, Drawable drawable) {
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }

        static void doOnclick(DialogInterface.OnClickListener listener, DialogInterface dialogInterface, int i) {
            if (listener != null) {
                listener.onClick(dialogInterface, i);
            }
        }

        public Builder(Context context) {
            this.context = context;
            this.resources = context.getResources();
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int resId) {
            this.title = resources.getString(resId);
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int resId) {
            this.message = resources.getString(resId);
            return this;
        }

        public Builder setNegativeButton(String text, DialogInterface.OnClickListener onClickListener) {
            this.cancel = text;
            this.negativeClickListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(int resId, DialogInterface.OnClickListener onClickListener) {
            this.cancel = resources.getString(resId);
            this.negativeClickListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(String text, DialogInterface.OnClickListener onClickListener) {
            this.ok = text;
            this.positiveClickListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(int resId, DialogInterface.OnClickListener onClickListener) {
            this.ok = resources.getString(resId);
            this.positiveClickListener = onClickListener;
            return this;
        }

        public Builder setIcon(int resId) {
            this.icon = resources.getDrawable(resId);
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.icon = drawable;
            return this;
        }

        public void setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
        }

        public DialogCustom show() {
            DialogCustom dialogCustom = new DialogCustom(context, cancelable, cancelListener, this);
            dialogCustom.show();
            return dialogCustom;
        }
    }
}
