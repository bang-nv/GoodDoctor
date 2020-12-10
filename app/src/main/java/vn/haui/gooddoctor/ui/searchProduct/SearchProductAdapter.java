package vn.haui.gooddoctor.ui.searchProduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemProductBinding;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.utils.CommonUtils;


public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.SearchProductHolder> {

    private List<Product> Products;
    private Context context;
    private SearchProductMvpView searchProductMvpView;
//    private GioHangDAO gioHangDAO;

    public SearchProductAdapter(List<Product> Products, Context context, SearchProductMvpView searchProductMvpView) {
        this.Products = Products;
        this.context = context;
        this.searchProductMvpView = searchProductMvpView;
//        gioHangDAO = new GioHangDAO(context);
    }

    @NonNull
    @Override
    public SearchProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchProductHolder(
                ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProductHolder holder, int position) {
        Glide.with(context).load(Products.get(position).getImage()).into(holder.binding.imgProduct);
        holder.binding.tvProductName.setText(Products.get(position).getName());
        holder.binding.tvAmount.setText(String.format("Số lượng: %d", Products.get(position).getAmount()));
        holder.binding.tvPriceSale.setText(CommonUtils.parseMoney(Products.get(position).getPriceSale()));
        holder.binding.tvPrice.setText(CommonUtils.parseMoney(Products.get(position).getPrice()));
        holder.binding.getRoot().setOnClickListener(v -> {
            searchProductMvpView.onClickItem(position, Products);
        });

        //Thêm giỏ hàng
//        holder.binding.imgItemCart.setOnClickListener(v -> {
//            switch (gioHangDAO.insertGioHang(Products.get(position))){
//                case -1:
//                    Toast.makeText(context, "Thêm sản phẩm thất bại !!!", Toast.LENGTH_SHORT).show();
//                    break;
//                case 0:
//                    Toast.makeText(context, "Sản phẩm đã tồn tại trong giỏ hàng !!!", Toast.LENGTH_SHORT).show();
//                    break;
//                case 1:
//                    Toast.makeText(context, "Thêm sản phẩm thành công !!!", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return Products.size() == 0 ? 0 : Products.size();
    }

    public class SearchProductHolder extends RecyclerView.ViewHolder {
        private ItemProductBinding binding;

        public SearchProductHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
