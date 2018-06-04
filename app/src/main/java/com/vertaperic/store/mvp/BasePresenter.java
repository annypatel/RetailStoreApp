package com.vertaperic.store.mvp;

/**
 * The base presenter with some lifecycle events.
 *
 * @author Anny Patel
 */
public class BasePresenter<V extends IView> implements IPresenter {

    /**
     * The view attached with this presenter.
     */
    private V view;

    /**
     * @return The view attached with this presenter.
     */
    public final V view() {
        return view;
    }

    @Override
    public final void bind(IView view) {
        //noinspection unchecked
        this.view = (V) view;
        onViewAttached();
    }

    @Override
    public final void unbind() {
        onViewDetached();
        this.view = null;
    }

    /**
     * Called when view is attached to presenter.
     */
    @SuppressWarnings("WeakerAccess")
    protected void onViewAttached() {
    }

    /**
     * Called when view is detached from presenter.
     */
    protected void onViewDetached() {
    }

    /**
     * @return true if view is attached to presenter otherwise false.
     */
    protected final boolean isAttached() {
        return view() != null;
    }
}
