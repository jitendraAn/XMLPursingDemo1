package com.example.user.xmlpursingdemo1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetTitleData {

    private ListView lv;
    private ArrayAdapter<Data> arrayAdapter;
    private ArrayList<Data> arrayList;
    private RecyclerView rcv;
    private RecyclerView.LayoutManager layoutManager;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();

        rcv = (RecyclerView) findViewById(R.id.rcv);
        layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(this, arrayList);
        rcv.setAdapter(itemAdapter);

        if (checkInternet() != null && checkInternet().isConnected()) {
            DownloadXmlAsysnTask downloadXmlAsysnTask = new DownloadXmlAsysnTask();
            downloadXmlAsysnTask.getTitleData = this;
            downloadXmlAsysnTask.execute();
        } else {
            Toast.makeText(this, "Please Check NetConnect", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void getTitleData(ArrayList<Data> strings) {
        arrayList.addAll(strings);
        itemAdapter.notifyDataSetChanged();

    }

    public NetworkInfo checkInternet() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();

        return activeInfo;
    }
}
