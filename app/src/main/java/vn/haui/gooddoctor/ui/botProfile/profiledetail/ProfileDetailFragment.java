package vn.haui.gooddoctor.ui.botProfile.profiledetail;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import vn.haui.gooddoctor.databinding.FragmentProfileDetailBinding;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.network.AppNetworkUtils;

import static android.content.Context.MODE_PRIVATE;

public class ProfileDetailFragment extends Fragment {

    public static final String TAG = ProfileDetailFragment.class.getCanonicalName();
    public static final String ARG_IMG_PRF = "ARG_IMG_PRF";
    public static final String ARG_NAME_PRF = "ARG_NAME_PRF";
    public static final String ARG_PHONE_PRF = "ARG_PHONE_PRF";
    public static final String ARG_BD_PRF = "ARG_BD_PRF";
    public static final String ARG_ADR_PRF = "ARG_ADR_PRF";
    public static final String ARG_GD_PRF = "ARG_GD_PRF";
    public static final String ARG_EMAIL_PRF = "ARG_EMAIL_PRF";

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int SELECT_REQUEST_CODE = 1;
    private Button captureImage, selectImage;
    private ImageView imageView;
    private ProgressDialog progressDialog;

    FragmentProfileDetailBinding binding;

    String imgPrf;
    String namePrf;
    String phonePrf;
    String birthdayPrf;
    String addressPrf;
    int genderPrf;
    String emailPrf;
    String token;

    int radioButtonID;
    View radioButton;
    String brth;
    File image;
    File file;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    public static ProfileDetailFragment newInstance(String imgPrf, String namePrf, String phonePrf, String birthdayPrf, String addressPrf, int genderPrf, String emailPrf) {
        ProfileDetailFragment fragment = new ProfileDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMG_PRF, imgPrf);
        args.putString(ARG_NAME_PRF, namePrf);
        args.putString(ARG_PHONE_PRF, phonePrf);
        args.putString(ARG_BD_PRF, birthdayPrf);
        args.putString(ARG_ADR_PRF, addressPrf);
        args.putInt(ARG_GD_PRF, genderPrf);
        args.putString(ARG_EMAIL_PRF, emailPrf);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        imgPrf = getArguments().getString(ARG_IMG_PRF);
        namePrf = getArguments().getString(ARG_NAME_PRF);
        phonePrf = getArguments().getString(ARG_PHONE_PRF);
        birthdayPrf = getArguments().getString(ARG_BD_PRF);
        addressPrf = getArguments().getString(ARG_ADR_PRF);
        genderPrf = getArguments().getInt(ARG_GD_PRF, 1);
        emailPrf = getArguments().getString(ARG_EMAIL_PRF);

        Glide.with(getContext()).load(imgPrf).into(binding.imgAvt);
        binding.edtFullName.setText(namePrf);
        binding.edtPhone.setText(phonePrf);
        binding.edtAddress.setText(addressPrf);
        binding.edtEmail.setText(emailPrf);
        binding.tvBirthday.setText(birthdayPrf);


        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        token = prefs.getString("token", "No token");

        Log.e("TokenApi", "onCreateView: " + token);

//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Image Upload....");

        if (genderPrf == 0) {
            binding.rdFemale.setChecked(true);
        } else if (genderPrf == 1) {
            binding.rdMale.setChecked(true);
        } else {
            binding.rdOtherGender.setChecked(true);
        }

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    binding.tvBirthday.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTimeInMillis()));
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        binding.btnUpdate.setOnClickListener(v -> {
            binding.pbLoading.setVisibility(View.VISIBLE);
            binding.llPrfDt.setVisibility(View.INVISIBLE);
            String name = binding.edtFullName.getText().toString().trim();
            // gender
            radioButtonID = binding.radioGrp.getCheckedRadioButtonId();
            radioButton = binding.radioGrp.findViewById(radioButtonID);
            genderPrf = binding.radioGrp.indexOfChild(radioButton);
            //
            String phone = binding.edtPhone.getText().toString().trim();
            String address = binding.edtAddress.getText().toString().trim();
            String email = binding.edtEmail.getText().toString().trim();

            String birthday = binding.tvBirthday.getText().toString().trim();

            //AppApi appApi = RetrofitClients.getInstance().create(AppApi.class);

            MultipartBody.Part bodyFileNews = null;
            if (file != null) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                bodyFileNews = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
            }

            RequestBody body;
            if (bodyFileNews != null) {
                body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("name", name)
                        .addFormDataPart("address", address)
                        .addFormDataPart("email", email)
                        .addPart(bodyFileNews)
                        .addFormDataPart("gender", String.valueOf(genderPrf))
                        .addFormDataPart("birthday", birthday)
                        .build();
            } else {
                body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("name", name)
                        .addFormDataPart("address", address)
                        .addFormDataPart("email", email)
                        .addFormDataPart("avatar", "")
                        .addFormDataPart("gender", String.valueOf(genderPrf))
                        .addFormDataPart("birthday", birthday)
                        .build();
            }

            AppNetworkUtils.getData().postUpdateAccount(token, body).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        binding.pbLoading.setVisibility(View.GONE);
                        binding.llPrfDt.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });


        });

        binding.imgEditAvatar.setOnClickListener(v -> {
            if (CheckPermission()) {
                ImagePicker.with(this)
                        .setFolderMode(true)
                        .setFolderTitle("Album")
                        .setDirectoryName("Image Picker")
                        .setMultipleMode(true)
                        .setShowNumberIndicator(true)
                        .setMaxSize(1)
                        .setLimitMessage("You can select up to 1 images")
                        .setRequestCode(100)
                        .start();
            }
        });

        //ImagePicker.with(getActivity())

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 100)) {
            List<Image> images = ImagePicker.getImages(data);
            for (Image image : images) {
                binding.imgAvt.setImageURI(image.getUri());
                file = new File(image.getPath());
            }
        }
    }

    private void ImageUpload(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        image = new File(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        //Log.e("TAG", "ImageUpload: " + image.getPath());

    }


    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder((Activity) getContext())
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions((Activity) getContext(),
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


//                                startActivity(new Intent((Activity) getContext(), MainActivity.class));
//                                MainActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }


}