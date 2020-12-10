package vn.haui.gooddoctor.ui.botAccuPoint;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.AccumulatePoints;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class AccuPointFrPresenter implements AccuPointFrMvpPresent {

    AccuPointFrMvpView accuPointFrMvpView;

    public AccuPointFrPresenter(AccuPointFrMvpView accuPointFrMvpView) {
        this.accuPointFrMvpView = accuPointFrMvpView;
    }

    @Override
    public void getAccuPoint(String token) {
        AppNetworkUtils.getData().getAccPoint(token).enqueue(new Callback<Response<ListResponse<AccumulatePoints>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<AccumulatePoints>>> call, retrofit2.Response<Response<ListResponse<AccumulatePoints>>> response) {
                if (response.body().isSuccessNonNull()) {
                    accuPointFrMvpView.onDoneAccuPoint(response.body().getData().getData());
                } else {
//                    accuPointFrMvpView.onErr(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<AccumulatePoints>>> call, Throwable t) {

//                accuPointFrMvpView.onErr(R.string.message_request_unknow_error);
            }
        });
    }

    @Override
    public void getUserPointNow(String token) {
        AppNetworkUtils.getData().getUserPointNow(token).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                accuPointFrMvpView.onDoneUserPointNow(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
