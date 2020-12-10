package vn.haui.gooddoctor.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static String TAG = SessionManager.class.getName();
    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    private static final String USER_MANAGER = "USER_MANAGER";
    private static final String IS_LOGIN = "IS_LOGIN";

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(USER_MANAGER, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLogin(boolean isLogin) {
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGIN, false);
    }
}
