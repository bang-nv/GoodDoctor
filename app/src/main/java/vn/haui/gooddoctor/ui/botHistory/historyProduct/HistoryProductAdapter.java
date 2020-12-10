package vn.haui.gooddoctor.ui.botHistory.historyProduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.ItemHistoryProductBinding;
import vn.haui.gooddoctor.models.response.HistoryProduct;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;
import vn.haui.gooddoctor.utils.CommonUtils;

public class HistoryProductAdapter extends RecyclerView.Adapter<HistoryProductAdapter.HistoryProductViewHolder> {

    private List<HistoryProduct> historyProducts;
    private Context context;
    private HistoryProductFrMvpView historyProductFrMvpView;
    private SubHistoryProductDetail subHistoryProductDetail;

    public HistoryProductAdapter(List<HistoryProduct> historyProducts, Context context, HistoryProductFrMvpView historyProductFrMvpView) {
        this.historyProducts = historyProducts;
        this.context = context;
        this.historyProductFrMvpView = historyProductFrMvpView;
    }


    @NonNull
    @Override
    public HistoryProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryProductViewHolder(ItemHistoryProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull HistoryProductViewHolder holder, int position) {
//        Glide.with(context).load(subHistoryProductDetail.getImage()).into(holder.itemHistoryProductBinding.ivItemImage);
//        holder.itemHistoryProductBinding.tvItemName.setText(subHistoryProductDetail.getName());

        holder.binding.tvItemName.setText(String.format("ĐH mã %s", historyProducts.get(position).getCode()));
        holder.binding.tvPhone.setText(String.format(historyProducts.get(position).getReceivePhone()));
        holder.binding.tvItemDateTime.setText(historyProducts.get(position).getDate());

        if (historyProducts.get(position).getStatus() == 0) {
            holder.binding.tvStatus.setText("Đã hủy");
            holder.binding.tvStatus.setTextColor(R.color.colorFF0000);
        } else if (historyProducts.get(position).getStatus() == 1) {
            holder.binding.tvStatus.setText("Mới/ Chờ xác nhận");
            holder.binding.tvStatus.setTextColor(R.color.color1BB01A);
        } else if (historyProducts.get(position).getStatus() == 2) {
            holder.binding.tvStatus.setText("Đã xác nhận");
            holder.binding.tvStatus.setTextColor(R.color.colorF6AE2D);
        } else {
            holder.binding.tvStatus.setText("Hoàn thành");
            holder.binding.tvStatus.setTextColor(R.color.colorPrimary);
        }

        holder.binding.tvItemTotalMoney.setText(CommonUtils.parseMoney(historyProducts.get(position).getTotalMoney()));


//        holder.binding.getRoot().setOnClickListener(v -> {
//            historyProductFrMvpView.onClick(position, historyProducts.get(position).getCode());
//        });
    }

    @Override
    public int getItemCount() {
        return historyProducts.size();
    }

    public class HistoryProductViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryProductBinding binding;

        public HistoryProductViewHolder(@NonNull ItemHistoryProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                historyProductFrMvpView.onClick(getAdapterPosition(), historyProducts.get(getAdapterPosition()).getCode());
            });
        }
    }
}
