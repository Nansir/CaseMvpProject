package com.sir.app.retrofit.api.news;

import com.sir.app.retrofit.api.NetWorkApi;
import com.sir.app.retrofit.model.news.bean.NewsChannelList;
import com.sir.app.retrofit.model.news.bean.NewsCityList;
import com.sir.app.retrofit.model.news.bean.NewsContent;
import com.sir.app.retrofit.model.news.bean.NewsResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 新闻接口
 * Created by zhuyinan on 2017/4/1.
 */
public interface NewsApi {

    @GET(NetWorkApi.getNewsChannelList)
    Observable<NewsResponse<NewsChannelList>> getNewsChannelList(@Query("showapi_appid") String appId,
                                                                 @Query("showapi_sign") String apiKey);

    @GET(NetWorkApi.getNewsCityList)
    Observable<NewsResponse<NewsCityList>> getCityList(@Query("showapi_appid") String appId,
                                                              @Query("showapi_sign") String apiKey);

    @GET(NetWorkApi.getChannelNews)
    Observable<NewsResponse<NewsContent>> getChannelNews(@QueryMap Map<String, String> params);
}
