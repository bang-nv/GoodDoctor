package vn.haui.gooddoctor.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import vn.haui.gooddoctor.R;


public final class CommonUtils {

    private static final String TAG = vn.haui.gooddoctor.utils.CommonUtils.class.getSimpleName();
    private final static DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0");

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static Dialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static String parseMoney(Object money) {
        try {
            double number = Double.parseDouble(money.toString());
            money = decimalFormat.format(number).replaceAll(",", ".");
        } catch (Exception e) {
            return "";
        }
        return money.toString() + "đ";
    }

    public static String parseThousands(Object money) {
        try {
            double number = Double.parseDouble(money.toString());
            money = decimalFormat.format(number).replaceAll(",", ".");
        } catch (Exception e) {
            return "";
        }
        return money.toString();
    }


    public static void shortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void shortToast(Context context, @StringRes int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void longToast(Context context, @StringRes int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

//    public static String formatDateToDayddHHyyyyHHmm(Date date) {
//
//        String strDate = "";
//        if (date == null) return strDate;
//
//        try {
//            Calendar calendarInput = Calendar.getInstance();
//            calendarInput.setTime(date);
//
//            Calendar calendarInputWithoutTime = Calendar.getInstance();
//            Calendar calendarNow = Calendar.getInstance();
//            calendarNow.setTimeInMillis(calendarInputWithoutTime.getTimeInMillis());
//
//            calendarInputWithoutTime.set(Calendar.DAY_OF_MONTH, calendarInput.get(Calendar.DAY_OF_MONTH));
//            calendarInputWithoutTime.set(Calendar.MONTH, calendarInput.get(Calendar.MONTH));
//            calendarInputWithoutTime.set(Calendar.YEAR, calendarInput.get(Calendar.YEAR));
//            Date dateInputWithoutTime = calendarInputWithoutTime.getTime();
//
//            Date dateNow = calendarNow.getTime();
//            calendarNow.add(Calendar.DATE, -1);
//            Date dateYesterday = calendarNow.getTime();
//            calendarNow.add(Calendar.DATE, 2);
//            Date dateTomorrow = calendarNow.getTime();
//
//            SimpleDateFormat sdfFormatDDMMYY = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//            if(dateInputWithoutTime.equals(dateTomorrow)) {
//                strDate = String.format("Ngày mai, %s",sdfFormatDDMMYY.format(date));
//            } else if(dateInputWithoutTime.equals(dateYesterday)) {
//                strDate += String.format("Hôm qua, %s", sdfFormatDDMMYY.format(date));
//            } else if(dateInputWithoutTime.equals(dateNow)){
//                strDate = String.format("Hôm nay, %s", sdfFormatDDMMYY.format(date));
//            } else {
//                strDate = sdfFormatDDMMYY.format(date);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return strDate;
//    }

    public static String formatDateddHHyyyyHHmm(Date date) {

        String strDate = "";
        if (date == null) return strDate;

        try {
            Calendar calendarInput = Calendar.getInstance();
            calendarInput.setTime(date);

            Calendar calendarInputWithoutTime = Calendar.getInstance();
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTimeInMillis(calendarInputWithoutTime.getTimeInMillis());

            calendarInputWithoutTime.set(Calendar.DAY_OF_MONTH, calendarInput.get(Calendar.DAY_OF_MONTH));
            calendarInputWithoutTime.set(Calendar.MONTH, calendarInput.get(Calendar.MONTH));
            calendarInputWithoutTime.set(Calendar.YEAR, calendarInput.get(Calendar.YEAR));

            SimpleDateFormat sdfFormatDDMMYY = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            strDate = sdfFormatDDMMYY.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    static boolean check = false;

    public static void hideShowPassword(EditText editText, ImageView imageView) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                imageView.setVisibility(String.valueOf(charSequence).equals("") ? View.INVISIBLE : View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imageView.setOnClickListener(view -> {
            check = !check;
            if (check) {
                editText.setTransformationMethod(new HideReturnsTransformationMethod());
                imageView.setImageResource(R.drawable.ic_hide);
                editText.setSelection(editText.length());
            } else {
                editText.setTransformationMethod(new PasswordTransformationMethod());
                imageView.setImageResource(R.drawable.ic_show);
                editText.setSelection(editText.length());
            }
        });
    }

    public static boolean containsWhiteSpace(final String testString) {
        if (testString != null) {
            for (int i = 0; i < testString.length(); i++) {
                if (Character.isWhitespace(testString.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
