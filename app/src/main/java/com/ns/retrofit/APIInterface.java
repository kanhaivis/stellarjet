package com.ns.retrofit;

import com.google.gson.JsonElement;
import com.ns.model.AddManager.ManagerResponse;
import com.ns.model.AddressDetailsPojo;
import com.ns.model.BoardingList.BoardingListResponse;
import com.ns.model.BoardingPass.BoardingPassResponse;
import com.ns.model.Booking.BookingIdResponse;
import com.ns.model.Booking.ConfirmSeatBookingResponse;
import com.ns.model.CancelBooking.CancelBookingRequest;
import com.ns.model.City.CityResponse;
import com.ns.model.CommonPojo;
import com.ns.model.ConfirmFlightBookingResponse;
import com.ns.model.ContactDetailsPojo;
import com.ns.model.FeedBack.FeedBackCategoriesResponse;
import com.ns.model.FeedBack.FeedBackRequest;
import com.ns.model.FlightScheduleResponse;
import com.ns.model.FlightSeatList.FlightSeatListResponse;
import com.ns.model.FlightSeatRequest;
import com.ns.model.FoodDay.CabPersonalizeRequest;
import com.ns.model.FoodDay.FoodPrefsUpdatePojo;
import com.ns.model.FoodMenuList.FoodMenuListResponse;
import com.ns.model.FoodMenuList.FoodPersonalizeUpdateRequest;
import com.ns.model.ForgotPasswordResponse;
import com.ns.model.Guest.GuestAddResponse;
import com.ns.model.GuestInfoRequest;
import com.ns.model.LoginResponse.LoginInfo;
import com.ns.model.OrderResponse;
import com.ns.model.PaymentResponse.MembershipCostdetailsResponse;
import com.ns.model.PaymentResponse.PaymentLastOrderDetails;
import com.ns.model.PaymentResponse.PurchaseDataBean;
import com.ns.model.PaymentResponse.PurchaseSeatDetails;
import com.ns.model.PaymentResponse.VerifyPurchaseResponse;
import com.ns.model.SeatPurchaseRequest;
import com.ns.model.SeatResponse.ConformSeatRequest;
import com.ns.model.SecondaryUser.ActiveDeactiveRequest;
import com.ns.model.SecondaryUser.SecondaryUserLoginResponse;
import com.ns.model.SecondaryUser.SwitchPrimaryUsersRequest;
import com.ns.model.StaticTCUrlModelResponse;
import com.ns.model.UserValidResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("user/validate")
    Observable<Response<UserValidResponse>> doValidateCustomer(
            @Body Map username
    );


    @POST("auth/login")
    Observable<Response<LoginInfo>> userLogin(
            @Body Map map
    );

    //    @POST("http://35.154.43.51/app/v2/public/api/v2/password/request_test")
    @POST("password/request_test")
    Observable<Response<ForgotPasswordResponse>> forgotPassword(
            @Body Map map
    );

    @GET("customer/data")
    Observable<LoginInfo> getCustomerData(
            @Query("token") String token
    );

    @GET("flight_schedule/list")
    Observable<FlightScheduleResponse> getFlightSchedules(
            @Query("token") String token,
            @Query("from_city") String fromCity,
            @Query("to_city") String toCity,
            @Query("days") String days
    );

    @POST("flight_seat/list")
    Observable<FlightSeatListResponse> getFlightSeats(
            @Body FlightSeatRequest body
    );

    @GET("booking/pass")
    Observable<BoardingPassResponse> getBoardingPassResponse(
            @Query("token") String token,
            @Query("offset") int offset,
            @Query("limit") int limit
    );

    @GET("booking/list")
    Observable<BoardingListResponse> getBookingHistoryResponse(
            @Query("token") String token,
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("show_list") String show_list
    );

    @POST("flight_seat/list")
    Observable<FlightSeatListResponse> getFlightSeats(
            @Field("token") String token,
            @Field("flight_id") int flightId,
            @Field("from_city") int fromCity,
            @Field("to_city") int toCity,
            @Field("journey_date") String journeyDate,
            @Field("journey_time") String journeyTime
    );

    @POST("seat/confirm")
    Observable<Response<ConformSeatRequest>> confirmFlightSeats(
            @Body FlightSeatRequest request
    );


    ////////////// START Purchase Api...

    @GET("purchase/last_order")
    Observable<PaymentLastOrderDetails> getSeatPurchaseLastOrder(
            @Query("token") String tokens
    );

    @POST("purchase/seats")
    Observable<Response<PurchaseSeatDetails>> getOrderId(
            @Body SeatPurchaseRequest request
    );

    @POST("purchase/verify")
    Observable<VerifyPurchaseResponse> getVerifyPurchase(
            @Body Map map
    );

    ////////////// END


    @POST("membership/get_calculated_amount")
    Observable<MembershipCostdetailsResponse> getSeatCalculateAmount(
            @Body Map map
    );


    @POST("flight/book")
    Observable<Response<ConfirmFlightBookingResponse>> confirmFlightBooking(
            @Body ConfirmSeatBookingResponse response
    );


    @GET("guest/list")
    Observable<ContactDetailsPojo> getContactDetails(
            @Query("token") String token
    );


    @GET("address/list")
    Observable<AddressDetailsPojo> getAddressDetails(
            @Query("token") String token,
            @Query("city") int city
    );

    @POST("address/add")
    Observable<CommonPojo> addNewAddress(
            @Body Map addrssMap
    );

    @POST("guest/confirm")
    Observable<GuestAddResponse> getAddMultipleGuest(
            @Body GuestInfoRequest guestInfoRequest
    );


    @POST("booking/cancel")
    Observable<CommonPojo> getCancelBooking(
            @Body CancelBookingRequest cancelBookingRequest
    );


    @POST("customer/prefs_update")
    Observable<CommonPojo> getUpdateCommonFoodPreferences(
            @Body FoodPrefsUpdatePojo prefsUpdatePojo
    );


    @POST("secondary_user/active_deactive")
    Observable<CommonPojo> getDeactivateSecondaryUser(
            @Body ActiveDeactiveRequest activeDeactiveRequest
    );

    @GET("foodmenu/list")
    Observable<FoodMenuListResponse> getFoodListByJourneyDate(
            @Query("token") String token,
            @Query("schedule_id") int schedule_id,
            @Query("journey_date") String journey_date
    );

    @POST("food/personalize")
    Observable<Response<CommonPojo>> getPersonalizeFood(
            @Body FoodPersonalizeUpdateRequest foodPersonalizeUpdateRequest
    );

    @POST("cab/personalize")
    Observable<Response<CommonPojo>> getPersonalizeCab(
            @Body CabPersonalizeRequest cabPersonalizeRequest
    );


    @GET("booking/details")
    Observable<BookingIdResponse> getBookingIdDetails(
            @Query("token") String token,
            @Query("booking_id") String paymentId
    );


    /// Secondaty user


    @POST("secondary_user/login")
    Observable<SecondaryUserLoginResponse> getSecondaryUserLogin(
            @Body Map map
    );

    @POST("secondary_user/add")
    Observable<CommonPojo> getAddManagerInfo(
            @Body Map map
    );

    @GET("secondary_user/list")
    Observable<ManagerResponse> getAddManagerList(
            @Query("token") String token,
            @Query("status") int status
    );

    @POST("secondary_user/switch_to_primary")
    Observable<LoginInfo> getSwitchPrimaryUsers(
            @Body SwitchPrimaryUsersRequest switchPrimaryUsersRequest
    );


    /// Feed back

    @GET("feedback/categories")
    Observable<FeedBackCategoriesResponse> getFeedBackCategories(
            @Query("token") String token
    );

    @POST("feedback/update")
    Observable<CommonPojo> getFeedbackInfo(
            @Body FeedBackRequest feedBackRequest
    );


    @GET("content/getStatic")
    Observable<StaticTCUrlModelResponse> getStaticTCUrl();


    @GET("city/list")
    Observable<CityResponse> getCityList(
            @Query("token") String token
    );

}
