package vn.haui.gooddoctor.models.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {
    @SerializedName("date")
    private String mDate;
    @SerializedName("discount_code")
    private String mDiscountCode;
    @SerializedName("district_id")
    private String mDistrictId;
    @SerializedName("notes")
    private String mNotes;
    @SerializedName("pay_method")
    private String mPayMethod;
    @SerializedName("products")
    private List<Product> mProducts;
    @SerializedName("province_id")
    private String mProvinceId;
    @SerializedName("receive_address")
    private String mReceiveAddress;
    @SerializedName("receive_method")
    private String mReceiveMethod;
    @SerializedName("receive_phone")
    private String mReceivePhone;
    @SerializedName("ward_id")
    private String mWardId;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDiscountCode() {
        return mDiscountCode;
    }

    public void setDiscountCode(String discountCode) {
        mDiscountCode = discountCode;
    }

    public String getDistrictId() {
        return mDistrictId;
    }

    public void setDistrictId(String districtId) {
        mDistrictId = districtId;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public String getPayMethod() {
        return mPayMethod;
    }

    public void setPayMethod(String payMethod) {
        mPayMethod = payMethod;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public String getProvinceId() {
        return mProvinceId;
    }

    public void setProvinceId(String provinceId) {
        mProvinceId = provinceId;
    }

    public String getReceiveAddress() {
        return mReceiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        mReceiveAddress = receiveAddress;
    }

    public String getReceiveMethod() {
        return mReceiveMethod;
    }

    public void setReceiveMethod(String receiveMethod) {
        mReceiveMethod = receiveMethod;
    }

    public String getReceivePhone() {
        return mReceivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        mReceivePhone = receivePhone;
    }

    public String getWardId() {
        return mWardId;
    }

    public void setWardId(String wardId) {
        mWardId = wardId;
    }

}
