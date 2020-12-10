package vn.haui.gooddoctor.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("user_name")
    @Expose
    private Object userName;
    @SerializedName("user_created")
    @Expose
    private String userCreated;
    @SerializedName("order_code")
    @Expose
    private Object orderCode;
    @SerializedName("booking_code")
    @Expose
    private Object bookingCode;
    @SerializedName("created")
    @Expose
    private String created;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public Object getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Object orderCode) {
        this.orderCode = orderCode;
    }

    public Object getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(Object bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
