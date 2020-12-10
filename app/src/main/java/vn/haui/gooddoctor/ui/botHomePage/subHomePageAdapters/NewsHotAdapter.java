package vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemNewsBinding;
import vn.haui.gooddoctor.models.response.News;
import vn.haui.gooddoctor.ui.botHomePage.HomePageFrMvpView;

public class NewsHotAdapter extends RecyclerView.Adapter<NewsHotAdapter.NewsHotViewHolder> {

    private List<News> news;
    private Context context;
    private HomePageFrMvpView homePageFrMvpView;

    private long mLastClickTime = 0;

    public NewsHotAdapter(List<News> news, Context context, HomePageFrMvpView homePageFrMvpView) {
        this.news = news;
        Log.e("aaaa", "NewsHotAdapter: " + news.size());
        this.context = context;
        this.homePageFrMvpView = homePageFrMvpView;
    }

    @NonNull
    @Override
    public NewsHotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHotViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHotViewHolder holder, int position) {
        News new1 = news.get(position);
        if (new1 == null) {
            return;
        }

        Glide.with(context).load(new1.getImage()).into(holder.binding.imgNews);
        holder.binding.tvTitleNews.setText(new1.getTitle());
        holder.binding.tvCreated.setText(new1.getCreated());
        holder.binding.tvContent.setText(new1.getExcerpt());

        holder.binding.getRoot().setOnClickListener(v -> {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

            homePageFrMvpView.onClickItemListNewsHot(position, news.get(position).getCode());
        });


    }

    @Override
    public int getItemCount() {
        if (news == null) {
            return 0;
        } else if (news.size() < 2) {
            return news.size();
        } else {
            return 2;
        }
    }


    public class NewsHotViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        public NewsHotViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
