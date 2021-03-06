package com.hacredition.xph.hacredition.repository.network;

import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.CarInfo;
import com.hacredition.xph.hacredition.mvp.entity.CourtInfo;
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.entity.Entrepreneurship;
import com.hacredition.xph.hacredition.mvp.entity.FiscalSpend;
import com.hacredition.xph.hacredition.mvp.entity.GuaranteeInfo;
import com.hacredition.xph.hacredition.mvp.entity.HonourInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.InsuranceInfo;
import com.hacredition.xph.hacredition.mvp.entity.MachineInfo;
import com.hacredition.xph.hacredition.mvp.entity.MortgageInfo;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.entity.OperationalEntity;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfo;
import com.hacredition.xph.hacredition.mvp.entity.OwnerShip;
import com.hacredition.xph.hacredition.mvp.entity.PoliceInfo;
import com.hacredition.xph.hacredition.mvp.entity.QueryItem;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;

import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pc on 2017/1/24.
 */

public interface NewsService {

    @GET("test")
    Observable<List<NewsSummary>> getNewsList(
            @Query("existNewsId") int existNewsId);

    @GET("test1")
    Observable<NewsDetail> getNewsDetail(
         @Query("newsId") int newsId);

    @GET("login")
    Observable<UserInfo> getUserInfo(
            @Query("username") String username,@Query("password") String password);


    @GET("input")
    Observable<List<InputItem>> getInputItems(
            @Query("userId") int userId);

    @GET("query")
    Observable<List<QueryItem>> getQueryItems(
            @Query("userId") int userId);


    @Headers("Content-Type:application/json")
    @POST("servlet/saveHouseInfo")
    Observable<String> saveHouseInfo(
            @Body HouseInfo houseInfo);


    @Headers("Content-Type:application/json")
    @POST("servlet/saveFiscalSpend")
    Observable<String> saveFiscalSpend(
            @Body FiscalSpend fiscalSpend);

    @Headers("Content-Type:application/json")
    @POST("saveOperationalEntity")
    Observable<String> saveOperationalEntity(
            @Body OperationalEntity operationalEntity);

    @Headers("Content-Type:application/json")
    @POST("saveEntrepreneurship")
    Observable<String> saveEntrepreneurship(
            @Body Entrepreneurship entrepreneurship);


    @Headers("Content-Type:application/json")
    @POST("saveOwnerShip")
    Observable<String> saveOwnerShip(
            @Body OwnerShip ownerShip);


    @Headers("Content-Type:application/json")
    @POST("saveHonourInfo")
    Observable<String> saveHonourInfo(
            @Body HonourInfo honourInfo);


    @Headers("Content-Type:application/json")
    @POST("saveMachineInfo")
    Observable<String> saveMachine(
            @Body MachineInfo machineInfo);


    @Headers("Content-Type:application/json")
    @POST("servlet/savePoliceInfo")
    Observable<String> savePoliceInfo(
            @Body PoliceInfo policeInfo);


    @Headers("Content-Type:application/json")
    @POST("saveCreditInfo")
    Observable<String> saveCreditInfo(
            @Body CreditInfo creditInfo);


    @Headers("Content-Type:application/json")
    @POST("servlet/saveGuarantee")
    Observable<String> saveGuarantee(
            @Body GuaranteeInfo guaranteeInfo);


    @Headers("Content-Type:application/json")
    @POST("servlet/saveMortgage")
    Observable<String> saveMortgage(
            @Body MortgageInfo mortgageInfo);


    @Headers("Content-Type:application/json")
    @POST("servlet/saveCourtInfo")
    Observable<String> saveCourtInfo(
            @Body CourtInfo courtInfo);


    @Headers("Content-Type:application/json")
    @POST("saveCarInfo")
    Observable<String> saveCarInfo(
            @Body CarInfo carInfo);

    @Headers("Content-Type:application/json")
    @POST("servlet/saveInsuranceInfo")
    Observable<String> saveInsuranceInfo(
            @Body InsuranceInfo insuranceInfo);


    @Headers("Content-Type:application/json")
    @POST("saveIncomeInfo")
    Observable<String> saveIncomeInfo(
            @Body IncomeInfo incomeInfo);


    @Headers("Content-Type:application/json")
    @POST("saveOutputInfo")
    Observable<String> saveOutputInfo(
            @Body OutputInfo outputInfo);


    @GET("queryHouseHoldOtherInfo")
    Observable<List<BaseAdapterItem>> getHouseHoldOtherInfoItems(
            @Query("idcard") String idcard,@Query("type") int type);
}
