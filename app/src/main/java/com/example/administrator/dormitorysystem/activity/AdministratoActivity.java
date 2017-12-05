package com.example.administrator.dormitorysystem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Administrato;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import qiu.niorgai.StatusBarCompat;

public class AdministratoActivity extends BaseActivity {

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tb_administrato)
    Toolbar tbAdministrato;
    @Bind(R.id.bu_find)
    Button buFind;
    @Bind(R.id.bu_notice)
    Button buNotice;
    @Bind(R.id.bu_repair)
    Button buRepair;
    @Bind(R.id.bu_comment)
    Button buComment;
    @Bind(R.id.cm_person)
    CircleImageView cmPerson;
    @Bind(R.id.tv_true_name)
    TextView tvTrueName;
    @Bind(R.id.tv_id)
    TextView tvId;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_mainRage)
    TextView tvMainRage;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.iv_setting)
    ImageView ivSetting;
    private List<Integer> images;
    private List<String> strings;
    private Administrato administrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
        setContentView(R.layout.activity_administrato);
        ButterKnife.bind(this);
        administrato = (Administrato) getIntent().getSerializableExtra("aInfo");
        initData();
        initDrawer(tbAdministrato);
        banner.setImageLoader(new GlideImagerLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImages(images);
        banner.setBannerTitles(strings);
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.start();
    }

    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.refresh, R.string.refresh) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            //更新状态
            mDrawerToggle.syncState();
            drawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    public class GlideImagerLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object o, ImageView imageView) {
            Glide.with(context).load(o).into(imageView);
        }
    }

    private void initData() {
        tvTrueName.setText(administrato.getName().toString());
        tvId.setText(administrato.getUsername().toString());
        tvSex.setText(administrato.getSex().toString());
        tvTel.setText(administrato.getMobilePhoneNumber().toString());
        tvMainRage.setText(administrato.getMainRage().toString());
        if (administrato.getNickUrl().isEmpty()) {
            Glide.with(this).load("http://bmob-cdn-13164.b0.upaiyun.com/2017/09/04/b1b8899cc0934c899bc86f88bafdf302.jpg").into(cmPerson);
        } else {
            Glide.with(this).load(administrato.getNickUrl().toString()).into(cmPerson);
        }
        images = new ArrayList<>();
        strings = new ArrayList<>();
        images.add(R.drawable.one);
        images.add(R.drawable.two);
        images.add(R.drawable.three);
        strings.add("湖南工业大学欢迎你");
        strings.add("学校夜景");
        strings.add("厚德博学 和而不同");
    }

    @OnClick({R.id.bu_find, R.id.bu_notice, R.id.bu_repair, R.id.bu_comment,R.id.iv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bu_find:
                break;
            case R.id.bu_notice:
                break;
            case R.id.bu_repair:
                break;
            case R.id.bu_comment:
                break;
            case R.id.iv_setting:
                startActivity(new Intent(AdministratoActivity.this,SettingActivity.class));
                break;
        }
    }
}
