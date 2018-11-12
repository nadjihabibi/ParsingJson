package com.example.bital_117.parsingjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<ListItem> mListItems;
    private TextView tv;
    private RequestQueue mReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv =findViewById(R.id.tv1);
        //Button button = findViewById(R.id.button1);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mListItems = new ArrayList<>();

        mReq = Volley.newRequestQueue(this);

        //button.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
                jsonParse();
           // }
       // });
    }

    private void jsonParse() {
        String url = "http://api.jakarta.go.id/v1/cctvbalitower/?format=geojson";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("features");

                    for (int i = 0; i < jsonArray.length();i++){
                        JSONObject feature = jsonArray.getJSONObject(i);

                        JSONObject properti = feature.getJSONObject("properties");

                        int id = properti.getInt("id");
                        String urlCamera = properti.getString("url");

                        JSONObject location = properti.getJSONObject("location");
                        double latitude = location.getDouble("latitude");
                        double longitude = location.getDouble("longitude");

                        mListItems.add(new ListItem(urlCamera, id, latitude, longitude));

                        //tv.append(id+"\n"+urlCamera+"\n"+latitude+"\n"+longitude);

                        //String semua = feature.getString("type");
                        //tv.append(semua);

                    }

                    mAdapter = new Adapter(MainActivity.this, mListItems);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                /*

                try {
                    //JSONObject jsonObject = response.getJSONObject("result");
                    //jsonObject object = jsonObject.getString("");
                    //String respon = jsonObject.getString("result");
                    String respon = response.getString("result");
                    String data = response.getString("data");

                    tv.append(respon+data);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params =new HashMap<String, String>();
                params.put("authorization","J4cMsZFdpYnK2R/gE6uDKA5iH1Ldj9bbs8qVGWF90VsooOkPQVr1j1XlNDzrWrbW4iMI5m/0ZDEX1pYI0w+hsvpIy/697QTmWJ/POR1q6tk=");
                //return super.getHeaders();
                return  params;
            }
        };
        mReq.add(request);
    }
}
