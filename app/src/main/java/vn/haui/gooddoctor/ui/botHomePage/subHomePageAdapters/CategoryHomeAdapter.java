package vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.ItemCategoryHomeBinding;
import vn.haui.gooddoctor.models.response.CategoryService;
import vn.haui.gooddoctor.ui.botHomePage.HomePageFrMvpView;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeViewHolder> {

    private List<CategoryService> categoryServices;
    private List<CategoryService> categoryServicesLocal = new ArrayList<>();

    private Context context;
    private HomePageFrMvpView homePageFrMvpView;

    public CategoryHomeAdapter(List<CategoryService> categoryServices, Context context, HomePageFrMvpView homePageFrMvpView) {
        this.categoryServices = categoryServices;
        this.context = context;
        this.homePageFrMvpView = homePageFrMvpView;
        for (int i = 0; i < 5; i++) {
            categoryServicesLocal.add(i, categoryServices.get(i));
        }
        //Integer id, String code, String name, String slug, String image, Integer parentId
        categoryServicesLocal.add(new CategoryService(-1, "code1", "Sản phẩm", "slug1", "anh1", -1));
        categoryServicesLocal.add(new CategoryService(-2, "code2", "Khuyễn mãi", "slug2", "anh2", -2));
        categoryServicesLocal.add(new CategoryService(-3, "code3", "Tin tức", "slug3", "anh3", -3));
        categoryServicesLocal.add(new CategoryService(-4, "code4", "Xem thêm", "slug4", "anh4", -4));
    }


    @NonNull
    @Override
    public CategoryHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHomeViewHolder(ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeViewHolder holder, int position) {

        if (position < (categoryServicesLocal.size() - 4)) {
            Glide.with(context).load(categoryServicesLocal.get(position).getImage()).into(holder.binding.imgAvt);
        } else if (position == (categoryServicesLocal.size() - 4)) {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_cate_product)
                    .into(holder.binding.imgAvt);
        } else if (position == (categoryServicesLocal.size() - 3)) {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_cate_promotion)
                    .into(holder.binding.imgAvt);
        } else if (position == (categoryServicesLocal.size() - 2)) {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_cate_news)
                    .into(holder.binding.imgAvt);
        } else { //position == (listServicesLocal.size() - 1)
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_cate_more)
                    .into(holder.binding.imgAvt);
        }

        holder.binding.tvTittle.setText(categoryServicesLocal.get(position).getName());
        Log.d("TAG", "onBindViewHolder: " + categoryServicesLocal.get(position).getName());

        holder.binding.getRoot().setOnClickListener(v -> {
            if (position < (categoryServicesLocal.size() - 4)) {
                homePageFrMvpView.onClickItemServiceCategory(categoryServicesLocal.size(), position, categoryServices.get(position).getId());
            } else {
                homePageFrMvpView.onClickItemServiceCategory(categoryServicesLocal.size(), position, -1);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (categoryServicesLocal.size() > 9) {
            return 9;
        } else {
            return categoryServicesLocal.size();
        }
    }

    public class CategoryHomeViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryHomeBinding binding;

        public CategoryHomeViewHolder(@NonNull ItemCategoryHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
