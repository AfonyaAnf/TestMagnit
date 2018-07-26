package com.example.alexey.test.ui.view;

import android.content.Context;
import android.support.v7.widget.SearchView;

/**
 * Created by alexey on 26.07.18.
 */

public class CustomSearchView extends SearchView {
    public CustomSearchView(Context context) {
        super(context);
        setIconifiedByDefault(true);
        setFocusable(true);
        setIconified(false);
        requestFocusFromTouch();
        setOnCloseListener(() -> {
            clearFocus();
            return true;
        });
    }
}
