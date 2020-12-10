package vn.haui.gooddoctor.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import java.util.ArrayList;
import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.ActivityMainBinding;
import vn.haui.gooddoctor.models.FragmentController;
import vn.haui.gooddoctor.ui.botAccuPoint.AccuPointFragment;
import vn.haui.gooddoctor.ui.botChatRoom.ChatRoomFragment;
import vn.haui.gooddoctor.ui.botHistory.BotHistoryServiceFragment;
import vn.haui.gooddoctor.ui.botHistory.historyService.HistoryServiceFragment;
import vn.haui.gooddoctor.ui.botHomePage.HomePageFragment;
import vn.haui.gooddoctor.ui.botProfile.ProfileFragment;
import vn.haui.gooddoctor.ui.searchProduct.SearchProductActivity;
import vn.haui.gooddoctor.ui.widget.UiToolbar;
import vn.haui.gooddoctor.utils.AppUtils;
import vn.haui.gooddoctor.utils.CommonUtils;

public class MainActivity extends AppCompatActivity implements UiToolbar.OnToolbarWithBackClickListener {

    List<FragmentController> fragmentControllers;

    HomePageFragment homePageFragment;
    ChatRoomFragment chatRoomFragment;
    BotHistoryServiceFragment botHistoryServiceFragment;
    AccuPointFragment accuPointFragment;
    ProfileFragment profileFragment; //TODO
    private Fragment activedFragment;

    ActivityMainBinding binding;

//    private GioHangDAO gioHangDAO;


    private boolean doubleBackToExitPressedOnce = false;


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setupBotNavigation();
    }

    private void init() {
        fragmentControllers = new ArrayList<>();
        homePageFragment = HomePageFragment.newInstance();
        chatRoomFragment = ChatRoomFragment.newInstance();
        botHistoryServiceFragment = botHistoryServiceFragment.newInstance();
        accuPointFragment = AccuPointFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();


        fragmentControllers.add(new FragmentController(homePageFragment, HomePageFragment.TAG));
        fragmentControllers.add(new FragmentController(chatRoomFragment, ChatRoomFragment.TAG));
        fragmentControllers.add(new FragmentController(botHistoryServiceFragment, HistoryServiceFragment.TAG));
        fragmentControllers.add(new FragmentController(accuPointFragment, AccuPointFragment.TAG));
        fragmentControllers.add(new FragmentController(profileFragment, ProfileFragment.TAG));
        activedFragment = AppUtils.addFragmentsToActivity(getSupportFragmentManager(), fragmentControllers, R.id.frMain, 0);


//        gioHangDAO = new GioHangDAO(this);

        binding.toolbarMainActivity.setSearchViewHome(true);
        binding.toolbarMainActivity.setOnToolbarWithCloseClick(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (activedFragment != null && (activedFragment instanceof HomePageFragment)) {
            //TODO
//            int totalAmountProduct = gioHangDAO.getTotalAmountProduct();
//            binding.toolbarMainActivity.setAmountCartHome(true, totalAmountProduct);
        }


    }

    private void setupBotNavigation() {
        binding.toolbarMainActivity.setActionRight(true, R.drawable.ic_cart_home);
        //TODO
//        int totalAmountProduct = gioHangDAO.getTotalAmountProduct();
//        binding.toolbarMainActivity.setAmountCartHome(true, totalAmountProduct);

        // Create items
        AHBottomNavigationItem itemHome =
                new AHBottomNavigationItem(R.string.title_bot_home, R.drawable.ic_home_default, R.color.color_white);
        AHBottomNavigationItem itemChat =
                new AHBottomNavigationItem(R.string.title_bot_chat_doctor, R.drawable.ic_chat_default, R.color.color_white);
        AHBottomNavigationItem itemHistory
                = new AHBottomNavigationItem(R.string.title_bot_history_service, R.drawable.ic_oder_default, R.color.color_white);
        AHBottomNavigationItem itemPoint =
                new AHBottomNavigationItem(R.string.title_bot_accu_point, R.drawable.ic_points_default, R.color.color_white);
        AHBottomNavigationItem itemProfile =
                new AHBottomNavigationItem(R.string.title_bot_profile, R.drawable.ic_account_default, R.color.color_white);

        // Add items
        binding.AHMainBottomNavigation.addItem(itemHome);
        binding.AHMainBottomNavigation.addItem(itemChat);
        binding.AHMainBottomNavigation.addItem(itemHistory);
        binding.AHMainBottomNavigation.addItem(itemPoint);
        binding.AHMainBottomNavigation.addItem(itemProfile);

        // Change colors
        binding.AHMainBottomNavigation.setAccentColor(Color.parseColor("#024F87"));
        binding.AHMainBottomNavigation.setInactiveColor(Color.parseColor("#AAAAAA"));

        binding.AHMainBottomNavigation.setTitleTextSize(
                getResources().getDimension(R.dimen.text_size_14sp),
                getResources().getDimension(R.dimen.text_size_12sp)
        );

        // binding.AHMainBottomNavigation.setTitleTypeface(Typeface.DEFAULT_BOLD); //or
        binding.AHMainBottomNavigation.setTitleTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

        // Customize notification
// binding.AHMainBottomNavigation.setNotification("FREE", 1); //default
        AHNotification notification = new AHNotification.Builder() //custom
                .setText("FREE")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorFF8800Transparent))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_white))
                .build();
        binding.AHMainBottomNavigation.setNotification(notification, 1);

        //set Title State
        binding.AHMainBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Set Listeners
        binding.AHMainBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                onAHBotNaviItemSelected(position);
                return true;
            }
        });
    }

    public boolean onAHBotNaviItemSelected(int position) {
        int maybeActive = -1;
        String title = "";
        boolean enableActionRight = false;
        int iconActionRight = R.drawable.ic_cart_home;
        switch (position) {
            case 0:
                maybeActive = 0;
                enableActionRight = true;
                iconActionRight = R.drawable.ic_cart_home;
                title = getResources().getString(R.string.title_toolbar_home);
                break;
            case 1:
                maybeActive = 1;
                enableActionRight = false;
                title = getResources().getString(R.string.title_toolbar_chat_doctor);
                break;
            case 2:
                maybeActive = 2;
                enableActionRight = false;
                title = getResources().getString(R.string.title_toolbar_history_service);
                break;
            case 3:
                maybeActive = 3;
                enableActionRight = false;
                iconActionRight = R.drawable.ic_voucher;
                title = getResources().getString(R.string.title_toolbar_accu_point);
                break;
            case 4:
                maybeActive = 4;
                enableActionRight = true;
                iconActionRight = R.drawable.ic_cart_toolbar;
//                        title = getResources().getString(R.string.title_profile) + " " + (!TextUtils.isEmpty(userDefault.getName()) ? userDefault.getName() : "");
                title = getResources().getString(R.string.title_toolbar_profile);
                break;
        }

        if (maybeActive == 0) { //HomePage
            binding.toolbarMainActivity.setSearchViewHome(true);
            binding.toolbarMainActivity.setActionRight(enableActionRight, iconActionRight);

            //TODO
//            int totalAmountProduct = gioHangDAO.getTotalAmountProduct();
//            binding.toolbarMainActivity.setAmountCartHome(enableActionRight, totalAmountProduct);
//            binding.toolbarMainActivity.setAmountCartOther(false, totalAmountProduct);

            activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, fragmentControllers.get(maybeActive).getFragment());
            return true;
        } else if (maybeActive == 4) { //Profile
            binding.toolbarMainActivity.setSearchViewHome(false);
            binding.toolbarMainActivity.setTitle(title);
            binding.toolbarMainActivity.setActionRight(enableActionRight, iconActionRight);

            //TODO
//            int totalAmountProduct = gioHangDAO.getTotalAmountProduct();
//            binding.toolbarMainActivity.setAmountCartOther(enableActionRight, totalAmountProduct);
//            binding.toolbarMainActivity.setAmountCartHome(false, totalAmountProduct);

            activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, fragmentControllers.get(maybeActive).getFragment());
            return true;
        } else if (maybeActive == 3) { //accupoint
            binding.toolbarMainActivity.setSearchViewHome(false);
            binding.toolbarMainActivity.setTitle(title);
            binding.toolbarMainActivity.setActionRight(enableActionRight, iconActionRight);

            binding.toolbarMainActivity.setAmountCartOther(false, 0);
            binding.toolbarMainActivity.setAmountCartHome(false, 0);

            activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, fragmentControllers.get(maybeActive).getFragment());
            return true;
        } else if (maybeActive != -1) {
            binding.toolbarMainActivity.setSearchViewHome(false);
            binding.toolbarMainActivity.setTitle(title);
            binding.toolbarMainActivity.setActionRight(enableActionRight, iconActionRight);

            //TODO
//            int totalAmountProduct = gioHangDAO.getTotalAmountProduct();
//            binding.toolbarMainActivity.setAmountCartHome(enableActionRight, totalAmountProduct);
//            binding.toolbarMainActivity.setAmountCartOther(enableActionRight, totalAmountProduct);

            activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, fragmentControllers.get(maybeActive).getFragment());
            return true;
        }

        return false;
    }

    @Override
    public void onToolbarBackClick() {
        if (isHomePage)
            backToMain();
    }

    @Override
    public void onToolbarActionRightClick() {

        if (activedFragment != null
//                 && (activedFragment instanceof AccuPointFragment) //TODO
        ) {
//            startActivity(new Intent(this, VoucherActivity.class));
////            CommonUtils.shortToast(this, "Tính năng đang phát triển!");

            CommonUtils.shortToast(this, "Đây không phải bản full!");
        } else {
            //TODO
//            startActivity(new Intent(this, CartActivity.class));
            CommonUtils.shortToast(this, "Đây không phải bản full!");
        }
    }

    @Override
    public void onToolbarSearchBarHomeClick() {
        goToSearchProductActivity();
    }

    private void goToSearchProductActivity() {
        Intent intent = new Intent(this, SearchProductActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (isHomePage) {
            backToMain();
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


    private boolean isHomePage = false;

    public void enableNavigationBottom(boolean isHomePage) {
        binding.AHMainBottomNavigation.setVisibility(isHomePage ? View.GONE : View.VISIBLE);
        binding.toolbarMainActivity.setEnableBack(isHomePage);
        this.isHomePage = isHomePage;
    }

    public void setTitleToolBar(String title) {
        binding.toolbarMainActivity.setTitle(title);
    }

    public void backToMain() {
        isHomePage = !isHomePage;
        enableNavigationBottom(false);
        this.getSupportFragmentManager().popBackStack();
        binding.toolbarMainActivity.setTitle(getString(R.string.str_home));
        activedFragment = AppUtils.switchFragmentActivity(getSupportFragmentManager(), activedFragment, fragmentControllers.get(0).getFragment());
    }


    public void getResultHomePageFragment() {
        //TODO
//        int totalAmountProduct = gioHangDAO.getTotalAmountProduct();
//        binding.toolbarMainActivity.setAmountCartHome(true, totalAmountProduct);
    }

    public void setHideBottomNavigation() {
        binding.AHMainBottomNavigation.setVisibility(View.GONE);
    }

    public void setShowBottomNavigation() {
        binding.AHMainBottomNavigation.setVisibility(View.VISIBLE);
    }
}