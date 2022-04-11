package ro.pub.cs.systems.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView instructions;
    private Button registerButton, cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    setResult(1, null);
                    break;
                case R.id.register:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        instructions = (TextView)findViewById(R.id.textView);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.INSTRUCTIONS)) {
            String instr = intent.getExtras().get(Constants.INSTRUCTIONS).toString();
            instructions.setText(instr);
        } else {
            instructions.setText("");
        }

        registerButton = (Button)findViewById(R.id.register);
        registerButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.button);
        cancelButton.setOnClickListener(buttonClickListener);

    }
}