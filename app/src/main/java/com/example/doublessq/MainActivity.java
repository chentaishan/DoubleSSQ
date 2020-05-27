package com.example.doublessq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;

    private static final String TAG = "MainActivity";
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

        Observable.create(new ObservableOnSubscribe<List<ItemBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ItemBean>> e) throws Exception {

//                final HtmlPage content = HtmlUtils.getContent(Constants.testUrl);
                final List<ItemBean> jsoupData = HtmlUtils.getJsoupData();
                e.onNext(jsoupData);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ItemBean> value) {

                        homeAdapter.setItemBeanList(value);
                        Log.d(TAG, "onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        homeAdapter = new HomeAdapter(this);
        mRecyclerview.setAdapter(homeAdapter);
    }
}
