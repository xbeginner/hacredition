package com.hacredition.xph.hacredition.mvp.presenter.impl;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.common.LoadNewsType;
import com.hacredition.xph.hacredition.listener.DBOprationRequestCallback;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummaryDao;
import com.hacredition.xph.hacredition.mvp.interactor.NewsInteractor;
import com.hacredition.xph.hacredition.mvp.interactor.impl.NewsInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.NewsPresenter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.NewsFragment;
import com.hacredition.xph.hacredition.mvp.view.NewsView;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;
import com.hacredition.xph.hacredition.utils.NetUtil;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.hacredition.xph.hacredition.common.LoadNewsType.TYPE_REFRESH_SUCCESS;

/**
 * Created by pc on 2017/1/16.
 */

public class NewsPresenterImpl extends BasePresenterImpl<NewsView,List<NewsSummary>>
        implements NewsPresenter,RequestCallBack<List<NewsSummary>>,DBOprationRequestCallback {

    private NewsInteractor<List<NewsSummary>> mNewsInteractor;

    private boolean misFirstLoad;

    private boolean mIsRefresh = true;

    private int mStartPage;

    private  DaoSession session;

    private NewsFragment fragment;

   @Inject
   public NewsPresenterImpl(NewsInteractorImpl newsInteractorImpl ){
       mNewsInteractor = newsInteractorImpl;
   }

    public NewsInteractor getInteractor(){
        return mNewsInteractor;
    }

    /**
     * 有网络的情况下加载更多
     */
    @Override
    public void refreshMore() {
        int existId = getExistMaxNewsId();
        mNewsInteractor.loadNewsFromNet(this,existId);
        mIsRefresh = true;
        mStartPage = 0;
        loadNewsData();
    }

    /**
     * 分页加载
     */
    @Override
    public void loadMore(){
        mIsRefresh = false;
        loadNewsData();
    }

    /**
     * 初始化数据，从数据库和网络两反面开始查询
     */
    @Override
    public void onCreate() {
        session = App.getmDaoSession();
        if(NetUtil.isNetworkAvailable()) {
            mView.showProgress();
        }
       if(mView!=null){
           loadNewsData();
       }
    }

    @Override
    public void beforeRequest() {
        if(!misFirstLoad&&getExistMaxNewsId()==0){
            mView.showProgress();
        }
    }

    /**
     * 获取成功后
     */
    @Override
    public void success(List<NewsSummary> items) {
        misFirstLoad = true;
        if(mView!=null){
            mView.hideProgress();
        }
        mNewsInteractor.saveNewsToDB(items,this);
    }

    @Override
    public void onError() {
        super.onError();
        if (mView != null) {
            //mView.setNewsList(null,LoadNewsType.TYPE_REFRESH_ERROR);
            //loadNewsFromDB(LoadNewsType.TYPE_REFRESH_SUCCESS);
            getDBNewsList();
            mView.showMsg();
        }
    }

    /**
     * 加载从现在开始新增的信息保存进数据库然后显示
     */
    private void loadNewsData() {
        int loadType = mIsRefresh? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        if(mIsRefresh == true){
            mNewsInteractor.loadNewsFromNet(this,0);
        }else{
            loadNewsFromDB(loadType);
        }

    }

    public void loadNewsFromDB(int loadType){
        List<NewsSummary> news = mNewsInteractor.loadNewsFromDB(mStartPage,20);
        mView.setNewsList(news,loadType);
        mStartPage += 20;
    }



    private int getExistMaxNewsId(){

        List<NewsSummary> maxNewsId = session.getNewsSummaryDao().queryBuilder().where(NewsSummaryDao.Properties.NewsId.isNotNull()).orderDesc(NewsSummaryDao.Properties.NewsId).limit(1).list();
        if(maxNewsId==null||maxNewsId.size()==0){
            return 0;
        }
        int maxId = maxNewsId.get(0).getNewsId();
        return maxId;
    }


    private void getDBNewsList(){
        List<NewsSummary> news = mNewsInteractor.loadNewsFromDB(0,20);
        mView.setNewsList(news,LoadNewsType.TYPE_REFRESH_SUCCESS);
        mStartPage = 20;
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        super.attachView(view);
        fragment = (NewsFragment) view;
    }

    @Override
    public void operationSuccessfully() {
        fragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getDBNewsList();
            }
        });

    }
}
