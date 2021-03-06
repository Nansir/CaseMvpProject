package com.sir.app.retrofit.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.sir.app.retrofit.R;
import com.space.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 启动初始化程序检测更新
 * https://github.com/hanks-zyh/HTextView
 * Created by zhuyinan on 2017/4/20.
 */

public class LeadActivity extends BaseActivity {

    @Bind(R.id.lead_logo)
    ImageView imageView;
    ScaleAnimation animation;
    @Bind(R.id.lead_title)
    HTextView title;
    HTextViewType[] type;
    final int REQUEST_CODE_ASK_PERMISSIONS = 1001;


    @Override
    public int bindLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//取消状态栏
        return R.layout.activity_lead;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {


        animation = new ScaleAnimation(0.3f, 1f, 0.3f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000); //设置持续时间5秒
        imageView.startAnimation(animation);

        final Random random = new Random();

        type = new HTextViewType[5];
        type[0] = HTextViewType.ANVIL;
        type[1] = HTextViewType.TYPER;
        type[2] = HTextViewType.FALL;
        type[3] = HTextViewType.SCALE;
        type[4] = HTextViewType.RAINBOW;

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //上个动画结束开始下个随机动画
                title.setAnimateType(type[random.nextInt(type.length)]);
                title.animateText(getString(R.string.app_name)); // animate
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestPermissions();
            }
        }, 3000);


    }

    private Handler handler = new Handler(Looper.getMainLooper());

    @OnClick(R.id.lead_logo)
    public void loading(View view) {
        AlphaAnimation animation = new AlphaAnimation((float) 0.1, (float) 1.0);
        animation.setDuration(2000); //设置持续时间5秒
        imageView.startAnimation(animation);
    }


    /**
     * 请求的权限
     */
    private void requestPermissions() {
        //权限组
        String[] permissionsArray = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
        };

        //还需申请的权限列表
        List<String> permissionsList = new ArrayList<>();

        for (String permission : permissionsArray) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
            }
        }
        if (!permissionsList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_PERMISSIONS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int i = 0; i < permissions.length; i++) {
                    Log.e("TAG", permissions[i]);
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        //申请成功的权限
                    } else {
                        //权限被拒绝
                    }
                }
                Log.e("TAG", "在请求权限的结果");
                getOperation().forward(MainActivity.class);
                finish();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
