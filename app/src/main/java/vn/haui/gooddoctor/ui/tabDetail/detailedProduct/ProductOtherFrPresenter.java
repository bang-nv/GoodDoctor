package vn.haui.gooddoctor.ui.tabDetail.detailedProduct;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.models.response.ProductDetail;
import vn.haui.gooddoctor.network.AppNetworkUtils;

public class ProductOtherFrPresenter implements ProductOtherFrMvpPresent {

    ProductOtherFrMvpView productOtherFrMvpView;

    public ProductOtherFrPresenter(ProductOtherFrMvpView productOtherFrMvpView) {
        this.productOtherFrMvpView = productOtherFrMvpView;
    }


    @Override
    public void getProductOther() {
        AppNetworkUtils.getData().getListProduct().enqueue(new Callback<Response<ListResponse<Product>>>() {
            @Override
            public void onResponse(Call<Response<ListResponse<Product>>> call, retrofit2.Response<Response<ListResponse<Product>>> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        productOtherFrMvpView.onDoneProductOther(response.body().getData().getData());
                    } else {
                        productOtherFrMvpView.onErr(response.body().getMessage());
                    }
                } else {
                    productOtherFrMvpView.onErr("Không thể kết nối với Server");
                }
            }

            @Override
            public void onFailure(Call<Response<ListResponse<Product>>> call, Throwable t) {
                Log.e("error", t.getMessage());
                productOtherFrMvpView.onErr(t.getMessage());
            }
        });
    }

    @Override
    public void getProductDetail(String codeProduct) {
        Log.e("TAG", "getProductDetail: " + codeProduct);
        AppNetworkUtils.getData().getProductDetail(codeProduct).enqueue(new Callback<Response<ProductDetail>>() {
            @Override
            public void onResponse(Call<Response<ProductDetail>> call, retrofit2.Response<Response<ProductDetail>> response) {
                productOtherFrMvpView.onDoneProductDetail(response.body().getData());
            }

            @Override
            public void onFailure(Call<Response<ProductDetail>> call, Throwable t) {

            }
        });
    }
}
