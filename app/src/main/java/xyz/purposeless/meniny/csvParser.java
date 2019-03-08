package xyz.purposeless.meniny;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;


public class csvParser {
    private Context context;
    private final String fileName;
    private ArrayList<Name> names;

    /**
     * Used for parsing and creating names from .csv file
     */
    public csvParser(Context context, String fileName) throws IOException {
        this.context = context;
        this.fileName = fileName;
        this.names = readCSV();
    }

    public ArrayList<Name> readCSV() throws IOException {
        InputStream is = context.getAssets().open(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        String splitBy = ",";

        ArrayList<Name> nams = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            String[] row = line.split(splitBy);
            String name = row[0];
            String date = row[1];
            boolean isEvent = false;
            if(row.length > 2) { //Sviatok (iny vypis)
                isEvent = true;
            }
            nams.add(new Name(name, date, isEvent));
        }
        return nams;
    }

    public String parseName() {
        return parseCustomName(new Date());
    }

    public String parseCustomName(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM");
        String datum = sdf.format(date);

        for (Name name : names) {
            if (name.getDate().equals(datum)) {
                return name.getName();
            }
        }
        return "Error 404";
    }

    /**
     * @return an array of Strings
     * If required name is found, 0th element is name and 1st is customised date.
     */
    public String[] findByName(String name) {
        String[] returner = new String[] {"Nenájdené", "-||-"};
        for (Name nm : names) {
            if (nm.evaluate(name)) {
                returner[0] = nm.getName();
                returner[1] = nm.getDate();
            }
        }
        return returner;
    }

    public ArrayList<Name> getNames() {
        return names;
    }

}
