package com.jc.mvvmrxjavaretrofitsample.viewModel;

import androidx.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.jc.mvvmrxjavaretrofitsample.model.data.RetrofitHelper;
import com.jc.mvvmrxjavaretrofitsample.model.entity.Movie;
import com.jc.mvvmrxjavaretrofitsample.view.CompletedListener;
import com.jc.mvvmrxjavaretrofitsample.view.MovieAdapter;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * Created by HaohaoChang on 2017/2/11.
 */
public class MainViewModel {
    public ObservableField<Integer> contentViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    public ObservableField<Integer> errorInfoLayoutVisibility;
    public ObservableField<String> exception;
    private Observer<Movie> movieObserver;
    private MovieAdapter movieAdapter;
    private CompletedListener completedListener;
    //默认的参数
    String type = "Douban";
    int skip = 0;
    int limit=50;
    String lang="Cn";


    public MainViewModel(MovieAdapter movieAdapter,CompletedListener completedListener) {
        //设置Adapter和completeListener
        this.movieAdapter = movieAdapter;
        this.completedListener = completedListener;
        //初始化数据
        initData();
        //进行电影获取
        getMovies();
    }
//重点函数：获取电影的逻辑
    private void getMovies() {
        //创建一个“订阅者”
        movieObserver = new Observer<Movie>() {
            //重写请求完成时的函数
            @Override
            public void onComplete() {
                Log.d("[MainViewModel]", "onCompleted");
                //隐藏所有
                hideAll();
                //设置可见
                contentViewVisibility.set(View.VISIBLE);
                //调用完成函数
                completedListener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                //报错时函数
                Log.d("[MainViewModel]", "onError");
                e.printStackTrace();
                hideAll();
                errorInfoLayoutVisibility.set(View.VISIBLE);
                exception.set(e.getMessage());
            }

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("[MainViewModel]", "onCompleted");
            }

            //这是rxjava的回调方法，获取到数据时就会进行操作
            //这里的传入参数来源是retrofitHelper的定义。
            @Override
            public void onNext(Movie movie) {
                movieAdapter.addItem(movie);

            }
        };
        //其获取实例，然后获取Movies
      RetrofitHelper.getInstance().getMovies(movieObserver, type,skip,limit,lang);
    }
    //刷新数据就是再获取一遍movies，嗯，河里
    public void refreshData() {
        getMovies();
    }
    //初始化数据（只是初始化框架）
    private void initData() {
        //这些值是用来更新显示的
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorInfoLayoutVisibility = new ObservableField<>();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.VISIBLE);
    }
    //隐藏所有原本的？
    private void hideAll(){
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }
}
