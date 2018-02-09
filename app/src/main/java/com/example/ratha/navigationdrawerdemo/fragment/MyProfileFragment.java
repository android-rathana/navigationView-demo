package com.example.ratha.navigationdrawerdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratha.navigationdrawerdemo.R;

/**
 * Created by ratha on 2/8/2018.
 */

public class MyProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_profile_frag_layout,container,false);
    }

    private static MyProfileFragment INSTANCE;
    public static MyProfileFragment getINSTANCE(String name){
        if(INSTANCE==null)
            INSTANCE= new MyProfileFragment();
        Bundle args=new Bundle();
        args.putString("TAG",name);
        INSTANCE.setArguments(args);
        return INSTANCE;
    }
}
