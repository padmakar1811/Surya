package com.sveltetech.surya.auth;

import static com.sveltetech.surya.app.AppConfig.PAYLOAD_BUNDLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sveltetech.surya.MainActivity;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.constants.BaseActivity;
import com.sveltetech.surya.databinding.ActivityConfirmPinBinding;

public class ConfirmPinActivity extends BaseActivity {
    ActivityConfirmPinBinding binding;
    TextView[] pinBoxArray;
    String userEntered="",confirmEntered="";
    final int PIN_LENGTH = 4;
    boolean keyPadLockedFlag = false;
    String matchpin="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        binding=ActivityConfirmPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle it=getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        if(it!=null)
        {
            matchpin=it.getString("matchpin");

        }
        pinBoxArray = new TextView[PIN_LENGTH];
        pinBoxArray[0] = binding.pinBox0;
        pinBoxArray[1] = binding.pinBox1;
        pinBoxArray[2] = binding.pinBox2;
        pinBoxArray[3] = binding.pinBox3;
        binding.tvpintitle.setText("Confirm PIN");
        keypadFunction();
    }
    private void keypadFunction() {
        View.OnClickListener pinButtonHandler = new View.OnClickListener() {
            public void onClick(View v) {
                if (keyPadLockedFlag == true) {
                    return;
                }
                Button pressedButton = (Button) v;
                if (userEntered.length() < PIN_LENGTH) {
                    userEntered = userEntered + pressedButton.getText();
                    Log.v("PinView", "User entered=" + userEntered);
                    //Update pin boxes
                    pinBoxArray[userEntered.length() - 1].setText("8");
                    if (userEntered.length() == PIN_LENGTH) {
                    }
                }
                else {
                    clearMPin(pressedButton);
                }
            }
        };
        binding.button1.setOnClickListener(pinButtonHandler);
        binding.button2.setOnClickListener(pinButtonHandler);
        binding.button3.setOnClickListener(pinButtonHandler);
        binding.button4.setOnClickListener(pinButtonHandler);
        binding.button5.setOnClickListener(pinButtonHandler);
        binding.button6.setOnClickListener(pinButtonHandler);
        binding.button7.setOnClickListener(pinButtonHandler);
        binding.button8.setOnClickListener(pinButtonHandler);
        binding.button9.setOnClickListener(pinButtonHandler);
        binding.button0.setOnClickListener(pinButtonHandler);

        binding.buttonDeleteBack.setOnClickListener(v -> {
            if (keyPadLockedFlag == true) {
                return;
            }
            if (userEntered.length() > 0) {
                userEntered = userEntered.substring(0, userEntered.length() - 1);
                pinBoxArray[userEntered.length()].setText("");
            }
        });

        /*Check Listner*/
        binding.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userEntered.equals(""))
                {
                    if(matchpin.equals(userEntered)){
                        PreferencesManager.getInstance(context).setMPin(userEntered);
                        goToActivityWithFinish(context, MainActivity.class,null);
                    }
                    else
                    {
                        showMessage("PIN Doesn't Match");
                    }
                }
                else
                {
                    showMessage("Enter 4-digits PIN");
                }
            }
        });
    }
    private void clearMPin(Button pressedButton) {
        //Roll over
        pinBoxArray[0].setText("");
        pinBoxArray[1].setText("");
        pinBoxArray[2].setText("");
        pinBoxArray[3].setText("");
        userEntered = "";
        userEntered = userEntered + pressedButton.getText();
        Log.v("PinView", "User entered=" + userEntered);
        //Update pin boxes
        pinBoxArray[userEntered.length() - 1].setText("8");
    }
}