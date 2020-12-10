package vn.haui.gooddoctor.ui.botHistory.historyProduct;

import java.util.List;

import vn.haui.gooddoctor.models.response.HistoryProduct;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;

public interface HistoryProductFrMvpView {
    void onProcess();

    void onDoneGetHistoryProduct(List<HistoryProduct> historyProducts);

    void onDoneFirstItemHistoryProduct(List<SubHistoryProductDetail> subHistoryProductDetail);

    void onErr(String err);

    void onClick(int position, String code);
}
