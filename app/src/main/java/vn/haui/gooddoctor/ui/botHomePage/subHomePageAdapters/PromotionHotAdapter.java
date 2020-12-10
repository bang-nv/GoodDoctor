package vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemPromotionBinding;
import vn.haui.gooddoctor.models.response.Promotion;
import vn.haui.gooddoctor.ui.botHomePage.HomePageFrMvpView;

public class PromotionHotAdapter extends RecyclerView.Adapter<PromotionHotAdapter.PromotionHotViewHolder> {
    private List<Promotion> promotions;
    private Context context;
    private HomePageFrMvpView homePageFrMvpView;

    private long mLastClickTime = 0;


    public PromotionHotAdapter(List<Promotion> promotions, Context context, HomePageFrMvpView homePageFrMvpView) {
        this.promotions = promotions;
        this.context = context;
        this.homePageFrMvpView = homePageFrMvpView;
    }


    @NonNull
    @Override
    public PromotionHotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PromotionHotViewHolder(ItemPromotionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionHotViewHolder holder, int position) {
        Glide.with(context).load(promotions.get(position).getImage()).into(holder.binding.imgPromotion);
        holder.binding.tvTitlePromotion.setText(promotions.get(position).getTitle());
        holder.binding.tvTimeStart.setText("Thời gian bắt đầu: " + promotions.get(position).getStartAt());
        holder.binding.tvTimeEnd.setText("Thời gian kết thúc: " + promotions.get(position).getEndAt());
        holder.binding.tvContent.setText(promotions.get(position).getExcerpt());

//        holder.binding.getRoot().setOnClickListener(v -> {
//
//            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                return;
//            }
//            mLastClickTime = SystemClock.elapsedRealtime();
//
//            homePageFrMvpView.onClickItemListPromotionHot(position, promotions.get(position).getCode());
//
//        });


    }

    @Override
    public int getItemCount() {
        if (promotions == null) {
            return 0;
        } else if (promotions.size() < 2) {
            return promotions.size();
        } else {
            return 2;
        }
    }

    public class PromotionHotViewHolder extends RecyclerView.ViewHolder {
        private ItemPromotionBinding binding;

        public PromotionHotViewHolder(@NonNull ItemPromotionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                homePageFrMvpView.onClickItemListPromotionHot(getAdapterPosition(), promotions.get(getAdapterPosition()).getCode());

            });

        }
    }

}

