package vn.haui.gooddoctor.ui.auth.login;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.Objects;

import vn.haui.gooddoctor.data.prefs.SessionManager;
import vn.haui.gooddoctor.databinding.FragmentLoginBinding;
import vn.haui.gooddoctor.models.request.LoginRequest;
import vn.haui.gooddoctor.models.response.DefaultUser;
import vn.haui.gooddoctor.ui.auth.AuthActivity;
import vn.haui.gooddoctor.ui.main.MainActivity;
import vn.haui.gooddoctor.utils.CommonUtils;

import static android.content.Context.MODE_PRIVATE;

public class LoginFrFragment extends Fragment implements LoginFrMvpView {
    public static final String TAG = LoginFrFragment.class.getCanonicalName();

    FragmentLoginBinding binding;
    LoginFrPresenter loginFrPresenter;
    private SessionManager sessionManager;
    private String apiToken;
    private String phoneLoggedShared;
    private String fcmToken;


    public static LoginFrFragment newInstance() {
        LoginFrFragment fragment = new LoginFrFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sessionManager = new SessionManager(Objects.requireNonNull(getContext()));

        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        phoneLoggedShared = prefs.getString("phoneLogged", "");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginFrPresenter = new LoginFrPresenter(this);

        if (!"".equals(phoneLoggedShared)) {
            binding.edtPhone.setText(phoneLoggedShared);
        }

        binding.btnLogin.setOnClickListener(view1 -> {
            loginFrPresenter.doLogin(
                    new LoginRequest(
                            binding.edtPhone.getText().toString().trim(),
                            binding.edtPassword.getText().toString().trim()
                    )
            );
        });

        binding.tvRegister.setOnClickListener(view2 -> {
            ((AuthActivity) getActivity()).onTitleRegisterClick();
        });
        binding.tvForgotPassword.setOnClickListener(view3 -> {
            ((AuthActivity) getActivity()).onTitleForgotClick();
        });

        CommonUtils.hideShowPassword(binding.edtPassword, binding.ivHideShowPassword);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void showLoading() {
        binding.pbLoading.setVisibility(View.VISIBLE);
        binding.ctLogin.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.pbLoading.setVisibility(View.GONE);
        binding.ctLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProcess() {
        showLoading();
    }

    @Override
    public void onSuccess(DefaultUser user, String phoneLogged) {
        hideLoading();

        sessionManager.setLogin(true);

        apiToken = user.getToken();
        Log.e("TAG", "onSuccess: " + apiToken);

        @SuppressLint("WrongConstant") SharedPreferences.Editor editor
                = getActivity().getSharedPreferences("MY_PREFS_NAME", Context.MODE_PRIVATE).edit();
        editor.putString("token", apiToken);
        editor.putString("phoneLogged", phoneLogged);
        editor.apply();


        updateToServerTokeFCM();
        startMainActivity();
    }


    private void updateToServerTokeFCM() {
        //TODO
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        Log.e(TAG, "Fetching FCM registration token failed", task.getException());
//                        return;
//                    }
//
//                    // Get new FCM registration token
//                    fcmToken = task.getResult();
//
//                    // Log and toast
//                    FcmToken fcm = new FcmToken(fcmToken);
//                    Log.e("updateToServerTokeFCM", "updateToServerTokeFCM: fragment: " +fcmToken );
//
//                    @SuppressLint("WrongConstant") SharedPreferences.Editor editor_FCM
//                            = getActivity().getSharedPreferences("MY_PREFS_TOKEN_FCM", MODE_PRIVATE).edit();
//                    editor_FCM.putString("TOKEN_FCM", fcmToken);
//                    editor_FCM.apply();
//
//                    loginFrPresenter.postUpdateTokenFcm(apiToken, fcm);
//                });
    }

    private void startMainActivity() {
        startActivity(MainActivity.newInstance(getContext()));
        getActivity().finish();
    }


    @Override
    public void onError(String err) {
        hideLoading();

        Toast.makeText(getContext(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int resId) {
        hideLoading();

        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }


}
