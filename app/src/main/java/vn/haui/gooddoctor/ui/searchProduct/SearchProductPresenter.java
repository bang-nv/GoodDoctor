package vn.haui.gooddoctor.ui.searchProduct;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class SearchProductPresenter implements SearchProductMvpPresent {

    SearchProductMvpView searchProductMvpView;

    public SearchProductPresenter(SearchProductMvpView searchProductMvpView) {
        this.searchProductMvpView = searchProductMvpView;
    }

    @Override
    public void getListProduct() {
        AppNetworkUtils.getData().getListProduct().enqueue(new Callback<Response<ListResponse<Product>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<Product>>> call, retrofit2.Response<Response<ListResponse<Product>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        searchProductMvpView.onDoneProduct(response.body().getData().getData());
                    } else {
                        searchProductMvpView.onErr(response.body().getMessage());
                    }
                } else {
                    searchProductMvpView.onErr("Không thể kết nối với Server");
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<Product>>> call, Throwable t) {
                Log.e("error", t.getMessage());
                searchProductMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getSearchProduct(String txtSearch) {
        AppNetworkUtils.getData().getSearchProduct(txtSearch).enqueue(new Callback<Response<ListResponse<Product>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<Product>>> call, retrofit2.Response<Response<ListResponse<Product>>> response) {
                if (response.body().getStatus() == 1) {
                    searchProductMvpView.onDoneProduct(response.body().getData().getData());
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<Product>>> call, Throwable t) {
                searchProductMvpView.onErr(t.getMessage());
            }
        });
    }


}
