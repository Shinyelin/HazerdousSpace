package com.example.android_resapi.ui.apicall;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.android_resapi.R;
import com.example.android_resapi.httpconnection.GetRequest;

public class GetThingShadow extends GetRequest {
    final static String TAG = "AndroidAPITest";
    String urlStr;
    public GetThingShadow(Activity activity, String urlStr) {
        super(activity);
        this.urlStr = urlStr;
    }

    @Override
    protected void onPreExecute() {
        try {
            Log.e(TAG, urlStr);
            url = new URL(urlStr);

        } catch (MalformedURLException e) {
            Toast.makeText(activity,"URL is invalid:"+urlStr, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            activity.finish();
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        if (jsonString == null)
            return;
        Map<String, String> state = getStateFromJSONString(jsonString);
        TextView reported_ledTV = activity.findViewById(R.id.reported_led);
        TextView reported_distTV = activity.findViewById(R.id.reported_dist);
        TextView reported_bzrTV = activity.findViewById(R.id.reported_bzr);

        reported_distTV.setText(state.get("reported_distance"));
        reported_ledTV.setText(state.get("reported_LED"));
        reported_bzrTV.setText(state.get("reported_Buzzer"));
        TextView desired_ledTV = activity.findViewById(R.id.desired_led);
        TextView desired_distTV = activity.findViewById(R.id.desired_dist);
        TextView desired_bzrTV = activity.findViewById(R.id.desired_bzr);
        desired_distTV.setText(state.get("desired_distance"));
        desired_ledTV.setText(state.get("desired_LED"));
        desired_bzrTV.setText(state.get("desired_Buzzer"));

    }

    protected Map<String, String> getStateFromJSONString(String jsonString) {
        Map<String, String> output = new HashMap<>();
        try {
            // 처음 double-quote와 마지막 double-quote 제거
            jsonString = jsonString.substring(1,jsonString.length()-1);
            // \\\" 를 \"로 치환
            jsonString = jsonString.replace("\\\"","\"");
            Log.i(TAG, "jsonString="+jsonString);
            JSONObject root = new JSONObject(jsonString);
            JSONObject state = root.getJSONObject("state");
            JSONObject reported = state.getJSONObject("reported");
            String distValue = reported.getString("distance");
            String ledValue = reported.getString("LED");
            String bzrValue = reported.getString("Buzzer");
            output.put("reported_distance", distValue);
            output.put("reported_LED",ledValue);
            output.put("reported_Buzzer",bzrValue);


            JSONObject desired = state.getJSONObject("desired");
            String desired_distValue = desired.getString("distance");
            String desired_ledValue = desired.getString("LED");
            String desired_bzrValue = desired.getString("Buzzer");
            output.put("desired_distance", desired_distValue);
            output.put("desired_LED",desired_ledValue);
            output.put("desired_Buzzer",desired_bzrValue);

        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }
}
