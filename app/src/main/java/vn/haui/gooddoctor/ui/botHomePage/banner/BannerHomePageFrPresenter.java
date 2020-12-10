package vn.haui.gooddoctor.ui.botHomePage.banner;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.Banner;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class BannerHomePageFrPresenter implements BannerHomePageFrMvpPresent {

    BannerHomePageFrMvpView bannerHomePageFrMvpView;

    public BannerHomePageFrPresenter(BannerHomePageFrMvpView bannerHomePageFrMvpView) {
        this.bannerHomePageFrMvpView = bannerHomePageFrMvpView;
    }


    @Override
    public void getListBanner() {
        AppNetworkUtils.getData().getBaner().enqueue(new Callback<Response<ListResponse<Banner>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<Banner>>> call, retrofit2.Response<Response<ListResponse<Banner>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        bannerHomePageFrMvpView.onDoneBannerHomePage(response.body().getData().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<Banner>>> call, Throwable t) {
            }
        });
    }
}
