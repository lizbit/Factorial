package com.example.robertcockrell.factorial;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
TextView userNum;
TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNum = (TextView)findViewById(R.id.userNumber);
        answer = (TextView)findViewById(R.id.answer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onButtonPressed(View view){
        EditText user = (EditText)findViewById(R.id.userNumber);
        String message = user.getText().toString();
        RunnableClass runnable = new RunnableClass(message, handler);
        Thread thread = new Thread(runnable);
        thread.start();
    }

public class RunnableClass implements Runnable {
    String message;
    Handler handler;

    public RunnableClass(String message, Handler handler) {
        this.message = message;
        this.handler = handler;
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        int numToFact = Integer.parseInt(message);
        int ans;
        int i;
        int k = 1;

        if(numToFact == 0){
            ans = 1;
        }else if(numToFact == 1){
            ans = 1;
        }else{
            for(i = numToFact; i > 0; i--){
                k = k * i;
            }
            ans = k;
        }
        String answer = "" + ans;
        Bundle bundle = new Bundle();
        bundle.putString("answer", answer);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}

     Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            TextView ans = (TextView)findViewById(R.id.answer);
            Bundle bundle = msg.getData();
            String answer = bundle.getString("answer");
            ans.setText(answer);
        }
    };

}
