package com.example.android_resapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_resapi.R;
import com.example.android_resapi.ui.apicall.GetThings;


public class MainActivity extends AppCompatActivity {
    final static String TAG = "AndroidAPITest";
    EditText listThingsURL, thingShadowURL, getLogsURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetThings(MainActivity.this, "https://tzma7xdqq1.execute-api.ap-northeast-2.amazonaws.com/prod/devices").execute();
       // listThingsURL = findViewById(R.id.listThingsURL);
        //thingShadowURL = findViewById(R.id.thingShadowURL);
       // getLogsURL = findViewById(R.id.getLogsURL);

        /*Button listThingsBtn = findViewById(R.id.listThingsBtn);
        listThingsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // String urlstr = listThingsURL.getText().toString();
                //Log.i(TAG, "listThingsURL=" + urlstr);
                /*
                if (urlstr == null || urlstr.equals("")) {
                    Toast.makeText(MainActivity.this, "사물목록 조회 API URI 입력이 필요합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ListThingsActivity.class);
                //intentd.putExtra("listThingsURL", listThingsURL.getText().toString());
                intent.putExtra("listThingsURL", "https://tzma7xdqq1.execute-api.ap-northeast-2.amazonaws.com/prod/devices");

                startActivity(intent);
                //  new GetThings(MainActivity.this).execute();
                //  new GetThingShadow(MainActivity.this, "MyMKRWiFi1010").execute();

            }
        });*/

        /*Button thingShadowBtn = findViewById(R.id.thingShadowBtn);
        thingShadowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String urlstr = thingShadowURL.getText().toString();
                /*
                if (urlstr == null || urlstr.equals("")) {
                    Toast.makeText(MainActivity.this, "사물상태 조회/변경 API URI 입력이 필요합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, DeviceActivity.class);
                //intent.putExtra("thingShadowURL", thingShadowURL.getText().toString());
                intent.putExtra("thingShadowURL", "https://tzma7xdqq1.execute-api.ap-northeast-2.amazonaws.com/prod/devices/MyMKRWiFi1010");
                startActivity(intent);

            }
        });*/

        Button listLogsBtn = findViewById(R.id.listLogsBtn);
        listLogsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String urlstr = getLogsURL.getText().toString();
                /*if (urlstr == null || urlstr.equals("")) {
                    Toast.makeText(MainActivity.this, "사물로그 조회 API URI 입력이 필요합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                //intent.putExtra("getLogsURL", "https://tzma7xdqq1.execute-api.ap-northeast-2.amazonaws.com/prod/devices/DeviceData/log");
                intent.putExtra("getLogsURL", "https://tzma7xdqq1.execute-api.ap-northeast-2.amazonaws.com/prod/devices/Logging/log");

                startActivity(intent);
            }
        });
    }
}


