package vn.haui.gooddoctor.models.offline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpForgotPassword {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password_confirm")
    @Expose
    private String passwordConfirm;

    public OtpForgotPassword(String phone, String password, String passwordConfirm) {
        this.phone = phone;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
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
}
