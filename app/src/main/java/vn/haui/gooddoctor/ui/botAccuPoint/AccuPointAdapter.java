package vn.haui.gooddoctor.ui.botAccuPoint;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemHisAccumulatePointBinding;
import vn.haui.gooddoctor.models.response.AccumulatePoints;
import vn.haui.gooddoctor.utils.CommonUtils;

public class AccuPointAdapter extends RecyclerView.Adapter<AccuPointAdapter.AccuPointViewHolder> {
    Context context;
    List<AccumulatePoints> accumulatePoints;
    AccuPointFrMvpView accuPointFrMvpView;

    public AccuPointAdapter(Context context, List<AccumulatePoints> accumulatePoints, AccuPointFrMvpView accuPointFrMvpView) {
        this.context = context;
        this.accumulatePoints = accumulatePoints;
        this.accuPointFrMvpView = accuPointFrMvpView;
    }


    @NonNull
    @Override
    public AccuPointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccuPointViewHolder(ItemHisAccumulatePointBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccuPointViewHolder holder, int position) {
        if (!TextUtils.isEmpty(accumulatePoints.get(position).getContent())) {
            holder.binding.tvItemContent.setText(accumulatePoints.get(position).getContent());
        }
        if (!TextUtils.isEmpty(accumulatePoints.get(position).getCreatedAt())) {
            holder.binding.tvItemCreatedAt.setText(accumulatePoints.get(position).getCreatedAt());
        }
        if (!TextUtils.isEmpty(accumulatePoints.get(position).getPoint())) {
            holder.binding.tvItemPoint.setText(
                    String.format("%s điểm", CommonUtils.parseThousands(accumulatePoints.get(position).getPoint()))
            );
        }


    }

    @Override
    public int getItemCount() {
        return accumulatePoints.size();
    }

    public class AccuPointViewHolder extends RecyclerView.ViewHolder {
        private ItemHisAccumulatePointBinding binding;

        public AccuPointViewHolder(@NonNull ItemHisAccumulatePointBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
