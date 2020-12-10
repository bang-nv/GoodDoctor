package vn.haui.gooddoctor.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.data.AsyncTaskExecutor;
import vn.haui.gooddoctor.databinding.ActivityBackableBinding;
import vn.haui.gooddoctor.models.FragmentController;
import vn.haui.gooddoctor.ui.tabDetail.detailedProduct.ProductDetailFragment;
import vn.haui.gooddoctor.ui.widget.UiToolbar;
import vn.haui.gooddoctor.utils.AppUtils;

public class BackableActivity extends AppCompatActivity implements UiToolbar.OnToolbarWithBackClickListener {

    public static final String ARG_NAVIGATOR = "ARG_NAVIGATOR";

    public static final String NAVIGATOR_FPFDT = "NAVIGATOR_FPFDT";
    public static final String NAVIGATOR_FMW = "NAVIGATOR_FMW";
    public static final String NAVIGATOR_FMDC = "NAVIGATOR_FMDC";
    public static final String NAVIGATOR_FMDCDT = "NAVIGATOR_FMDCDT";
    public static final String NAVIGATOR_FFB = "NAVIGATOR_FFB";
    public static final String NAVIGATOR_FNTF = "NAVIGATOR_FNTF";
    public static final String NAVIGATOR_FCP = "NAVIGATOR_FCP";
    public static final String NAVIGATOR_FACH = "NAVIGATOR_FACH";

    public static final String NAVIGATOR_FPRMDT = "NAVIGATOR_FPRMDT";
    public static final String NAVIGATOR_FNDT = "NAVIGATOR_FNDT";
    public static final String NAVIGATOR_FPRDDT = "NAVIGATOR_FPRDDT";
    public static final String NAVIGATOR_FSVDT = "NAVIGATOR_FSVDT";
    public static final String NAVIGATOR_FSCHSV = "NAVIGATOR_FSCHSV";
    public static final String NAVIGATOR_FCT = "NAVIGATOR_FCT";
    public static final String NAVIGATOR_FHTRDT = "NAVIGATOR_FHTRDT";
    public static final String NAVIGATOR_FHPDT = "NAVIGATOR_FHPDT";
    public static final String NAVIGATOR_FCFSV = "NAVIGATOR_FCFSV";
    public static final String NAVIGATOR_FPRD = "NAVIGATOR_FPRD";
    public static final String NAVIGATOR_FSV = "NAVIGATOR_FSV";

    String codePrd;
//    String codeNews;
//    String codePrm;
//    String codeMdc;
//    String codeHstDt;
//    String codeHspDt;
//    String titlePrm;
//    String timeStart;
//    String timeEnd;
//    String img;
//    String excerpt;
//    String imgPrf;
//    String namePrf;
//    String phonePrf;
//    String birthdayPrf;
//    String addressPrf;
//    int genderPrf;
//    String emailPrf;
//
//
//    String codeSv;
//    String nameSv;
//    String imageSv;
//    String priceSv;
//    String priceSaleSv;
//
//    String codeSvScheS;
//    String nameSvScheS;
//    String imageSvScheS;
//    String priceSvScheS;
//    String priceSaleSvScheS;
//
//    //Confirm Service
//    ScheduleService scheduleServiceCf;
//    String nameSvCf;
//    String imageSvCf;
//    String priceSvCf;
//    String priceSaleCf;


    //==================================================================
    ActivityBackableBinding binding;
    private AsyncTask asyncTaskExecutor;
    private String navigator;
    //==================================================================

//    public static Intent newInstanceProfileDetail(Context context, String navigator, String imgPrf, String namePrf, String phonePrf, String birthdayPrf, String addressPrf, int genderPrf, String emailPrf) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(ProfileDetailFragment.ARG_IMG_PRF, imgPrf);
//        intent.putExtra(ProfileDetailFragment.ARG_NAME_PRF, namePrf);
//        intent.putExtra(ProfileDetailFragment.ARG_PHONE_PRF, phonePrf);
//        intent.putExtra(ProfileDetailFragment.ARG_BD_PRF, birthdayPrf);
//        intent.putExtra(ProfileDetailFragment.ARG_ADR_PRF, addressPrf);
//        intent.putExtra(ProfileDetailFragment.ARG_GD_PRF, genderPrf);
//        intent.putExtra(ProfileDetailFragment.ARG_EMAIL_PRF, emailPrf);
//        return intent;
//    }
//
//
//    public static Intent newInstanceFeedback(Context context, String navigator) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        return intent;
//    }
//
//    public static Intent newInstanceNotification(Context context, String navigator) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        return intent;
//    }
//
//    public static Intent newInstanceService(Context context, String navigator) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        return intent;
//    }
//
//    public static Intent newInstanceProduct(Context context, String navigator) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        return intent;
//    }
//
//    public static Intent newInstanceChangePassword(Context context, String navigator) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        return intent;
//    }
//
//    public static Intent newInstanceAllCategoryHome(Context context, String navigator) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        return intent;
//    }
//
//    public static Intent newInstanceMyWallet(Context context, String navigatorFmw) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigatorFmw);
//        return intent;
//    }
//
//    public static Intent newInstanceMedical(Context context, String navigatorFmdc) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigatorFmdc);
//        return intent;
//    }
//
//    public static Intent newInstanceContact(Context context, String navigatorCt) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, NAVIGATOR_FCT);
//        return intent;
//    }
//
//    public static Intent newInstancePromotionDetail(Context context, String navigator, String code) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(PromotionDetailFragment.ARG_CODE_PRM, code);
//
//
//        return intent;
//    }
//
//    public static Intent newInstanceNewsDetail(Context context, String navigator, String codeNews) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(NewsDetailFragment.ARG_CODE_NEWS, codeNews);
//
//        return intent;
//    }

    public static Intent newInstanceProductDetail(Context context, String navigator, String codePrd) {
        Intent intent = new Intent(context, BackableActivity.class);
        intent.putExtra(ARG_NAVIGATOR, navigator);
        intent.putExtra(ProductDetailFragment.ARG_CODE_PRD, codePrd);

        return intent;
    }

//    public static Intent newInstanceHistoryServiceDetail(Context context, String navigator, String codeHstDt) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(HistoryServiceDetailFragment.ARG_CODE_HTR, codeHstDt);
//
//        return intent;
//    }
//
//    public static Intent newInstanceHistoryProductDetail(Context context, String navigator, String codeHspDt) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(HistoryDetailProductFragment.ARG_CODE_HTR, codeHspDt);
//
//        return intent;
//    }
//
//    public static Intent newInstanceServiceDetail(Context context, String navigator,
//                                                  String codeSv, String nameSv, String imageSv, String priceSv, String priceSaleSv) {
//        Intent intent = new Intent(context, BackableActivity.class);
//
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(ServiceDetailFragment.ARG_CODE_SV, codeSv);
//        intent.putExtra(ServiceDetailFragment.ARG_NAME_SV, nameSv);
//        intent.putExtra(ServiceDetailFragment.ARG_IMAGE_SV, imageSv);
//        intent.putExtra(ServiceDetailFragment.ARG_PRICE_SV, priceSv);
//        intent.putExtra(ServiceDetailFragment.ARG_PRICE_SALE_SV, priceSaleSv);
//
//        return intent;
//    }
//
//    public static Intent newInstanceScheduleService(Context context, String navigator,
//                                                    String codeSvScheS, String nameSvScheS, String imageSvScheS,
//                                                    String priceSvScheS, String priceSaleSvScheS) {
//        Intent intent = new Intent(context, BackableActivity.class);
//
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(ScheduleServiceFragment.ARG_CODE_SV, codeSvScheS);
//        intent.putExtra(ScheduleServiceFragment.ARG_NAME_SV, nameSvScheS);
//        intent.putExtra(ScheduleServiceFragment.ARG_IMAGE_SV, imageSvScheS);
//        intent.putExtra(ScheduleServiceFragment.ARG_PRICE_SV, priceSvScheS);
//        intent.putExtra(ScheduleServiceFragment.ARG_PRICE_SALE_SV, priceSaleSvScheS);
//
//        return intent;
//    }
//
//    public static Intent newInstanceConfirmService(Context context, String navigator,
//                                                   ScheduleService scheduleServiceSv,
//                                                   String nameSvCf, String imageSvCf, String priceSvCf, String priceSaleCf) {
//        Intent intent = new Intent(context, BackableActivity.class);
//
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(ConfirmServiceFragment.ARG_SCHEDULE_FC, scheduleServiceSv);
//        intent.putExtra(ConfirmServiceFragment.ARG_NAME_CF, nameSvCf);
//        intent.putExtra(ConfirmServiceFragment.ARG_IMAGE_CF, imageSvCf);
//        intent.putExtra(ConfirmServiceFragment.ARG_PRICE_CF, priceSvCf);
//        intent.putExtra(ConfirmServiceFragment.ARG_PRICE_SALE_CF, priceSaleCf);
//
//        return intent;
//    }
//
//    public static Intent newInstanceMedicalDetail(Context context, String navigator, String codeMdc) {
//        Intent intent = new Intent(context, BackableActivity.class);
//        intent.putExtra(ARG_NAVIGATOR, navigator);
//        intent.putExtra(MedicalDetailFragment.ARG_CODE_MDC, codeMdc);
//
//        return intent;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBackableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarBackable.setOnToolbarWithCloseClick(this);
        navigator = getIntent().getExtras().getString(ARG_NAVIGATOR);

        //==================================================================

        codePrd = getIntent().getExtras().getString(ProductDetailFragment.ARG_CODE_PRD);

//        codePrm = getIntent().getExtras().getString(PromotionDetailFragment.ARG_CODE_PRM);
//        titlePrm = getIntent().getExtras().getString(PromotionDetailFragment.ARG_TT_PRM);
//        timeStart = getIntent().getExtras().getString(PromotionDetailFragment.ARG_TS_PRM);
//        timeEnd = getIntent().getExtras().getString(PromotionDetailFragment.ARG_TE_PRM);
//        img = getIntent().getExtras().getString(PromotionDetailFragment.ARG_IMG_PRM);
//        excerpt = getIntent().getExtras().getString(PromotionDetailFragment.ARG_EX_PRM);
//
//        codeNews = getIntent().getExtras().getString(NewsDetailFragment.ARG_CODE_NEWS);
//
//        codeSv = getIntent().getExtras().getString(ServiceDetailFragment.ARG_CODE_SV);
//
//        nameSv = getIntent().getExtras().getString(ServiceDetailFragment.ARG_NAME_SV);
//        imageSv = getIntent().getExtras().getString(ServiceDetailFragment.ARG_IMAGE_SV);
//        priceSv = getIntent().getExtras().getString(ServiceDetailFragment.ARG_PRICE_SV);
//        priceSaleSv = getIntent().getExtras().getString(ServiceDetailFragment.ARG_PRICE_SALE_SV);
//
//        codeSvScheS = getIntent().getExtras().getString(ScheduleServiceFragment.ARG_CODE_SV);
//        nameSvScheS = getIntent().getExtras().getString(ScheduleServiceFragment.ARG_NAME_SV);
//        imageSvScheS = getIntent().getExtras().getString(ScheduleServiceFragment.ARG_IMAGE_SV);
//        priceSvScheS = getIntent().getExtras().getString(ScheduleServiceFragment.ARG_PRICE_SV);
//        priceSaleSvScheS = getIntent().getExtras().getString(ScheduleServiceFragment.ARG_PRICE_SALE_SV);
//        codeMdc = getIntent().getExtras().getString(MedicalDetailFragment.ARG_CODE_MDC);
//        codeHstDt = getIntent().getExtras().getString(HistoryServiceDetailFragment.ARG_CODE_HTR);
//
//        codeHspDt = getIntent().getExtras().getString(HistoryDetailProductFragment.ARG_CODE_HTR);
//
//
//        scheduleServiceCf = getIntent().getExtras().getParcelable(ConfirmServiceFragment.ARG_SCHEDULE_FC);
//        nameSvCf = getIntent().getExtras().getString(ConfirmServiceFragment.ARG_NAME_CF);
//        imageSvCf = getIntent().getExtras().getString(ConfirmServiceFragment.ARG_IMAGE_CF);
//        priceSvCf = getIntent().getExtras().getString(ConfirmServiceFragment.ARG_PRICE_CF);
//        priceSaleCf = getIntent().getExtras().getString(ConfirmServiceFragment.ARG_PRICE_SALE_CF);
//
//
//        imgPrf = getIntent().getExtras().getString(ProfileDetailFragment.ARG_IMG_PRF);
//        namePrf = getIntent().getExtras().getString(ProfileDetailFragment.ARG_NAME_PRF);
//        phonePrf = getIntent().getExtras().getString(ProfileDetailFragment.ARG_PHONE_PRF);
//        birthdayPrf = getIntent().getExtras().getString(ProfileDetailFragment.ARG_BD_PRF);
//        addressPrf = getIntent().getExtras().getString(ProfileDetailFragment.ARG_ADR_PRF);
//        genderPrf = getIntent().getExtras().getInt(ProfileDetailFragment.ARG_GD_PRF);
//        emailPrf = getIntent().getExtras().getString(ProfileDetailFragment.ARG_EMAIL_PRF);


        //==================================================================
        navigatorFragment();
    }

    @SuppressLint("StaticFieldLeak")
    void navigatorFragment() {
        asyncTaskExecutor = AsyncTaskExecutor.executeConcurrently(new AsyncTask<Void, Void, FragmentController>() {
            @Override
            protected FragmentController doInBackground(Void... voids) {
                String title = "";
                Fragment fragment = null;
                String TAG = "";

                switch (navigator) {

//                    case NAVIGATOR_FPFDT:
//                        title = getString(R.string.backable_edit_profile);
//                        fragment = ProfileDetailFragment.newInstance(imgPrf, namePrf, phonePrf, birthdayPrf, addressPrf, genderPrf, emailPrf);
//                        TAG = ProfileDetailFragment.TAG;
//                        break;
//
//
//                    case NAVIGATOR_FFB:
//                        title = getString(R.string.backable_feedback);
//                        fragment = new FeedbackFragment();
//                        TAG = FeedbackFragment.TAG;
//                        break;
//
//                    case NAVIGATOR_FNTF:
//                        title = getString(R.string.backable_notification);
//                        fragment = new NotificationFragment();
//                        TAG = NotificationFragment.TAG;
//                        break;
//
//                    case NAVIGATOR_FCP:
//                        title = getString(R.string.backable_change_password);
//                        fragment = new ChangePasswordFragment();
//                        TAG = ChangePasswordFragment.TAG;
//                        break;
//
//                    case NAVIGATOR_FMW:
//                        title = getString(R.string.backable_my_wallet);
//                        fragment = new MyWalletFragment();
//                        TAG = MyWalletFragment.TAG;
//                        break;
//
//                    case NAVIGATOR_FMDC:
//                        title = getString(R.string.backable_medical);
//                        fragment = new MedicalFragment();
//                        TAG = MedicalFragment.TAG;
//                        break;
//
//                    case NAVIGATOR_FACH:
//                        title = getString(R.string.backable_all_category_home);
//                        fragment = new AllCategoryHomeFragment();
//                        TAG = AllCategoryHomeFragment.TAG;
//                        break;
//
//                    case NAVIGATOR_FPRMDT:
//                        title = getString(R.string.chi_tiet_km);
//                        fragment = PromotionDetailFragment.newInstance(codePrm);
//                        TAG = PromotionDetailFragment.TAG;
//
//                        Log.e("TAG", "doInBackground PRM: " + titlePrm);
//                        break;
//
//                    case NAVIGATOR_FNDT:
//                        title = getString(R.string.chi_tiet_news);
//                        fragment = NewsDetailFragment.newInstance(codeNews);
//                        TAG = NewsDetailFragment.TAG;
//                        break;

                    case NAVIGATOR_FPRDDT:
                        title = getString(R.string.chi_tiet_sp);
                        fragment = ProductDetailFragment.newInstance(codePrd);
                        TAG = ProductDetailFragment.TAG;
                        break;

//                    case NAVIGATOR_FSVDT:
//                        title = getString(R.string.chi_tiet_dich_vu);
//                        fragment = ServiceDetailFragment.newInstance(codeSv, nameSv, imageSv, priceSv, priceSaleSv);
//                        TAG = ServiceDetailFragment.TAG;
//                        break;
//
//                    case NAVIGATOR_FSCHSV:
//                        title = getString(R.string.backable_schedule_service);
//                        fragment = ScheduleServiceFragment.newInstance(codeSvScheS, nameSvScheS, imageSvScheS, priceSvScheS, priceSaleSvScheS);
//                        TAG = ScheduleServiceFragment.TAG;
//                        break;
//
//
//                    case NAVIGATOR_FCFSV:
//                        title = getString(R.string.backable_confirm_service);
//                        fragment = ConfirmServiceFragment.newInstance(scheduleServiceCf, nameSvCf, imageSvCf, priceSvCf, priceSaleCf);
//                        TAG = ConfirmServiceFragment.TAG;
//                        break;
//
//
//                    case NAVIGATOR_FMDCDT:
//                        title = "Chi tiết hồ sơ bệnh án";
//                        fragment = MedicalDetailFragment.newInstance(codeMdc);
//                        TAG = MedicalDetailFragment.TAG;
//
//                        break;
//                    case NAVIGATOR_FHTRDT:
//                        title = "Chi tiết lịch sử dịch vụ";
//                        fragment = HistoryServiceDetailFragment.newInstance(codeHstDt);
//                        TAG = HistoryServiceDetailFragment.TAG;
//
//                        break;
//                    case NAVIGATOR_FHPDT:
//                        title = "Chi tiết lịch sử sản phẩm";
//                        fragment = HistoryDetailProductFragment.newInstance(codeHspDt);
//                        TAG = HistoryDetailProductFragment.TAG;
//
//                        break;
//
//                    case NAVIGATOR_FCT:
//                        title = "Thông tin liên hệ";
//                        fragment = ContactFragment.newInstance();
//                        TAG = ContactFragment.TAG;
//
//                        break;
//                    case NAVIGATOR_FPRD:
//                        title = "Tất cả sản phẩm";
//                        fragment = ProductFragment.newInstance();
//                        TAG = ProductFragment.TAG;
//
//                        break;
//                    case NAVIGATOR_FSV:
//                        title = "Tất cả dịch vụ";
//                        fragment = ServiceFragment.newInstance();
//                        TAG = ServiceFragment.TAG;
//
//                        break;
                }
                if (fragment != null) {
                    return new FragmentController(title, fragment, TAG);
                }
                return null;
            }

            @Override
            protected void onPostExecute(FragmentController fragmentController) {
                super.onPostExecute(fragmentController);
                if (fragmentController != null) {
                    binding.toolbarBackable.setTitle(fragmentController.getTitle());
                    AppUtils.replaceFragmentToActivity(getSupportFragmentManager(), fragmentController.getFragment(), R.id.frBackable, false, fragmentController.getTag());
                }
            }
        }, null);
    }

    @Override
    protected void onDestroy() {
        if (asyncTaskExecutor != null) asyncTaskExecutor.cancel(true);
//        clearActivity(this, R.id.mainBackable);
        finish();
        super.onDestroy();
    }

    @Override
    public void onToolbarBackClick() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }

    @Override
    public void onToolbarActionRightClick() {
        //TODO
//        startActivity(new Intent(this, CartActivity.class));
//        Toast.makeText(this, "pppp", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onToolbarSearchBarHomeClick() {

    }


    // Release all resource from activity or fragment activity
    public synchronized void clearActivity(Activity activity,
                                           int rootLayout) {
        try {
            View view = activity.findViewById(rootLayout);
            if (view != null) {
                unbindViewReferences(view);
                if (view instanceof ViewGroup)
                    unbindViewGroupReferences((ViewGroup) view);
            }
            System.gc();
        } catch (Throwable e) {
        }
    }

    public synchronized void clearFragmentActivity(
            FragmentActivity activity, int rootLayout) {
        try {
            View view = activity.findViewById(rootLayout);
            if (view != null) {
                unbindViewReferences(view);
                if (view instanceof ViewGroup)
                    unbindViewGroupReferences((ViewGroup) view);
            }
            System.gc();
        } catch (Throwable e) {

        }
    }

    protected void unbindViewGroupReferences(ViewGroup viewGroup) {
        int nrOfChildren = viewGroup.getChildCount();
        for (int i = 0; i < nrOfChildren; i++) {
            View view = viewGroup.getChildAt(i);
            unbindViewReferences(view);
            if (view instanceof ViewGroup)
                unbindViewGroupReferences((ViewGroup) view);
        }
        try {
            viewGroup.removeAllViews();
        } catch (Throwable mayHappen) {
        }
    }

    protected void unbindViewReferences(View view) {
        try {
            view.setOnClickListener(null);
        } catch (Throwable mayHappen) {
        }

        try {
            view.setOnCreateContextMenuListener(null);
        } catch (Throwable mayHappen) {
        }

        try {
            view.setOnFocusChangeListener(null);
        } catch (Throwable mayHappen) {
        }

        try {
            view.setOnKeyListener(null);
        } catch (Throwable mayHappen) {
        }

        try {
            view.setOnLongClickListener(null);
        } catch (Throwable mayHappen) {
        }

        try {
            view.setOnClickListener(null);
        } catch (Throwable mayHappen) {
        }
        setNullView(view);
        if (view instanceof ImageView) {
            setNullImageView((ImageView) view);
        }

        if (view instanceof WebView) {
            ((WebView) view).destroy();
        }

        if (view instanceof Button) {
            Button bt = (Button) view;
            bt.setBackgroundResource(0);
        }
        if (view instanceof RelativeLayout) {
            RelativeLayout rl = (RelativeLayout) view;
            if (rl != null) {
                rl.destroyDrawingCache();
                rl.setBackgroundResource(0);
            }
        }
        if (view instanceof LinearLayout) {
            LinearLayout ln = (LinearLayout) view;
            if (ln != null) {
                ln.destroyDrawingCache();
                ln.setBackgroundResource(0);
            }
        }
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            if (view != null) {
                listView.destroyDrawingCache();
            }
        }

        if (view instanceof ConstraintLayout) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            if (constraintLayout != null) {
                constraintLayout.destroyDrawingCache();
                constraintLayout.setBackgroundResource(0);
            }
        }

    }

    @SuppressWarnings("deprecation")
    protected void setNullView(View view) {
        try {
            if (view != null) {
                view.setBackgroundDrawable(null);
                view.destroyDrawingCache();
            }
        } catch (Exception e) {
        }

    }

    @SuppressWarnings("deprecation")
    protected void setNullImageView(ImageView imgView) {
        try {
            if (imgView != null) {
                if (imgView.getDrawingCache() != null
                        && !imgView.getDrawingCache().isRecycled()) {
                    imgView.getDrawingCache().recycle();
                }
                imgView.destroyDrawingCache();
                imgView.setBackgroundDrawable(null);
                imgView.setImageBitmap(null);
                imgView.setImageDrawable(null);
                imgView.setBackgroundResource(0);
                imgView.setImageResource(0);
            }
        } catch (Exception e) {
        }
    }

}