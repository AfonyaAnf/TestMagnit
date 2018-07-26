package com.example.alexey.test.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alexey.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexey on 22.06.18.
 */

public class CustomTextView extends RelativeLayout {

    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.text)
    TextView textView;

    private String name;

    private String data;

    public CustomTextView(Context context) {
        super(context);
        init();
        update();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray attr = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        CharSequence text = attr.getText(R.styleable.CustomTextView_nameField);
        if (text != null) name = text.toString();
        attr.recycle();

        update();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_custom_text_view, this, true);
        ButterKnife.bind(this);
    }

    private void update() {
        if (TextUtils.isEmpty(data)) {
            textView.setText(name);
            titleView.setVisibility(INVISIBLE);
        } else {
            textView.setText(data);
            titleView.setText(name);
            titleView.setVisibility(VISIBLE);
        }
    }

    public void setData(String data) {
        this.data = data;
        update();
    }

    public String getText() {
        return data;
    }

}
