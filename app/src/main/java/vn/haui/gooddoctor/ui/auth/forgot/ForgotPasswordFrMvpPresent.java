package vn.haui.gooddoctor.ui.auth.forgot;

import vn.haui.gooddoctor.models.offline.OtpForgotPassword;
import vn.haui.gooddoctor.models.request.ForgotPasswordRequest;

public interface ForgotPasswordFrMvpPresent {

    boolean isValidateOTP(OtpForgotPassword otpForgotPassword);

    boolean isVerifyForgotPassword(String otp);

    void doGetOTP(OtpForgotPassword otpForgotPassword);

    void doForgotPassword(ForgotPasswordRequest forgotPasswordRequest);

}
