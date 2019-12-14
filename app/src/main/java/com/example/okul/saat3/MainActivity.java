package com.example.okul.saat3;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends FragmentActivity {
    String saat="00";
    String dakika="00";
    String derece1="00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button saatsec1txt = (Button) findViewById(R.id.txtTime);
        final Button derecesectxt=(Button) findViewById(R.id.txtderecesec);
        final Button eklemsj=(Button) findViewById(R.id.eklemsj);
        eklemsj .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(fm, "Sample Fragment");

            }
        });

        saatsec1txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        saat=Integer.toString(selectedHour);
                        dakika=Integer.toString(selectedMinute);
                        if(saat.length()==1){
                            saat="0"+saat;
                        }
                        if(dakika.length()==1){
                            dakika="0"+dakika;
                        }
                        saatsec1txt.setText(saat + ":" + dakika);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);

                timePicker.show();
            }
        });
        derecesectxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int derece = 10;

                TimePickerDialog derecepicker;
                derecepicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        derece1 = Integer.toString(minute);
                        derecesectxt.setText(derece1);

                    }
                }, 0, derece, true);

                derecepicker.setTitle("Dakika Seç");
                derecepicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", derecepicker);
                derecepicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", derecepicker);
                derecepicker.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
