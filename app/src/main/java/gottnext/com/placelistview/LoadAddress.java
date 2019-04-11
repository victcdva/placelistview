package gottnext.com.placelistview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LoadAddress extends AsyncTask<String, Void, ArrayList<Address>> {

    private static final String API_URL = "http://gottnext.com/GotNext/gotnext/address/";
    private static final String KEY_ADDRESSES = "addresses";
    private static final String KEY_STREET = "street";
    private static final String KEY_COLONY = "colony";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LOGITUDE = "logitude";

    private Activity activity;
    private ProgressDialog progress;

    public LoadAddress(Activity activity) { this.activity = activity; }

    protected void onPreExecute() {
        progress = new ProgressDialog(this.activity);
        progress.setMessage("Loading...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected ArrayList<Address> doInBackground(String... strings) {
        ArrayList<Address> list = new ArrayList<>();
        InputStream data;
        String result = "";
        JSONObject resultJSON = null;
        boolean error = false;
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(API_URL);
            connection = (HttpURLConnection)url.openConnection();
            data = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(data));
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            Log.d("result", result);
        }
        catch (MalformedURLException ex) {
            error = true;
            Log.e("Error", ex.getMessage());
        }
        catch (IOException ex) {
            Log.e("Error", ex.getMessage());
        }
        finally {
            if(connection != null) connection.disconnect();
        }

        if(!error) {
            try {
                resultJSON = new JSONObject(result);
            }
            catch(JSONException ex) {
                error = true;
                Log.e("Error", ex.getMessage());
            }
        }
        if(!error) {
            try {
                JSONArray array = resultJSON.getJSONArray(KEY_ADDRESSES);
                for(int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    String street = item.getString(KEY_STREET);
                    String colony = item.getString(KEY_COLONY);
                    String latitude = item.getString(KEY_LATITUDE);
                    String logitude = item.getString(KEY_LOGITUDE);
                    Address ad = new Address(street, colony, latitude, logitude);
                    list.add(ad);
                }
            }
            catch(JSONException ex) {
                error = true;
                Log.e("Error", ex.getMessage());
            }
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Address> list) {
        ListView listAddress = this.activity.findViewById(R.id.listPlaces);
        AddressInflater adapter = new AddressInflater(this.activity, list);
        listAddress.setAdapter(adapter);
        listAddress.setOnClickListener((View.OnClickListener) this);
        progress.dismiss();
    }
}
