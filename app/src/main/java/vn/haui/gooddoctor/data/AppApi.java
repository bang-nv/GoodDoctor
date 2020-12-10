package vn.haui.gooddoctor.data;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.haui.gooddoctor.models.ListResponse;
import vn.haui.gooddoctor.models.Response;
import vn.haui.gooddoctor.models.offline.ScheduleService;
import vn.haui.gooddoctor.models.request.FcmToken;
import vn.haui.gooddoctor.models.request.FeedBack;
import vn.haui.gooddoctor.models.request.ForgotPasswordRequest;
import vn.haui.gooddoctor.models.request.LoginRequest;
import vn.haui.gooddoctor.models.request.OrderRequest;
import vn.haui.gooddoctor.models.request.RegisterRequest;
import vn.haui.gooddoctor.models.response.AccumulatePoints;
import vn.haui.gooddoctor.models.response.AgencyChien;
import vn.haui.gooddoctor.models.response.Banner;
import vn.haui.gooddoctor.models.response.CategoryProduct;
import vn.haui.gooddoctor.models.response.CategoryService;
import vn.haui.gooddoctor.models.response.ChangePassword;
import vn.haui.gooddoctor.models.response.Contact;
import vn.haui.gooddoctor.models.response.DefaultUser;
import vn.haui.gooddoctor.models.response.District;
import vn.haui.gooddoctor.models.response.DistrictChien;
import vn.haui.gooddoctor.models.response.HistoryProduct;
import vn.haui.gooddoctor.models.response.HistoryProductDetail;
import vn.haui.gooddoctor.models.response.HistoryService;
import vn.haui.gooddoctor.models.response.HistoryServiceDetail;
import vn.haui.gooddoctor.models.response.MedicalDetail;
import vn.haui.gooddoctor.models.response.MedicalRecord;
import vn.haui.gooddoctor.models.response.News;
import vn.haui.gooddoctor.models.response.NewsDetail;
import vn.haui.gooddoctor.models.response.Notification;
import vn.haui.gooddoctor.models.response.Product;
import vn.haui.gooddoctor.models.response.ProductDetail;
import vn.haui.gooddoctor.models.response.Profile;
import vn.haui.gooddoctor.models.response.Promotion;
import vn.haui.gooddoctor.models.response.PromotionDetail;
import vn.haui.gooddoctor.models.response.Province;
import vn.haui.gooddoctor.models.response.ProvinceChien;
import vn.haui.gooddoctor.models.response.Service;
import vn.haui.gooddoctor.models.response.ServiceDetail;
import vn.haui.gooddoctor.models.response.Shop;
import vn.haui.gooddoctor.models.response.SubHistoryProductDetail;
import vn.haui.gooddoctor.models.response.Village;
import vn.haui.gooddoctor.models.response.WardChien;

public interface AppApi {
    @NonNull
    @GET("category/service/list")
    Call<Response<List<CategoryService>>> getCategoryService(); //HomePageFragment, ListServiceFragment

    @NonNull
    @GET("category/product/list")
    Call<Response<List<CategoryProduct>>> getCategoryProduct(); //ListProductFragment

    @NonNull
    @GET("service/list")
    Call<Response<ListResponse<Service>>> getListService(); //ListServiceFragment

    @NonNull
    @GET("service/list")
    Call<Response<ListResponse<Service>>> getListService(@Query("category_id") int category_id); // When Pick Category in ListServiceFragment  @NonNull

    @GET("service/list")
    Call<Response<ListResponse<Service>>> getListServiceHot(@Query("type") int type);

    @GET("promotion/list")
    Call<Response<ListResponse<Promotion>>> getListPromotionHot(@Query("type") int type);

    @GET("news/list")
    Call<Response<ListResponse<News>>> getListNewsHot(@Query("type") int type);

    @NonNull
    @GET("product/list")
    Call<Response<ListResponse<Product>>> getListProduct(); //ListProductFragment

    @NonNull
    @GET("product/list")
    Call<Response<ListResponse<Product>>> getListProduct(@Query("category_id") int category_id); // When Pick Category in ListProductFragment

    @GET("product/list")
    Call<Response<ListResponse<Product>>> getSearchProduct(@Query("product_name") String product_name);

    @GET("product/list")
    Call<Response<ListResponse<Product>>> getListProductHot(@Query("type") int type);

    @NonNull
    @GET("service/{service-code}/detail")
    Call<Response<ServiceDetail>> getServiceDetail(@Path("service-code") String code);

    @NonNull
    @GET("product/{product-code}/detail")
    Call<Response<ProductDetail>> getProductDetail(@Path("product-code") String code);


    @NonNull
    @GET("news/list")
    Call<Response<ListResponse<News>>> getListNews();

    @NonNull
    @GET("news/{news-code}/detail")
    Call<Response<NewsDetail>> getNewsDetail(@Path("news-code") String code);

    @NonNull
    @GET("promotion/{promotion-code}/detail")
    Call<Response<PromotionDetail>> getPromotionDetail(@Path("promotion-code") String code);

    @NonNull
    @GET("promotion/list")
    Call<Response<ListResponse<Promotion>>> getPromotion();

    @NonNull
    @GET("banner/list")
    Call<Response<ListResponse<Banner>>> getBaner();

    @NonNull
    @GET("contact/detail")
    Call<Response<Contact>> getContact();

    @NonNull
    @GET("contact/detail")
    Observable<Response<Contact>> getContactRxJava();

    @NonNull
    @GET("banner/list")
    Observable<Response<Contact>> getBannerRxJava();


    //Auth
    @NonNull
    @POST("auth/sign-in")
    Call<Response<DefaultUser>> postLogin(@Body LoginRequest loginRequest);


    //Auth
    @NonNull
    @FormUrlEncoded
    @POST("auth/verify")
    Call<Response> getVerifyCode(@Field("phone") String phone);

    //Auth
    @NonNull
    @POST("auth/register")
    Call<Response> postRegister(@Body RegisterRequest registerRequest);

    //Auth
    @NonNull
    @POST("auth/forgot-password")
    Call<Response> postForgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

    @NonNull
    @GET("profile")
    Call<Response<Profile>> getProfile(@Header("Authorization") String authHeader);

    @NonNull
    @GET("medical-record/list")
    Call<Response<ListResponse<MedicalRecord>>> getMedicalRecord(@Header("Authorization") String authHeader);

    @NonNull
    @GET("user-point/now")
    Call<Response> getUserPointNow(@Header("Authorization") String authHeader);

    @NonNull
    @GET("user-point/list")
    Call<Response<ListResponse<AccumulatePoints>>> getAccPoint(@Header("Authorization") String authHeader);

    @NonNull
    @GET("order/{order-code}/cancel")
    Call<Response> getCancelOder(@Header("Authorization") String authHeader, @Path("order-code") String codeMdc);

    @NonNull
    @GET("booking/{booking-code}/cancel")
    Call<Response> getCancelBooking(@Header("Authorization") String authHeader, @Path("booking-code") String codeMdc);


    @GET("agency/list")
    Call<Response<ListResponse<AgencyChien>>> getListAgency(
            @Header("Authorization") String token
    );

    @GET("address/province/list")
    Call<Response<List<ProvinceChien>>> getListProvince();

    @GET("address/district/list")
    Call<Response<List<DistrictChien>>> getListDistrict(
            @Query("province_id") int province_id
    );

    @NonNull
    @GET("address/province/list")
    Call<Response<List<Province>>> getProvince();

    @NonNull
    @GET("address/district/list")
    Call<Response<List<District>>> getDistrict();

    @NonNull
    @GET("address/district/list")
    Call<Response<List<District>>> getDistrict(@Query("province_id") int province_id);

    @NonNull
    @GET("address/ward/list")
    Call<Response<List<District>>> getVillage();

//    @NonNull
//    @GET("address/ward/list")
//    Call<Response<List<District>>> getVillageDistrict(@Field("district_id") int district_id);
//
//    @NonNull
//    @GET("/address/ward/list")
//    Call<Response<List<District>>> getVillageProvince(@Field("province_id") int province_id);

    @NonNull
    @GET("address/ward/list")
    Call<Response<List<Village>>> getVillage(@Query("province_id") int province_id,
                                             @Query("district_id") int district_id);


    @NonNull
    @GET("agency/list")
    Call<Response<ListResponse<Shop>>> getShop(@Header("Authorization") String authHeader);


    @NonNull
    @GET("medical-record/{medical-record-code}/detail")
    Call<Response<MedicalDetail>> getMedicalDetail(@Header("Authorization") String authHeader, @Path("medical-record-code") String codeMdc);


    @NonNull
    @POST("feedback/store")
    Call<Response<FeedBack>> postFeedBack(@Header("Authorization") String authHeader, @Body FeedBack feedBack);

    @NonNull
    @POST("firebase/save-token")
    Call<Response<FcmToken>> postFcmToken(@Header("Authorization") String authHeader, @Body FcmToken fcmToken);


    @NonNull
    @POST("change-password")
    Call<Response<ChangePassword>> postChangePassword(@Header("Authorization") String authHeader, @Body ChangePassword changePassword);

    @NonNull
    @POST("update-account")
    Call<Response> postUpdateAccount(
            @Header("Authorization") String authHeader,
            @Body RequestBody requestBody
    );

    @GET("address/ward/list")
    Call<Response<List<WardChien>>> getListWard(
            @Query("district_id") int district_id
    );

    @NonNull
    @GET("booking/list")
    Call<Response<ListResponse<HistoryService>>> getHistoryService(@Header("Authorization") String authHeader);

    @NonNull
    @GET("booking/{booking-code}/detail")
    Call<Response<HistoryServiceDetail>> getHistoryServiceDetail(@Header("Authorization") String authHeader, @Path("booking-code") String codeHtr);

    @POST("booking/store")
    Call<Response<ScheduleService>> postService(@Header("Authorization") String authHeader, @Body ScheduleService scheduleService);


    @NonNull
    @GET("order/list")
    Call<Response<ListResponse<HistoryProduct>>> getHistoryProduct(@Header("Authorization") String authHeader);

    @NonNull
    @GET("sign-out")
    Call<Response> getSignOut(@Header("Authorization") String authHeader);

    @NonNull
    @GET("order/{order-code}/detail")
    Call<Response<HistoryProductDetail>> getHistoryProductDetail(@Header("Authorization") String authHeader, @Path("order-code") String codeHtr);

    @NonNull
    @GET("order/{order-code}/detail")
    Call<Response<HistoryProductDetail<SubHistoryProductDetail>>> getSubHistoryProductDetail(@Header("Authorization") String authHeader, @Path("order-code") String codeHtr);

    @NonNull
    @GET("order/{order-code}/detail")
    Call<Response<HistoryProductDetail<SubHistoryProductDetail>>> getFirstSubHistoryProductDetail(@Header("Authorization") String authHeader, @Path("order-code") String codeHtr);

    @POST("order/store")
    Call<Response> createOrder(
            @Header("Authorization") String authHeader,
            @Body OrderRequest orderRequest
    );

    @NonNull
    @GET("notification/list")
    Call<Response<ListResponse<Notification>>> getListNotification(@Header("Authorization") String authHeader);

}
