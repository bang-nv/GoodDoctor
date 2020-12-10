package vn.haui.gooddoctor.ui.auth.login;

import android.text.TextUtils;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.request.FcmToken;
import vn.haui.gooddoctor.models.request.LoginRequest;
import vn.haui.gooddoctor.models.response.DefaultUser;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class LoginFrPresenter implements LoginFrMvpPresent {

    LoginFrMvpView loginFrMvpView;

    public LoginFrPresenter(LoginFrMvpView loginFrMvpView) {
        this.loginFrMvpView = loginFrMvpView;
    }


    @Override
    public void doLogin(LoginRequest loginRequest) {
        loginFrMvpView.onProcess();
        if (isValidateLogin(loginRequest)) {
            AppNetworkUtils.getData().postLogin(loginRequest).enqueue(new Callback<Response<DefaultUser>>() {
                @Override
                public void onResponse(Call<Response<DefaultUser>> call, retrofit2.Response<Response<DefaultUser>> response) {
                    if (response.body().isSuccessNonNull()) {
                        loginFrMvpView.onSuccess(response.body().getData(), loginRequest.getPhone());
                    } else {
                        loginFrMvpView.onError(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Response<DefaultUser>> call, Throwable t) {
                    loginFrMvpView.onError(R.string.message_request_unknow_error);
                }
            });
        }
    }

    @Override
    public boolean isValidateLogin(LoginRequest loginRequest) {
        if (TextUtils.isEmpty(loginRequest.getPhone())) {
            loginFrMvpView.onError(R.string.msg_error_empty_phone);
            return false;
        }
        if (TextUtils.isEmpty(loginRequest.getPassword())) {
            loginFrMvpView.onError(R.string.msg_error_empty_password);
            return false;
        }
        if (loginRequest.getPhone().length() != 10) {
            loginFrMvpView.onError(R.string.msg_error_invalid_phone);
            return false;
        }
        if ("-".equals(loginRequest.getPhone().charAt(0) + "")) {
            loginFrMvpView.onError(R.string.msg_error_invalid_phone);
            return false;
        }

        return true;
    }

    @Override
    public void postUpdateTokenFcm(String apiToken, FcmToken fcm) {
        AppNetworkUtils.getData().postFcmToken(apiToken, fcm).enqueue(new Callback<Response<FcmToken>>() {
            @Override
            public void onResponse(Call<Response<FcmToken>> call, retrofit2.Response<Response<FcmToken>> response) {
                Log.e("postUpdateTokenFcm", "onResponse: " + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<Response<FcmToken>> call, Throwable t) {
                Log.e("postUpdateTokenFcm", "onFailure: " + t.getMessage());
            }
        });
    }
}
