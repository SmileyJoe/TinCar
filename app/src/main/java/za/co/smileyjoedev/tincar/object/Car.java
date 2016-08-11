package za.co.smileyjoedev.tincar.object;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import za.co.smileyjoedev.tincar.helper.DateHelper;
import za.co.smileyjoedev.tincar.helper.JsonHelper;

/**
 * Created by cody on 2016/08/10.
 */
public class Car {

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
    private boolean mMoneyBackGaruntee;
    private int mViews;
    private User mUser;

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

    public void setCreatedAt(String apiDate){
        mCreatedAt = getDate(apiDate);
    }

    public String getCreatedAt(){
        return getDate(mCreatedAt);
    }

    public void setUpdatedAt(String apiDate){
        mUpdatedAt = getDate(apiDate);
    }

    public String getUpdatedAt(){
        return getDate(mUpdatedAt);
    }

    public void setEnableAt(String apiDate){
        mEnableAt = getDate(apiDate);
    }

    public String getEnableAt(){
        return getDate(mEnableAt);
    }

    public void setDisableAt(String apiDate){
        mDisableAt = getDate(apiDate);
    }

    public void setUrl(String url) {
        mUrl = BASE_URL + url;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        mDefaultImageUrl = defaultImageUrl;
    }

    public String getDefaultImageUrl() {
        return mDefaultImageUrl;
    }

    public void setAmount(Amount amount) {
        mAmount = amount;
    }

    public void setNegotiable(boolean negotiable) {
        mIsNegotiable = negotiable;
    }

    public void setEngineSize(String engineSize) {
        mEngineSize = engineSize;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public void setMileage(int mileage) {
        mMileage = mileage;
    }

    public void setRegistration(String registration) {
        mRegistration = registration;
    }

    public void setMoneyBackGaruntee(boolean moneyBackGaruntee) {
        mMoneyBackGaruntee = moneyBackGaruntee;
    }

    public void setViews(int views) {
        mViews = views;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public boolean isNegotiable() {
        return mIsNegotiable;
    }

    public String getEngineSize() {
        return mEngineSize;
    }

    public int getYear() {
        return mYear;
    }

    public int getMileage() {
        return mMileage;
    }

    public String getRegistration() {
        return mRegistration;
    }

    public boolean isMoneyBackGaruntee() {
        return mMoneyBackGaruntee;
    }

    public int getViews() {
        return mViews;
    }

    public User getUser() {
        return mUser;
    }

    public Amount getAmount() {
        return mAmount;
    }

    public String getDisableAt(){
        return getDate(mDisableAt);
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

    public static ArrayList<Car> fromApiResponse(JsonArray jsonArray){
        ArrayList<Car> cars = new ArrayList<>();

        for(int i = 0; i < jsonArray.size(); i++){
            JsonElement element = jsonArray.get(i);

            if(element.isJsonObject()) {
                cars.add(fromApiResponse(element.getAsJsonObject()));
            }
        }

        return cars;
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
        car.setMoneyBackGaruntee(helper.getBoolean("money_back_guarantee"));
        car.setViews(helper.getInt("views"));
        car.setUser(User.fromApiResponse(helper));

        for(Extra.Type type:Extra.Type.values()){
            car.addExtra(type, Extra.fromApiResponse(helper.getObject(type.getApiKey())));
        }

        return car;
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
                ", mMoneyBackGaruntee=" + mMoneyBackGaruntee +
                ", mViews=" + mViews +
                ", mUser=" + mUser +
                '}';
    }
}
