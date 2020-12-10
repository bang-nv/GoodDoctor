package vn.haui.gooddoctor.ui.botHomePage.allCategoryHome;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.databinding.ItemCategoryHomeBinding;
import vn.haui.gooddoctor.models.response.CategoryService;;

public class AllCategoryHomeAdapter extends RecyclerView.Adapter<AllCategoryHomeAdapter.AllCategoryHomeViewHolder> {

    private List<CategoryService> categoryServices;

    private Context context;
    private AllCategoryHomeFrMvpView allCategoryHomeFrMvpView;

    public AllCategoryHomeAdapter(List<CategoryService> categoryServices, Context context, AllCategoryHomeFrMvpView allCategoryHomeFrMvpView) {
        this.categoryServices = categoryServices;
        this.context = context;
        this.allCategoryHomeFrMvpView = allCategoryHomeFrMvpView;
        //Integer id, String code, String name, String slug, String image, Integer parentId
        categoryServices.add(new CategoryService(-1, "code1", "Sản phẩm", "slug1", "anh1", -1));
        categoryServices.add(new CategoryService(-2, "code2", "Khuyễn mãi", "slug2", "anh2", -2));
        categoryServices.add(new CategoryService(-3, "code3", "Tin tức", "slug3", "anh3", -3));
    }


    @NonNull
    @Override
    public AllCategoryHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllCategoryHomeViewHolder(
                ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryHomeViewHolder holder, int position) {

        if (position < (categoryServices.size() - 3)) {
            Glide.with(context).load(categoryServices.get(position).getImage()).into(holder.binding.imgAvt);
        } else if (position == (categoryServices.size() - 3)) {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_cate_product)
                    .into(holder.binding.imgAvt);
        } else if (position == (categoryServices.size() - 2)) {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_cate_promotion)
                    .into(holder.binding.imgAvt);
        } else {    //position == (listServicesLocal.size() - 1)
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_cate_news)
                    .into(holder.binding.imgAvt);
        }

        holder.binding.tvTittle.setText(categoryServices.get(position).getName());
        Log.d("TAG", "onBindViewHolder: " + categoryServices.get(position).getName());

        holder.binding.getRoot().setOnClickListener(v -> {
            allCategoryHomeFrMvpView.onClickItemServiceCategory(categoryServices.size(), position, categoryServices.get(position).getId());
        });

    }

    @Override
    public int getItemCount() {
        return categoryServices.size();
    }

    public class AllCategoryHomeViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryHomeBinding binding;

        public AllCategoryHomeViewHolder(@NonNull ItemCategoryHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                allCategoryHomeFrMvpView.onClickItemServiceCategory(
                        categoryServices.size(), getAdapterPosition(), categoryServices.get(getAdapterPosition()).getId()
                );
            });
        }
    }
}
