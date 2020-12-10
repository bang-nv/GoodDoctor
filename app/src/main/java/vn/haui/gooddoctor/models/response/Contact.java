package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slogan")
    @Expose
    private Object slogan;
    @SerializedName("excerpt")
    @Expose
    private String excerpt;
    @SerializedName("intro")
    @Expose
    private Object intro;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("headquarters")
    @Expose
    private String headquarters;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("hotline")
    @Expose
    private String hotline;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("page_facebook")
    @Expose
    private String pageFacebook;
    @SerializedName("link_youtube")
    @Expose
    private Object linkYoutube;
    @SerializedName("link_android")
    @Expose
    private Object linkAndroid;
    @SerializedName("qr_android")
    @Expose
    private String qrAndroid;
    @SerializedName("link_ios")
    @Expose
    private Object linkIos;
    @SerializedName("qr_ios")
    @Expose
    private String qrIos;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSlogan() {
        return slogan;
    }

    public void setSlogan(Object slogan) {
        this.slogan = slogan;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Object getIntro() {
        return intro;
    }

    public void setIntro(Object intro) {
        this.intro = intro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPageFacebook() {
        return pageFacebook;
    }

    public void setPageFacebook(String pageFacebook) {
        this.pageFacebook = pageFacebook;
    }

    public Object getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(Object linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public Object getLinkAndroid() {
        return linkAndroid;
    }

    public void setLinkAndroid(Object linkAndroid) {
        this.linkAndroid = linkAndroid;
    }

    public String getQrAndroid() {
        return qrAndroid;
    }

    public void setQrAndroid(String qrAndroid) {
        this.qrAndroid = qrAndroid;
    }

    public Object getLinkIos() {
        return linkIos;
    }

    public void setLinkIos(Object linkIos) {
        this.linkIos = linkIos;
    }

    public String getQrIos() {
        return qrIos;
    }

    public void setQrIos(String qrIos) {
        this.qrIos = qrIos;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }
}
