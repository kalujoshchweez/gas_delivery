package com.goland.gas_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.goland.gas_delivery.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ExpandableListView expandableListView;

    private ExpandableAdapter expandableAdapter;

    private List<String> listDataGroup;

    private HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();

    }


    /**
     * method to initialize the views
     */
    private void initViews() {

        expandableListView = findViewById(R.id.expandableListView);

    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataGroup.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataGroup.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // ExpandableListView Group collapsed listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {

        // initializing the list of groups
        listDataGroup = new ArrayList<>();

        // initializing the list of child
        listDataChild = new HashMap<>();

        // initializing the adapter object
        expandableAdapter = new ExpandableAdapter(this, listDataGroup, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(expandableAdapter);

    }

    /*
     * Preparing the list data
     */
    private void initListData() {


        // Adding group data
        listDataGroup.add(getString(R.string.text_Total));
        listDataGroup.add(getString(R.string.text_k));
        listDataGroup.add(getString(R.string.text_lake));
        listDataGroup.add(getString(R.string.text_pro));

        // array of strings
        String[] array;

        // list of total gas
        List<String> Total = new ArrayList<>();
        array = getResources().getStringArray(R.array.Total_Gas);
        for (String item : array) {
            Total.add(item);
        }

        // list of k gas
        List<String> K = new ArrayList<>();
        array = getResources().getStringArray(R.array.K_Gas);
        for (String item : array) {
            K.add(item);
        }

        // list of lake gas
        List<String> Lake = new ArrayList<>();
        array = getResources().getStringArray(R.array.Lake_Gas);
        for (String item : array) {
            Lake.add(item);
        }

        // list of pro gas
        List<String> Pro = new ArrayList<>();
        array = getResources().getStringArray(R.array.Pro_Gas);
        for (String item : array) {
            Pro.add(item);
        }

        // Adding child data
        listDataChild.put(listDataGroup.get(0), Total);
        listDataChild.put(listDataGroup.get(1), K);
        listDataChild.put(listDataGroup.get(2), Lake);
        listDataChild.put(listDataGroup.get(3), Pro);

        // notify the adapter
        expandableAdapter.notifyDataSetChanged();
    }

}