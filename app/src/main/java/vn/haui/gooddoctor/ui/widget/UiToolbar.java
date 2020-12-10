package vn.haui.gooddoctor.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.UiToolbarBinding;


public class UiToolbar extends ConstraintLayout implements BaseWidget {

    TextView tvTitle;
    ImageView ivBack;
    ImageView ivActionRight;
    ConstraintLayout ctSearchHome;


    TextView tvAmountCartHome;
    TextView tvAmountCartOther;

    private OnToolbarWithBackClickListener onToolbarWithCloseClick;

    private View v;
    UiToolbarBinding binding;

    public UiToolbar(Context context) {
        super(context);
        init(context, null, -1);
    }

    public UiToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1);
    }

    @Override
    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        v = LayoutInflater.from(context).inflate(R.layout.ui_toolbar, this, false);
        addView(v);

        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        ivActionRight = findViewById(R.id.ivActionRight);
        ctSearchHome = findViewById(R.id.ctSearchHome);


        tvAmountCartHome = findViewById(R.id.tvAmountCartHome);
        tvAmountCartOther = findViewById(R.id.tvAmountCartOther);


        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.ToolbarStyleable);
        try {
            String title = t.getString(R.styleable.ToolbarStyleable_tsTitle);
            if (!TextUtils.isEmpty(title)) tvTitle.setText(title);
            boolean enableBack = t.getBoolean(R.styleable.ToolbarStyleable_tsEnableBack, true);
            ivBack.setVisibility(enableBack ? VISIBLE : GONE);
        } finally {
            t.recycle();
        }

        ivBack.setOnClickListener(v1 -> {
            onBackClickListener(v1);
        });

        ivActionRight.setOnClickListener(v2 -> {
            onActionRightClick(v2);
        });


        ctSearchHome.setOnClickListener(v3 -> {
            onSearchBarHomeListener(v3);
        });


    }

    public void setTitle(String title) {
        tvTitle.setVisibility(VISIBLE);
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
    }

    public void setSearchViewHome(boolean enable) {
        if (enable) {
            tvTitle.setVisibility(GONE);
            ctSearchHome.setVisibility(VISIBLE);
        } else {
            tvTitle.setVisibility(VISIBLE);
            ctSearchHome.setVisibility(GONE);
        }
    }


    public String getTitle() {
        return tvTitle.getText().toString();
    }

    public void setStyleTitle(int gravity) {
        tvTitle.setGravity(gravity);
    }

    public void setEnableBack(Boolean isEnableBack) {
        ivBack.setVisibility(isEnableBack ? VISIBLE : INVISIBLE);
    }

    public boolean getEnableBack() {
        return ivBack.getVisibility() == VISIBLE;
    }

    public void setActionRight(boolean enable, int icon) {
        ivActionRight.setVisibility(enable ? VISIBLE : INVISIBLE);
        if (icon != -1) {
            ivActionRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), icon, null));
        }
    }

    public void setAmountCartHome(boolean enable, int amountCart) {
        tvAmountCartHome.setVisibility(enable ? VISIBLE : INVISIBLE);
        tvAmountCartHome.setText(String.valueOf(amountCart));
    }

    public void setAmountCartOther(boolean enable, int amountCart) {
        tvAmountCartOther.setVisibility(enable ? VISIBLE : INVISIBLE);
        tvAmountCartOther.setText(String.valueOf(amountCart));
    }

    public void setActionRightBackground(boolean enable, int icon) {
        if (icon != -1) {
            ivActionRight.setBackgroundColor(0xFFFFFF);
        }
    }

    public void onBackClickListener(View v) {
        if (onToolbarWithCloseClick != null) onToolbarWithCloseClick.onToolbarBackClick();
    }

    public void onActionRightClick(View v) {
        if (onToolbarWithCloseClick != null) onToolbarWithCloseClick.onToolbarActionRightClick();
    }

    public void onSearchBarHomeListener(View v) {
        if (onToolbarWithCloseClick != null) onToolbarWithCloseClick.onToolbarSearchBarHomeClick();
    }


    public interface OnToolbarWithBackClickListener {
        void onToolbarBackClick();

        void onToolbarActionRightClick();

        void onToolbarSearchBarHomeClick();

    }

    public void setOnToolbarWithCloseClick(OnToolbarWithBackClickListener onToolbarWithCloseClick) {
        this.onToolbarWithCloseClick = onToolbarWithCloseClick;
    }
}
