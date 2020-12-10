package vn.haui.gooddoctor.ui.botHistory.historyProductDetail.cancelorder;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class CancelOrderPresenter implements CancelOrderMvpPresent {

    CancelOrderMvpView cancelOrderMvpView;

    public CancelOrderPresenter(CancelOrderMvpView cancelOrderMvpView) {
        this.cancelOrderMvpView = cancelOrderMvpView;
    }

    @Override
    public void getCancelOrder(String auth, String code) {
        AppNetworkUtils.getData().getCancelOder(auth, code).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                cancelOrderMvpView.onDoneCancelOrder(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}
