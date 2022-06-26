package com.jc.mvvmrxjavaretrofitsample.view;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.mvvmrxjavaretrofitsample.R;
import com.jc.mvvmrxjavaretrofitsample.databinding.MovieFragmentBinding;
import com.jc.mvvmrxjavaretrofitsample.viewModel.MainViewModel;

/**
 * Created by HaohaoChang on 2017/2/11.
 */
public class MovieFragment extends Fragment implements CompletedListener,SwipeRefreshLayout.OnRefreshListener{

    private static String TAG = MovieFragment.class.getSimpleName();
    private MainViewModel viewModel;
    private MovieFragmentBinding movieFragmentBinding;
    private MovieAdapter movieAdapter;
    //获取实例，返回实例
    public static MovieFragment getInstance() {
        return new MovieFragment();
    }

    @Nullable
    @Override
    //创建视图
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //新建了一个视图，定义方式是后面这一堆
        View contentView = inflater.inflate(R.layout.movie_fragment, container, false);
        //此处绑定了当前视图
        movieFragmentBinding = MovieFragmentBinding.bind(contentView);
        //初始化数据信息
        initData();
        //返回该视图
        return contentView;
    }

    private void initData() {
        //创建新的Adapter并赋值
        movieAdapter = new MovieAdapter();
        //设置Layout
        movieFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置默认的 item view 的动效
        movieFragmentBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置Adpater
        movieFragmentBinding.recyclerView.setAdapter(movieAdapter);
        //设置关于下拉颜色的部分
        movieFragmentBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        //设置刷新监听为自身
        movieFragmentBinding.swipeRefreshLayout.setOnRefreshListener(this);
        //以该Adapter创建视图模型
        viewModel = new MainViewModel(movieAdapter,this);
        //为绑定了当前视图的它设置视图模型
        movieFragmentBinding.setViewModel(viewModel);

    }

    @Override
    //刷新时
    public void onRefresh() {
        //Adapter清理所有文件
        movieAdapter.clearItems();
        //视图模型刷新数据
        viewModel.refreshData();
    }

    @Override
    public void onCompleted() {
        if (movieFragmentBinding.swipeRefreshLayout.isRefreshing()) {
            movieFragmentBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }
}
