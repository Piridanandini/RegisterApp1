package com.example.welcome.registerapp.planner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.welcome.registerapp.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PlannerActivity extends AppCompatActivity {
    CompactCalendarView compactCalendarView;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    SimpleDateFormat sdf;
    TextView tx_date, tx_today;
    LinearLayout ly_detail;
    LinearLayout ly_left, ly_right;
    Calendar myCalendar;
    ImageView im_back;
    Date c;
    SimpleDateFormat df;
    String formattedDate;
    String[] dates = new String[0];
    RecyclerView recyclerView;
    TextView tx_item;


    String[] day={"10","20","21","25","27"};
    String[] month={"09","09","10","10","10"};
    String[] year ={"2019","2019","2019","2019","2019"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_planner);

        init();
        calendarlistener();
        Setdate();


        tx_date.setText(""+formattedDate);


        ly_right.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                compactCalendarView.showCalendarWithAnimation();
                compactCalendarView.showNextMonth();
            }
        });

        ly_left.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                compactCalendarView.showCalendarWithAnimation();
                compactCalendarView.showPreviousMonth();
            }
        });

        tx_today.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent = new Intent(PlannerActivity.this, PlannerActivity.class);
                startActivity(intent);
                finish();

            }
        });

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                finish();
            }
        });


    }

    //variable initialization

    public void init() {
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        tx_date = (TextView) findViewById(R.id.text);
        ly_left = (LinearLayout) findViewById(R.id.layout_left);
        ly_right = (LinearLayout) findViewById(R.id.layout_right);
        im_back = (ImageView) findViewById(R.id.image_back);
        tx_today = (TextView) findViewById(R.id.text_today);
    }



    //calendar method

    public void calendarlistener() {
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override

            public void onDayClick(Date dateClicked) {

                if ( DateFormat.format(dateClicked).equals("2019-09-20")){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlannerActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogLayout = inflater.inflate(R.layout.custom_alert, null);
                    alertDialog.setView(dialogLayout);
                    alertDialog.setPositiveButton("OK", null);
                    alertDialog.setView(dialogLayout);
                    // create and show the alert dialog
                    AlertDialog dialog = alertDialog.create();
                    alertDialog.show();
                }
                else {
                    Toast.makeText(getApplicationContext(),DateFormat.format(dateClicked)+" In This day no Events Available",Toast.LENGTH_LONG).show();
                }

            }

            @Override

            public void onMonthScroll(Date firstDayOfNewMonth) {

                compactCalendarView.removeAllEvents();
                Setdate();
                tx_date.setText(simpleDateFormat.format(firstDayOfNewMonth));

            }
        });
    }

    //get current date

    public void Setdate() {


        c = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c);

        compactCalendarView.setUseThreeLetterAbbreviation(true);

        sdf = new SimpleDateFormat("MMMM yyyy");


        myCalendar = Calendar.getInstance();

        for (int j = 0; j < month.length; j++) {

            int mon = Integer.parseInt(month[j]);
            myCalendar.set(Calendar.YEAR, Integer.parseInt(year[j]));
            myCalendar.set(Calendar.MONTH, mon - 1);
            myCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day[j]));

            Event event = new Event(Color.RED, myCalendar.getTimeInMillis(), "test");
            compactCalendarView.addEvent(event);
        }
    }


}
