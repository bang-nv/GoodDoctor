package vn.haui.gooddoctor.ui.botProfile;

import vn.haui.gooddoctor.models.response.Profile;

public interface ProfileFrMvpView {
    void onProcess();

    void onSuccess(Profile profile);

    void onDoneSignOut(String ntf);

    void onErrorSignOut(String ntf);

    void onError(String err);

}
