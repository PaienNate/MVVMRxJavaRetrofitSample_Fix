package com.jc.mvvmrxjavaretrofitsample.model.data;

import static com.jc.mvvmrxjavaretrofitsample.model.data.OkhttpCertUtils.getUnsafeOkHttpClient;

import android.util.Log;

import com.jc.mvvmrxjavaretrofitsample.model.entity.Movie;


import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HaohaoChang on 2017/2/11.
 * Fix by PaienNate on 2022/6/26
 */
public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private DouBanMovieService movieService;
    OkHttpClient.Builder builder;
    //下方需要使用到的参数




    /**
     * 获取RetrofitHelper对象的单例
     *
     * */
    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {

        return Singleton.INSTANCE;

    }
    //初始化
    private RetrofitHelper()  {
        //注：因为我测试的虚拟机安卓6.0很老了，好像证书过期了
        //所以请求会报错。我用我的小米9测试就没有问题，网上说可以用自定义证书解决
        //我刚开始考虑找一个ca-bundle.crt但是没找到，所以只能出此下策
        //直接关掉验证：
        //理论上，自己下载一个
        try
        {
            builder = getUnsafeOkHttpClient().newBuilder();
        }
        catch (Exception e)
        {
            Log.i("测试","测试");
        }
        //builder = new OkHttpClient.Builder();

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //修改部分：切换为RXJava3CallAdapter
        //new Activity().getApplicationContext();
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(DouBanMovieService.BASE_URL)
                .build();
        //这里创建了对应的retrofit数据，作为services
        movieService = retrofit.create(DouBanMovieService.class);
        // 新增对应的天气Service
    }
    //获取电影 - 最底层方法
    public void getMovies(Observer<Movie> observer,String type,int skip,int limit,String lang) {
        //调用retrofit的API方法，获取到对应的返回值，由于Gson插件的存在，它会自动归类为对象
        //注：由于使用了Gson自动生成对象，所以免去了自己处理对象加序列化名的麻烦。
        movieService.getMovies(type,skip,limit,lang)
                //Func1的< I,O >I,O模版分别为输入和输出值的类型，
                // 实现Function的apply方法对I类型进行处理后返回O类型数据，
                // map和flatMap的不同，根据网上搜的是这么说的：
                // flatMap中执行的方法的返回类型为Observable类型
                // 由于我只需要解析出一个一个的信息出来就可以了，所以只需要用到flatMap。
                .flatMap(new Function<List<Movie>, ObservableSource<Movie>>() {
                    //上方解码之后是Observable<List<Movie>>，将他转换为单个movie的观察者类型
                    @Override
                    public ObservableSource<Movie> apply(List<Movie> movies) {
                        return Observable.fromIterable(movies);
                    }
                })
                //指定处理的事件流在哪个线程中执行
                .subscribeOn(Schedulers.io())
                //指定最后的结果处于哪个线程中
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者是传入的subscriber,在rxjava2里，光荣的变成了observer……
                //显然rxjava3他们沿用了。
                .subscribe(observer);
    }
}
