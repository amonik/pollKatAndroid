package com.example.amon.pollkat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PollKatPresenterParticipant extends ActionBarActivity {
    Button button;
    public static String line = "";
    public final static String Extra_Message1 = "com.kavya.qns.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_kat_presenter_participant);
        addListenerOnButton();
    }
    public void onParticipant(View view){
        Thread thrd = new Thread(){
            @Override
            public void  run(){
                try {
                    String ip = "http://192.168.252.28:8080/";

                    URL url = new URL(ip+"request_questions");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    readStream(con.getInputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        thrd.start();
        //Log.d("questions",line);
        //Intent intent = new Intent(this, Participant.class);
        //intent.putExtra("QuestionsString",line);
        //startActivity(intent);

    }

    private void readStream(InputStream in) {
        BufferedReader reader = null;
        Intent intent = new Intent(this, Participant.class);
        try {
            reader = new BufferedReader(new InputStreamReader(in));

            while ((line = reader.readLine()) != null) {
                Log.d("pollKAT", line);
                intent.putExtra(Extra_Message1,line);
            }
            startActivity(intent);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.pollKatPresenterID);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,Presenter.class);
                startActivity(intent);

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poll_kat_presenter_participant, menu);
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
