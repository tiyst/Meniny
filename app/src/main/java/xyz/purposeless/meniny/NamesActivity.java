package xyz.purposeless.meniny;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class NamesActivity extends AppCompatActivity implements MeninyEntryFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;

    /**
     * Displays all names parsed by csvParser class in ListView
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        csvParser parser;
        this.fragmentManager = getSupportFragmentManager();
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();

        try {
            parser = new csvParser(this, "sk-meniny.csv");
            final ArrayList<Name> nameList = parser.getNames();
            String[] listItems = new String[nameList.size()];

            for (int i = 0; i < listItems.length-1; i++) {
                String name = nameList.get(i).getName();
                String date = nameList.get(i).getDate();
                listItems[i] = name.toString();
                MeninyEntryFragment frag = MeninyEntryFragment.newInstance(name, date);
                fTransaction.add(R.id.fragmentLinearLayout, frag);
            }
            fTransaction.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //TODO - Implement with Fragments


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
