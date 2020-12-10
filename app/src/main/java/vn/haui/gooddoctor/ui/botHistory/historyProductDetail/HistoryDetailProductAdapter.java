package vn.haui.gooddoctor.ui.botHistory.historyProductDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemDetailHistoryProductBinding;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;
import vn.haui.gooddoctor.utils.CommonUtils;

public class HistoryDetailProductAdapter extends RecyclerView.Adapter<HistoryDetailProductAdapter.HistoryDetailProductViewHolder> {

    private List<SubHistoryProductDetail> subHistoryProductDetails;
    private Context context;
    private HistoryDetailProductFrMvpView historyDetailProductFrMvpView;

    public HistoryDetailProductAdapter(List<SubHistoryProductDetail> subHistoryProductDetails, Context context, HistoryDetailProductFrMvpView historyDetailProductFrMvpView) {
        this.subHistoryProductDetails = subHistoryProductDetails;
        this.context = context;
        this.historyDetailProductFrMvpView = historyDetailProductFrMvpView;
    }

    @NonNull
    @Override
    public HistoryDetailProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryDetailProductViewHolder(
                ItemDetailHistoryProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull HistoryDetailProductViewHolder holder, int position) {
        Glide.with(context).load(subHistoryProductDetails.get(position).getImage()).into(holder.binding.ivItemImage);
        holder.binding.tvItemName.setText(subHistoryProductDetails.get(position).getName());
        holder.binding.tvItemPrice.setText(CommonUtils.parseMoney(subHistoryProductDetails.get(position).getPrice()));
        holder.binding.tvItemAmount.setText(String.valueOf(subHistoryProductDetails.get(position).getAmount()));

    }

    @Override
    public int getItemCount() {
        return subHistoryProductDetails.size();
    }

    public class HistoryDetailProductViewHolder extends RecyclerView.ViewHolder {
        private ItemDetailHistoryProductBinding binding;

        public HistoryDetailProductViewHolder(@NonNull ItemDetailHistoryProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
