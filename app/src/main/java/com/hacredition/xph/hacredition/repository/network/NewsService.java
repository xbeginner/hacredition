package com.hacredition.xph.hacredition.repository.network;

import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;

import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pc on 2017/1/24.
 */

public interface NewsService {

    @GET("servlet/test")
    Observable<List<NewsSummary>> getNewsList(
            @Query("existNewsId") int existNewsId);

    @GET("servlet/test1")
    Observable<NewsDetail> getNewsDetail(
         @Query("newsId") int newsId);

    @GET("servlet/login")
    Observable<UserInfo> getUserInfo(
            @Query("username") String username,@Query("password") String password);

}
