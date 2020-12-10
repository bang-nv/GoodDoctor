package vn.haui.gooddoctor.ui.auth.register;


import androidx.annotation.StringRes;

import vn.haui.gooddoctor.models.Response;

public interface RegisterFrMvpView {
    void showLoading();

    void hideLoading();

    void onProcess();

    void onSuccessOtp(Response response);

    void onSuccessLogin(Response response);

    void onError(String err);

    void onError(@StringRes int resId);
}
