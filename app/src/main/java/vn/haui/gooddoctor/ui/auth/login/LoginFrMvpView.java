package vn.haui.gooddoctor.ui.auth.login;


import androidx.annotation.StringRes;

import vn.haui.gooddoctor.models.response.DefaultUser;

public interface LoginFrMvpView {

    void showLoading();

    void hideLoading();

    void onProcess();

    void onSuccess(DefaultUser user, String phoneLogged);

    void onError(String err);

    void onError(@StringRes int resId);


}
