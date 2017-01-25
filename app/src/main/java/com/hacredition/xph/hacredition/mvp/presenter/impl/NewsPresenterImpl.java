package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.common.LoadNewsType;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummaryDao;
import com.hacredition.xph.hacredition.mvp.interactor.NewsInteractor;
import com.hacredition.xph.hacredition.mvp.interactor.impl.NewsInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.NewsPresenter;
import com.hacredition.xph.hacredition.mvp.view.NewsView;
import com.hacredition.xph.hacredition.utils.NetUtil;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/1/16.
 */

public class NewsPresenterImpl extends BasePresenterImpl<NewsView,List<NewsSummary>>
        implements NewsPresenter,RequestCallBack<List<NewsSummary>> {

    private NewsInteractor<List<NewsSummary>> mNewsInteractor;

    private boolean misFirstLoad;

    private boolean mIsRefresh = true;

    private int mStartPage;

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
        //判断数据库中的内容

        //请求新的内容
        List<NewsSummary> summaryList = new ArrayList<NewsSummary>();
        for(int i=0;i<20;i++){
            NewsSummary summary = new NewsSummary();
            summary.setTitle("title1"+i);
            summary.setSubTitle("subtitle1"+i);
            summary.setTime("2017-01-02");
            summary.setHasImg(false);
            summaryList.add(summary);
        }

        mView.setNewsList(summaryList,LoadNewsType.TYPE_REFRESH_SUCCESS);


    }

    /**
     * 分页加载
     */
    @Override
    public void loadMore(){

        List<NewsSummary>  summaryList = new ArrayList<NewsSummary>();
//

        mView.setNewsList(summaryList,LoadNewsType.TYPE_LOAD_MORE_SUCCESS);
    }

    /**
     * 初始化数据，从数据库和网络两反面开始查询
     */
    @Override
    public void onCreate() {
       if(mView!=null){
           loadNewsData();
       }
    }

    @Override
    public void beforeRequest() {
        if(!misFirstLoad){
            mView.showProgress();
        }
    }

    /**
     * 获取成功后
     */
    @Override
    public void success(List<NewsSummary> items) {
        misFirstLoad = true;
        if(items!=null){
            mStartPage += 20;
        }
        int loadType = mIsRefresh?LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        if(mView!=null){
            mView.setNewsList(items,loadType);
            mView.hideProgress();
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if (mView != null) {
            int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_ERROR : LoadNewsType.TYPE_LOAD_MORE_ERROR;
            mView.setNewsList(null, loadType);
        }
    }

    /**
     * 加载从现在开始新增的信息保存进数据库
     */
    private void loadNewsData() {
       int existId = getExistMaxNewsId();
        mNewsInteractor.loadNews(this,existId);
    }


    private int getExistMaxNewsId(){
        DaoSession session = App.getmDaoSession();
        List<NewsSummary> maxNewsId = session.getNewsSummaryDao().queryBuilder().where(NewsSummaryDao.Properties.NewsId.isNotNull()).orderDesc(NewsSummaryDao.Properties.NewsId).limit(1).list();
        int maxId = maxNewsId.get(0).getNewsId();
        return maxId;
    }

}
