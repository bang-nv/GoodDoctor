package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.SerializedName;

public class WardChien {

    @SerializedName("district_id")
    private int mDistrictId;
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("prefix")
    private String mPrefix;
    @SerializedName("province_id")
    private int mProvinceId;

    public int getDistrictId() {
        return mDistrictId;
    }

    public void setDistrictId(int districtId) {
        mDistrictId = districtId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrefix() {
        return mPrefix;
    }

    public void setPrefix(String prefix) {
        mPrefix = prefix;
    }

    public int getProvinceId() {
        return mProvinceId;
    }

    public void setProvinceId(int provinceId) {
        mProvinceId = provinceId;
    }

}
