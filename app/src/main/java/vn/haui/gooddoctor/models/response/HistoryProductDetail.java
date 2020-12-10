package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryProductDetail<T> {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("discount_code")
    @Expose
    private Object discountCode;
    @SerializedName("receive_method")
    @Expose
    private Integer receiveMethod;
    @SerializedName("receive_address")
    @Expose
    private String receiveAddress;
    @SerializedName("province_id")
    @Expose
    private Integer provinceId;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("ward_id")
    @Expose
    private Integer wardId;
    @SerializedName("receive_phone")
    @Expose
    private String receivePhone;
    @SerializedName("pay_method")
    @Expose
    private Integer payMethod;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("total_money")
    @Expose
    private String totalMoney;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("products")
    @Expose
    private List<T> data = null;
    @SerializedName("province_name")
    @Expose
    private String provinceName;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("ward_name")
    @Expose
    private String wardName;
    @SerializedName("created")
    @Expose
    private String created;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Object discountCode) {
        this.discountCode = discountCode;
    }

    public Integer getReceiveMethod() {
        return receiveMethod;
    }

    public void setReceiveMethod(Integer receiveMethod) {
        this.receiveMethod = receiveMethod;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
