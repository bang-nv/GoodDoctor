package vn.haui.gooddoctor.ui.botHomePage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.data.AppApi;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.CategoryService;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.models.response.Service;
import vn.haui.gooddoctor.models.response.News;
import vn.haui.gooddoctor.models.response.Promotion;
import vn.haui.gooddoctor.network.AppNetworkUtils;
import vn.haui.gooddoctor.ui.botHomePage.allCategoryHome.AllCategoryHomeFrMvpView;

public class HomePageFrPresenter implements HomePageFrMvpPresent {
    HomePageFrMvpView homePageFrMvpView;
    AllCategoryHomeFrMvpView allCategoryHomeFrMvpView;


    public HomePageFrPresenter(HomePageFrMvpView mainModel) {
        this.homePageFrMvpView = mainModel;
    }

    public HomePageFrPresenter(AllCategoryHomeFrMvpView allCategoryHomeFrMvpView) {
        this.allCategoryHomeFrMvpView = allCategoryHomeFrMvpView;
    }



    @Override
    public void getListProductHot(int type) {
        AppNetworkUtils.getData().getListProductHot(type).enqueue(new Callback<Response<ListResponse<Product>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<Product>>> call, retrofit2.Response<Response<ListResponse<Product>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        homePageFrMvpView.onDoneProductHot(response.body().getData().getData());
                    } else {
                        homePageFrMvpView.onErr(response.body().getMessage());
                    }
                } else {
                    homePageFrMvpView.onErr("Không thể kết nối với Server");
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<Product>>> call, Throwable t) {
                homePageFrMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getListServiceHot(int type) {
        AppNetworkUtils.getData().getListServiceHot(type).enqueue(new Callback<Response<ListResponse<Service>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<Service>>> call, retrofit2.Response<Response<ListResponse<Service>>> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        homePageFrMvpView.onDoneServiceHot(response.body().getData().getData());
                    } else {
                        homePageFrMvpView.onErr(response.body().getMessage());
                    }
                } else {
                    homePageFrMvpView.onErr("Không thể kết nối với Server");
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<Service>>> call, Throwable t) {
                homePageFrMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getListPromotionHot(int type) {
        AppNetworkUtils.getData().getListPromotionHot(type).enqueue(new Callback<Response<ListResponse<Promotion>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<Promotion>>> call, retrofit2.Response<Response<ListResponse<Promotion>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        homePageFrMvpView.onDoneListPromotionHot(response.body().getData().getData());
                    } else {
                        homePageFrMvpView.onErr(response.body().getMessage());
                    }
                } else {
                    homePageFrMvpView.onErr("Không thể kết nối với Server");
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<Promotion>>> call, Throwable t) {
                homePageFrMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getListNewsHot(int type) {
        AppNetworkUtils.getData().getListNewsHot(type).enqueue(new Callback<Response<ListResponse<News>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<News>>> call, retrofit2.Response<Response<ListResponse<News>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        homePageFrMvpView.onDoneListNewsHot(response.body().getData().getData());
                    } else {
                        homePageFrMvpView.onErr(response.body().getMessage());
                    }
                } else {
                    homePageFrMvpView.onErr("Không thể kết nối với Server");
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<News>>> call, Throwable t) {
                homePageFrMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getCategoryService() {
        homePageFrMvpView.onProcess();
        AppNetworkUtils.getData().getCategoryService().enqueue(new Callback<Response<List<CategoryService>>>() {
            @Override
            public void onResponse(Call<Response<List<CategoryService>>> call, retrofit2.Response<Response<List<CategoryService>>> response) {

                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            homePageFrMvpView.onDoneCategoryService(response.body().getData());
                        } else {
                            homePageFrMvpView.onErr(response.body().getMessage());
                        }
                    } else {
                        homePageFrMvpView.onErr("Không thể kết nối với Server");
                    }

            }

            @Override
            public void onFailure(Call<Response<List<CategoryService>>> call, Throwable t) {
                homePageFrMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getCategoryServiceAll() {
        allCategoryHomeFrMvpView.onProcess();
        AppNetworkUtils.getData().getCategoryService().enqueue(new Callback<Response<List<CategoryService>>>() {
            @Override
            public void onResponse(Call<Response<List<CategoryService>>> call, retrofit2.Response<Response<List<CategoryService>>> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        allCategoryHomeFrMvpView.onDoneCategoryService(response.body().getData());
                    } else {
                        homePageFrMvpView.onErr(response.body().getMessage());
                    }
                } else {
                    homePageFrMvpView.onErr("Không thể kết nối với Server");
                }
            }

            @Override
            public void onFailure(Call<Response<List<CategoryService>>> call, Throwable t) {
                homePageFrMvpView.onErr(t.getMessage());
            }
        });
    }
}
