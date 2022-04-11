package ro.pub.cs.systems.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button center, topleft, topright, bottomright, bottomleft;
  int buttonClicks;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private TextView print;
    int serviceStatus = 0;
    private Button secondActiv;
    private IntentFilter intentFilter = new IntentFilter();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("tag","esafffr");
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String text, to_put;
            if(view.getId() == R.id.center) {
                buttonClicks++;
                text = print.getText().toString();
                to_put = text + "center, ";
                print.setText(to_put);
            } else if(view.getId() == R.id.topleft) {
                buttonClicks++;
                text = print.getText().toString();
                to_put = text + "topleft, ";
                print.setText(to_put);
            }  else if (view.getId() == R.id.topright) {
                buttonClicks++;
                text = print.getText().toString();
                to_put = text + "topright, ";
                print.setText(to_put);
            } else if (view.getId() == R.id.bottomright) {
                buttonClicks++;
                text = print.getText().toString();
                to_put = text + "bottomleft, ";
                print.setText(to_put);
            }
            else if (view.getId() == R.id.bottomleft) {
                buttonClicks++;
                text = print.getText().toString();
                to_put = text + "bottomleft, ";
                print.setText(to_put);
            }
            else if (view.getId() == R.id.button6) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("instructions", print.getText().toString());
                Log.d("measj","tag");
                startActivityForResult(intent,2);
                buttonClicks = 0;
                print.setText("");
            }
            if (buttonClicks >= 3 && serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra(Constants.INSTRUCTIONS, print.getText().toString());
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topleft = (Button)findViewById(R.id.topleft);
        topleft.setOnClickListener(buttonClickListener);
        topright = (Button)findViewById(R.id.topright);
        topright.setOnClickListener(buttonClickListener);
        center = (Button)findViewById(R.id.center);
        center.setOnClickListener(buttonClickListener);
        bottomright = (Button)findViewById(R.id.bottomright);
        bottomright.setOnClickListener(buttonClickListener);
        bottomleft = (Button)findViewById(R.id.bottomleft);
        secondActiv = (Button) findViewById(R.id.button6) ;
        secondActiv.setOnClickListener(buttonClickListener);
        print = (TextView) findViewById(R.id.print_text);
        intentFilter.addAction(Constants.actionTypes[0]);
        buttonClicks = 0;


    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("clicks", buttonClicks);
        System.out.println(buttonClicks);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("tag","mesaj");
        if (savedInstanceState.containsKey("clicks")) {
            buttonClicks = savedInstanceState.getInt("clicks");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}