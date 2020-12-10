package vn.haui.gooddoctor.ui.botHistory.historyProduct;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.HistoryProduct;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class HistoryProductFrPresenter implements HistoryProductFrMvpPresent {


    HistoryProductFrMvpView historyProductFrMvpView;

    public HistoryProductFrPresenter(HistoryProductFrMvpView historyProductFrMvpView) {
        this.historyProductFrMvpView = historyProductFrMvpView;
    }

    @Override
    public void getHistoryProduct(String token) {
        AppNetworkUtils.getData().getHistoryProduct(token).enqueue(new Callback<Response<ListResponse<HistoryProduct>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<HistoryProduct>>> call, retrofit2.Response<Response<ListResponse<HistoryProduct>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        historyProductFrMvpView.onDoneGetHistoryProduct(response.body().getData().getData());
                        Log.e("getHistoryProduct", "ok: " + response.body().getMessage());
                    } else {
                        historyProductFrMvpView.onErr(response.body().getMessage());
                        Log.e("getHistoryProduct", "err: " + response.body().getMessage());
                    }
                } else {
                    historyProductFrMvpView.onErr("Không thể kết nối với Server");
                    Log.e("getHistoryProduct", "err 1: " + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<HistoryProduct>>> call, Throwable t) {
                historyProductFrMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getFirstItemHistoryProduct(String token, String codeHtr) {
//        appApi.getFirstSubHistoryProductDetail(token, codeHtr).enqueue(new Callback<Response<HistoryProductDetail<SubHistoryProductDetail>>>() {
//            @Override
//            public void onResponse(Call<Response<HistoryProductDetail<SubHistoryProductDetail>>> call, retrofit2.Response<Response<HistoryProductDetail<SubHistoryProductDetail>>> response) {
//                if (response.body() != null) {
//                    if (response.body().getStatus() == 1) {
//                        historyProductFrMvpView.onDoneFirstItemHistoryProduct(response.body().getData().getData());
//                        Log.e("TAG", response.body().getMessage());
//                    } else {
//                        historyProductFrMvpView.onErr(response.body().getMessage());
//                        Log.e("Medical_err", "onResponse: " + response.body().getMessage());
//                    }
//                } else {
//                    historyProductFrMvpView.onErr("Không thể kết nối với Server");
//                    Log.e("Medical_err", "onResponse: 1" + response.body().getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response<HistoryProductDetail<SubHistoryProductDetail>>> call, Throwable t) {
//
//            }
//        });
    }


}
