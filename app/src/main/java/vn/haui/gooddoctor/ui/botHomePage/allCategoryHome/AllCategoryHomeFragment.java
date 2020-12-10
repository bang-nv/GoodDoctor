package vn.haui.gooddoctor.ui.botHomePage.allCategoryHome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

import vn.haui.gooddoctor.databinding.FragmentAllCategoryHomeBinding;
import vn.haui.gooddoctor.models.response.CategoryService;
import vn.haui.gooddoctor.ui.botHomePage.HomePageFrPresenter;
import vn.haui.gooddoctor.utils.CommonUtils;
//import vn.haui.gooddoctor.ui.tab.TabActivity; //TODO

public class AllCategoryHomeFragment extends Fragment implements AllCategoryHomeFrMvpView {

    public static final String TAG = AllCategoryHomeFragment.class.getCanonicalName();

    private static final String POSITION_CATEGORY = "POSITION_CATEGORY";
    private static final String CATEGORY_SERVICE_POSITION = "CATEGORY_SERVICE_POSITION";
    private static final String CATEGORY_SERVICE_ID = "CATEGORY_SERVICE_ID";


    FragmentAllCategoryHomeBinding binding;
    private int positionCategory = 0;
    private int categoryServicePosition = 0;
    private int categoryServiceId = 0;

    public static AllCategoryHomeFragment newInstance() {
        AllCategoryHomeFragment fragment = new AllCategoryHomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAllCategoryHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        HomePageFrPresenter  homePageFrPresenter = new HomePageFrPresenter(this);
        homePageFrPresenter.getCategoryServiceAll();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    public void onDoneCategoryService(List<CategoryService> categoryServices) {
        AllCategoryHomeAdapter allCategoryHomeAdapter = new AllCategoryHomeAdapter(categoryServices, getContext(), this);
        binding.rcAllCategory.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.rcAllCategory.setAdapter(allCategoryHomeAdapter);
    }



    @Override
    public void onClickItemServiceCategory(int listSize, int position, int serviceCategoryId) {
        Log.e("ClickPosition fragment", " " + position);

        if (position < (listSize - 3)) {     // 0 < position < (listSize - 4)   of list service
            positionCategory = 0;
            categoryServicePosition = position;
            categoryServiceId = serviceCategoryId;
            goToTabActivity(positionCategory, categoryServicePosition, categoryServiceId); //
        } else if (position == (listSize - 3)) {    //product
            positionCategory = 1;
            goToTabActivity(positionCategory, -1, -1);
        } else if (position == (listSize - 2)) {    //promotion
            positionCategory = 2;
            goToTabActivity(positionCategory, -1, -1);
        } else if (position == (listSize - 1)) {    //news
            positionCategory = 3;
            goToTabActivity(positionCategory, -1, -1);
        }
    }

    private void goToTabActivity(int positionCategory, int categoryServicePosition, int categoryServiceId) {
        CommonUtils.shortToast(getContext(), "Not full version!");

        //TODO
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