package xyz.purposeless.meniny;

import android.app.DatePickerDialog;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;

import xyz.purposeless.meniny.Database.NameDatabase;
import xyz.purposeless.meniny.Services.NotificationService;

//TODO - widget support
public class MainActivity extends AppCompatActivity {

    private csvParser cp;
    private Calendar cal;
    private NameDatabase nameDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cal = Calendar.getInstance();

        parseCsv();
        setupFab();
        initEditText();
        Intent i = new Intent(this, NotificationService.class);
        this.startService(i);

        this.nameDatabase = Room.databaseBuilder(getApplicationContext(),
                NameDatabase.class, "name").build();
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
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Contact yay", Snackbar.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, ContactActivity.class));
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
        final EditText edittext = findViewById(R.id.customDateText);
        final DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            ((TextView) findViewById(R.id.headerText)).setText("Meniny " + cal.get(Calendar.DAY_OF_MONTH)
                    + "/" + cal.get(Calendar.MONTH) + " má: ");

            edittext.setText(cal.get(Calendar.DAY_OF_MONTH) + "/" +
                    cal.get(Calendar.MONTH));
            ((TextView) findViewById(R.id.nameText)).setText(cp.parseCustomName(cal.getTime()));
        };

        edittext.setOnClickListener(v ->
                new DatePickerDialog(MainActivity.this, date, cal
                .get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show());
    }
}
