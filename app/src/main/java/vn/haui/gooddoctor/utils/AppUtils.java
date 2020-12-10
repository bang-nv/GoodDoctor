package vn.haui.gooddoctor.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;

import vn.haui.gooddoctor.R;
import vn.haui.gooddoctor.models.FragmentController;


public final class AppUtils {

    private AppUtils() {
    }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }

//    public static boolean isPermisstionLocationGrant(Context context) {
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static boolean isPermisstionStorageGrant(Context context) {
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static void requestPermission(Activity activity, String[] permission, PermissionCallBack permissionCallBack) {
//        Dexter.withActivity(activity)
//                .withPermissions(permission)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        permissionCallBack.onPermissionsChecked(report);
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        permissionCallBack.onPermissionRationaleShouldBeShown(permissions, token);
//                    }
//                }).check();
//    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, int frameId, boolean onBackstack, @Nullable String tag) {


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.setCustomAnimations(R.anim.attack_fragment,R.anim.detack_fragment,R.anim.backstack_in,R.anim.backstack_out);
        transaction.replace(frameId, fragment, tag);
        if (onBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();


    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean onBackstack, @Nullable String tag) {

        Fragment currentFr = fragmentManager.findFragmentById(frameId);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(currentFr);
        transaction.add(frameId, fragment, tag);

        if (onBackstack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }


    public static Fragment addFragmentsToActivity(@NonNull FragmentManager fragmentManager, List<FragmentController> fragmentControllers, int frameId, int activePosition) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentControllers.size(); i++) {
            if (i != activePosition) {
                transaction.add(frameId, fragmentControllers.get(i).getFragment(), fragmentControllers.get(i).getTag())
                        .hide(fragmentControllers.get(i).getFragment());
            }
        }
        transaction.add(frameId,
                fragmentControllers.get(activePosition).getFragment(),
                fragmentControllers.get(activePosition).getTag());
        transaction.commit();
        return fragmentControllers.get(activePosition).getFragment();

    }

    public static Fragment switchFragmentActivity(@NonNull FragmentManager fragmentManager, Fragment activedFragment, Fragment mustActiveFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(activedFragment).show(mustActiveFragment).commit();
        return mustActiveFragment;
    }

    public static void clearBackStacks(@NonNull FragmentManager fm) {

        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

    }

//    public interface PermissionCallBack {
//        void onPermissionsChecked(MultiplePermissionsReport report);
//
//        void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token);
//    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    public static ImagePicker.Builder takeImageToFragment(Fragment fr, ArrayList<Image> images) {
        return ImagePicker.with(fr)
                .setToolbarColor("#EF494D")
                .setStatusBarColor("#EF494D")
                .setToolbarTextColor("#FFFFFF")
                .setToolbarIconColor("#FFFFFF")
                .setProgressBarColor("#EF494D")
                .setBackgroundColor("#E5E5E5")
                .setMultipleMode(false)
                .setFolderMode(true)
                .setFolderTitle("Thư mục")
                .setImageTitle("Bộ sưu tập")
                .setSelectedImages(images);

    }

    public static Spanned loadHtml(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(html);
        }
    }

}
