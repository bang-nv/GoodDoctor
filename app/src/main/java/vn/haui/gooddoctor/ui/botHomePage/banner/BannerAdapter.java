package vn.haui.gooddoctor.ui.botHomePage.banner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemBannerBinding;
import vn.haui.gooddoctor.models.response.Banner;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerHomeViewHolder> {

    private List<Banner> banners;
    private Context context;
    private BannerHomePageFrMvpView bannerHomePageFrMvpView;


    public BannerAdapter(List<Banner> banners, Context context, BannerHomePageFrMvpView bannerHomePageFrMvpView) {
        this.banners = banners;
        this.context = context;
        this.bannerHomePageFrMvpView = bannerHomePageFrMvpView;
    }

    @NonNull
    @Override
    public BannerHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BannerHomeViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerHomeViewHolder holder, int position) {
        Glide.with(context).load(banners.get(position).getImage()).into(holder.binding.imgBanner);
        Log.e("TAG", "onBindViewHolder: " + banners.get(position).getImage());
//        holder.binding.getRoot().setOnClickListener(v -> {
//
//        });
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class BannerHomeViewHolder extends RecyclerView.ViewHolder {
        private ItemBannerBinding binding;

        public BannerHomeViewHolder(@NonNull ItemBannerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {

            });
        }
    }
}
