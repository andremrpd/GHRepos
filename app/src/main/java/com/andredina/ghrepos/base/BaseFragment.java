package com.andredina.ghrepos.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.andredina.ghrepos.di.component.ActivityComponent;

/**
 * Created by André Dina on 02/09/2017.
 */

public abstract class BaseFragment extends Fragment implements BaseView{

    private BaseActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.baseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    public ActivityComponent getActivityComponent() {
        if (baseActivity != null) {
            return baseActivity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }


}
