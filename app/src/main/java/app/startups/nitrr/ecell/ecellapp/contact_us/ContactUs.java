package app.startups.nitrr.ecell.ecellapp.contact_us;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.startups.nitrr.ecell.ecellapp.R;

/**
 * Created by aditya on 17/5/16.
 */
public class ContactUs extends Activity {
    Bitmap myBitmap;
    int flag = 0;
    private RecyclerView recycler1;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter Radapter;
    private ArrayList<datavar> datals;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override

    public void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        datals = new ArrayList<>();

        //new AsyncTaskParseJson().execute();
//        datals.add(new datavar(3, "Aditya", "Tech", "7587485272", "aditya999123@gmail.com"));


        recycler1 = (RecyclerView) findViewById(R.id.contactus_recycler1);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycler1.setLayoutManager(lmanager);
        Radapter = new adapter(getApplicationContext(), datals);
        //datals.add(new datavar(3, "Aditya", "Tech", "7587485272", "aditya999123@gmail.com"));

        String url = "http://adityaagr.tk/ContactJson3.json";
        //final String[] strjson = {""};
// Request a string response

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //           System.out.println(response);
                        try {
                            //             text1.setText("b");
                            JSONObject jsonobj = new JSONObject(response);

                            //                  datals.add(new datavar(3, "Aditya4", "Tech", "7587485272", "aditya999123@gmail.com"));


                            JSONArray dataJsonArr = jsonobj.getJSONArray("Contacts");

                            //                datals.add(new datavar(3, "Aditya", "Tech", "7587485272", "aditya999123@gmail.com"));

                            for (int i = 0; i < dataJsonArr.length(); i++) {
                                JSONObject jsonObject = dataJsonArr.getJSONObject(i);

                                String jsonname = jsonObject.optString("name").toString();
                                String jsondsgn = jsonObject.optString("designation").toString();
                                String jsonnumber = jsonObject.optString("phone").toString();
                                String jsonemail = jsonObject.optString("email").toString();


                                //new ImageLoadTask("http://adityaagr.tk/"+jsonname+".jpg");

                                datals.add(new datavar(i + 1, jsonname, jsondsgn, jsonnumber, jsonemail));


                            }
                            recycler1.setAdapter(Radapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        });

// Add the request to the queue

        Volley.newRequestQueue(this).add(stringRequest);


    }
}
