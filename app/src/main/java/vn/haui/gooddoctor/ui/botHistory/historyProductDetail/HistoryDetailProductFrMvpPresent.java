package vn.haui.gooddoctor.ui.botHistory.historyProductDetail;

public interface HistoryDetailProductFrMvpPresent {

    void getHistoryProductDetail(String token, String codeHtr);

    void getSubHistoryProductDetail(String token, String codeHtr);
}
