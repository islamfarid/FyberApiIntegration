package com.example.islam.fyberapiintegration.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.islam.fyberapiintegration.R;
import com.example.islam.fyberapiintegration.common.Constants;
import com.example.islam.fyberapiintegration.param.view.APiParamFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.fragment_container_framlayout,
                new APiParamFragment(),APiParamFragment.class.getName()).addToBackStack(Constants.MAIN_ACTIVITY_STACK).commit();
    }
    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 1) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }
}
