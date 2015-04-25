package com.example.amon.pollkat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PollKatPresenterParticipant extends ActionBarActivity {

    //Button button;
    public static String line = "";
    public final static String Extra_Message1 = "com.kavya.qns.MESSAGE";

    ImageButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_kat_presenter_participant);
        setTitle("PollKat");
        addListenerOnButton();
    }
    public void onParticipant(View view){
        Thread thrd = new Thread(){
            @Override
            public void  run(){
                try {
                    SharedPreferences settings = getSharedPreferences("pollKatIP",
                            Context.MODE_PRIVATE);
                    String ip = settings.getString("IP", null);
                    Log.d("IP Address", ip);
                    //String ip = "http://172.24.104.97:8080/";//192.168.252.28:8080/";

                    URL url = new URL(ip+"request_questions");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    readStream(con.getInputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        thrd.start();

    }

    private void readStream(InputStream in) {
        BufferedReader reader = null;
        Intent intent = new Intent(this, Participant.class);
        try {
            reader = new BufferedReader(new InputStreamReader(in));

            line = reader.readLine();
                Log.d("pollKAT", line);
                intent.putExtra(Extra_Message1, line);

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

        button = (ImageButton) findViewById(R.id.pollKatPresenterID);

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
