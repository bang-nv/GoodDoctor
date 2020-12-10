package vn.haui.gooddoctor.ui.botHistory.historyProduct;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import vn.haui.gooddoctor.databinding.FragmentHistoryProductBinding;
import vn.haui.gooddoctor.models.response.HistoryProduct;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;

import static android.content.Context.MODE_PRIVATE;

public class HistoryProductFragment extends Fragment implements HistoryProductFrMvpView {
    public static final String TAG = HistoryProductFragment.class.getCanonicalName();

    FragmentHistoryProductBinding binding;
    HistoryProductFrPresenter presenter;

    HistoryProductAdapter historyProductAdapter;
    List<HistoryProduct> historyProductsDone = new ArrayList<>();
    String token;

    List<HistoryProduct> historyProductsEmpty;

    public static HistoryProductFragment newInstance() {
        HistoryProductFragment fragment = new HistoryProductFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryProductBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        token = prefs.getString("token", "No token");

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new HistoryProductFrPresenter(this);
        presenter.getHistoryProduct(token);
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
    public void onDoneGetHistoryProduct(List<HistoryProduct> historyProducts) {
        historyProductAdapter =
                new HistoryProductAdapter(historyProducts, getContext(), this);
        binding.rcHistoryProduct.setLayoutManager(new GridLayoutManager(getContext(), 1));
        binding.rcHistoryProduct.setAdapter(historyProductAdapter);
        historyProductsDone = historyProducts;
    }

    @Override
    public void onDoneFirstItemHistoryProduct(List<SubHistoryProductDetail> subHistoryProductDetail) {
//        historyProductAdapter =
//                new HistoryProductAdapter(historyProductsDone, subHistoryProductDetail.get(0), getContext(), this);
//        binding.rcHistoryProduct.setLayoutManager(new GridLayoutManager(getContext(), 1));
//        binding.rcHistoryProduct.setAdapter(historyProductAdapter);
    }


    @Override
    public void onErr(String err) {

    }

    @Override
    public void onClick(int position, String code) {
        //TODO
//        String codePrd = code;
//        Log.e(TAG, "onClick: " + code);
//        getActivity().finish();
//        startActivity(BackableActivity.newInstanceHistoryProductDetail(getContext(), BackableActivity.NAVIGATOR_FHPDT, codePrd));
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 100) {
//            if (resultCode == Activity.RESULT_OK){
//                final
//            }
//        }
//    }
}
