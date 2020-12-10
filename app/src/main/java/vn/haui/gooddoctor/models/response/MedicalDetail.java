package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicalDetail {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("booking_code")
    @Expose
    private String bookingCode;
    @SerializedName("booking_method")
    @Expose
    private String bookingMethod;
    @SerializedName("booking_address")
    @Expose
    private String bookingAddress;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("service_code")
    @Expose
    private Object serviceCode;
    @SerializedName("service_name")
    @Expose
    private Object serviceName;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("images")
    @Expose
    private List<ListImage> images = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getBookingMethod() {
        return bookingMethod;
    }

    public void setBookingMethod(String bookingMethod) {
        this.bookingMethod = bookingMethod;
    }

    public String getBookingAddress() {
        return bookingAddress;
    }

    public void setBookingAddress(String bookingAddress) {
        this.bookingAddress = bookingAddress;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Object getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(Object serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Object getServiceName() {
        return serviceName;
    }

    public void setServiceName(Object serviceName) {
        this.serviceName = serviceName;
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

    public List<ListImage> getImages() {
        return images;
    }

    public void setImages(List<ListImage> images) {
        this.images = images;
    }
}
