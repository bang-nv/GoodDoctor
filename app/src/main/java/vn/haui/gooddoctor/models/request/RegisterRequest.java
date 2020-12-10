package vn.haui.gooddoctor.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("name")
    @Expose
    private String name;
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
    @SerializedName("invite_code")
    @Expose
    private String inviteCode;

    //For Register
    public RegisterRequest(String name, String phone, String password, String passwordConfirm,
                           String token, String otp) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.token = token;
        this.otp = otp;
    }

    //For Register with inviteCode
    public RegisterRequest(String name, String phone, String password, String passwordConfirm, String token, String otp, String inviteCode) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.token = token;
        this.otp = otp;
        this.inviteCode = inviteCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
