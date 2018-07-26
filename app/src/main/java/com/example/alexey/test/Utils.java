package com.example.alexey.test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by alexey on 19.07.18.
 */

public class Utils {

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Скрываем клавиатуру
     *
     * @param v любая {@link View}
     */
    public static void hideKeyboard(View v) {
        if (v != null && v.getContext() != null) {
            if (!v.isFocused()) {
                v.requestFocus();
            }
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment) {
        openFragment(fragmentManager, fragment, false);
    }

    public static void openFragmentAsRoot(FragmentManager fragmentManager, Fragment fragment) {
        openFragment(fragmentManager, fragment, true);
    }

    private static void openFragment(FragmentManager fragmentManager, Fragment fragment,
                                    boolean asRoot) {
        if (asRoot) fragmentManager.popBackStack();
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(R.id.container, fragment);
        if (!asRoot) transaction.addToBackStack(null);
        transaction.commit();
    }
}
