package vn.haui.gooddoctor.ui.auth.login;

import vn.haui.gooddoctor.models.request.FcmToken;
import vn.haui.gooddoctor.models.request.LoginRequest;

public interface LoginFrMvpPresent {

    void doLogin(LoginRequest loginRequest);

    boolean isValidateLogin(LoginRequest loginRequest);

    void postUpdateTokenFcm(String apiToken, FcmToken fcm);

}
