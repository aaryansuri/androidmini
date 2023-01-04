package com.example.hello.hello.LoginSignup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.hello.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class You extends Fragment {

    static EditText birthdate;
    Calendar calendar;
    static EditText height;
    static EditText weight;
    RadioGroup radioGroup;
    public You(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.you_fragment,container,false);
        calendar = Calendar.getInstance();
        birthdate = view.findViewById(R.id.birth_date_id);
        height = view.findViewById(R.id.height_id);
        weight = view.findViewById(R.id.weight_id);



        radioGroup = view.findViewById(R.id.radio_group_id);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked = group.findViewById(checkedId);
                UserDetails.setGENDER(checked.getText().toString());
            }
        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                Calendar dob = Calendar.getInstance();
                Calendar today = Calendar.getInstance();

                dob.set(year,month,dayOfMonth);

                int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                    age--;
                }

                Integer ageInt = new Integer(age);

                String ageString =  ageInt.toString();

                UserDetails.setAGE(ageString);



                updateLabel();
            }
        };

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthdate.setText(sdf.format(calendar.getTime()));
    }

}
