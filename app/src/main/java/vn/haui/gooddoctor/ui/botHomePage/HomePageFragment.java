package vn.haui.gooddoctor.ui.botHomePage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import vn.haui.gooddoctor.databinding.FragmentHomePageBinding;
import vn.haui.gooddoctor.models.response.Banner;
import vn.haui.gooddoctor.models.response.CategoryService;
import vn.haui.gooddoctor.models.response.News;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.models.response.Promotion;
import vn.haui.gooddoctor.models.response.Service;
import vn.haui.gooddoctor.ui.BackableActivity;
import vn.haui.gooddoctor.ui.botHomePage.banner.ImageSliderHomeAdapter;
import vn.haui.gooddoctor.ui.botHomePage.banner.BannerHomePageFrMvpView;
import vn.haui.gooddoctor.ui.botHomePage.banner.BannerHomePageFrPresenter;
import vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters.CategoryHomeAdapter;
import vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters.NewsHotAdapter;
import vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters.ProductHotAdapter;
import vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters.PromotionHotAdapter;
import vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters.ServiceHotAdapter;
import vn.haui.gooddoctor.utils.CommonUtils;

import static android.content.Context.MODE_PRIVATE;

public class HomePageFragment extends Fragment implements HomePageFrMvpView, BannerHomePageFrMvpView
{


    public static final String TAG = HomePageFragment.class.getCanonicalName();

    private static final String POSITION_CATEGORY = "POSITION_CATEGORY";
    private static final String CATEGORY_SERVICE_POSITION = "CATEGORY_SERVICE_POSITION";
    private static final String CATEGORY_SERVICE_ID = "CATEGORY_SERVICE_ID";

    FragmentHomePageBinding binding;
    private int positionCategory = 0;
    private int categoryServicePosition = 0;
    private int categoryServiceId = 0;

    private ImageSliderHomeAdapter imageSliderHomeAdapter;

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        BannerHomePageFrPresenter bannerHomePageFrPresenter = new BannerHomePageFrPresenter(this);
        bannerHomePageFrPresenter.getListBanner();


        HomePageFrPresenter homePageFrPresenter = new HomePageFrPresenter(this);
        homePageFrPresenter.getCategoryService();
        homePageFrPresenter.getListProductHot(1);
        homePageFrPresenter.getListServiceHot(1);
        homePageFrPresenter.getListPromotionHot(1);
        homePageFrPresenter.getListNewsHot(1);


        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_TOKEN_FCM", MODE_PRIVATE);
        String token_fcm = prefs.getString("TOKEN_FCM", "No token FCM");
        Log.e("TOKENNNNN", "homepage onViewCreated: " + token_fcm);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.tvShowAllService.setOnClickListener(v -> {
            positionCategory = 0;
            goToTabActivity(positionCategory, -1, -1);
        });
        binding.tvShowAllPrd.setOnClickListener(v -> {
            positionCategory = 1;
            goToTabActivity(positionCategory, -1, -1);
        });
        binding.tvShowAllPrm.setOnClickListener(v -> {
            positionCategory = 2;
            goToTabActivity(positionCategory, -1, -1);
        });
        binding.tvShowAllNews.setOnClickListener(v -> {
            positionCategory = 3;
            goToTabActivity(positionCategory, -1, -1);
        });


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
    public void onDoneBannerHomePage(List<Banner> banners) {
        imageSliderHomeAdapter = new ImageSliderHomeAdapter(getContext());
        binding.imageSlider.setSliderAdapter(imageSliderHomeAdapter);
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //set indicator animation by using SliderLayout.IndicatorAnimations.
        // :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider.setScrollTimeInSec(2);
        binding.imageSlider.setAutoCycle(true);
        binding.imageSlider.startAutoCycle();


        List<Banner> sliderItemList = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            Banner banner = new Banner();
            banner.setImage(banners.get(i).getImage());
            sliderItemList.add(banner);
        }
        imageSliderHomeAdapter.renewItems(sliderItemList);

    }


    @Override
    public void onDoneCategoryService(List<CategoryService> categoryServices) {
//        binding.progressBar.setVisibility(View.GONE);

        CategoryHomeAdapter categoryHomeAdapter = new CategoryHomeAdapter(categoryServices, getContext(), this);
        binding.rcvService.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.rcvService.setAdapter(categoryHomeAdapter);
    }

    @Override
    public void onDoneProductHot(List<Product> listProducts) {
        ProductHotAdapter productHotAdapter = new ProductHotAdapter(listProducts, getContext(), this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rcvProduct.setLayoutManager(layoutManager);
        binding.rcvProduct.setHasFixedSize(true);
        binding.rcvProduct.setAdapter(productHotAdapter);
    }

    @Override
    public void onDoneServiceHot(List<Service> services) {
        ServiceHotAdapter serviceHotAdapter = new ServiceHotAdapter(services, getContext(), this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rcvService.setLayoutManager(layoutManager);
        binding.rcvService.setHasFixedSize(true);
        binding.rcvService.setAdapter(serviceHotAdapter);

    }

    @Override
    public void onClickItemProductHot(int position, List<Product> listProducts) {
        String codePrd = listProducts.get(position).getCode();
        startActivity(BackableActivity.newInstanceProductDetail(getContext(), BackableActivity.NAVIGATOR_FPRDDT, codePrd));
    }

    @Override
    public void onClickItemServiceHot(int position, List<Service> services) {
        String codeSv = services.get(position).getCode();
        String nameSv = services.get(position).getName();
        String imageSv = services.get(position).getImage();
        String priceSv = services.get(position).getPrice();
        String priceSaleSv = services.get(position).getPriceSale();
        //TODO
//        startActivity(
//                BackableActivity.newInstanceServiceDetail(
//                        getContext(),
//                        BackableActivity.NAVIGATOR_FSVDT,
//                        codeSv,
//                        nameSv,
//                        imageSv,
//                        priceSv,
//                        priceSaleSv
//                )
//        );
    }

    @Override
    public void onDoneListPromotionHot(List<Promotion> promotions) {
        PromotionHotAdapter promotionHotAdapter = new PromotionHotAdapter(promotions, getContext(), this);
        binding.rcvPromotion.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rcvPromotion.setAdapter(promotionHotAdapter);
    }

    @Override
    public void onClickItemListPromotionHot(int position, String code) {
        //TODO
//        startActivity(BackableActivity.newInstancePromotionDetail(getContext(), BackableActivity.NAVIGATOR_FPRMDT, code));
    }

    @Override
    public void onDoneListNewsHot(List<News> news) {
        Log.e("onDoneListNewsHot", "news size: " + news.size());
        NewsHotAdapter newsHotAdapter = new NewsHotAdapter(news, getContext(), this);
        binding.rcvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rcvNews.setAdapter(newsHotAdapter);
    }

    @Override
    public void onClickItemListNewsHot(int position, String code) {
        //TODO
//        startActivity(BackableActivity.newInstanceNewsDetail(getContext(), BackableActivity.NAVIGATOR_FNDT, code));
    }


    @Override
    public void onErr(String err) {

    }

    @Override
    public void onClickAddProducttoCart() {
//        ((MainActivity) getActivity()).getResultHomePageFragment();
        // TODO
//        ((MainActivity) Objects.requireNonNull(getActivity())).getResultHomePageFragment();
    }

    
    @Override
    public void onClickItemServiceCategory(int listSize, int position, int serviceCategoryId) {
        Log.e("ClickPosition fragment", " " + position);

        if (position < (listSize - 4)) {     // 0 < position < (listSize - 4)   of list service
            positionCategory = 0;
            categoryServicePosition = position;
            categoryServiceId = serviceCategoryId;
            goToTabActivity(positionCategory, categoryServicePosition, categoryServiceId); //
        } else if (position == (listSize - 4)) {    //product
            positionCategory = 1;
            goToTabActivity(positionCategory, -1, -1);
        } else if (position == (listSize - 3)) {    //promotion
            positionCategory = 2;
            goToTabActivity(positionCategory, -1, -1);
        } else if (position == (listSize - 2)) {    //news
            positionCategory = 3;
            goToTabActivity(positionCategory, -1, -1);
        } else if (position == (listSize - 1)) {    //all
            goToAllCategoryHome();
        }
    }

    private void goToAllCategoryHome() {
        //TODO
//        startActivity(BackableActivity.newInstanceAllCategoryHome(getContext(), BackableActivity.NAVIGATOR_FACH));
    }

    private void goToTabActivity(int positionCategory, int categoryServicePosition, int categoryServiceId) {
        CommonUtils.shortToast(getContext(), "Not full version!");
//        Intent intent = new Intent(getContext(), TabActivity.class);
//        intent.putExtra(POSITION_CATEGORY, positionCategory);
//        intent.putExtra(CATEGORY_SERVICE_POSITION, categoryServicePosition);
//        intent.putExtra(CATEGORY_SERVICE_ID, categoryServiceId);
//        Log.e("positionSV_send", "positionCategory: " + positionCategory);
//        Log.e("positionSV_send", "categoryServicePosition: " + categoryServicePosition);
//        Log.e("positionSV_send", "positionItemService: " + categoryServiceId);
//        startActivity(intent);
    }

}