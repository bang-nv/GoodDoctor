package vn.haui.gooddoctor.ui.botHomePage.subHomePageAdapters;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.haui.gooddoctor.databinding.ItemServiceHomePageBinding;
import vn.haui.gooddoctor.models.response.Service;
import vn.haui.gooddoctor.ui.botHomePage.HomePageFrMvpView;
import vn.haui.gooddoctor.utils.CommonUtils;

public class ServiceHotAdapter extends RecyclerView.Adapter<ServiceHotAdapter.ServiceHotViewHolder> {

    private List<Service> listServices;
    private Context context;
    private HomePageFrMvpView homePageFrMvpView;

    private long mLastClickTime = 0;

    public ServiceHotAdapter(List<Service> listServices, Context context, HomePageFrMvpView homePageFrMvpView) {
        this.listServices = listServices;
        this.context = context;
        this.homePageFrMvpView = homePageFrMvpView;
    }


    @NonNull
    @Override
    public ServiceHotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceHotViewHolder(
                ItemServiceHomePageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHotViewHolder holder, int position) {
        Glide.with(context).load(listServices.get(position).getImage()).into(holder.binding.imgService);
        holder.binding.tvServiceName.setText(listServices.get(position).getName());
        holder.binding.tvAmount.setText(String.format("Số lượng: %d", listServices.get(position).getAmount()));
        holder.binding.tvPriceSale.setText(CommonUtils.parseMoney(listServices.get(position).getPriceSale()));
        holder.binding.tvPrice.setText(CommonUtils.parseMoney(listServices.get(position).getPrice()));

//        holder.binding.getRoot().setOnClickListener(v -> {
//
//            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                return;
//            }
//            mLastClickTime = SystemClock.elapsedRealtime();
//
//            homePageFrMvpView.onClickItemServiceHot(position, listServices);
//        });
    }

    @Override
    public int getItemCount() {
        return listServices.size() == 0 ? 0 : listServices.size();
    }

    public class ServiceHotViewHolder extends RecyclerView.ViewHolder {
        private ItemServiceHomePageBinding binding;

        public ServiceHotViewHolder(@NonNull ItemServiceHomePageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                homePageFrMvpView.onClickItemServiceHot(getAdapterPosition(), listServices);
            });

        }
    }
}
