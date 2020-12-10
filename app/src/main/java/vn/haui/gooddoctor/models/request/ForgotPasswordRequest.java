package vn.haui.gooddoctor.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordRequest {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password_confirm")
    @Expose
    private String passwordConfirm;
    @SerializedName("verification_token")
    @Expose
    private String token;
    @SerializedName("verification_code")
    @Expose
    private String otp;

    public ForgotPasswordRequest(String phone, String password, String passwordConfirm, String token, String otp) {
        this.phone = phone;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.token = token;
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
