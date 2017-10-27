package com.vertaperic.store.mvp;

import android.content.Context;

import com.vertaperic.store.app.NavigationDrawerFragment;

import javax.inject.Inject;

/**
 * Base fragment with Model-View-Presenter support.
 *
 * @author Anny Patel
 */
public class BaseFragment<P extends IPresenter> extends NavigationDrawerFragment implements IView {

    /**
     * The presenter attached with this view.
     */
    @Inject
    P presenter;

    /**
     * @return The presenter attached with this view.
     */
    public final P presenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter().bind(this);
    }

    @Override
    public void onDetach() {
        presenter().unbind();
        super.onDetach();
    }
}
