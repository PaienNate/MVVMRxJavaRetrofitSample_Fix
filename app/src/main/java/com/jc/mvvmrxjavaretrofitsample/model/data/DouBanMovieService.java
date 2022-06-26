package com.jc.mvvmrxjavaretrofitsample.model.data;

import com.jc.mvvmrxjavaretrofitsample.model.entity.Movie;

import java.util.List;
//import rx.Observable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by HaohaoChang on 2017/2/11.
 */
public interface DouBanMovieService {
    //修改为https://www.iqi360.com/p/douban-imdb-api提供的API接口
    String BASE_URL = "https://api.wmdb.tv/api/v1/";

    @GET("top")
    //?type=Douban&skip=0&limit=50&lang=Cn
    Observable<List<Movie>> getMovies(@Query("type") String type, @Query("skip") int skip,@Query("limit") int limit,@Query("lang") String lang);
}
