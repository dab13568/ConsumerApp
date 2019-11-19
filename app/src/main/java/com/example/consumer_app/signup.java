package com.example.consumer_app;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class signup extends AppCompatActivity implements TextWatcher {
    // test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        final TextView back = findViewById(R.id.back);
        //back to login
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //bold the text
        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        back.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        back.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });

        final Button nxt = findViewById(R.id.next);
        final EditText id = findViewById(R.id.id_person);
        final EditText fname = findViewById(R.id.fname);
        final EditText lname = findViewById(R.id.lname);
        final EditText phone = findViewById(R.id.phone_number);
        id.addTextChangedListener(this);
        fname.addTextChangedListener(this);
        lname.addTextChangedListener(this);
        phone.addTextChangedListener(this);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = new FragmentSignUp2();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment_place, fragment);
                ft.commit();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        final ScrollView scrollView=findViewById(R.id.scroll);

        final Button nxt = findViewById(R.id.next);
        final EditText id = findViewById(R.id.id_person);
        final EditText fname = findViewById(R.id.fname);
        final EditText lname = findViewById(R.id.lname);
        final TextView warning = findViewById(R.id.error_messege);
        final EditText phone = findViewById(R.id.phone_number);

        if (!(id.getText().toString().length() == 9)) {
            warning.setVisibility(View.VISIBLE);
            warning.setText("There is no ID with " + id.getText().length() + " digits!");

        } else if (fname.getText().toString().isEmpty()) {
            warning.setVisibility(View.VISIBLE);
            warning.setText("Please enter your first name!");

        } else if (lname.getText().toString().isEmpty()) {
            warning.setVisibility(View.VISIBLE);
            warning.setText("Please enter your last name!");

        } else if (!(phone.getText().toString().matches("05[0-9]{8}"))) {
            warning.setVisibility(View.VISIBLE);
            warning.setText("Please enter correct cellphone!");
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            });
        } else {
            warning.setVisibility(View.GONE);
            nxt.setEnabled(true);
            nxt.setTextColor(Color.parseColor("#000000"));
        }
    }
}
