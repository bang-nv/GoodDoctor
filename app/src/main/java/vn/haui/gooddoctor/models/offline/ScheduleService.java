package vn.haui.gooddoctor.models.offline;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleService implements Parcelable {
    @SerializedName("service_code")
    @Expose
    private String serviceCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("province_id")
    @Expose
    private String provinceId;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("ward_id")
    @Expose
    private String wardId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("pay_method")
    @Expose
    private String payMethod;
    @SerializedName("discount_code")
    @Expose
    private String discountCode;
    @SerializedName("notes")
    @Expose
    private String notes;

    public ScheduleService() {
    }

    public static final Creator<ScheduleService> CREATOR = new Creator<ScheduleService>() {
        @Override
        public ScheduleService createFromParcel(Parcel in) {
            return new ScheduleService(in);
        }

        @Override
        public ScheduleService[] newArray(int size) {
            return new ScheduleService[size];
        }
    };


    protected ScheduleService(Parcel in) {
        serviceCode = in.readString();
        phone = in.readString();
        method = in.readString();
        address = in.readString();
        provinceId = in.readString();
        districtId = in.readString();
        wardId = in.readString();
        date = in.readString();
        payMethod = in.readString();
        discountCode = in.readString();
        notes = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //        this.serviceCode = serviceCode;
        //        this.phone = phone;
        //        this.method = method;
        //        this.address = address;
        //        this.provinceId = provinceId;
        //        this.districtId = districtId;
        //        this.wardId = wardId;
        //        this.date = date;
        //        this.payMethod = payMethod;
        //        this.discountCode = discountCode;
        //        this.notes = notes;
        parcel.writeString(serviceCode);
        parcel.writeString(phone);
        parcel.writeString(method);
        parcel.writeString(address);
        parcel.writeString(provinceId);
        parcel.writeString(districtId);
        parcel.writeString(wardId);
        parcel.writeString(date);
        parcel.writeString(payMethod);
        parcel.writeString(discountCode);
        parcel.writeString(notes);
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
