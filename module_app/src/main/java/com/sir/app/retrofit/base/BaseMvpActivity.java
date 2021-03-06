package com.sir.app.retrofit.base;

import android.view.View;

import com.sir.app.retrofit.model.ContractProxy;
import com.space.app.base.BaseActivity;

/**
 * MVP 模式的 activity_skin
 * Created by zhuyinan on 2017/4/5.
 */

public abstract class BaseMvpActivity<M extends BaseModel, P extends BasePresenter> extends BaseActivity {

    protected abstract BaseView getViewImp();

    public abstract void initMvpView(final View view);

    //  定义Presenter
    protected P mPresenter;

    @Override
    public void initView(View view) {
        initMvpView(view);
        bindMVP();
    }

    //获取presenter 实例
    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            mPresenter.mContext = this;
            bindVM();
        }
    }

    //结合Model and View
    private void bindVM() {
        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getViewImp() != null) {
            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
            ContractProxy.getInstance().bindView(getViewImp(), mPresenter);
            mPresenter.mContext = this;
        }
    }

    //   获得抽取接口Presenter对象
    protected Class getPresenterClazz() {
        return ContractProxy.getPresnterClazz(getClass(), 1);
    }

    //   获得抽取接口Model对象
    protected Class getModelClazz() {
        return ContractProxy.getModelClazz(getClass(), 0);
    }

    //  获得实现接口Presenter对象
    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            ContractProxy.getInstance().unbindView(getViewImp(), mPresenter);
            ContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
            mPresenter = null;
        }
    }
}
