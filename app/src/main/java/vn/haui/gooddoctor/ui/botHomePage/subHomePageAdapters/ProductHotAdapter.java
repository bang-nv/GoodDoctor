package vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.ui.botHomePage.HomePageFrMvpView;
import vn.haui.gooddoctor.utils.CommonUtils;
import vn.haui.gooddoctor.databinding.ItemProductHomePageBinding;

public class ProductHotAdapter extends RecyclerView.Adapter<ProductHotAdapter.ProductHotViewHolder> {

    private List<Product> listProducts;
    private Context context;
    private HomePageFrMvpView homePageFrMvpView;
//    private GioHangDAO gioHangDAO;

    private long mLastClickTime = 0;

    public ProductHotAdapter(List<Product> listProducts, Context context, HomePageFrMvpView homePageFrMvpView) {
        this.listProducts = listProducts;
        this.context = context;
        this.homePageFrMvpView = homePageFrMvpView;
//        gioHangDAO = new GioHangDAO(context);
    }


    @NonNull
    @Override
    public ProductHotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductHotViewHolder(
                ItemProductHomePageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHotViewHolder holder, int position) {
        Glide.with(context).load(listProducts.get(position).getImage()).into(holder.binding.imgProduct);
        holder.binding.tvProductName.setText(listProducts.get(position).getName());
        holder.binding.tvAmount.setText(String.format("Số lượng: %d", listProducts.get(position).getAmount()));
        holder.binding.tvPriceSale.setText(CommonUtils.parseMoney(listProducts.get(position).getPriceSale()));
        holder.binding.tvPrice.setText(CommonUtils.parseMoney(listProducts.get(position).getPrice()));

//        holder.binding.getRoot().setOnClickListener(v -> {
//
//            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                return;
//            }
//            mLastClickTime = SystemClock.elapsedRealtime();
//
//            homePageFrMvpView.onClickItemProductHot(position, listProducts);
//        });
        
        //TODO add vao gio hang
//        holder.itemProductHomePageBinding.btnAddProduct.setOnClickListener(v -> {
//            switch (gioHangDAO.insertGioHang(listProducts.get(position))) {
//                case -1:
//                    Toast.makeText(context, "Thêm sản phẩm thất bại !!!", Toast.LENGTH_SHORT).show();
//                    break;
//                case 0:
//                    Toast.makeText(context, "Sản phẩm đã tồn tại trong giỏ hàng !!!", Toast.LENGTH_SHORT).show();
//                    break;
//                case 1:
//                    Toast.makeText(context, "Thêm sản phẩm thành công !!!", Toast.LENGTH_SHORT).show();
//                    homePageFrMvpView.onClickAddProducttoCart();
//                    break;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public class ProductHotViewHolder extends RecyclerView.ViewHolder {
        private ItemProductHomePageBinding binding;

        public ProductHotViewHolder(@NonNull ItemProductHomePageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                homePageFrMvpView.onClickItemProductHot(getAdapterPosition(), listProducts);
            });

        }
    }

}