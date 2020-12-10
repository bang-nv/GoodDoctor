package vn.haui.gooddoctor.ui.botChatRoom;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.Objects;

import vn.haui.gooddoctor.databinding.FragmentChatRoomBinding;
import vn.haui.gooddoctor.ui.main.MainActivity;

public class ChatRoomFragment extends Fragment implements ChatRoomFrMvpView {
    public static final String TAG = ChatRoomFragment.class.getCanonicalName();

    FragmentChatRoomBinding binding;
    ChatRoomFrPresenter chatRoomFrPresenter;

    public static ChatRoomFragment newInstance() {
        ChatRoomFragment fragment = new ChatRoomFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentChatRoomBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chatRoomFrPresenter = new ChatRoomFrPresenter(this);

        checkKeyboard();
        clickIvMoreAction();

        editTextSearchChange();
    }

    private void checkKeyboard() {
        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
//                            ((MainActivity) getActivity()).setHideBottomNavigation();
                            ((MainActivity) Objects.requireNonNull(getActivity())).setHideBottomNavigation();
                        } else {
//                            ((MainActivity) getActivity()).setShowBottomNavigation();
                            ((MainActivity) Objects.requireNonNull(getActivity())).setShowBottomNavigation();
                        }
                    }
                }
        );
    }

    private void editTextSearchChange() {
        binding.edtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                setHideIvMoreAction(String.valueOf(charSequence));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void clickIvMoreAction() {
        binding.ivMoreAction.setOnClickListener(v -> {
            binding.ivMoreAction.setVisibility(View.GONE);
            binding.llActions.setVisibility(View.VISIBLE);
        });
    }

    private void setHideIvMoreAction(String text) {
        if (text.equals("")) {
            binding.ivMoreAction.setVisibility(View.GONE);
            binding.llActions.setVisibility(View.VISIBLE);
        } else {
            binding.ivMoreAction.setVisibility(View.VISIBLE);
            binding.llActions.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
