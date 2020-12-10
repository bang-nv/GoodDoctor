package vn.haui.gooddoctor.ui.botHistory.historyServiceDetail;

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

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.FragmentHistoryServiceDetailBinding;
import vn.haui.gooddoctor.models.response.HistoryServiceDetail;
import vn.haui.gooddoctor.ui.botHistory.historyServiceDetail.cancelservice.CancelBookingMvpView;
import vn.haui.gooddoctor.ui.botHistory.historyServiceDetail.cancelservice.CancelBookingPresenter;
import vn.haui.gooddoctor.ui.tabDetail.detailedProduct.ProductDetailFragment;
import vn.haui.gooddoctor.utils.CommonUtils;

import static android.content.Context.MODE_PRIVATE;


public class HistoryServiceDetailFrMvpFragment extends Fragment implements HistoryServiceDetailFrMvpView, CancelBookingMvpView {
    public static final String TAG = ProductDetailFragment.class.getCanonicalName();
    public static final String ARG_CODE_HTR = "ARG_CODE_HTR";
    FragmentHistoryServiceDetailBinding binding;
    String codeHtrSv;
    String token;

    HistoryServiceDetailFrPresenter historyServiceDetailFrPresenter;

    CancelBookingPresenter cancelBookingPresenter;

    public static HistoryServiceDetailFrMvpFragment newInstance(String codeMdc) {
        HistoryServiceDetailFrMvpFragment fragment = new HistoryServiceDetailFrMvpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODE_HTR, codeMdc);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryServiceDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        codeHtrSv = getArguments().getString(ARG_CODE_HTR);

        Log.e(TAG, "onCreateView: " + codeHtrSv);

        historyServiceDetailFrPresenter = new HistoryServiceDetailFrPresenter(this);
        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        token = prefs.getString("token", "No token");
        historyServiceDetailFrPresenter.getHistoryServiceDetail(token, codeHtrSv);

        cancelBookingPresenter = new CancelBookingPresenter(this);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBookingPresenter.getCancelBooking(token, codeHtrSv);
                getActivity().finish();
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void onProcess() {

    }

    @Override
    public void onDoneCancelBooking(String notifi) {
        Toast.makeText(getContext(), notifi, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onDoneHistoryDetailService(HistoryServiceDetail historyServiceDetails) {
        binding.tvDate.setText(String.format("Thời gian: %s", historyServiceDetails.getDate()));
        binding.tvAddressReceive.setText(String.format("Địa điểm nhận hàng: %s", historyServiceDetails.getAddress()));

        binding.tvTotalMoney.setText(CommonUtils.parseMoney(historyServiceDetails.getTotalMoney()));

        if (historyServiceDetails.getPayMethod() == 0) {
            binding.tvPaymentType.setText("Tiền mặt");
        } else if (historyServiceDetails.getPayMethod() == 1) {
            binding.tvPaymentType.setText("Ví tiền");
        } else {
            binding.tvPaymentType.setText("Thanh toán online/ Ví điện tử");
        }

        binding.tvNote.setText(historyServiceDetails.getNotes());

        if (historyServiceDetails.getStatus() == 0) {
            binding.tvStatus.setText("Đã hủy");
            binding.tvStatus.setTextColor(R.color.colorFF0000);
            binding.tvCancelOrder.setBackground(getResources().getDrawable(R.drawable.bg_all_border_botton_gray));
            binding.tvCancelOrder.setEnabled(false);
        } else if (historyServiceDetails.getStatus() == 1) {
            binding.tvStatus.setText("Mới/ Chờ xác nhận");
            binding.tvStatus.setTextColor(R.color.color1BB01A);
        } else if (historyServiceDetails.getStatus() == 2) {
            binding.tvStatus.setText("Đã xác nhận");
            binding.tvStatus.setTextColor(R.color.colorF6AE2D);
        } else {
            binding.tvStatus.setText("Hoàn thành");
            binding.tvStatus.setTextColor(R.color.colorPrimary);
        }
    }


    @Override
    public void onErr(String err) {

    }
}