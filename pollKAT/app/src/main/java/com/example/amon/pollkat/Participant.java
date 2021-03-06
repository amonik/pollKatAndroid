package com.example.amon.pollkat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Participant extends ActionBarActivity {
    public static ArrayList<String> questionList = new ArrayList<String>();
    public static ArrayList<String> timeList = new ArrayList<String>();

    public static ArrayList<String> questionTextList = new ArrayList<String>();

    public static ArrayList<String> idList = new ArrayList<String>();
    public static ArrayList<String> questions = new ArrayList<String>();

    //public static ArrayList<String> timeList = new ArrayList<String>();

    public static ArrayList<String> SplitQuestions;
    public static String[] splitQns;
    public static String questionID;
    public static ArrayList<String> questionIDList= new ArrayList<String>();

    public static String TAG = "qest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);

        questionList.clear();
        //timeList.clear();
        // read from server
        //readNames("noteHeaders");
        Intent intent = getIntent();
        String qns = intent.getStringExtra(PollKatPresenterParticipant.Extra_Message1);

        Log.d("Participant", qns);


        splitQns = qns.split("<--->");
        SplitQuestions = new ArrayList<String>(Arrays.asList(splitQns));
        Log.d(TAG, SplitQuestions.toString());

        for (String s: SplitQuestions){
            questionIDList.add(s.substring(0, s.indexOf("@")));


        }


        for(String q :SplitQuestions){
            timeList.add(q.substring(q.lastIndexOf("@")+1));
            questionTextList.add(q.substring(q.indexOf("@")+1,q.lastIndexOf("@")));
        }


        //Log.d("TimeList",timeList);
        //Log.d("Participant", qns);

        //questionList.add(qns);//"Do you like this course?");
        //timeList.add("april 24");


        questionList.add(qns);//"Do you like this course?");






        // SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.listlayout,questionList,timeList);
        //adapter.setViewBinder(VIEW_BINDER);
        //SimpleCursorAdapter.ViewBinder VIEW_BINDER = new SimpleCursorAdapter.ViewBinder()
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questionList);

        final ListView questionListView = (ListView)findViewById(R.id.listView);

        questionListView.setAdapter(namesAdapter);
        // ListView Item Click Listener
        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) questionListView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(getApplicationContext(), statisticsPage.class);
                intent.putExtra("question", itemValue);
                intent.putExtra("ID","id");//to add id value
                startActivity(intent);
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_participant, menu);
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
