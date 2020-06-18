package com.example.bloodbank.data.api;

import com.example.bloodbank.data.model.allPosts.AllPosts;
import com.example.bloodbank.data.model.city.City;
import com.example.bloodbank.data.model.contactUs.ContactUs;
import com.example.bloodbank.data.model.createDonation.CreateDonation;
import com.example.bloodbank.data.model.displayDonationDetails.DisplayDonationDetails;
import com.example.bloodbank.data.model.donationRequest.DonationRequest;
import com.example.bloodbank.data.model.favoruitePosts.FavoruitePosts;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.data.model.newPassword.NewPassword;
import com.example.bloodbank.data.model.notificationSetting.NotificationSetting;
import com.example.bloodbank.data.model.notificationsList.NotificationsList;
import com.example.bloodbank.data.model.profile.Profile;

import com.example.bloodbank.data.model.resetPassword.ResetPassword;
import com.example.bloodbank.data.model.settingsApp.SettingsApp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // Spinner
    @POST("profile")
    @FormUrlEncoded
    Call<Profile> getProfile(@Field("api_token") String apiToken);

    @GET("governorates")
    Call<City> getGavarment();

    @GET("cities")
    Call<City> getCity(@Query("governorate_id") int governorateId);

    @GET("blood-types")
    Call<City> getBloodTypes();

    //Login
    @POST("login")
    @FormUrlEncoded
    Call<Login> Login_Call(@Field("phone") String phone, @Field("password") String password);

    //Register
    @POST("signup")
    @FormUrlEncoded
    Call<Login> registerCall(@Field("name") String name,
                             @Field("email") String email,
                             @Field("birth_date") String birthDate,
                             @Field("city_id") int cityId,
                             @Field("phone") String phone,
                             @Field("donation_last_date") String donationLastDate,
                             @Field("password") String password,
                             @Field("password_confirmation") String passwordConfirmation,
                             @Field("blood_type_id") int bloodTypeId);

    // Reset pass
    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> getPhone(@Field("phone") String phone);

    // new pass
    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> newPassword(@Field("password") String password ,
                                  @Field("password_confirmation") String passwordConfirmation);

    // All Posts
    @GET("posts")
    Call<AllPosts> ALL_POSTS_CALL(
            @Query("api_token") String api_token ,
            @Query("page") int page);

    // favorite Post
    @GET("my-favourites?")
    Call<FavoruitePosts> FAVORUITE_POSTS_CALL(@Query("api_token") String apiToken);

    //NotificationSetting
    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSetting> getNotificationsSettings(@Field("api_token") String ApiToken);

    //donation-request
    @POST("donation-request/create")
    @FormUrlEncoded
    Call<CreateDonation> DONATION_CREATE_CALL(@Field("api_token") String apiToken ,
                                                       @Field("patient_name") String name ,
                                                       @Field("patient_age") String age ,
                                                       @Field("blood_type_id") int typeId ,
                                                       @Field("bags_num") String bagsNum ,
                                                       @Field("hospital_name") String hospitalName ,
                                                       @Field("city_id") int cityId ,
                                                       @Field("phone") String phone ,
                                                       @Field("notes") String notes);

    //DonationRequest
    @GET("donation-requests?")
    Call<DonationRequest> DONATION_REQUEST_CALL (@Query("api_token") String apiToken,
                                                 @Query("page") int page);

    //DisplayDonationDetails
    @GET("donation-request?")
    Call<DisplayDonationDetails> DISPLAY_DONATION_DETAILS_CALL(@Query("api_token") String apiToken,
                                                        @Query("page") int page);

    //NotificationsList
    @GET("notifications?")
    Call<NotificationsList> NOTIFICATIONS_LIST_CALL(@Query("api_token") String apiToken,
                                                    @Query("page") int page);

    //ConnectWithUs
    @POST("contact")
    @FormUrlEncoded
    Call<ContactUs> CONTACT_US_CALL(@Field("api_token") String apiToken ,
                                    @Field("title") String title ,
                                    @Field("message") String message);

    // setting app
    @GET("settings?")
    Call<SettingsApp> SETTINGS_APP_CALL (@Query("api_token") String api_token);
}
