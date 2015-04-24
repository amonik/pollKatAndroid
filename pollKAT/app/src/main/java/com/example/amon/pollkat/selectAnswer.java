package com.example.amon.pollkat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class selectAnswer extends ActionBarActivity {

    public RadioGroup rgOpinion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_answer);


        String singleQuestion = getIntent().getStringExtra("question");
        TextView textView1 = (TextView) findViewById(R.id.textQuestion);
        textView1.setText(singleQuestion);

        rgOpinion = (RadioGroup) findViewById(R.id.rgOpinion);
        Button btnSubmit = (Button) findViewById(R.id.saveButton);

    }

    public void saveAnswer(View view){
        RadioButton selectRadio = (RadioButton) findViewById(rgOpinion.getCheckedRadioButtonId());
        String opinion = selectRadio.getText().toString();
        Toast.makeText(getApplicationContext(),opinion, Toast.LENGTH_LONG).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_answer, menu);
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
