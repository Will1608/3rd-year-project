package com.example.williamfuller.anything;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    Button activityChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityChange=(Button)findViewById(R.id.changeActivity);


        /*numbers=(ListView) findViewById(R.id.list);

        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,num);

        numbers.setAdapter(adapter);*/
     activityChange.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent myIntent =new Intent(MainActivity.this,WiFiP2pActivity.class);
             startActivity(myIntent);
         }
     });


    }

}
