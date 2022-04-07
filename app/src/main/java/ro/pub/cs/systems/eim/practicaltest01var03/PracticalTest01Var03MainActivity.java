package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private EditText text1;
    private EditText text2;
    private TextView text3;
    private Button buttonplus;
    private Button buttonminus;
    private Button buttonnext;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            try {
                int Value = Integer.parseInt(text1.getText().toString());
                Value = Integer.parseInt(text2.getText().toString());
                switch(view.getId()) {
                    case R.id.button2:
                        int sum = Integer.parseInt(text1.getText().toString());
                        sum += Integer.parseInt(text2.getText().toString());
                        text3.setText(text1.getText().toString() + " + " +
                                text2.getText().toString() + " = " + sum);

                        break;
                    case R.id.button:
                        int dif = Integer.parseInt(text1.getText().toString());
                        dif -= Integer.parseInt(text2.getText().toString());
                        text3.setText(text1.getText().toString() + " - " +
                                text2.getText().toString() + " = " + dif);
                        break;
                    case R.id.button3:
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
                        startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                        break;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "One of inputs is not number",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.NUMBER1, text1.getText().toString());
        savedInstanceState.putString(Constants.NUMBER2, text2.getText().toString());
        savedInstanceState.putString(Constants.OPERATION, text3.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.NUMBER1)) {
            text1.setText(savedInstanceState.getString(Constants.NUMBER1));
        }
        if (savedInstanceState.containsKey(Constants.NUMBER2)) {
            text2.setText(savedInstanceState.getString(Constants.NUMBER2));
        }
        if (savedInstanceState.containsKey(Constants.NUMBER2)) {
            text3.setText(savedInstanceState.getString(Constants.OPERATION));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        text1 = (EditText)findViewById(R.id.textView);
        text2 = (EditText)findViewById(R.id.textView4);
        text3 = (TextView)findViewById(R.id.textView6);
        buttonplus = (Button)findViewById(R.id.button2);
        buttonplus.setOnClickListener(buttonClickListener);
        buttonminus = (Button)findViewById(R.id.button);
        buttonminus.setOnClickListener(buttonClickListener);
        buttonnext = (Button)findViewById(R.id.button3);
        buttonnext.setOnClickListener(buttonClickListener);

        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(Constants.NUMBER1)){
                text1.setText(savedInstanceState.getString(Constants.NUMBER1));
            }
            if(savedInstanceState.containsKey(Constants.NUMBER2)){
                text2.setText(savedInstanceState.getString(Constants.NUMBER2));
            }
            if(savedInstanceState.containsKey(Constants.OPERATION)){
                text3.setText(savedInstanceState.getString(Constants.OPERATION));
            }
            Toast.makeText(getApplicationContext(), text3.getText().toString(),
                                                                Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " +
                    resultCode, Toast.LENGTH_LONG).show();
        }
    }
}