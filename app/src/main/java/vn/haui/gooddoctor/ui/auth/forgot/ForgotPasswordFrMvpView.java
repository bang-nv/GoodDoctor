package vn.haui.gooddoctor.ui.auth.forgot;


import androidx.annotation.StringRes;

import vn.haui.gooddoctor.models.Response;

public interface ForgotPasswordFrMvpView {
    void showLoading();

    void hideLoading();

    void onProcess();

    void onSuccessVerifiCode(Response response);

    void onSuccessForgotPassword(Response response);

    void onError(String err);

    void onError(@StringRes int resId);
}
