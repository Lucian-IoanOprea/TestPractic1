package ro.pub.cs.systems.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private Button secondActiv;
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
}