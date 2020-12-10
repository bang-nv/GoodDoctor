package vn.haui.gooddoctor.ui.auth.register;

import android.text.TextUtils;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.offline.OtpRegister;
import vn.haui.gooddoctor.models.request.RegisterRequest;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class RegisterFrPresenter implements RegisterFrMvpPresent {

    RegisterFrMvpView registerFrMvpView;

    public RegisterFrPresenter(RegisterFrMvpView registerFrMvpView) {
        this.registerFrMvpView = registerFrMvpView;
    }

    @Override
    public void doGetOTP(OtpRegister otpRegister) {
        registerFrMvpView.onProcess();
        if (isValidateOTP(otpRegister)) {
            AppNetworkUtils.getData().getVerifyCode(otpRegister.getPhone()).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.body().isSuccessNonNull()) {
                        registerFrMvpView.onSuccessOtp(response.body());
                    } else {
                        registerFrMvpView.onError(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    registerFrMvpView.onError(R.string.message_request_unknow_error);
                }
            });
        }
    }

    @Override
    public void doRegister(RegisterRequest registerRequest) {
        registerFrMvpView.onProcess();
        if (
                isValidateOTP(new OtpRegister(
                        registerRequest.getName(),
                        registerRequest.getPhone(),
                        registerRequest.getPassword(),
                        registerRequest.getPasswordConfirm())
                ) && isVerifyRegister(registerRequest.getOtp())
        )
            AppNetworkUtils.getData().postRegister(registerRequest).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            registerFrMvpView.onSuccessLogin(response.body());
                        } else {
                            registerFrMvpView.onError(response.body().getMessage());
                        }

                    } else {
                        registerFrMvpView.onError("Không thể kết nối với Server");
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });
    }

    @Override
    public void doRegisterInviteCode(RegisterRequest registerRequest) {
        registerFrMvpView.onProcess();
        if (
                isValidateOTP(new OtpRegister(
                        registerRequest.getName(),
                        registerRequest.getPhone(),
                        registerRequest.getPassword(),
                        registerRequest.getPasswordConfirm())
                ) && isVerifyRegisterInviteCode(
                        registerRequest.getOtp(),
                        registerRequest.getInviteCode()
                )
        ) {
            AppNetworkUtils.getData().postRegister(registerRequest).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            registerFrMvpView.onSuccessLogin(response.body());
                        } else {
                            registerFrMvpView.onError(response.body().getMessage());
                        }

                    } else {
                        registerFrMvpView.onError("Không thể kết nối với Server");
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });
        }
    }


    @Override
    public boolean isValidateOTP(OtpRegister otpRegister) {
        if (TextUtils.isEmpty(otpRegister.getName())) {
            registerFrMvpView.onError(R.string.msg_error_empty_name);
            return false;
        }
        if (TextUtils.isEmpty(otpRegister.getPhone())) {
            registerFrMvpView.onError(R.string.msg_error_empty_phone);
            return false;
        }
        if (TextUtils.isEmpty(otpRegister.getPassword())) {
            registerFrMvpView.onError(R.string.msg_error_empty_password);
            return false;
        }
        if (TextUtils.isEmpty(otpRegister.getPasswordConfirm())) {
            registerFrMvpView.onError(R.string.msg_error_empty_repassword);
            return false;
        }
        if (otpRegister.getPhone().length() != 10) {
            registerFrMvpView.onError(R.string.msg_error_invalid_phone);
            return false;
        }
        if (!otpRegister.getPassword().equals(otpRegister.getPasswordConfirm())) {
            registerFrMvpView.onError(R.string.msg_error_invalid_repassword);
            return false;
        }
        return true;
    }

    @Override
    public boolean isVerifyRegister(String Otp) {

        if (TextUtils.isEmpty(String.valueOf(Otp))) {
            registerFrMvpView.onError(R.string.msg_error_empty_otp);
            return false;
        }
        return true;

    }

    @Override
    public boolean isVerifyRegisterInviteCode(String otp, String inviteCode) {

        if (TextUtils.isEmpty(String.valueOf(otp))) {
            registerFrMvpView.onError(R.string.msg_error_empty_otp);
            return false;
        }
        if (inviteCode.length() != 10) {
            registerFrMvpView.onError(R.string.msg_error_invalid_refcode);
            return false;
        }

        return true;

    }
}
