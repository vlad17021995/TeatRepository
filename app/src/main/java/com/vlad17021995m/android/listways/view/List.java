package com.vlad17021995m.android.listways.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.vlad17021995m.android.listways.R;
import com.vlad17021995m.android.listways.controller.RouteProvider;
import com.vlad17021995m.android.listways.model.Route;

public class List extends AppCompatActivity {

    private ListView listView;
    private EditText date1Edit;
    private EditText date2Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.listView);
        date1Edit = (EditText)findViewById(R.id.date1Edit);
        date2Edit = (EditText)findViewById(R.id.date2Edit);
        java.util.List<Route> list = RouteProvider.getInstance().getRoutesFromFile(this);
        if (list.size() > 0){
            String []arr = new String[list.size()];
            for (int i = 0;i < list.size();i++) {
                arr[i] = "маршрут номер " + list.get(i).getId();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(List.this, android.R.layout.simple_list_item_1, arr);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(itemClickListener);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.List<Route> routes = RouteProvider.getInstance().getRoutes(date1Edit.getText().toString(), date2Edit.getText().toString(), List.this);
                String []arr = new String[routes.size()];
                for (int i = 0;i < routes.size();i++) {
                    arr[i] = "маршрут номер " + routes.get(i).getId();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(List.this, android.R.layout.simple_list_item_1, arr);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(itemClickListener);
            }
        });
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            java.util.List<Route> list = RouteProvider.getInstance().getRouteList();
            Route r = list.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(List.this);
            builder.setTitle("Маршрут номер " + r.getId())
                    .setMessage(r.toString())
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
