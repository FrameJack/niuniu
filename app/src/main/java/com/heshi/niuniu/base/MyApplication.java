package com.heshi.niuniu.base;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerAppComponent;
import com.heshi.niuniu.di.module.AppModule;
import com.heshi.niuniu.net.Network;
import com.heshi.niuniu.util.Ext;
import com.heshi.niuniu.util.ViewUtils;
import com.litesuits.orm.LiteOrm;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by min on 2017/3/1.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication mInstance;
    private static final String realmName = "hf.realm";
    public static LiteOrm liteOrm;
    public Gson gson;

    //依赖注入
    private static AppComponent mAppComponent;
    public static int USER_TYPE;
    public static String USER_ID = "";
    public static String appToken = "123456"; // 用户登录token
    public static String APPID = "ANDROID-1.0.0";//appid
    public static Application application;

    public static Context applicationContext;
    private static MyApplication instance;
    public static String currentUserNick = "";


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = (Application) getApplicationContext();
      /*  if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }*/
        /**
         *  极光推送
         */
        mInstance = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        dagger();
        JpushInit();
        initData();

        //        GlideCacheUtil.getInstance().clearImageAllCache(getApplication());
        // bugly 配置  初始化Bugly
        CrashReport.initCrashReport(this, "5d4e9977de", false);
        Context context = this;
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        initTencentWebView(); //初始化tencent WebView
    }


    private void initTencentWebView() {
        //初始化X5内核
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。

            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("@@", "加载内核是否成功:" + b);
            }
        });

    }

    private ArrayList<InputStream> attachmentsInputStreams;

    public ArrayList<InputStream> getAttachmentsInputStreams() {
        return attachmentsInputStreams;
    }

    public void setAttachmentsInputStreams(ArrayList<InputStream> attachmentsInputStreams) {
        this.attachmentsInputStreams = attachmentsInputStreams;
    }

    /**
     * 极光推送相关的初始化操作
     */
    public void JpushInit() {
//        JPushUtil.getInstance()
//                .JpushInit(getApplication())
//                .SetDebugMode(true);
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private void initData() {
//        Constants.readSpUserInfo();
//        Constants.readSpUserInfoModel();
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "liteorm.db");
        }
        liteOrm.setDebugged(true); // open the log
        gson = new Gson();
    }

    private void dagger() {
        initExtension();
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    /**
     * 当前实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return mInstance;
    }


    //获取当前版本信息
    private void initExtension() {
        Ext.init(this, new ExtImpl());
    }

    public static final class ExtImpl extends Ext {

        @Override
        public String getCurOpenId() {
            return null;
        }

        @Override
        public String getDeviceInfo() {
            return null;
        }

        @Override
        public String getPackageNameForResource() {
            return "com.hctx.company.oa";
        }

        @Override
        public int getScreenHeight() {
            return ViewUtils.getScreenHeight();
        }

        @Override
        public int getScreenWidth() {
            return ViewUtils.getScreenWidth();
        }

        @Override
        public boolean isAvailable() {
            return Network.isAvailable();
        }

        @Override
        public boolean isWap() {
            return Network.isWap();
        }

        @Override
        public boolean isMobile() {
            return Network.isMobile();
        }

        @Override
        public boolean is2G() {
            return Network.is2G();
        }

        @Override
        public boolean is3G() {
            return Network.is3G();
        }

        @Override
        public boolean isWifi() {
            return Network.isWifi();
        }

        @Override
        public boolean isEthernet() {
            return Network.isEthernet();
        }
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }


}
