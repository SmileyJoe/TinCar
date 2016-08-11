package za.co.smileyjoedev.tincar.object;

import za.co.smileyjoedev.tincar.helper.JsonHelper;

/**
 * Created by cody on 2016/08/11.
 */
public class User {

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mMobileNumber;
    private Extra mContactMethod;
    private Extra mId;

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setMobileNumber(String mobileNumber) {
        mMobileNumber = mobileNumber;
    }

    public void setContactMethod(Extra contactMethod) {
        mContactMethod = contactMethod;
    }

    public void setId(Extra id) {
        mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getMobileNumber() {
        return mMobileNumber;
    }

    public Extra getContactMethod() {
        return mContactMethod;
    }

    public Extra getId() {
        return mId;
    }

    public static User fromApiResponse(JsonHelper helper){
        User user = new User();

        user.setFirstName(helper.getString("firstname"));
        user.setLastName(helper.getString("lastname"));
        user.setEmail(helper.getString("email"));
        user.setMobileNumber(helper.getString("mobile_number"));
        user.setId(Extra.fromApiResponse(helper.getObject("user_id")));
        user.setContactMethod(Extra.fromApiResponse(helper.getObject("contact_method")));

        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mMobileNumber='" + mMobileNumber + '\'' +
                ", mContactMethod=" + mContactMethod +
                ", mId=" + mId +
                '}';
    }
}
