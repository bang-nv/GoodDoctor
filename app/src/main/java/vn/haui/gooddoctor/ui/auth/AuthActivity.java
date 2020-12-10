package vn.haui.gooddoctor.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.models.FragmentController;
import vn.haui.gooddoctor.ui.auth.forgot.ForgotPasswordFragment;
import vn.haui.gooddoctor.ui.auth.login.LoginFrFragment;
import vn.haui.gooddoctor.ui.auth.register.RegisterFragment;
import vn.haui.gooddoctor.utils.AppUtils;
import vn.haui.gooddoctor.utils.CommonUtils;


public class AuthActivity extends AppCompatActivity {

    LoginFrFragment loginFragment;
    RegisterFragment registerFragment;
    ForgotPasswordFragment forgotPasswordFragment;
    long savedFragmentId = -1;


    List<FragmentController> fragmentControllers;

    private Fragment activedFragment;

    private boolean doubleBackToExitPressedOnce = false;


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AuthActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //Phuc hoi
        if (savedInstanceState != null) {
            savedFragmentId = savedInstanceState.getLong("savedFragmentId");
            Log.e("savedFragmentId", "savedFragmentId: " + savedFragmentId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    public void onBackPressed() {
        if (activedFragment != null &&
                (activedFragment instanceof RegisterFragment || activedFragment instanceof ForgotPasswordFragment)) {
            activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, loginFragment);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            CommonUtils.shortToast(this, R.string.msg_back_again_to_exit);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Lưu lại giá trị mLession với tên là "lesson" trong outState
        outState.putLong("savedFragmentId", activedFragment.getId());
    }

    private void init() {
        loginFragment = LoginFrFragment.newInstance();
        registerFragment = RegisterFragment.newInstance();
        forgotPasswordFragment = ForgotPasswordFragment.newInstance();  //them
        fragmentControllers = new ArrayList<>();

        fragmentControllers.add(new FragmentController(loginFragment, LoginFrFragment.TAG));
        fragmentControllers.add(new FragmentController(registerFragment, RegisterFragment.TAG));
        fragmentControllers.add(new FragmentController(forgotPasswordFragment, ForgotPasswordFragment.TAG));

        activedFragment = AppUtils.addFragmentsToActivity(getSupportFragmentManager(), fragmentControllers, R.id.frAuth, 0);
    }

    public void onTitleRegisterClick() {
        if (activedFragment != null && activedFragment instanceof RegisterFragment) return;
        activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, registerFragment);
    }

    public void onTitleLoginClick() {
        if (activedFragment != null && activedFragment instanceof LoginFrFragment) return;
        activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, loginFragment);
    }

    public void onTitleForgotClick() {
        if (activedFragment != null && activedFragment instanceof ForgotPasswordFragment) return;
        activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, forgotPasswordFragment);
    }
}