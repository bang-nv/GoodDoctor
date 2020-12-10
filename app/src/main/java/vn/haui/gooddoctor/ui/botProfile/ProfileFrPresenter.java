package vn.haui.gooddoctor.ui.botProfile;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.Profile;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class ProfileFrPresenter implements ProfileFrMvpPresent {

    ProfileFrMvpView profileFrMvpView;

    public ProfileFrPresenter(ProfileFrMvpView profileFrMvpView) {
        this.profileFrMvpView = profileFrMvpView;
    }

    @Override
    public void getProfile(String token) {
        AppNetworkUtils.getData().getProfile(token).enqueue(new Callback<Response<Profile>>() {
            @Override
            public void onResponse(Call<Response<Profile>> call, retrofit2.Response<Response<Profile>> response) {
                profileFrMvpView.onProcess();
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        profileFrMvpView.onSuccess(response.body().getData());
                    } else {
                        profileFrMvpView.onError(response.body().getMessage());
                    }
                } else {
                    profileFrMvpView.onError("Không thể kết nối với Server");

                }

            }

            @Override
            public void onFailure(Call<Response<Profile>> call, Throwable t) {
                profileFrMvpView.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getSignOut(String token) {
        AppNetworkUtils.getData().getSignOut(token).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body().isSuccessNonNull()) {
                    profileFrMvpView.onDoneSignOut(response.body().getMessage());
                } else {
                    profileFrMvpView.onErrorSignOut(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                profileFrMvpView.onErrorSignOut("Đã xảy ra lỗi không xác định. Vui lòng kiểm tra kết nối mạng và thử lại!");
            }
        });
    }


}
