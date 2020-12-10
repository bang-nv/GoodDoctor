package vn.haui.gooddoctor.ui.botHistory.historyService;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.HistoryService;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class HistoryServiceFrPresenter implements HistoryServiceFrMvpPresent {

    HistoryServiceFrMvpView historyServiceFrMvpView;

    public HistoryServiceFrPresenter(HistoryServiceFrMvpView historyServiceFrMvpView) {
        this.historyServiceFrMvpView = historyServiceFrMvpView;
    }

    @Override
    public void getHistoryService(String token) {
        AppNetworkUtils.getData().getHistoryService(token).enqueue(new Callback<Response<ListResponse<HistoryService>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<HistoryService>>> call, retrofit2.Response<Response<ListResponse<HistoryService>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        historyServiceFrMvpView.onDoneGetHistoryService(response.body().getData().getData());
                        Log.e("TAG", response.body().getMessage());
                    } else {
                        historyServiceFrMvpView.onErr(response.body().getMessage());
                        Log.e("Medical_err", "onResponse: " + response.body().getMessage());
                    }
                } else {
                    historyServiceFrMvpView.onErr("Không thể kết nối với Server");
                    Log.e("Medical_err", "onResponse: 1" + response.body().getMessage());
                }
            }


            @Override
            public void onFailure(Call<Response<ListResponse<HistoryService>>> call, Throwable t) {
                Log.e("TAG", "onFailure: That bai");
            }
        });
    }
}
