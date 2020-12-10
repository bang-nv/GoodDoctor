package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("hash_code")
    @Expose
    private Object hashCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("trademark_id")
    @Expose
    private String trademarkId;
    @SerializedName("origin_id")
    @Expose
    private String originId;
    @SerializedName("object_id")
    @Expose
    private String objectId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("element")
    @Expose
    private String element;
    @SerializedName("tutorial")
    @Expose
    private String tutorial;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_sale")
    @Expose
    private String priceSale;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("images")
    @Expose
    private List<ListImage> images = null;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("trademark_name")
    @Expose
    private String trademarkName;
    @SerializedName("origin_name")
    @Expose
    private String originName;
    @SerializedName("object_name")
    @Expose
    private String objectName;
    @SerializedName("created")
    @Expose
    private String created;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Object getHashCode() {
        return hashCode;
    }

    public void setHashCode(Object hashCode) {
        this.hashCode = hashCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTrademarkId() {
        return trademarkId;
    }

    public void setTrademarkId(String trademarkId) {
        this.trademarkId = trademarkId;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public List<ListImage> getImages() {
        return images;
    }

    public void setImages(List<ListImage> images) {
        this.images = images;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
