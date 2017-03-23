package com.hacredition.xph.hacredition.mvp.inputInterface.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.InputInfoInterface;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2017/2/27.
 */

public class OutputInfoInputInfo implements InputInfoInterface {

    private OutputInfo outputInfo;

    public OutputInfoInputInfo(OutputInfo info){
          this.outputInfo = info;
    }


    @Override
    public void save(final SaveCallback saveCallback) {
        saveOutputInfoToDB(outputInfo);
        saveCallback.saveSuccessfully();
         //保存
//        RetrofitManager manager = RetrofitManager.getInstance();
//        manager.saveOutputInfo(outputInfo)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String value) {
//                        saveCallback.saveSuccessfully();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        saveCallback.saveFially();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }


    /**
     * 先保存到数据库
     */
    private void saveOutputInfoToDB(OutputInfo info){
        DaoSession session = App.getmDaoSession();
        session.getOutputInfoDao().insert(info);
    }
}
