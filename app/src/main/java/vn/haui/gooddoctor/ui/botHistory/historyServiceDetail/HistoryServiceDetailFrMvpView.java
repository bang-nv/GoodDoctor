package vn.haui.gooddoctor.ui.botHistory.historyServiceDetail;

import vn.haui.gooddoctor.models.response.HistoryServiceDetail;

public interface HistoryServiceDetailFrMvpView {

    void onProcess();

    void onDoneHistoryDetailService(HistoryServiceDetail historyServiceDetails);

    void onErr(String err);
}
