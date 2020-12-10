package vn.haui.gooddoctor.ui.botProfile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.Objects;


import vn.haui.gooddoctor.data.prefs.SessionManager;
import vn.haui.gooddoctor.databinding.FragmentProfileBinding;
import vn.haui.gooddoctor.models.response.Profile;
import vn.haui.gooddoctor.ui.auth.AuthActivity;
import vn.haui.gooddoctor.utils.CommonUtils;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment implements ProfileFrMvpView{
    public static final String TAG = ProfileFragment.class.getCanonicalName();

    FragmentProfileBinding binding;
    SessionManager sessionManager;
    ProfileFrPresenter presenter;

    String imgPrf;
    String namePrf;
    String phonePrf;
    String birthdayPrf;
    String addressPrf;
    String emailPrf;
    int genderPrf;
    String token;


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sessionManager = new SessionManager(Objects.requireNonNull(getContext()));

        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        token = prefs.getString("token", "No token");

        Log.e("TokenApi", "onCreateView: " + token);

        presenter = new ProfileFrPresenter(this);
        presenter.getProfile(token);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clickItemProfile();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getProfile(token);
    }

    @Override
    public void onSuccess(Profile profile) {
        imgPrf = profile.getLinkAvatar();
        namePrf = profile.getName();
        phonePrf = profile.getPhone();
        birthdayPrf = profile.getBirthday();
        addressPrf = profile.getAddress();
        emailPrf = profile.getEmail();
        genderPrf = profile.getGender();

        Log.e("TAG", "onSuccess: " + genderPrf);

        Glide.with(getContext()).load(imgPrf).into(binding.imgAvtPrf);
        binding.tvName.setText(namePrf);
        binding.tvPhone.setText(phonePrf);

    }

    private void clickItemProfile() {

        binding.clInfoHeader.setOnClickListener(v -> {
//            startActivity(BackableActivity.newInstanceProfileDetail(getContext(), BackableActivity.NAVIGATOR_FPFDT, imgPrf, namePrf, phonePrf, birthdayPrf, addressPrf, genderPrf, emailPrf));
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });

        binding.llWallet.setOnClickListener(v -> {
//            startActivity(BackableActivity.newInstanceMyWallet(getContext(), BackableActivity.NAVIGATOR_FMW));
//            Toast.makeText(getContext(), "Chức năng này đang được phát triển", Toast.LENGTH_SHORT).show();
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });

        binding.llMedical.setOnClickListener(v -> {
//            startActivity(BackableActivity.newInstanceMedical(getContext(), BackableActivity.NAVIGATOR_FMDC));
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });

        binding.llFeedBack.setOnClickListener(v -> {
//            startActivity(BackableActivity.newInstanceFeedback(getContext(), BackableActivity.NAVIGATOR_FFB));
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });

        binding.llNotifi.setOnClickListener(v -> {
//            startActivity(BackableActivity.newInstanceNotification(getContext(), BackableActivity.NAVIGATOR_FNTF));
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });

        binding.llChangePassword.setOnClickListener(v -> {
//            startActivity(BackableActivity.newInstanceChangePassword(getContext(), BackableActivity.NAVIGATOR_FCP));
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });

        binding.llRatingApp.setOnClickListener(v -> {
//            AppUtils.openPlayStoreForApp(getContext());
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });

        binding.llContact.setOnClickListener(v -> {
//            startActivity(BackableActivity.newInstanceContact(getContext(), BackableActivity.NAVIGATOR_FCT));
            CommonUtils.shortToast(getContext(), "Not Full Version!");
        });


        binding.llShare.setOnClickListener(this::clickItemShare);

        binding.llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getSignOut(token);
                sessionManager.setLogin(false);
                startActivity(AuthActivity.newInstance(getActivity()));
                getActivity().finish();
            }

        });
    }

    private void clickItemShare(View view) {
        try {
            ShareCompat.IntentBuilder.from(getActivity())
                    .setType("text/plain")
                    .setChooserTitle("Chia sẻ ứng dụng cho")
                    .setText("https://play.google.com/store/apps/details?id=" + getContext().getPackageName())
                    .startChooser();
        } catch (Exception ex) {
            CommonUtils.shortToast(getContext(), ex.getMessage());
        }
    }

    private void clickItemLogout(View view) {
        sessionManager.setLogin(false);
        startActivity(AuthActivity.newInstance(getActivity()));
        getActivity().finish();
    }

    @Override
    public void onProcess() {

    }


    @Override
    public void onError(String err) {

    }

    @Override
    public void onDoneSignOut(String ntf) {
//        sessionManager.setLogin(false);
//        Toast.makeText(getContext(), ntf, Toast.LENGTH_SHORT).show();
//        startActivity(AuthActivity.newInstance(getActivity()));
//        getActivity().finish();
    }

    @Override
    public void onErrorSignOut(String ntf) {
        CommonUtils.shortToast(getContext(), ntf);
    }
}
