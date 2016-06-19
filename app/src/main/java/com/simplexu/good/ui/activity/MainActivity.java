package com.simplexu.good.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.simplexu.good.R;
import com.simplexu.good.ui.fragment.MainFragment;
import com.simplexu.good.ui.fragment.CollectFragment;
import com.simplexu.good.ui.fragment.MeFragment;
import com.simplexu.good.utils.ViewUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Drawer mDrawer;
    private AccountHeader mLoginHeader;
    private Toolbar mToolbar;

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    private int mCurrentFmIndex = 1;
    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar(mToolbar);

        initFragment(savedInstanceState);
        setUpDrawer();

    }

    /**
     * 初始化Fragment
     */
    private void initFragment(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = new Fragment();
        mCurrentFragment = mFragmentManager.findFragmentById(R.id.layout_main);
        if (mCurrentFragment == null){
            mCurrentFragment = ViewUtils.createFragment(MainFragment.class);
            mFragmentManager.beginTransaction().add(R.id.layout_main,mCurrentFragment).commit();
        }
        FragmentTransaction transction = mFragmentManager.beginTransaction();
        if (savedInstanceState != null){
            List<Fragment> list = mFragmentManager.getFragments();
            for (int i = 0; i < list.size(); i++) {
                transction.hide(list.get(i));
            }
        }
        transction.show(mCurrentFragment).commitAllowingStateLoss();
    }

    /**
     * 标题栏的设置
     */
    private void getSupportActionBar(Toolbar mToolbar) {
        mToolbar.setTitle(getResources().getString(R.string.main_home));
    }


    /**
     * 侧边栏的设置
     */
    private void setUpDrawer() {
        //侧边栏登录
        mLoginHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(new ColorDrawable(getResources().getColor(R.color.colorDrawer)))
                .addProfiles(
                        new ProfileDrawerItem().withName("登录")
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();
        //侧边栏菜单1
        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().
                withName(getResources().getString(R.string.main_home)).
                withIcon(GoogleMaterial.Icon.gmd_home);
        /*PrimaryDrawerItem itemToday = new PrimaryDrawerItem().
                withName(getResources().getString(R.string.main_today)).
                withIcon(GoogleMaterial.Icon.gmd_nature);*/
        PrimaryDrawerItem itemCollect = new PrimaryDrawerItem().
                withName(getResources().getString(R.string.main_collect)).
                withIcon(GoogleMaterial.Icon.gmd_collection_bookmark);
        //侧边栏菜单2
        SwitchDrawerItem itemSwitchWifi = new SwitchDrawerItem().
                withName(getResources().getString(R.string.main_wifi)).
                withIcon(GoogleMaterial.Icon.gmd_network_wifi).
                withOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {

                    }
                });
        PrimaryDrawerItem itemMe = new PrimaryDrawerItem().
                withName(getResources().getString(R.string.main_me)).
                withIcon(GoogleMaterial.Icon.gmd_account);
        PrimaryDrawerItem itemExit = new PrimaryDrawerItem().
                withName(getResources().getString(R.string.main_exit)).
                withIcon(GoogleMaterial.Icon.gmd_account_circle);
        //设置侧边栏
        mDrawer = new DrawerBuilder()
                .withAccountHeader(mLoginHeader)
                .withActivity(this)
                .withToolbar(mToolbar)
                .addDrawerItems(
                        itemHome,
                        //itemToday,
                        itemCollect,
                        new DividerDrawerItem(),
                        itemSwitchWifi,
                        itemMe,
                        itemExit
                )   //菜单点击事件
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switchDrawer(position);
                        return true;
                    }
                }).build();
    }

    private void switchDrawer(int position) {
        switchFragment(position);
        mDrawer.closeDrawer();
    }

    /**
     * 菜单点击事件
     */
    @Nullable
    private Class<?> getClazz(int position) {
        Class<?> clazz = null;
        switch (position){
            case 1:
                mCurrentFmIndex = 1;
                mToolbar.setTitle(getResources().getString(R.string.main_home));
                clazz = MainFragment.class;
                break;
            case 2:
                mCurrentFmIndex = 2;
                mToolbar.setTitle(getResources().getString(R.string.main_collect));
                clazz = CollectFragment.class;
                break;
            case 5:
                mCurrentFmIndex = 5;
                mToolbar.setTitle(getResources().getString(R.string.main_me));
                clazz = MeFragment.class;
                break;
        }
        return clazz;
    }

    /**
     * 切换Fragment
     */
    private void switchFragment(int position) {
        Class<?> clazz = getClazz(position);
        if (clazz == null)return;
        Fragment fragment = ViewUtils.createFragment(clazz);

        if (fragment.isAdded()){
            mFragmentManager.beginTransaction().hide(mCurrentFragment)
                    .show(fragment).commitAllowingStateLoss();
        }else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment)
                    .add(R.id.layout_main,fragment).commitAllowingStateLoss();
        }
        mCurrentFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen()) {//当前抽屉是打开的，则关闭
            mDrawer.closeDrawer();
            return;
        }
        long currentTick = System.currentTimeMillis();
        if (currentTick - mExitTime > 2000) {
            Toast.makeText(this, "再按一次,你我再见", Toast.LENGTH_SHORT).show();
            mExitTime = currentTick;
        } else {
            finish();
            System.exit(0);
        }
    }
}
