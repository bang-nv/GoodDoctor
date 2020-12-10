package vn.haui.gooddoctor.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedBack {
    @SerializedName("service_star")
    @Expose
    private String serviceStar;
    @SerializedName("quality_star")
    @Expose
    private String qualityStar;
    @SerializedName("content")
    @Expose
    private String content;

    public FeedBack(String serviceStar, String qualityStar, String content) {
        this.serviceStar = serviceStar;
        this.qualityStar = qualityStar;
        this.content = content;
    }

    public String getServiceStar() {
        return serviceStar;
    }

    public void setServiceStar(String serviceStar) {
        this.serviceStar = serviceStar;
    }

    public String getQualityStar() {
        return qualityStar;
    }

    public void setQualityStar(String qualityStar) {
        this.qualityStar = qualityStar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
