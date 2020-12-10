package vn.haui.gooddoctor.ui.auth.forgot;

import android.text.TextUtils;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.offline.OtpForgotPassword;
import vn.haui.gooddoctor.models.request.ForgotPasswordRequest;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class ForgotPasswordFrPresenter implements ForgotPasswordFrMvpPresent {

    ForgotPasswordFrMvpView forgotPasswordFrMvpView;

    public ForgotPasswordFrPresenter(ForgotPasswordFrMvpView forgotPasswordFrMvpView) {
        this.forgotPasswordFrMvpView = forgotPasswordFrMvpView;
    }

    @Override
    public void doGetOTP(OtpForgotPassword otpForgotPassword) {
        forgotPasswordFrMvpView.onProcess();
        if (isValidateOTP(otpForgotPassword)) {
            AppNetworkUtils.getData().getVerifyCode(otpForgotPassword.getPhone()).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.body().isSuccessNonNull()) {
                        forgotPasswordFrMvpView.onSuccessVerifiCode(response.body());
                    } else {
                        forgotPasswordFrMvpView.onError(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    forgotPasswordFrMvpView.onError(R.string.message_request_unknow_error);
                }
            });
        }

    }

    @Override
    public void doForgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        forgotPasswordFrMvpView.onProcess();
        if (
                isValidateOTP(
                        new OtpForgotPassword(
                                forgotPasswordRequest.getPhone(),
                                forgotPasswordRequest.getPassword(),
                                forgotPasswordRequest.getPasswordConfirm()
                        )
                ) && isVerifyForgotPassword(forgotPasswordRequest.getOtp())
        ) {

            AppNetworkUtils.getData().postForgotPassword(forgotPasswordRequest).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.body().isSuccessNonNull()) {
                        forgotPasswordFrMvpView.onSuccessForgotPassword(response.body());
                    } else {
                        forgotPasswordFrMvpView.onError(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    forgotPasswordFrMvpView.onError(R.string.message_request_unknow_error);
                }
            });
        }
    }


    @Override
    public boolean isValidateOTP(OtpForgotPassword otpForgotPassword) {

        if (TextUtils.isEmpty(otpForgotPassword.getPhone())) {
            forgotPasswordFrMvpView.onError(R.string.msg_error_empty_phone);
            return false;
        }
        if (TextUtils.isEmpty(otpForgotPassword.getPassword())) {
            forgotPasswordFrMvpView.onError(R.string.msg_error_empty_password);
            return false;
        }
        if (TextUtils.isEmpty(otpForgotPassword.getPasswordConfirm())) {
            forgotPasswordFrMvpView.onError(R.string.msg_error_empty_repassword);
            return false;
        }
        if (otpForgotPassword.getPhone().length() != 10) {
            forgotPasswordFrMvpView.onError(R.string.msg_error_invalid_phone);
            return false;
        }
        if (!otpForgotPassword.getPassword().equals(otpForgotPassword.getPasswordConfirm())) {
            forgotPasswordFrMvpView.onError(R.string.msg_error_invalid_repassword);
            return false;
        }

        return true;
    }

    @Override
    public boolean isVerifyForgotPassword(String otp) {

        if (TextUtils.isEmpty(String.valueOf(otp))) {
            forgotPasswordFrMvpView.onError(R.string.msg_error_empty_otp);
            return false;
        }

        return true;
    }
}
