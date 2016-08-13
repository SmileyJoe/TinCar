package za.co.smileyjoedev.tincar.object;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.helper.DateHelper;
import za.co.smileyjoedev.tincar.helper.JsonHelper;

/**
 * Created by cody on 2016/08/10.
 */
public class Car implements Serializable{

    public static final int STATUS_NEUTRAL = 0;
    public static final int STATUS_LIKED = 1;
    public static final int STATUS_DISLIKED = 2;

    private static final long serialVersionUID = 43L;
    private static final String BASE_URL = "https://www.cheki.co.ke/";

    private long mId;
    private String mTitle;
    private HashMap<Extra.Type, Extra> mExtras;
    private Date mCreatedAt;
    private Date mUpdatedAt;
    private Date mEnableAt;
    private Date mDisableAt;
    private String mUrl;
    private String mDescription;
    private String mDefaultImageUrl;
    private Amount mAmount;
    private boolean mIsNegotiable;
    private String mEngineSize;
    private int mYear;
    private int mMileage;
    private String mRegistration;
    private boolean mMoneyBackGuarantee;
    private int mViews;
    private User mUser;
    private int mStatusId = STATUS_NEUTRAL;

    public static ArrayList<Car> fromApiResponse(JsonArray jsonArray){
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Long> seenCarIds = getSeenIds();

        for(int i = 0; i < jsonArray.size(); i++){
            JsonElement element = jsonArray.get(i);

            if(element.isJsonObject()) {
                Long id = element.getAsJsonObject().get("id").getAsLong();

                if(!seenCarIds.contains(id)){
                    cars.add(fromApiResponse(element.getAsJsonObject()));
                }
            }
        }

        return cars;
    }

    private static ArrayList<Long> getSeenIds(){
        ArrayList<Long> seendIds = new ArrayList<>();
        List<DbCarObject> objects = DbCarObject.listAll(DbCarObject.class);

        for(DbCarObject object:objects){
            seendIds.add(object.getCarId());
        }

        return seendIds;
    }

    public static Car fromApiResponse(JsonObject object){
        Car car = new Car();
        JsonHelper helper = new JsonHelper(object);

        car.setId(helper.getLong("id"));
        car.setTitle(helper.getString("title"));
        car.setCreatedAt(helper.getString("created_at"));
        car.setUpdatedAt(helper.getString("updated_at"));
        car.setEnableAt(helper.getString("enable_at"));
        car.setDisableAt(helper.getString("disable_at"));
        car.setUrl(helper.getString("url"));
        car.setDescription(helper.getString("description"));
        car.setDefaultImageUrl(helper.getString("default_image"));
        car.setAmount(Amount.fromApiResponse(helper));
        car.setNegotiable(helper.getBoolean("is_negotiable"));
        car.setEngineSize(helper.getString("engine_size"));
        car.setYear(helper.getInt("year"));
        car.setMileage(helper.getInt("mileage"));
        car.setRegistration(helper.getString("registration"));
        car.setMoneyBackGuarantee(helper.getBoolean("money_back_guarantee"));
        car.setViews(helper.getInt("views"));
        car.setUser(User.fromApiResponse(helper));

        for(Extra.Type type:Extra.Type.values()){
            car.addExtra(type, Extra.fromApiResponse(helper.getObject(type.getApiKey())));
        }

        return car;
    }

    public void addExtra(Extra.Type type, Extra extra){
        if(mExtras == null){
            mExtras = new HashMap<>();
        }

        mExtras.put(type, extra);
    }

    public String getCreatedAt(){
        return getDate(mCreatedAt);
    }

    public void setCreatedAt(String apiDate){
        mCreatedAt = getDate(apiDate);
    }

    public String getUpdatedAt(){
        return getDate(mUpdatedAt);
    }

    public void setUpdatedAt(String apiDate){
        mUpdatedAt = getDate(apiDate);
    }

    public String getEnableAt(){
        return getDate(mEnableAt);
    }

    public void setEnableAt(String apiDate){
        mEnableAt = getDate(apiDate);
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = BASE_URL + url;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDefaultImageUrl() {
        return mDefaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        mDefaultImageUrl = defaultImageUrl;
    }

    public boolean isNegotiable() {
        return mIsNegotiable;
    }

    public void setNegotiable(boolean negotiable) {
        mIsNegotiable = negotiable;
    }

    public String getEngineSize() {
        return mEngineSize;
    }

    public void setEngineSize(String engineSize) {
        mEngineSize = engineSize;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getMileage() {
        return mMileage;
    }

    public void setMileage(int mileage) {
        mMileage = mileage;
    }

    public String getRegistration() {
        return mRegistration;
    }

    public void setRegistration(String registration) {
        mRegistration = registration;
    }

    public boolean isMoneyBackGuarantee() {
        return mMoneyBackGuarantee;
    }

    public String isMoneyBackGuaranteeFormatted(Context context) {
        if(mMoneyBackGuarantee){
            return context.getString(R.string.text_yes);
        } else {
            return context.getString(R.string.text_no);
        }
    }

    public void setMoneyBackGuarantee(boolean moneyBackGuarantee) {
        mMoneyBackGuarantee = moneyBackGuarantee;
    }

    public int getViews() {
        return mViews;
    }

    public void setViews(int views) {
        mViews = views;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Amount getAmount() {
        return mAmount;
    }

    public String getAmountNegotiable(Context context){
        if(mAmount != null){
            String amount = getAmount().getFormatted();

            if(isNegotiable()){
                amount += " " + context.getString(R.string.car_detail_extra_is_negotiable);
            }

            return amount;
        } else {
            return "";
        }
    }

    public void setAmount(Amount amount) {
        mAmount = amount;
    }

    public String getDisableAt(){
        return getDate(mDisableAt);
    }

    public void setDisableAt(String apiDate){
        mDisableAt = getDate(apiDate);
    }

    private Date getDate(String apiDate){
        return DateHelper.format(apiDate, DateHelper.FORMAT_API);
    }

    private String getDate(Date date){
        return DateHelper.format(date);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
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

    public void setStatusId(int statusId) {
        mStatusId = statusId;
    }

    public int getStatusId() {
        return mStatusId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mExtras=" + mExtras +
                ", mCreatedAt=" + mCreatedAt +
                ", mUpdatedAt=" + mUpdatedAt +
                ", mEnableAt=" + mEnableAt +
                ", mDisableAt=" + mDisableAt +
                ", mUrl='" + mUrl + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mDefaultImageUrl='" + mDefaultImageUrl + '\'' +
                ", mAmount=" + mAmount +
                ", mIsNegotiable=" + mIsNegotiable +
                ", mEngineSize='" + mEngineSize + '\'' +
                ", mYear=" + mYear +
                ", mMileage=" + mMileage +
                ", mRegistration='" + mRegistration + '\'' +
                ", mMoneyBackGuarantee=" + mMoneyBackGuarantee +
                ", mViews=" + mViews +
                ", mUser=" + mUser +
                '}';
    }
}
