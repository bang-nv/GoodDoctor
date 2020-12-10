package vn.haui.gooddoctor.models.offline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpRegister {

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

    public OtpRegister(String name, String phone, String password, String passwordConfirm) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
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
}
