package xyz.purposeless.meniny;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;

import xyz.purposeless.meniny.Services.NotificationService;

//TODO - widget support
public class MainActivity extends AppCompatActivity {

    private csvParser cp;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCalendar = Calendar.getInstance();

        parseCsv();
        setupFab();
        initEditText();
        Intent i = new Intent(this,NotificationService.class);
        this.startService(i);
    }

    public void parseCsv() {
        try {
            this.cp = new csvParser(this, "sk-meniny.csv");
            cp.readCSV();
            Log.d("mainActivity", "reading CSV");
        } catch (IOException e) {
            Log.e(this.toString(),"IO Exception");
            e.printStackTrace();
        }

        ((TextView)findViewById(R.id.nameText)).setText(cp.parseName());
    }

    public void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fabButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact yay", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this,ContactActivity.class));
            }
        });
    }

    public void findByName(View v) {
        String[] hlp = cp.findByName(((EditText)findViewById(R.id.nameEditText)).getText().toString());
        String requestedName = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        ((TextView) findViewById(R.id.headerText)).setText(hlp[0] + " má meniny: ");

        ((TextView) findViewById(R.id.nameText)).setText(hlp[1]);

    }

    public void showAllMeniny(View view) {
        Intent intent = new Intent(MainActivity.this, NamesActivity.class);
        startActivity(intent);
    }


    private void initEditText() {
        final EditText edittext = (EditText) findViewById(R.id.customDateText);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                ((TextView) findViewById(R.id.headerText)).setText("Meniny " + myCalendar.get(Calendar.DAY_OF_MONTH)
                        + "/" + myCalendar.get(Calendar.MONTH) + " má: ");

                edittext.setText(myCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                        myCalendar.get(Calendar.MONTH));
                ((TextView) findViewById(R.id.nameText)).setText(cp.parseCustomName(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}
