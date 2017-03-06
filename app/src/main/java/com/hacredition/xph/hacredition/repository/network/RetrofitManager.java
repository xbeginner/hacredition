package com.hacredition.xph.hacredition.repository.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.entity.Entrepreneurship;
import com.hacredition.xph.hacredition.mvp.entity.FiscalSpend;
import com.hacredition.xph.hacredition.mvp.entity.GuaranteeInfo;
import com.hacredition.xph.hacredition.mvp.entity.HonourInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.MachineInfo;
import com.hacredition.xph.hacredition.mvp.entity.MortgageInfo;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.entity.OperationalEntity;
import com.hacredition.xph.hacredition.mvp.entity.OwnerShip;
import com.hacredition.xph.hacredition.mvp.entity.PoliceInfo;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.utils.NetUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.socks.library.KLog;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;


/**
 * Created by pc on 2017/1/24.
 */

public class RetrofitManager {

    private NewsService mNewsService;

    private static final String BASE_URL = "http://192.168.0.82:8080/hacredition/";

    private static volatile OkHttpClient sOkHttpClient;

    private static RetrofitManager mRetrofitManager;


    private RetrofitManager() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//主机地址
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))//解析方法
                .client(getOkHttpClient())
                .build();
        //定义一个可以调用NewsService的API
        mNewsService = retrofit.create(NewsService.class);
    }


    public static RetrofitManager getInstance(){
        if(mRetrofitManager==null){
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }


    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6, TimeUnit.SECONDS)
                            .writeTimeout(6, TimeUnit.SECONDS)
                            .addInterceptor(mLoggingInterceptor)
                            .build();
                }
            }
        }
        return sOkHttpClient;
    }




    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            KLog.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            KLog.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };




    public Observable<List<NewsSummary>> getNewsListObservable(int existNewsId) {
        return mNewsService.getNewsList(existNewsId);
    }

    public Observable<NewsDetail> getNewsDetail(int newsId){
        return mNewsService.getNewsDetail(newsId);
    }


    public Observable<UserInfo> getUserInfo(String username,String password){
        return mNewsService.getUserInfo(username,password);
    }


    public Observable<List<InputItem>> getInputItems(int userId){
        return mNewsService.getInputItems(userId);
    }

    public Observable<String> saveHouseInfo(HouseInfo houseInfo){
        return mNewsService.saveHouseInfo(houseInfo);
    }


    public Observable<String> saveFiscalSpend(FiscalSpend fiscalSpend){
        return mNewsService.saveFiscalSpend(fiscalSpend);
    }


    public Observable<String> saveOperationalEntity(OperationalEntity operationalEntity){
        return mNewsService.saveOperationalEntity(operationalEntity);
    }


    public Observable<String> saveEntrepreneurship(Entrepreneurship entrepreneurship){
        return mNewsService.saveEntrepreneurship(entrepreneurship);
    }


    public Observable<String> saveOwnerShip(OwnerShip ownerShip){
        return mNewsService.saveOwnerShip(ownerShip);
    }

    public Observable<String> saveHonourInfo(HonourInfo honourInfo){
        return mNewsService.saveHonourInfo(honourInfo);
    }

    public Observable<String> saveMachine(MachineInfo machineInfo){
        return mNewsService.saveMachine(machineInfo);
    }

    public Observable<String> savePoliceInfo(PoliceInfo policeInfo){
        return mNewsService.savePoliceInfo(policeInfo);
    }

    public Observable<String> saveCreditInfo(CreditInfo creditInfo){
        return mNewsService.saveCreditInfo(creditInfo);
    }


    public Observable<String> saveGuaranteeInfo(GuaranteeInfo guaranteeInfo){
        return mNewsService.saveGuarantee(guaranteeInfo);
    }


    public Observable<String> saveMortgageInfo(MortgageInfo mortgageInfo){
        return mNewsService.saveMortgage(mortgageInfo);
    }
}
