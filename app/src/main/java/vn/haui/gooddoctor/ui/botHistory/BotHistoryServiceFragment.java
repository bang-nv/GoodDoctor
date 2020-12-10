package vn.haui.gooddoctor.ui.botHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.haui.gooddoctor.databinding.FragmentBotHistoryServiceBinding;
import vn.haui.gooddoctor.ui.botChatRoom.ChatRoomFrMvpView;
import vn.haui.gooddoctor.ui.botChatRoom.ChatRoomFrPresenter;
import vn.haui.gooddoctor.ui.botHistory.historyProduct.HistoryProductFragment;
import vn.haui.gooddoctor.ui.botHistory.historyService.HistoryServiceFragment;

public class BotHistoryServiceFragment extends Fragment implements ChatRoomFrMvpView {
    public static final String TAG = BotHistoryServiceFragment.class.getCanonicalName();


    FragmentBotHistoryServiceBinding binding;
    ChatRoomFrPresenter chatRoomFrPresenter;

    public static BotHistoryServiceFragment newInstance() {
        BotHistoryServiceFragment fragment = new BotHistoryServiceFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBotHistoryServiceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupViewpager(binding.viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewpager(ViewPager viewPager) {

        BotHistoryServiceAdapter adapter = new BotHistoryServiceAdapter(getChildFragmentManager());

        adapter.addFragment(new HistoryProductFragment(), "Lịch sử mua hàng");
        adapter.addFragment(new HistoryServiceFragment(), "Lịch sử dịch vụ");

        binding.viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
