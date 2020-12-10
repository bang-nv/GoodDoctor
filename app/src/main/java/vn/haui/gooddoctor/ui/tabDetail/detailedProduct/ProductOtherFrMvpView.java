package vn.haui.gooddoctor.ui.tabDetail.detailedProduct;

import java.util.List;

import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.models.response.ProductDetail;


public interface ProductOtherFrMvpView {
    void onProcess();

    void onDoneProductOther(List<Product> products);

    void onDoneProductDetail(ProductDetail productDetail);

    void onErr(String err);

    void onClickItemOther(int postion, List<Product> products);
}
