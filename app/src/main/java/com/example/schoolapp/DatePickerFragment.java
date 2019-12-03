package com.example.schoolapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    final Calendar c =Calendar.getInstance();


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,Year,Month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        update();

    }

    public String update(){
        String format="dd-MMM-yyyy";
        SimpleDateFormat sdf =new SimpleDateFormat(format, Locale.UK);
        return sdf.format(c.getTime());
    }

}
























