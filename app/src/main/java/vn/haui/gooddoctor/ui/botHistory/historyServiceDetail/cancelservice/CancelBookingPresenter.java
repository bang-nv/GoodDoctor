package vn.haui.gooddoctor.ui.botHistory.historyServiceDetail.cancelservice;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class CancelBookingPresenter implements CancelBookingMvpPresent {

    CancelBookingMvpView cancelBookingMvpView;

    public CancelBookingPresenter(CancelBookingMvpView cancelBookingMvpView) {
        this.cancelBookingMvpView = cancelBookingMvpView;
    }


    @Override
    public void getCancelBooking(String auth, String code) {
        AppNetworkUtils.getData().getCancelBooking(auth, code).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body().getStatus() == 1) {
                    cancelBookingMvpView.onDoneCancelBooking(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}

