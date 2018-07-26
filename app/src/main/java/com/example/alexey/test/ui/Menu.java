package com.example.alexey.test.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.example.alexey.test.R;
import com.example.alexey.test.Utils;
import com.example.alexey.test.ui.fragment.AboutFragment;
import com.example.alexey.test.ui.fragment.TimetableFragment;

/**
 * Created by alexey on 26.07.18.
 */

public enum Menu {
    TIMETABLE(R.id.timetable, R.string.timetable, new TimetableFragment()),
    ABOUT_APP(R.id.about_app, R.string.about_app, new AboutFragment());

    private final int itemId;
    private final int titleNameRes;
    private final Fragment fragment;

    Menu(int itemId, int titleNameRes, Fragment fragment) {
        this.itemId = itemId;
        this.titleNameRes = titleNameRes;
        this.fragment = fragment;
    }

    public void openFragment(FragmentManager fragmentManager) {
        Utils.openFragmentAsRoot(fragmentManager, fragment);
    }

    public int getTitleRes() {
        return titleNameRes;
    }

    public static Menu getByItemId(int itemId) {
        for (int i = 0; i < values().length; i++) {
            if (values()[i].itemId == itemId)
                return values()[i];
        }
        return values()[0];
    }

    public static Menu openStartMenuItem(FragmentManager fragmentManager) {
        Menu menu = values()[0];
        menu.openFragment(fragmentManager);
        return menu;
    }

}
