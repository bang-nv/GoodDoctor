package vn.haui.gooddoctor.ui.botHistory.historyServiceDetail.cancelservice;

public interface CancelBookingMvpView {
    void onProcess();

    void onDoneCancelBooking(String notifi);

    void onErr(String err);
}
