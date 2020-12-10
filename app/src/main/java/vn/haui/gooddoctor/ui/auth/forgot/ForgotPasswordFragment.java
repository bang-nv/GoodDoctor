package vn.haui.gooddoctor.ui.auth.forgot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.FragmentForgotPasswordBinding;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.offline.OtpForgotPassword;
import vn.haui.gooddoctor.models.request.ForgotPasswordRequest;
import vn.haui.gooddoctor.utils.CommonUtils;

public class ForgotPasswordFragment extends Fragment implements ForgotPasswordFrMvpView {
    public static final String TAG = ForgotPasswordFragment.class.getCanonicalName();
    private String app_hotline;

    FragmentForgotPasswordBinding binding;
    ForgotPasswordFrPresenter forgotPasswordFrPresenter;
    private String token = "";


    public static ForgotPasswordFragment newInstance() {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        app_hotline = getContext().getResources().getString(R.string.app_shop_phone_number);
        forgotPasswordFrPresenter = new ForgotPasswordFrPresenter(this);

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        binding.tvResendOTP.setOnClickListener(view1 -> {
            forgotPasswordFrPresenter.doGetOTP(
                    new OtpForgotPassword(
                            binding.edtPhone.getText().toString().trim(),
                            binding.edtPassword.getText().toString().trim(),
                            binding.edtConfirmPassword.getText().toString().trim()
                    )
            );
        });

        CommonUtils.hideShowPassword(binding.edtPassword, binding.ivHideShowPassword);

        CommonUtils.hideShowPassword(binding.edtConfirmPassword, binding.ivHideShowConfirmPassword);

        binding.btnForgotPassword.setOnClickListener(view2 -> {
            forgotPasswordFrPresenter.doForgotPassword(
                    new ForgotPasswordRequest(
                            binding.edtPhone.getText().toString().trim(),
                            binding.edtPassword.getText().toString().trim(),
                            binding.edtConfirmPassword.getText().toString().trim(),
                            getToken(),
                            binding.edtVerificationCode.getText().toString().trim()
                    )
            );
        });

        binding.tvContactHotLine.setOnClickListener(view3 -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + app_hotline));
            startActivity(callIntent);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showLoading() {
        binding.pbLoading.setVisibility(View.VISIBLE);
        binding.ctForgotPassword.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.pbLoading.setVisibility(View.GONE);
        binding.ctForgotPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProcess() {
        showLoading();
    }


    @Override
    public void onSuccessVerifiCode(Response response) {
        hideLoading();

        token = response.getData().toString();
        Log.e("token forgot", "token: " + token);
        CommonUtils.shortToast(getContext(), response.getMessage());
    }


    @Override
    public void onSuccessForgotPassword(Response response) {
        hideLoading();

        CommonUtils.shortToast(getContext(), response.getMessage());
        Log.e("token button", response.getMessage());
        binding.edtPhone.setText("" + response.getStatus());

    }


    @Override
    public void onError(String err) {
        hideLoading();

        CommonUtils.shortToast(getContext(), err);
        Log.e("token", "onError: " + err);
    }

    @Override
    public void onError(int resId) {
        hideLoading();

        CommonUtils.shortToast(getContext(), resId);
        Log.e("token", "onError: " + resId);
    }


    public String getToken() {
        return token;
    }
}
