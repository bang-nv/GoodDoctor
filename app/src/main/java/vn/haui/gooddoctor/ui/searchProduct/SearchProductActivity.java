package vn.haui.gooddoctor.ui.searchProduct;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vn.haui.gooddoctor.databinding.ActivitySearchProductBinding;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.ui.BackableActivity;
import vn.haui.gooddoctor.utils.CommonUtils;
import vn.haui.gooddoctor.utils.KeyboardUtils;

public class SearchProductActivity extends AppCompatActivity implements SearchProductMvpView {
    ActivitySearchProductBinding binding;
    SearchProductAdapter searchProductAdapter;

    private SearchProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setupActivity();

        presenter = new SearchProductPresenter(this);
        presenter.getListProduct();

        editTextSearchChange();
    }


    private void editTextSearchChange() {
        binding.edtSearchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                binding.ivClearEditText.setVisibility(String.valueOf(charSequence).equals("") ? View.GONE : View.VISIBLE);

                presenter.getSearchProduct(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.edtSearchProduct.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.edtSearchProduct.clearFocus();
            }
            return false;
        });
    }

    private void setupActivity() {

        binding.edtSearchProduct.requestFocus();

        binding.ivBack.setOnClickListener(v1 -> {
            finish();
            super.onBackPressed();
        });

        binding.ivClearEditText.setOnClickListener(v2 -> {
            binding.edtSearchProduct.setText("");
            binding.edtSearchProduct.clearFocus();
        });

        binding.ivSearchProduct.setOnClickListener(v3 -> {
            binding.edtSearchProduct.clearFocus();
            KeyboardUtils.hideSoftInput(this);
        });

    }


    @Override
    public void onProcess() {
    }

    @Override
    public void onDoneProduct(List<Product> listProducts) {
        searchProductAdapter = new SearchProductAdapter(listProducts, this, this);
        binding.rcProduct.setAdapter(searchProductAdapter);
        Log.e("SearchProduct", "onDoneProduct: listProducts size" + listProducts.size());
    }

    @Override
    public void onErr(String err) {
        CommonUtils.shortToast(this, err);
        Log.e("SearchProduct", "onErr: " + err);
    }

    @Override
    public void onClickItem(int postion, List<Product> products) {
        String codePrd = products.get(postion).getCode();
        startActivity(BackableActivity.newInstanceProductDetail(this, BackableActivity.NAVIGATOR_FPRDDT, codePrd));
    }
}