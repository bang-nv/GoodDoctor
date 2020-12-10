package vn.haui.gooddoctor.network;


import vn.haui.gooddoctor.data.AppApi;

public class AppNetworkUtils {
    public static final String BaseURL = "http://vinadoctor.eopdev.com/api/";

    public static AppApi getData() {
        return RetrofitClient.getClient(BaseURL).create(AppApi.class);
    }
}
