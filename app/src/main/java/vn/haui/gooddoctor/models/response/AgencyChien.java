package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.SerializedName;

public class AgencyChien {
    @SerializedName("address")
    private String mAddress;
    @SerializedName("code")
    private String mCode;
    @SerializedName("district_id")
    private int mDistrictId;
    @SerializedName("name")
    private String mName;
    @SerializedName("province_id")
    private int mProvinceId;
    @SerializedName("ward_id")
    private int mWardId;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public int getDistrictId() {
        return mDistrictId;
    }

    public void setDistrictId(int districtId) {
        mDistrictId = districtId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getProvinceId() {
        return mProvinceId;
    }

    public void setProvinceId(int provinceId) {
        mProvinceId = provinceId;
    }

    public int getWardId() {
        return mWardId;
    }

    public void setWardId(int wardId) {
        mWardId = wardId;
    }

}
