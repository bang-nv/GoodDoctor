package vn.haui.gooddoctor.ui.botHomePage;

public interface HomePageFrMvpPresent {
    // các hành động để thực hiện 1 công việc   cụ thể nào đấy
    void getCategoryService();

    void getCategoryServiceAll();

    void getListProductHot(int type);

    void getListServiceHot(int type);

    void getListPromotionHot(int type);

    void getListNewsHot(int type);

}
