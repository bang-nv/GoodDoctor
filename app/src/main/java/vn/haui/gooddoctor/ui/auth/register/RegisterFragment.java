package vn.haui.gooddoctor.ui.auth.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import vn.haui.gooddoctor.databinding.FragmentRegisterBinding;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.offline.OtpRegister;
import vn.haui.gooddoctor.models.request.RegisterRequest;
import vn.haui.gooddoctor.ui.auth.AuthActivity;
import vn.haui.gooddoctor.utils.CommonUtils;


public class RegisterFragment extends Fragment implements RegisterFrMvpView {
    public static final String TAG = RegisterFragment.class.getCanonicalName();

    FragmentRegisterBinding binding;
    RegisterFrPresenter registerFrPresenter;
    private String token = "";


    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerFrPresenter = new RegisterFrPresenter(this);


        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        binding.tvResendOTP.setOnClickListener(view1 -> {
            registerFrPresenter.doGetOTP(
                    new OtpRegister(
                            binding.edtFullName.getText().toString(),
                            binding.edtPhone.getText().toString(),
                            binding.edtPassword.getText().toString(),
                            binding.edtConfirmPassword.getText().toString()
                    )
            );
        });

        binding.btnRegister.setOnClickListener(view2 -> {
            if (TextUtils.isEmpty(binding.edtRefCode.getText().toString().trim())) {
                registerFrPresenter.doRegister(
                        new RegisterRequest(
                                binding.edtFullName.getText().toString().trim(),
                                binding.edtPhone.getText().toString().trim(),
                                binding.edtPassword.getText().toString().trim(),
                                binding.edtConfirmPassword.getText().toString().trim(),
                                getToken(),
                                binding.edtVerificationCode.getText().toString().trim()
                        )
                );

            } else {
                registerFrPresenter.doRegisterInviteCode(
                        new RegisterRequest(
                                binding.edtFullName.getText().toString().trim(),
                                binding.edtPhone.getText().toString().trim(),
                                binding.edtPassword.getText().toString().trim(),
                                binding.edtConfirmPassword.getText().toString().trim(),
                                getToken(),
                                binding.edtVerificationCode.getText().toString().trim(),
                                binding.edtRefCode.getText().toString().trim()
                        )
                );
            }

        });

        CommonUtils.hideShowPassword(binding.edtPassword, binding.ivHideShowPassword);

        CommonUtils.hideShowPassword(binding.edtConfirmPassword, binding.ivHideShowConfirmPassword);

        binding.tvLogin.setOnClickListener(view1 -> {
            ((AuthActivity) Objects.requireNonNull(getActivity())).onTitleLoginClick();
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
        binding.ctRegister.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.pbLoading.setVisibility(View.GONE);
        binding.ctRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProcess() {
        showLoading();
    }

    @Override
    public void onSuccessOtp(Response response) {
        hideLoading();

        if (response != null) {
            token = response.getData().toString();
            Log.e("token", "token: " + token);
            CommonUtils.shortToast(getContext(), response.getMessage());
        }

    }

    @Override
    public void onSuccessLogin(Response response) {
        hideLoading();

        if (response != null) {
            CommonUtils.shortToast(getContext(), response.getMessage());
        }

        //TODO Start ...

        ((AuthActivity) Objects.requireNonNull(getActivity())).onTitleLoginClick();
    }

    @Override
    public void onError(String err) {
        hideLoading();
        CommonUtils.shortToast(getContext(), err);
    }

    @Override
    public void onError(int resId) {
        hideLoading();
        CommonUtils.shortToast(getContext(), resId);
    }


    public String getToken() {
        return token;
    }
}
