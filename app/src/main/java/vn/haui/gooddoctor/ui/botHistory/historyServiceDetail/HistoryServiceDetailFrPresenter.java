package vn.haui.gooddoctor.ui.botHistory.historyServiceDetail;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.HistoryServiceDetail;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class HistoryServiceDetailFrPresenter implements HistoryServiceDetailFrMvpPresent {

    HistoryServiceDetailFrMvpView historyServiceDetailFrMvpView;

    public HistoryServiceDetailFrPresenter(HistoryServiceDetailFrMvpView historyServiceDetailFrMvpView) {
        this.historyServiceDetailFrMvpView = historyServiceDetailFrMvpView;
    }

    @Override
    public void getHistoryServiceDetail(String token, String codeHtr) {
        AppNetworkUtils.getData().getHistoryServiceDetail(token, codeHtr).enqueue(new Callback<Response<HistoryServiceDetail>>() {
            @Override
            public void onResponse(Call<Response<HistoryServiceDetail>> call, retrofit2.Response<Response<HistoryServiceDetail>> response) {
                if (response.body().getStatus() == 1) {
                    historyServiceDetailFrMvpView.onDoneHistoryDetailService(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Response<HistoryServiceDetail>> call, Throwable t) {

            }
        });
    }
}
