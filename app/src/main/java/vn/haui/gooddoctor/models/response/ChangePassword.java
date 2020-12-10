package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassword {
    @SerializedName("password_old")
    @Expose
    private String passwordOld;
    @SerializedName("password_new")
    @Expose
    private String passwordNew;
    @SerializedName("password_res")
    @Expose
    private String passwordRes;

    public ChangePassword(String passwordOld, String passwordNew, String passwordRes) {
        this.passwordOld = passwordOld;
        this.passwordNew = passwordNew;
        this.passwordRes = passwordRes;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getPasswordRes() {
        return passwordRes;
    }

    public void setPasswordRes(String passwordRes) {
        this.passwordRes = passwordRes;
    }
}
