package com.hacredition.xph.hacredition;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatDelegate;

import com.hacredition.xph.hacredition.common.Constants;
import com.hacredition.xph.hacredition.di.component.ApplicationComponent;
import com.hacredition.xph.hacredition.di.component.DaggerApplicationComponent;
import com.hacredition.xph.hacredition.di.module.ApplicationModule;
import com.hacredition.xph.hacredition.mvp.entity.DaoMaster;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.hacredition.xph.hacredition.utils.UncertainHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.database.Database;

import java.util.Date;

/**
 * Created by pc on 2017/1/9.
 * 完成Application的一些初始化
 */

public class App extends Application {

    public static boolean hasLogin = false;

    private ApplicationComponent mApplicationComponent;

    private static final String DB_NAME = "hacredition_db";



    //定义监控内存泄露
    private RefWatcher refWatcher;

    //其他类可以获取内存泄露监控对象
    public static RefWatcher getRefWatcher(Context context){
        App application = (App)context.getApplicationContext();
        return application.refWatcher;
    }


    private static Context mApplicationContext;


    protected static DaoSession mDaoSession;

    public static Context getAppContext(){
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        //初始化监控
        initUnCatchException();
        initLeakCanary();
        initStrictMode();
        //初始化夜晚模式
        initDayNightMode();
        //初始化注入
        initApplicationComponent();
        //初始化数据库
        initDB();
        initLoginLog();
    }


    private void initUnCatchException(){
        UncertainHandler catchExcep = new UncertainHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }

    /**
     * 根据数据库上次登录情况判断hasLogin的值
     */
    private void initLoginLog(){
        if(mDaoSession.getInputItemDao().count()>0){
            UserInfo userInfo = mDaoSession.getUserInfoDao().loadAll().get(0);
            if(userInfo==null||isTimeOut(userInfo.getLastLoginTime())){
                hasLogin = false;
            }else{
                hasLogin = true;
            }
        }else{
            hasLogin = false;
        }
    }

    private boolean isTimeOut(Date time){
        System.out.println(time);
        return true;
    }

    /**
     * 初始化内存监控
     */
    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }

    /**
     * 发布版本删除监控
     * @return
     */
    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

    /**
     * 初始化IO和网络监控
     */
    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
//                            .penaltyDialog() // 弹出违规提示对话框
                            .penaltyLog() // 在logcat中打印违规异常信息
                            .build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }
    }


    private void initDayNightMode() {
        if (MyUtils.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }


    private static boolean isShowPhoto(){
        return MyUtils.getSharedPreferences().getBoolean(Constants.SHOW_NEWS_PHOTO,true);
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }


    private void initDB(){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(mApplicationContext,DB_NAME);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession(){
        return mDaoSession;
    }

    public void finishApplication(){
        System.exit(0);
    }
}
