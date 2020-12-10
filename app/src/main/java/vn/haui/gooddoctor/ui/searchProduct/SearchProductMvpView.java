package vn.haui.gooddoctor.ui.searchProduct;

import java.util.List;

import vn.haui.gooddoctor.models.response.Product;

public interface SearchProductMvpView {
    void onProcess();

    void onDoneProduct(List<Product> listProducts);

    void onErr(String err);


    // For Adapter
    void onClickItem(int postion, List<Product> products);
}
