package vn.haui.gooddoctor.ui.auth.register;

import vn.haui.gooddoctor.models.offline.OtpRegister;
import vn.haui.gooddoctor.models.request.RegisterRequest;

public interface RegisterFrMvpPresent {


    boolean isValidateOTP(OtpRegister otpRegister);

    boolean isVerifyRegister(String Otp);

    boolean isVerifyRegisterInviteCode(String otp, String inviteCode);


    void doGetOTP(OtpRegister otpRegister);

    void doRegister(RegisterRequest registerRequest);

    void doRegisterInviteCode(RegisterRequest registerRequest);

}
