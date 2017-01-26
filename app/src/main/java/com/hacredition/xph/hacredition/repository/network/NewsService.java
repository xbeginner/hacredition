package com.hacredition.xph.hacredition.repository.network;

import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;

import java.util.List;
import java.util.Map;

import rx.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pc on 2017/1/24.
 */

public interface NewsService {

    @GET("getNewsList.do")
    Observable<List<NewsSummary>> getNewsList(
            @Query("sort") int existNewsId);



}
