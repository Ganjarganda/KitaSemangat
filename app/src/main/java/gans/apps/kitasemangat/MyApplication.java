package gans.apps.kitasemangat;

import android.app.Application;
import android.os.Build;
import android.util.Log;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public static int api_android = Build.VERSION.SDK_INT;
    public static final String app_version = BuildConfig.VERSION_NAME;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static void give_tag(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d("KitaSemangat Native", msg);
        }
    }

}


