package com.ns.retrofit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.ns.database.SharedPref;
import com.ns.model.AddManager.ManagerResponse;
import com.ns.model.AddressDetailsPojo;
import com.ns.model.BoardingList.BoardingListResponse;
import com.ns.model.Booking.BookingIdResponse;
import com.ns.model.CancelBooking.CancelBookingRequest;
import com.ns.model.City.CityResponse;
import com.ns.model.CommonPojo;
import com.ns.model.FeedBack.FeedBackCategoriesResponse;
import com.ns.model.FeedBack.FeedBackRequest;
import com.ns.model.FlightScheduleResponse;
import com.ns.model.FlightSeatList.FlightSeatListResponse;
import com.ns.model.FlightSeatRequest;
import com.ns.model.FoodDay.CabPersonalizeRequest;
import com.ns.model.FoodDay.FoodPrefsUpdatePojo;
import com.ns.model.FoodMenuList.FoodMenuListResponse;
import com.ns.model.FoodMenuList.FoodPersonalizeUpdateRequest;
import com.ns.model.LoginResponse.LoginInfo;
import com.ns.model.OrderResponse;
import com.ns.model.PaymentResponse.MembershipCostdetailsResponse;
import com.ns.model.PaymentResponse.PaymentLastOrderDetails;
import com.ns.model.PaymentResponse.PurchaseSeatDetails;
import com.ns.model.PaymentResponse.VerifyPurchaseResponse;
import com.ns.model.SeatPurchaseRequest;
import com.ns.model.SeatResponse.ConformSeatRequest;
import com.ns.model.SecondaryUser.ActiveDeactiveRequest;
import com.ns.model.SecondaryUser.SecondaryUserLoginResponse;
import com.ns.model.SecondaryUser.SwitchPrimaryUsersRequest;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class APICallBack {
    private static final String TAG = "APICallBack";

    /*
     * FLIGHT SEAT
     * Flight seat for api call and response callback
     *
     */

    /*
     * Flight seat Interface
     */

    public interface IFlightSeatInterface {
        void onFlightSeat(FlightSeatListResponse loginInfo);

        void onError(Throwable e);
    }

    /**
     * Flight seat passing parameter
     *
     * @param context
     */
    public static void getFlightSeat(IFlightSeatInterface iFlightSeatInterface, Context context, String mToken, String mJourneyDate, String mJourneyTime, int mFlightId, int mFromId, int mToId) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);


        FlightSeatRequest flightSeatRequest = new FlightSeatRequest(mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId);
        mApiInterface.getFlightSeats(flightSeatRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlightSeatListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlightSeatListResponse response) {
                        iFlightSeatInterface.onFlightSeat(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iFlightSeatInterface.onError(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /*
     * LOGIN
     * Login for api call and response callback
     *
     */

    /*
     * Custom data Interface
     */

    public interface ICustomerData {
        void onCustomerData(LoginInfo loginInfo);

        void onError(Throwable e);
    }

    /**
     * Custom the passing parameter
     *
     * @param context
     * @param iCustomerData
     */
    public static void getCustomerData(Context context, ICustomerData iCustomerData) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);
        String token = SharedPref.getSharedPreferences(context).getTocken();

        mApiInterface.getCustomerData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginInfo response) {
                        iCustomerData.onCustomerData(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        iCustomerData.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void getSwitchSecondaryUser(ICustomerData iCustomerData, Context context, int sid) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        SwitchPrimaryUsersRequest switchPrimaryUsersRequest = new SwitchPrimaryUsersRequest(token, sid);

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getSwitchPrimaryUsers(switchPrimaryUsersRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginInfo response) {
                        iCustomerData.onCustomerData(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        iCustomerData.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /*
     * CONFIRM FLIGHT SEAT
     * Confirm flight seat for api call and response callback
     *
     */

    /*
     * Confirm Flight seat Interface
     */

    public interface IConfirmFlightSeatInterface {
        void onConfirmFlightSeat(Response<ConformSeatRequest> requestResponse);

        void onError(Throwable e);
    }

    /**
     * Confirm Flight seat passing parameter
     *
     * @param context
     */
    public static void getConfirmFlightSeat(IConfirmFlightSeatInterface iConfirmFlightSeatInterface, Context context, String mToken, String mJourneyDate, String mJourneyTime, int mFlightId, int mFromId, int mToId, List<Integer> mUnlockSeatsList, List<Integer> mLockSeatsList) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        FlightSeatRequest flightSeatRequest = new FlightSeatRequest(mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId, mUnlockSeatsList, mLockSeatsList);


        mApiInterface.confirmFlightSeats(flightSeatRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ConformSeatRequest>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ConformSeatRequest> response) {
                        Log.d(TAG, "onNext: " + response);
                        iConfirmFlightSeatInterface.onConfirmFlightSeat(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iConfirmFlightSeatInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }



    /*
     * CANCEL TICKET
     * Cancel ticket for api call and response callback
     *
     */

    /*
     * Cancel ticket Interface
     */

    public interface ICancelTicket {
        void onCancelData(CommonPojo commonPojo);

        void onError(Throwable e);
    }

    /**
     * Cancel ticket the passing parameter
     *
     * @param iCancelTicket
     * @param context
     * @param cancelBookingRequest
     */
    public static void getCancelData(ICancelTicket iCancelTicket, Context context, CancelBookingRequest cancelBookingRequest) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);
        mApiInterface.getCancelBooking(cancelBookingRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonPojo response) {
                        iCancelTicket.onCancelData(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        iCancelTicket.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /*
     * BOARDING PASS LIST
     * Boarding pass list for api call and response callback
     *
     */

    /*
     * Boarding pass Interface
     */

    public interface IBookingHistory {
        void onBookingHistoryData(BoardingListResponse commonPojo);

        void onError(Throwable e);
    }


    /**
     * Boarding pass the parameter
     *
     * @param iBookingHistory
     * @param context
     * @param offset
     * @param limit
     * @param show_list
     */
    public static void getBookingHistoryData(IBookingHistory iBookingHistory, Context context, int offset, int limit, String show_list) {

        String tocken = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getBookingHistoryResponse(tocken, offset, limit, show_list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BoardingListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BoardingListResponse response) {
                        iBookingHistory.onBookingHistoryData(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        iBookingHistory.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    public static void getFoodPrefrenceUpdate(Activity context, String update_prefrence) {
        String tocken = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        FoodPrefsUpdatePojo prefsUpdatePojo = new FoodPrefsUpdatePojo(tocken, update_prefrence);

        mApiInterface.getUpdateCommonFoodPreferences(prefsUpdatePojo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonPojo commonPojo) {
                        if (commonPojo.getResultcode() == 1) {
                            ConstantMethod.DialogShowFragmentBackPress(context, commonPojo.getMessage());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /*
     * DELETE MANAGER
     * Delete manager list for api call and response callback
     *
     */

    /*
     * Delete manager Interface
     */

    public interface IDeleteManager {
        void onDeleteManager(CommonPojo commonPojo);

        void onError(Throwable e);
    }

    /**
     * Delete manager pass the parameter
     *
     * @param iDeleteManager
     * @param context
     * @param token
     * @param sid
     */
    public static void getDeleteManagerSecondaryUser(IDeleteManager iDeleteManager, Context context, String token, int sid) {

        ActiveDeactiveRequest activeDeactiveRequest = new ActiveDeactiveRequest(token, sid, 2); // status 1 is add and 2 delete
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getDeactivateSecondaryUser(activeDeactiveRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonPojo response) {
                        Log.d(TAG, "onNext: " + response);
                        iDeleteManager.onDeleteManager(response);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        iDeleteManager.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /*
     * COMMON
     * Common list for api call and response callback
     *
     */

    /*
     * Common Interface
     */

    public interface ICommonInterface {
        void onCommonData(CommonPojo commonPojo);

        void onError(Throwable e);
    }

    public static void getAddNewAddress(ICommonInterface iCommonInterface, Context context, Map map) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.addNewAddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonPojo response) {
                        Log.d(TAG, "onNext: " + response);
                        iCommonInterface.onCommonData(response);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        iCommonInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /*
     * FOOD MENU
     * Food menu list for api call and response callback
     *
     */

    /*
     * FOOD MENU Interface
     */
    public interface IFoodMenuListInterface {
        void onFoodMenuList(FoodMenuListResponse commonPojo);

        void onError(Throwable e);
    }

    /**
     * Food menu pass the parameter
     *
     * @param iFoodMenuListInterface
     * @param context
     * @param id
     * @param journeyDate
     */
    public static void getFoodMenuList(IFoodMenuListInterface iFoodMenuListInterface, Context context, int id, String journeyDate) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getFoodListByJourneyDate(token, id, journeyDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodMenuListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodMenuListResponse response) {
                        Log.d(TAG, "onNext: " + response);
                        iFoodMenuListInterface.onFoodMenuList(response);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        iFoodMenuListInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public interface IBookigIdInterface {
        void onBookingIdDetails(BookingIdResponse commonPojo);

        void onError(Throwable e);
    }

    public static void getBookigIdDetails(IBookigIdInterface iBookigIdInterface, Context context, String booking_id) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getBookingIdDetails(token, booking_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookingIdResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BookingIdResponse response) {
                        Log.d(TAG, "onNext: " + response);
                        iBookigIdInterface.onBookingIdDetails(response);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        iBookigIdInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }





    /*
     *  PURCHASE LAST ORDER
     *  Purchase last order for api call and response callback
     *
     */


    public interface IPurchaseLastOrderInterface {
        void onPurchanseLastOrder(PaymentLastOrderDetails lastOrderDetails);

        void onError(Throwable e);
    }

    public static void getPurchaseLastOrder(IPurchaseLastOrderInterface iPurchaseLastOrderInterface, Context context) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);


        mApiInterface.getSeatPurchaseLastOrder(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaymentLastOrderDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaymentLastOrderDetails orderResponse) {
                        iPurchaseLastOrderInterface.onPurchanseLastOrder(orderResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iPurchaseLastOrderInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /*
     *  PURCHASE SEAT
     *  Purchase Seat for api call and response callback
     *
     */


    public interface IPurchaseSeatInterface {
        void onPurchanseSeat(Response<PurchaseSeatDetails> orderResponse);

        void onError(Throwable e);
    }

    public static void getPurchaseSeat(IPurchaseSeatInterface iPurchaseSeatInterface, Context context, String mToken, int seatCount) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        SeatPurchaseRequest request = new SeatPurchaseRequest(mToken, seatCount);
        mApiInterface.getOrderId(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<PurchaseSeatDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<PurchaseSeatDetails> purchaseSeatDetailsResponse) {
                        iPurchaseSeatInterface.onPurchanseSeat(purchaseSeatDetailsResponse);
                    }



                    @Override
                    public void onError(Throwable e) {
                        iPurchaseSeatInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /// PURCHASE API AND INTERFACE

    /*
     * VERITY PURCHASE SEAT
     * Verify Purchase Seat for api call and response callback
     *
     */

    public interface IVerifyPurchaseSeatInterface {
        void onVerifyPurchaseSeat(VerifyPurchaseResponse verifyPurchaseResponse);

        void onError(Throwable e);
    }

    public static void getVerifyPurchaseSeat(IVerifyPurchaseSeatInterface iVerifyPurchaseSeatInterface, Context context, Map map) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getVerifyPurchase(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyPurchaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerifyPurchaseResponse verifyPurchaseResponse) {
                        iVerifyPurchaseSeatInterface.onVerifyPurchaseSeat(verifyPurchaseResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iVerifyPurchaseSeatInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    /*
     * VERITY PURCHASE SEAT
     * Verify Purchase Seat for api call and response callback
     *
     */

    public interface IMemberShipInterface {
        void onMemberShip(MembershipCostdetailsResponse verifyPurchaseResponse);

        void onError(Throwable e);
    }

    public static void getMembershipSeatCalculated(IMemberShipInterface iMemberShipInterface, Context context, Map map) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getSeatCalculateAmount(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MembershipCostdetailsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MembershipCostdetailsResponse membershipCostdetailsResponse) {
                        iMemberShipInterface.onMemberShip(membershipCostdetailsResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iMemberShipInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }










    /*
     * Personalize Cab
     * Personalize Cab for api call and response callback
     *
     */

    public interface IResponseCommon {
        void onResponseCommon(Response<CommonPojo> commonPojoResponse);

        void onError(Throwable e);
    }

    public static void getPersonalizeCab(IResponseCommon iPersonalizeCabInterface, Context context, int bookingid, int pickaddress, int dropaddress) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        CabPersonalizeRequest cabPersonalizeRequest = new CabPersonalizeRequest(token, bookingid, pickaddress, dropaddress);


        mApiInterface.getPersonalizeCab(cabPersonalizeRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<CommonPojo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<CommonPojo> commonPojoResponse) {
                        iPersonalizeCabInterface.onResponseCommon(commonPojoResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iPersonalizeCabInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void getPersonalFoodCategories(IResponseCommon iResponseCommon, Context context, int bookingid, List<String> foodCategoryIdList) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        FoodPersonalizeUpdateRequest foodCategories = new FoodPersonalizeUpdateRequest(token, bookingid + "", foodCategoryIdList);


        mApiInterface.getPersonalizeFood(foodCategories)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<CommonPojo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<CommonPojo> commonPojoResponse) {
                            iResponseCommon.onResponseCommon(commonPojoResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                            iResponseCommon.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    /*
     * VERITY PURCHASE SEAT
     * Verify Purchase Seat for api call and response callback
     *
     */

    public interface IAddressDetailsInterface {
        void onAddressDetails(AddressDetailsPojo addressDetailsPojo);

        void onError(Throwable e);
    }

    public static void getAddressDetails(IAddressDetailsInterface iAddressDetailsInterface, Context context, int cid) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getAddressDetails(token, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressDetailsPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddressDetailsPojo addressDetailsPojo) {
                        iAddressDetailsInterface.onAddressDetails(addressDetailsPojo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iAddressDetailsInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    ///       SECONDARY USER  /////////

    /*
     * SECONDARY USER
     *
     */

    public interface ISecondaryUserInterface {
        void onSecondaryUserDetails(SecondaryUserLoginResponse secondaryUserLoginResponse);

        void onError(Throwable e);
    }

    /**
     * @param iSecondaryUserInterface
     * @param context
     * @param map
     */
    public static void getSecondaryUserDetails(ISecondaryUserInterface iSecondaryUserInterface, Context context, Map map) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getSecondaryUserLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SecondaryUserLoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SecondaryUserLoginResponse secondaryUserLoginResponse) {
                        iSecondaryUserInterface.onSecondaryUserDetails(secondaryUserLoginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iSecondaryUserInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public interface IManagerResponseInterface {
        void onManagerResponse(ManagerResponse managerResponse);

        void onError(Throwable e);
    }


    public static void getManagerResponse(IManagerResponseInterface iManagerResponseInterface, Context context) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getAddManagerList(token, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ManagerResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ManagerResponse response) {
                        iManagerResponseInterface.onManagerResponse(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iManagerResponseInterface.onError(e);

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public interface IFlightScheduleInterface {
        void onFlightSchedule(FlightScheduleResponse managerResponse);

        void onError(Throwable e);
    }


    public static void getFlightSchedule(IFlightScheduleInterface iFlightScheduleInterface, Context context, int mFromIdInt, int mToIdInt) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getFlightSchedules(token, String.valueOf(mFromIdInt), String.valueOf(mToIdInt), UiConstants.FLIGHT_DAY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlightScheduleResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlightScheduleResponse response) {
                        iFlightScheduleInterface.onFlightSchedule(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iFlightScheduleInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /// Feed Back

    public interface IFeedBackCategoriesInterface {
        void onFeedBackCategories(FeedBackCategoriesResponse feedBackCategoriesResponse);

        void onError(Throwable e);
    }


    public static void getFeedBackCategories(IFeedBackCategoriesInterface iFlightScheduleInterface, Context context) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getFeedBackCategories(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FeedBackCategoriesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FeedBackCategoriesResponse feedBackCategoriesResponse) {
                        iFlightScheduleInterface.onFeedBackCategories(feedBackCategoriesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iFlightScheduleInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void getFeedBack(ICommonInterface iCommonInterface, Context context, FeedBackRequest feedBackRequest) {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getFeedbackInfo(feedBackRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonPojo commonPojo) {
                        iCommonInterface.onCommonData(commonPojo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCommonInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public interface ICityInterface {
        void onCityDetails(CityResponse cityResponse);

        void onError(Throwable e);
    }


    public static void getCityDetails(ICityInterface iCityInterface, Context context) {

        String token = SharedPref.getSharedPreferences(context).getTocken();
        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(context).create(APIInterface.class);

        mApiInterface.getCityList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CityResponse cityResponse) {
                        iCityInterface.onCityDetails(cityResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCityInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}