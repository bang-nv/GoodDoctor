package vn.haui.gooddoctor.ui.botHistory.historyService;

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

import vn.haui.gooddoctor.databinding.FragmentHistoryServiceBinding;
import vn.haui.gooddoctor.models.response.HistoryService;
import vn.haui.gooddoctor.utils.CommonUtils;

import static android.content.Context.MODE_PRIVATE;

public class HistoryServiceFragment extends Fragment implements HistoryServiceFrMvpView {
    public static final String TAG = HistoryServiceFragment.class.getCanonicalName();

    FragmentHistoryServiceBinding binding;

    HistoryServiceFrPresenter presenter;

    String token;

    public static HistoryServiceFragment newInstance() {
        HistoryServiceFragment fragment = new HistoryServiceFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryServiceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        token = prefs.getString("token", "No token");


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new HistoryServiceFrPresenter(this);
        presenter.getHistoryService(token);
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
    public void onDoneGetHistoryService(List<HistoryService> historyServices) {
        HistoryServiceAdapter historyServiceAdapter = new HistoryServiceAdapter(historyServices, getContext(), this);
        binding.rcHistoryService.setLayoutManager(new GridLayoutManager(getContext(), 1));
        binding.rcHistoryService.setAdapter(historyServiceAdapter);
    }

    @Override
    public void onErr(String err) {

    }

    @Override
    public void onClick(int position, List<HistoryService> historyServices) {
        //TODO
//        String codePrd = historyServices.get(position).getCode();
//        startActivity(BackableActivity.newInstanceHistoryServiceDetail(getContext(),BackableActivity.NAVIGATOR_FHTRDT, codePrd));

        CommonUtils.shortToast(getContext(), "Not Full Version!");
    }
}
