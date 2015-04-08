package com.raoul.theridge;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.raoul.theridge.R;


public class VirtualActivity extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater,

        ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.activity_virtual, container, false);
        }



}
