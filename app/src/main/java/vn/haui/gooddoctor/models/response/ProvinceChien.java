package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.SerializedName;

public class ProvinceChien {

    @SerializedName("code")
    private String mCode;
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
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

}
