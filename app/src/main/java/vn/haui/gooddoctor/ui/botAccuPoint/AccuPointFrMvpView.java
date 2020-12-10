package vn.haui.gooddoctor.ui.botAccuPoint;

import androidx.annotation.StringRes;

import java.util.List;

import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.AccumulatePoints;

public interface AccuPointFrMvpView {

    void onProcess();

    void onDoneAccuPoint(List<AccumulatePoints> pointsList);

    void onDoneUserPointNow(Response response);

    void onErr(String err);

    void onErr(@StringRes int resId);
}
