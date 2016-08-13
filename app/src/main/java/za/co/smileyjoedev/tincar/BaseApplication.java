package za.co.smileyjoedev.tincar;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orm.SugarContext;

/**
 * Created by cody on 2016/08/13.
 */
public class BaseApplication extends Application {

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        SugarContext.init(this);
    }

}
