package vn.haui.gooddoctor.ui.botHomePage;


import java.util.List;

import vn.haui.gooddoctor.models.response.CategoryService;
import vn.haui.gooddoctor.models.response.News;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.models.response.Promotion;
import vn.haui.gooddoctor.models.response.Service;

public interface HomePageFrMvpView {
    void onProcess();

    void onErr(String err);

    void onDoneCategoryService(List<CategoryService> categoryServices);

    void onClickItemServiceCategory(int listSize, int position, int serviceCategoryId);

    void onDoneProductHot(List<Product> listProducts);

    void onClickItemProductHot(int position, List<Product> listProducts);

    void onDoneServiceHot(List<Service> services);

    void onClickItemServiceHot(int position, List<Service> services);

    void onDoneListPromotionHot(List<Promotion> promotions);

    void onClickItemListPromotionHot(int position, String code);

    void onDoneListNewsHot(List<News> news);

    void onClickItemListNewsHot(int position, String code);

    void onClickAddProducttoCart();

}
