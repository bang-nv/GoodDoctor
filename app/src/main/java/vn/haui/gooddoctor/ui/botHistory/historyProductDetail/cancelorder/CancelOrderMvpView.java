package vn.haui.gooddoctor.ui.botHistory.historyProductDetail.cancelorder;

public interface CancelOrderMvpView {

    void onProcess();

    void onDoneCancelOrder(String notifi);

    void onErr(String err);
}
