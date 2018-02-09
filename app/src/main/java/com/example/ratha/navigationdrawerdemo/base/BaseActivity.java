package com.example.ratha.navigationdrawerdemo.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ratha.navigationdrawerdemo.R;
import com.example.ratha.navigationdrawerdemo.fragment.GroupFragment;
import com.example.ratha.navigationdrawerdemo.fragment.HomeFragment;
import com.example.ratha.navigationdrawerdemo.fragment.LoginFragment;
import com.example.ratha.navigationdrawerdemo.fragment.MyAccountFragment;
import com.example.ratha.navigationdrawerdemo.fragment.MyProfileFragment;
import com.example.ratha.navigationdrawerdemo.fragment.PaymentFragment;
import com.example.ratha.navigationdrawerdemo.fragment.SettingFragment;
import com.example.ratha.navigationdrawerdemo.fragment.TransferFragment;

/**
 * Created by ratha on 2/8/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected NavigationView navigationView;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected CharSequence mDrawerTitle;
    protected CharSequence mTitle;

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        setupNav();
        setupNavigationViewListener();
    }




    private void setToolBar() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
    }

    private void setupNav() {

        mDrawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigationView);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupNavigationViewListener(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                String title="";
                switch (item.getItemId()){
                    case R.id.home :
                            showToast("home");
                            fragment= HomeFragment.getINSTANCE("HOME");
                            title="Home";
                        break;
                    case R.id.my_account :
                        fragment= MyAccountFragment.getINSTANCE("MY_ACCOUNT");
                        showToast("account");
                        title="My account";
                        break;
                    case R.id.transfer :
                        fragment= TransferFragment.getINSTANCE("TRANSFER");
                        showToast("transfer");
                        title="Transfer";
                        break;
                    case R.id.payment :
                        fragment= PaymentFragment.getINSTANCE("PAYMENT");
                        showToast("payment");
                        title="Payment";
                        break;
                    case R.id.my_profile :
                        fragment= MyProfileFragment.getINSTANCE("PROFILE");
                        title="Profile";
                        showToast("profile");
                        break;
                    case R.id.my_group :
                        fragment= GroupFragment.getINSTANCE("GROUP");
                        title="Group";
                        showToast("group");
                        break;
                    case R.id.setting :
                        fragment= SettingFragment.getINSTANCE("SETTING");
                        title="Setting";
                        showToast("setting");
                        break;
                    case R.id.login :
                        fragment= LoginFragment.getINSTANCE("LOGIN");
                        title="Login";
                        showToast("login");
                        break;
                }

                FragmentTransaction t=getSupportFragmentManager().beginTransaction();
                t.replace(R.id.content_frame,fragment,fragment.getArguments().getString("TAG"));
                t.addToBackStack(null);
                t.commit();
                toolbar.setTitle(title);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected  void showToast(String text){
        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    protected void setDefaultFragment(){
        Fragment fragment=getSupportFragmentManager().findFragmentByTag("HOME");
        FragmentTransaction t=getSupportFragmentManager().beginTransaction();
        if(fragment!=null){
            t.replace(R.id.content_frame,fragment,"HOME");
        }else{
            fragment=HomeFragment.getINSTANCE("HOME");
            t.add(R.id.content_frame, fragment,"HOME");
            t.commit();
        }

    }
}
