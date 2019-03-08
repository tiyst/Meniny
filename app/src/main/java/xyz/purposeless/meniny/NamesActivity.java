package xyz.purposeless.meniny;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class NamesActivity extends Activity {

    private ListView namesListView;

    /**
     * Displays all names parsed by csvParser class in ListView
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        namesListView = (ListView) findViewById(R.id.name_view);
        final ArrayList<Name> nameList = csvParser.getParser().getNames();
        String[] listItems = new String[nameList.size()];

        for (int i = 0; i < listItems.length; i++) {
            Name name = nameList.get(i);
            listItems[i] = name.toString();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        //TODO - Implement with NameAdapter and Fragments
        //NameAdapter nmAdapter = new NameAdapter(this,android.R.layout.simple_list_item_1,listItems);
        namesListView.setAdapter(adapter);
    }
}
