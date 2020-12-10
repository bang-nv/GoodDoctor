package vn.haui.gooddoctor.ui.botHistory.historyProductDetail;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.HistoryProductDetail;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class HistoryDetailProductFrPresenter implements HistoryDetailProductFrMvpPresent {

    HistoryDetailProductFrMvpView historyDetailProductFrMvpView;

    public HistoryDetailProductFrPresenter(HistoryDetailProductFrMvpView historyDetailProductFrMvpView) {
        this.historyDetailProductFrMvpView = historyDetailProductFrMvpView;
    }

    @Override
    public void getHistoryProductDetail(String token, String codeHtr) {
        AppNetworkUtils.getData().getHistoryProductDetail(token, codeHtr).enqueue(new Callback<Response<HistoryProductDetail>>() {
            @Override
            public void onResponse(Call<Response<HistoryProductDetail>> call, retrofit2.Response<Response<HistoryProductDetail>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        historyDetailProductFrMvpView.onDoneHistoryProductDetail(response.body().getData());
                        Log.e("TAG", response.body().getMessage());
                    } else {
                        historyDetailProductFrMvpView.onErr(response.body().getMessage());
                        Log.e("Medical_err", "onResponse: " + response.body().getMessage());
                    }
                } else {
                    historyDetailProductFrMvpView.onErr("Không thể kết nối với Server");
                    Log.e("Medical_err", "onResponse: 1" + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response<HistoryProductDetail>> call, Throwable t) {
                historyDetailProductFrMvpView.onErr(t.getMessage());
            }
        });
    }


    @Override
    public void getSubHistoryProductDetail(String token, String codeHtr) {
        AppNetworkUtils.getData().getSubHistoryProductDetail(token, codeHtr).enqueue(new Callback<Response<HistoryProductDetail<SubHistoryProductDetail>>>() {
            @Override
            public void onResponse(Call<Response<HistoryProductDetail<SubHistoryProductDetail>>> call, retrofit2.Response<Response<HistoryProductDetail<SubHistoryProductDetail>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        historyDetailProductFrMvpView.onDoneSubHistoryProductDetail(response.body().getData().getData());
                        Log.e("SubHistoryProductDetail", response.body().getMessage());
                    } else {
                        historyDetailProductFrMvpView.onErr(response.body().getMessage());
                        Log.e("SubHistoryProductDetail", "onResponse: " + response.body().getMessage());
                    }
                } else {
                    historyDetailProductFrMvpView.onErr("Không thể kết nối với Server");
                    Log.e("SubHistoryProductDetail", "onResponse: 1" + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response<HistoryProductDetail<SubHistoryProductDetail>>> call, Throwable t) {

            }
        });
    }

//    @Override
//    public void getHistoryServiceDetail(String token, String codeHtr) {
//        appApi.getHistoryServiceDetail(token,codeHtr).enqueue(new Callback<Response<HistoryServiceDetail>>() {
//            @Override
//            public void onResponse(Call<Response<HistoryServiceDetail>> call, retrofit2.Response<Response<HistoryServiceDetail>> response) {
//                if (response.body().getStatus() == 1) {
//                    historyServiceDetailView.onDoneHistoryDetailService(response.body().getData());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response<HistoryServiceDetail>> call, Throwable t) {
//
//            }
//        });
//    }
}
