package win.tommy.tusha.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import win.tommy.tusha.MyApplication;
import win.tommy.tusha.R;
import win.tommy.tusha.base.BaseActivity;
import win.tommy.tusha.base.BaseFragment;
import win.tommy.tusha.ui.fragment.EpisodeFragment;
import win.tommy.tusha.ui.fragment.PictureFragment;
import win.tommy.tusha.util.Constant;
import win.tommy.tusha.util.SPUtil;
import win.tommy.tusha.util.StatusBarUtil;
import win.tommy.tusha.util.ToastUtil;

public class MainActivity extends BaseActivity {


    private DrawerLayout mDrawer;
    private CoordinatorLayout mCoordinator;
    private Toolbar mToolbar;
    private NavigationView mNavView;
    private BottomNavigationView mTabBottom;
    private ActionBarDrawerToggle mDrawerToggle;
    private ImageView mNavHeaderImg;
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private Fragment currentFragment = new Fragment();
    private FragmentManager fm;
    public boolean is_exit = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_main);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavView = (NavigationView) findViewById(R.id.nav_view_main);
        mTabBottom = (BottomNavigationView) findViewById(R.id.tab_bottom);


    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initData() {
        initToolbar(); //初始化toolbar
        initNightMode();//初始化夜间模式
        setupNavView();//初始化nav_view
        setupTabBottom();//初始化底部导航栏
        initFragment();//初始化话fragment
    }

    private void initToolbar() {
        mToolbar.setTitle(getResources().getString(R.string.main_welfare));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
    }

    private void initNightMode() {
        if ((Boolean) SPUtil.get(MainActivity.this, Constant.NIGHT_MODE, true)) {
            mCoordinator.setBackgroundColor(getResources().getColor(R.color.gray));
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            mDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
            mToolbar.getOverflowIcon().setTint(getResources().getColor(android.R.color.white));
            StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.colorPrimaryDark));
            mTabBottom.setBackgroundColor(getResources().getColor(android.R.color.white));
            mTabBottom.setItemIconTintList(getResources().getColorStateList(R.color.bottom_nav_view_tinit));
            mTabBottom.setItemTextColor(getResources().getColorStateList(R.color.bottom_nav_view_tinit));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_layout).setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_img_night).setBackground(getResources().getDrawable(R.mipmap.daily));
        } else {
            mCoordinator.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            mToolbar.setBackgroundColor(getResources().getColor(android.R.color.black));
            mToolbar.setTitleTextColor(getResources().getColor(android.R.color.darker_gray));
            mDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.darker_gray));
            mToolbar.getOverflowIcon().setTint(getResources().getColor(android.R.color.darker_gray));
            StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(android.R.color.black));
            mTabBottom.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTabBottom.setItemIconTintList(getResources().getColorStateList(R.color.bottom_nav_view_tinit_night));
            mTabBottom.setItemTextColor(getResources().getColorStateList(R.color.bottom_nav_view_tinit_night));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_layout).setBackgroundColor(getResources().getColor(android.R.color.black));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_img_night).setBackground(getResources().getDrawable(R.mipmap.night));
        }
    }

    private void setupNavView() {
        //nav 条目点击的监听
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(MyApplication.getContext(), "成为会员被点击", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Toast.makeText(MyApplication.getContext(), "关于被点击", Toast.LENGTH_SHORT).show();
                        break;
                }
                mDrawer.closeDrawers();
                return true;
            }
        });
        //切换夜间模式的按钮监听
        mNavView.getHeaderView(0).findViewById(R.id.nav_header_img_night).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNightMode();
            }
        });

        mNavHeaderImg = (ImageView) mNavView.getHeaderView(0).findViewById(R.id.nav_header_img);
        //更换头像
        mNavHeaderImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeHeader();
            }
        });
    }

    private void changeNightMode() {
        if ((Boolean) SPUtil.get(MainActivity.this, Constant.NIGHT_MODE, true)) {
            mCoordinator.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            mToolbar.setBackgroundColor(getResources().getColor(android.R.color.black));
            mToolbar.setTitleTextColor(getResources().getColor(android.R.color.darker_gray));
            mDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.darker_gray));
            mToolbar.getOverflowIcon().setTint(getResources().getColor(android.R.color.darker_gray));
            StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(android.R.color.black));
            mTabBottom.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTabBottom.setItemIconTintList(getResources().getColorStateList(R.color.bottom_nav_view_tinit_night));
            mTabBottom.setItemTextColor(getResources().getColorStateList(R.color.bottom_nav_view_tinit_night));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_layout).setBackgroundColor(getResources().getColor(android.R.color.black));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_img_night).setBackground(getResources().getDrawable(R.mipmap.night));
            SPUtil.put(MainActivity.this, Constant.NIGHT_MODE, false);
        } else {
            mCoordinator.setBackgroundColor(getResources().getColor(R.color.gray));
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            mDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
            mToolbar.getOverflowIcon().setTint(getResources().getColor(android.R.color.white));
            StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.colorPrimaryDark));
            mTabBottom.setBackgroundColor(getResources().getColor(android.R.color.white));
            mTabBottom.setItemIconTintList(getResources().getColorStateList(R.color.bottom_nav_view_tinit));
            mTabBottom.setItemTextColor(getResources().getColorStateList(R.color.bottom_nav_view_tinit));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_layout).setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            mNavView.getHeaderView(0).findViewById(R.id.nav_header_img_night).setBackground(getResources().getDrawable(R.mipmap.daily));
            SPUtil.put(MainActivity.this, Constant.NIGHT_MODE, true);
        }
    }

    private void changeHeader() {

    }

    private void setupTabBottom() {
        mTabBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_welfare:
                        mToolbar.setTitle(getResources().getString(R.string.main_welfare));
//                        selectFragment(0);
                        showAndhideFragment(0);
                        break;
                    case R.id.item_reading:
                        mToolbar.setTitle(getResources().getString(R.string.main_episode));
//                        selectFragment(1);
                        showAndhideFragment(1);

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initFragment() {
        fragments.add(new PictureFragment());
        fragments.add(new EpisodeFragment());
        fm = getSupportFragmentManager();
//        selectFragment(0);//来回切换会替换加载数据
        showAndhideFragment(0);

    }

    private void showAndhideFragment(int index) {
        if (currentFragment != fragments.get(index)) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragments.get(index);
            if (!fragments.get(index).isAdded()) {
                transaction.add(R.id.frame_home_activity, fragments.get(index)).show(fragments.get(index)).commit();
            } else {
                transaction.show(fragments.get(index)).commit();
            }
        }
    }


    private void selectFragment(int index) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_home_activity, fragments.get(index));
        fragmentTransaction.commit();
    }

    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return false;
    }

    private void exit() {
        Timer timer;
        if (!is_exit) {
            is_exit = true;
            ToastUtil.showLongToast(MainActivity.this,"再按一次退出");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    is_exit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }
}
