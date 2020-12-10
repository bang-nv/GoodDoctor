package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceDetail {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
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
    private Object element;
    @SerializedName("tutorial")
    @Expose
    private Object tutorial;
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
    private List<ImageService> images = null;
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
    @SerializedName("advantages")
    @Expose
    private List<Advantage> advantages = null;
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

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Object getTutorial() {
        return tutorial;
    }

    public void setTutorial(Object tutorial) {
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

    public List<ImageService> getImages() {
        return images;
    }

    public void setImages(List<ImageService> images) {
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

    public List<Advantage> getAdvantages() {
        return advantages;
    }

    public void setAdvantages(List<Advantage> advantages) {
        this.advantages = advantages;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
