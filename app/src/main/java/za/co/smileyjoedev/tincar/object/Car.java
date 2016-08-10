package za.co.smileyjoedev.tincar.object;

import java.util.HashMap;

/**
 * Created by cody on 2016/08/10.
 */
public class Car {

    private long mId;
    private String mTitle;
    private HashMap<Extra.Type, Extra> mExtras;

    public void setId(long id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void addExtra(Extra.Type type, Extra extra){
        if(mExtras == null){
            mExtras = new HashMap<>();
        }

        mExtras.put(type, extra);
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Extra getExtra(Extra.Type type){
        Extra extra = null;

        if(mExtras != null){
            if(mExtras.containsKey(type)){
                extra = mExtras.get(type);
            }
        }

        return extra;
    }
}
