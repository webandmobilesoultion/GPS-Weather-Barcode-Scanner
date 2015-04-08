package com.raoul.theridge;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.raoul.theridge.R;


public class GoogleActivity extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater,

        ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_google,
                container, false);
        Button button = (Button) view.findViewById(R.id.test_button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast. makeText(getActivity(),"hi",Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }

}
