package com.example.vkinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.vkinfo.utils.NetworksUtils.generateURL;
import static com.example.vkinfo.utils.NetworksUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private TextView result;
    private TextView errorMessage;

    private void shouResultTextView() {
        result.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
    }

    private void shouErrorTextView() {
        result.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    class VkQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            String firstName = null;
            String lastName = null;
            if (response != null && !response.equals("")) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("response");
                    JSONObject userInfo = jsonArray.getJSONObject(0);
                    firstName = userInfo.getString("first_name");
                    lastName = userInfo.getString("last_name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String resultingString = "Name: " + firstName + "\n" + "Last Name: " + lastName;
                result.setText(resultingString);
                shouResultTextView();
            }else {shouErrorTextView();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vc);
        result = findViewById(R.id.tv_result);
        errorMessage = findViewById(R.id.tv_error_message);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedURL = generateURL(searchField.getText().toString());

                new VkQueryTask().execute(generatedURL);
            }
        };
        searchButton.setOnClickListener(onClickListener);
    }
}