package vn.haui.gooddoctor.ui.botHistory.historyService;

import java.util.List;

import vn.haui.gooddoctor.models.response.HistoryService;

public interface HistoryServiceFrMvpView {

    void onProcess();

    void onDoneGetHistoryService(List<HistoryService> historyServices);

    void onErr(String err);

    void onClick(int position, List<HistoryService> historyServices);
}
