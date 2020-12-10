package vn.haui.gooddoctor.ui.botHistory.historyProductDetail;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.FragmentHistoryServiceDetailBinding;
import vn.haui.gooddoctor.models.response.HistoryProductDetail;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;
import vn.haui.gooddoctor.ui.botHistory.historyProductDetail.cancelorder.CancelOrderMvpView;
import vn.haui.gooddoctor.ui.botHistory.historyProductDetail.cancelorder.CancelOrderPresenter;
import vn.haui.gooddoctor.ui.tabDetail.detailedProduct.ProductDetailFragment;
import vn.haui.gooddoctor.utils.CommonUtils;

import static android.content.Context.MODE_PRIVATE;


public class HistoryDetailProductFragment extends Fragment implements HistoryDetailProductFrMvpView, CancelOrderMvpView {
    public static final String TAG = ProductDetailFragment.class.getCanonicalName();
    public static final String ARG_CODE_HTR = "ARG_CODE_HTR";
    FragmentHistoryServiceDetailBinding binding;
    String codeHtrPr;
    String token;
    CancelOrderPresenter mvp;

    HistoryDetailProductFrPresenter presenter;

    HistoryDetailProductAdapter historyDetailProductAdapter;

    public static HistoryDetailProductFragment newInstance(String codeHtrPr) {
        HistoryDetailProductFragment fragment = new HistoryDetailProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODE_HTR, codeHtrPr);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryServiceDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        codeHtrPr = getArguments().getString(ARG_CODE_HTR);
        Log.e("codeHtrPr", "codeHtrPr: " + codeHtrPr);

        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        token = prefs.getString("token", "No token");

        presenter = new HistoryDetailProductFrPresenter(this);
        presenter.getHistoryProductDetail(token, codeHtrPr);
        presenter.getSubHistoryProductDetail(token, codeHtrPr);

        mvp = new CancelOrderPresenter(this);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvp.getCancelOrder(token, codeHtrPr);
                getActivity().finish();
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void onProcess() {

    }

    @Override
    public void onDoneCancelOrder(String notifi) {
        Toast.makeText(getContext(), notifi, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onDoneHistoryProductDetail(HistoryProductDetail historyProductDetail) {
        binding.tvDate.setText(String.format("Thời gian: %s", historyProductDetail.getDate()));
        binding.tvAddressReceive.setText(String.format("Địa điểm nhận hàng: %s", historyProductDetail.getReceiveAddress()));
        binding.tvTotalMoney.setText(CommonUtils.parseMoney(historyProductDetail.getTotalMoney()));
        binding.tvNote.setText(historyProductDetail.getNotes());

        if (historyProductDetail.getPayMethod() == 0) {
            binding.tvPaymentType.setText("Tiền mặt");
        } else if (historyProductDetail.getPayMethod() == 1) {
            binding.tvPaymentType.setText("Ví tiền");
        } else {
            binding.tvPaymentType.setText("Thanh toán online/ Ví điện tử");
        }


        if (historyProductDetail.getStatus() == 0) {
            binding.tvStatus.setText("Đã hủy");
            binding.tvStatus.setTextColor(R.color.colorFF0000);
            binding.tvCancelOrder.setBackground(getResources().getDrawable(R.drawable.bg_all_border_botton_gray));
            binding.tvCancelOrder.setEnabled(false);
        } else if (historyProductDetail.getStatus() == 1) {
            binding.tvStatus.setText("Mới/ Chờ xác nhận");
            binding.tvStatus.setTextColor(R.color.color1BB01A);
        } else if (historyProductDetail.getStatus() == 2) {
            binding.tvStatus.setText("Đã xác nhận");
            binding.tvStatus.setTextColor(R.color.colorF6AE2D);
        } else {
            binding.tvStatus.setText("Hoàn thành");
            binding.tvStatus.setTextColor(R.color.colorPrimary);
        }
    }

    @Override
    public void onDoneSubHistoryProductDetail(List<SubHistoryProductDetail> subHistoryProductDetails) {
        int totalProduct = 0;
        for (int i = 0; i < subHistoryProductDetails.size(); i++) {
            totalProduct += subHistoryProductDetails.get(i).getAmount();
        }
        binding.tvTotalQty.setText(String.valueOf(totalProduct));
        Log.e("SubHistoryProductDetail", "onDoneSubHistoryProductDetail: " + totalProduct);
        Log.e("SubHistoryProductDetail", "onDoneSubHistoryProductDetail: size " + subHistoryProductDetails.size());

        historyDetailProductAdapter =
                new HistoryDetailProductAdapter(subHistoryProductDetails, getContext(), this);
        binding.rcHistoryDetailProduct.setLayoutManager(new GridLayoutManager(getContext(), 1));
        binding.rcHistoryDetailProduct.setAdapter(historyDetailProductAdapter);


    }

    @Override
    public void onErr(String err) {

    }
}