package vn.haui.gooddoctor.ui.botAccuPoint;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

import vn.haui.gooddoctor.databinding.FragmentAccumulatePointBinding;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.AccumulatePoints;
import vn.haui.gooddoctor.utils.CommonUtils;

import static android.content.Context.MODE_PRIVATE;

public class AccuPointFragment extends Fragment implements AccuPointFrMvpView {
    public static final String TAG = AccuPointFragment.class.getCanonicalName();

    FragmentAccumulatePointBinding binding;
    AccuPointFrPresenter accuPointFrPresenter;
    private String token;

    public static AccuPointFragment newInstance() {
        AccuPointFragment fragment = new AccuPointFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccumulatePointBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        token = prefs.getString("token", "No token");

        accuPointFrPresenter = new AccuPointFrPresenter(this);
        accuPointFrPresenter.getAccuPoint(token);
        accuPointFrPresenter.getUserPointNow(token);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onProcess() {

    }

    @Override
    public void onDoneAccuPoint(List<AccumulatePoints> pointsList) {

        AccuPointAdapter accuPointAdapter = new AccuPointAdapter(getContext(), pointsList, this);
        binding.rcHisAccuPoint.setLayoutManager(new GridLayoutManager(getContext(), 1));
        binding.rcHisAccuPoint.setAdapter(accuPointAdapter);

    }

    @Override
    public void onDoneUserPointNow(Response response) {

        if (response.getData() != null) {
            binding.tvTotalPoint.setText(String.format("%s điểm", CommonUtils.parseThousands(response.getData().toString())));
        }
    }

    @Override
    public void onErr(String err) {

    }

    @Override
    public void onErr(int resId) {

    }
}
