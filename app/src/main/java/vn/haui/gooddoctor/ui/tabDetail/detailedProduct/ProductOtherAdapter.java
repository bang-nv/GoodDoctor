package vn.haui.gooddoctor.ui.tabDetail.detailedProduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemProductOtherBinding;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.utils.CommonUtils;

public class ProductOtherAdapter extends RecyclerView.Adapter<ProductOtherAdapter.ProductDetailHolder> {

    private List<Product> products;
    private Context context;
    private ProductOtherFrMvpView productOtherFrMvpView;


    public ProductOtherAdapter(List<Product> products, Context context, ProductOtherFrMvpView productOtherFrMvpView) {
        this.products = products;
        this.context = context;
        this.productOtherFrMvpView = productOtherFrMvpView;
    }

    @NonNull
    @Override
    public ProductDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductDetailHolder(
                ItemProductOtherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailHolder holder, int position) {
        Glide.with(context).load(products.get(position).getImage()).into(holder.binding.imgProductOther);
        holder.binding.tvProductNameOther.setText(products.get(position).getName());
        holder.binding.tvAmountProductOther.setText(String.format("Số lượng: %s", products.get(position).getAmount().toString()));
        holder.binding.tvPriceSaleProductOther.setText(CommonUtils.parseMoney(products.get(position).getPriceSale()));
        holder.binding.tvPriceProductOther.setText(CommonUtils.parseMoney(products.get(position).getPrice()));

//        holder.binding.getRoot().setOnClickListener(v -> {
//            productOtherFrMvpView.onClickItemOther(position,products);
//        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ProductDetailHolder extends RecyclerView.ViewHolder {
        private ItemProductOtherBinding binding;

        public ProductDetailHolder(@NonNull ItemProductOtherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                productOtherFrMvpView.onClickItemOther(getAdapterPosition(), products);
            });
        }
    }
}
