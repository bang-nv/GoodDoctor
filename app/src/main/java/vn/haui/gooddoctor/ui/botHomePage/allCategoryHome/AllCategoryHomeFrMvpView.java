package vn.haui.gooddoctor.ui.botHomePage.allCategoryHome;

import java.util.List;

import vn.haui.gooddoctor.models.response.CategoryService;

public interface AllCategoryHomeFrMvpView {

    void onProcess();

    void onDoneCategoryService(List<CategoryService> categoryServices);

    void onClickItemServiceCategory(int listSize, int position, int serviceCategoryId);

}
