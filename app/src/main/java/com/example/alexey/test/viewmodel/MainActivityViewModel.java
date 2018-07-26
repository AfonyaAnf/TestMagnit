package com.example.alexey.test.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentManager;

import com.example.alexey.test.ui.Menu;

/**
 * Created by alexey on 26.07.18.
 */

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Boolean> drawerCloseLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> titleLiveData = new MutableLiveData<>();

    private Menu currentMenuItem;

    public MutableLiveData<Integer> getTitleLiveData() {
        return titleLiveData;
    }

    public MutableLiveData<Boolean> getDrawerCloseLiveData() {
        return drawerCloseLiveData;
    }


    /**
     * Если пункт меню не выбран (первый запуск), то запускаем стартовый фрагмент,
     * иначе ничего не трогаем
     */
    public void init(FragmentManager fragmentManager) {
        if (currentMenuItem == null) {
            currentMenuItem = Menu.openStartMenuItem(fragmentManager);
            currentMenuItem.openFragment(fragmentManager);
            updateUI();
        }
    }

    /**
     * Изменение пункта меню по resource id в drawer'е
     */
    public void setMenuById(FragmentManager fragmentManager, int id) {
        currentMenuItem = Menu.getByItemId(id);
        currentMenuItem.openFragment(fragmentManager);
        updateUI();
    }

    private void updateUI() {
        drawerCloseLiveData.setValue(true);
        titleLiveData.setValue(currentMenuItem.getTitleRes());
    }
}
