package vn.haui.gooddoctor.ui.tabDetail.detailedProduct;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import vn.haui.gooddoctor.databinding.FragmentProductDetailBinding;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.models.response.ProductDetail;
import vn.haui.gooddoctor.ui.BackableActivity;
import vn.haui.gooddoctor.utils.CommonUtils;

//import vn.haui.gooddoctor.Database.GioHangDAO;
//import vn.haui.gooddoctor.ui.tab.TabActivity;


public class ProductDetailFragment extends Fragment implements ProductOtherFrMvpView {

    public static final String TAG = ProductDetailFragment.class.getCanonicalName();
    public static final String ARG_CODE_PRD = "ARG_CODE_PRD";

    FragmentProductDetailBinding binding;
    ProductOtherFrPresenter presenter;

    String codePrd;

//    private GioHangDAO gioHangDAO;


    public static ProductDetailFragment newInstance(String codePrd) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODE_PRD, codePrd);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // chuyen code sang de hien thi chi tiet
        codePrd = getArguments().getString(ARG_CODE_PRD);
        //TODO
//        gioHangDAO = new GioHangDAO(getContext());


        presenter = new ProductOtherFrPresenter(this);
        presenter.getProductOther();
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            presenter.getProductDetail(codePrd);
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO
//        binding.tvShowAllPrd.setOnClickListener(v -> startActivity(BackableActivity.newInstanceProduct(getContext(), BackableActivity.NAVIGATOR_FPRD)));

    }

    private void backToTabActivity() {
        //TODO
//        Intent intent = new Intent(getContext(), TabActivity.class);
//        intent.putExtra("positionCategory", 0);
//        intent.putExtra("positionItemService", 0);
//        startActivity(intent);
    }


    @Override
    public void onProcess() {

    }

    @Override
    public void onDoneProductOther(List<Product> products) {
        ProductOtherAdapter productOtherAdapter = new ProductOtherAdapter(products, getContext(), this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rcvProductOther.setLayoutManager(layoutManager);
        binding.rcvProductOther.setAdapter(productOtherAdapter);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDoneProductDetail(ProductDetail productDetail) {
        binding.tvProductName.setText(productDetail.getName());
        binding.tvType.setText(productDetail.getCategoryName());
        binding.tvAmount.setText(productDetail.getAmount().toString());
        binding.tvPrice.setText(CommonUtils.parseMoney(productDetail.getPrice()));
        binding.tvPriceSale.setText(CommonUtils.parseMoney(productDetail.getPriceSale()));
        binding.tvDescription.setText("Mô tả: " + productDetail.getDescription());
//                Glide.with(getContext()).load(response.body().getData().getImages());
//                Log.e("TAG", "onResponse: " +  response.body().getData().getImages());
//
//
//                        TextSliderView textSliderView = new TextSliderView(getContext());
//                        textSliderView
//                                .image(String.valueOf(response.body().getData().getImages()))
//                                .setScaleType(BaseSliderView.ScaleType.CenterCrop);
//                        binding.sliderProduct.addSlider(textSliderView);


        Product listproduct3 = new Product();
        listproduct3.setCode(productDetail.getCode());
        listproduct3.setName(productDetail.getName());
        listproduct3.setCategoryName(productDetail.getCategoryName());
        listproduct3.setObjectName(productDetail.getObjectName());
        listproduct3.setCreated(productDetail.getCreated());
        listproduct3.setDescription(productDetail.getDescription());
        listproduct3.setPriceSale(productDetail.getPriceSale());
        listproduct3.setPrice(productDetail.getPrice());
        listproduct3.setAmount(productDetail.getAmount());

        listproduct3.setImage(String.valueOf(productDetail.getImages().get(0).getImage()));


        // Thêm vào giỏ hàng
//        binding.btnAddProduct.setOnClickListener(v -> {
//            switch (gioHangDAO.insertGioHang(listproduct3)) {
//                case -1:
//                    Toast.makeText(getContext(), "Thêm sản phẩm thất bại !!!", Toast.LENGTH_SHORT).show();
//                    break;
//                case 0:
//                    Toast.makeText(getContext(), "Sản phẩm đã tồn tại trong giỏ hàng !!!", Toast.LENGTH_SHORT).show();
//                    break;
//                case 1:
//                    Toast.makeText(getContext(), "Thêm sản phẩm thành công !!!", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        });

    }

    @Override
    public void onErr(String err) {

    }

    @Override
    public void onClickItemOther(int postion, List<Product> products) {
        String codePrd = products.get(postion).getCode();
        startActivity(BackableActivity.newInstanceProductDetail(getContext(), BackableActivity.NAVIGATOR_FPRDDT, codePrd));
        getActivity().finish();
    }


}