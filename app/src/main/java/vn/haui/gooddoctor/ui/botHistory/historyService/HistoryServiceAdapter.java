package vn.haui.gooddoctor.ui.botHistory.historyService;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.ItemHistoryServiceBinding;
import vn.haui.gooddoctor.models.response.HistoryService;
import vn.haui.gooddoctor.utils.CommonUtils;

public class HistoryServiceAdapter extends RecyclerView.Adapter<HistoryServiceAdapter.HistoryServiceViewHolder> {

    private List<HistoryService> historyServices;
    private Context context;
    private HistoryServiceFrMvpView historyServiceFrMvpView;

    public HistoryServiceAdapter(List<HistoryService> historyServices, Context context, HistoryServiceFrMvpView historyServiceFrMvpView) {
        this.historyServices = historyServices;
        this.context = context;
        this.historyServiceFrMvpView = historyServiceFrMvpView;
    }

    @NonNull
    @Override
    public HistoryServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryServiceViewHolder(
                ItemHistoryServiceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull HistoryServiceViewHolder holder, int position) {
        Glide.with(context).load(historyServices.get(position).getServiceImage()).into(holder.binding.ivItemImage);
        holder.binding.tvItemName.setText(historyServices.get(position).getServiceName());
        holder.binding.tvPhone.setText(String.format(historyServices.get(position).getPhone()));
        holder.binding.tvItemDateTime.setText(historyServices.get(position).getDate());

        if (historyServices.get(position).getStatus() == 0) {
            holder.binding.tvStatus.setText("Đã hủy");
            holder.binding.tvStatus.setTextColor(R.color.colorFF0000);
        } else if (historyServices.get(position).getStatus() == 1) {
            holder.binding.tvStatus.setText("Mới/ Chờ xác nhận");
            holder.binding.tvStatus.setTextColor(R.color.color1BB01A);
        } else if (historyServices.get(position).getStatus() == 2) {
            holder.binding.tvStatus.setText("Đã xác nhận");
            holder.binding.tvStatus.setTextColor(R.color.colorF6AE2D);
        } else {
            holder.binding.tvStatus.setText("Hoàn thành");
            holder.binding.tvStatus.setTextColor(R.color.colorPrimary);
        }

        holder.binding.tvItemTotalMoney.setText(String.format("%sđ", CommonUtils.parseMoney(historyServices.get(position).getTotalMoney())));

        Log.e("TAG", "onBindViewHolder: " + historyServices.get(position).getName());
        holder.binding.getRoot().setOnClickListener(v -> {
            historyServiceFrMvpView.onClick(position, historyServices);
        });
    }

    @Override
    public int getItemCount() {
        return historyServices.size();
    }

    public class HistoryServiceViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryServiceBinding binding;

        public HistoryServiceViewHolder(@NonNull ItemHistoryServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
