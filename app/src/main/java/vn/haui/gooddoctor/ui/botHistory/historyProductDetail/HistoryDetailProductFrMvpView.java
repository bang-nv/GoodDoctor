package vn.haui.gooddoctor.ui.botHistory.historyProductDetail;

import java.util.List;

import vn.haui.gooddoctor.models.response.HistoryProductDetail;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;

public interface HistoryDetailProductFrMvpView {

    void onProcess();

    void onDoneHistoryProductDetail(HistoryProductDetail historyProductDetail);

    void onDoneSubHistoryProductDetail(List<SubHistoryProductDetail> subHistoryProductDetails);

    void onErr(String err);

}
