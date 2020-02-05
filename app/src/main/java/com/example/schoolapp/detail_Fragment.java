package com.example.schoolapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class detail_Fragment extends Fragment {


    TextView regno,name,dob,email,phonenumber,region,district,ward;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        course_manager take=(course_manager)getActivity();


        regno=view.findViewById(R.id.regno);
        name=view.findViewById(R.id.detailname);
        dob=view.findViewById(R.id.dobtag);
        email=view.findViewById(R.id.detailemail);
        phonenumber=view.findViewById(R.id.phone_numbertag);
        region=view.findViewById(R.id.regiontag);
        district=view.findViewById(R.id.districttag);
        ward=view.findViewById(R.id.wardtag);

        regno.setText(take.Regno);
        name.setText(take.getData()[0]+" "+take.getData()[1]+" "+take.getData()[2]);
        dob.setText(take.dob);
        email.setText(take.email);
        phonenumber.setText(take.phoneno);
        region.setText(take.region);
        district.setText(take.district);
        ward.setText(take.ward);

    }
}
