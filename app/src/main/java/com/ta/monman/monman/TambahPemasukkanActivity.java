package com.ta.monman.monman;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TambahPemasukkanActivity extends AppCompatActivity {
    EditText nominalTxt,detailTxt;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pemasukkan);

        setTitle("Tambah Pemasukkan");

        final  User user = SharedPrefManager.getInstance(this).getUser();
        userID = String.valueOf(user.getId());

        nominalTxt = findViewById(R.id.pm_nominal);
        detailTxt = findViewById(R.id.pm_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemasukkan();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);/*your activity name*/
                startActivity(intent);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);/*your activity name*/
        startActivity(intent);
    }

    private void pemasukkan() {
        final String nominal = nominalTxt.getText().toString().trim();
        final String detail = detailTxt.getText().toString().trim();


        //first we will do the validations
        if (TextUtils.isEmpty(nominal)) {
            nominalTxt.setError("Tolong isi nominalnya");
            nominalTxt.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(detail)) {
            detailTxt.setError("Silahkan isi darimana duitnya..");
            detailTxt.requestFocus();
            return;
        }

        //if it passes all the validations

        class PemasukkanBaru extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("transaksi", "Pemasukkan");
                params.put("nominal", nominal);
                params.put("detail", detail);
                params.put("user", userID);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_TAMBAH_PEMASUKKAN, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        PemasukkanBaru ru = new PemasukkanBaru();
        ru.execute();
    }

}
