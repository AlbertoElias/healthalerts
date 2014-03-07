package com.bynd2015.vida;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlertFormActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_form);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Button submit = (Button)findViewById(R.id.submitBtn);
        final EditText descEdit = (EditText)findViewById(R.id.inputDesc);
        final EditText sympEdit = (EditText)findViewById(R.id.inputSymp);
        final EditText prevEdit = (EditText)findViewById(R.id.inputPrev);
        final EditText cureEdit = (EditText)findViewById(R.id.inputCure);
        final Spinner prioSpinner = (Spinner)findViewById(R.id.inputPrio);
        final EditText radEdit = (EditText)findViewById(R.id.inputRadius);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("description", descEdit.getText().toString());
                params.put("symptoms", sympEdit.getText().toString());
                params.put("prevent", prevEdit.getText().toString());
                params.put("help", cureEdit.getText().toString());
                params.put("urgency", String.valueOf(prioSpinner.getSelectedItemPosition()+1));
                params.put("radius", radEdit.getText().toString());
                client.get("http://byndhack.herokuapp.com/events/", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
                        if (response.equalsIgnoreCase("true")) {
                            new AlertDialog.Builder(AlertFormActivity.this)
                                    .setMessage("Alert added successfully")
                                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            startActivity(getIntent());
                                        }
                                    })
                                    .show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, AlertListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}