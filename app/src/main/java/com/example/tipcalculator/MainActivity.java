package com.example.tipcalculator;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_base;
    SeekBar sb_tippercent;
    TextView tv_tippercent, tv_tipamount,tv_total,tv_totalamount,tv_tipDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_base = findViewById(R.id.edBaseAmount);
        sb_tippercent = findViewById(R.id.SeekBarTip);
        tv_tipamount = findViewById((R.id.tvTipAmount));
        tv_tippercent = findViewById((R.id.tvTipPercentLabel));
        tv_totalamount = findViewById(R.id.tvTotalAmount);
        tv_tipDescription=findViewById(R.id.tvTipDescription);

        sb_tippercent.setProgress(15);
        tv_tippercent.setText("15%");
        UpdateTipDescription(15);


        sb_tippercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG, "onProgressChanged " + progress);
                progressChangedValue = progress;
                tv_tippercent.setText(String.valueOf(progress + "%"));
                computeTipAndTotal();
                UpdateTipDescription(progress);

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });
        et_base.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i(TAG, "onProgressChanged" + editable);

                computeTipAndTotal();
            }


        });
    }

    private void UpdateTipDescription(int progress) {
        String tipremarks= "Good";
        int id = 0;

        if(progress<=9)
        {
            tipremarks="Poor";
           id=  ContextCompat.getColor(this,R.color.color_worst_tip);}
        else if (progress > 9 && progress < 15){
            tipremarks="Acceptable";
            id=  ContextCompat.getColor(this,R.color.color_worst_tip);}
        else if (progress > 15 && progress < 20){
            tipremarks="Good";
            id=   ContextCompat.getColor(this,R.color.primary_blue);}
        else if (progress > 20 && progress < 25){
            tipremarks="Great";
            id=  ContextCompat.getColor(this,R.color.color_best_tip);}
        else{
            tipremarks="Amazing";
            id=  ContextCompat.getColor(this,R.color.color_best_tip);}


        tv_tipDescription.setTextColor(id);
        tv_tipDescription.setText(tipremarks);

        //update the color based on the tipPercent




    }

    public void computeTipAndTotal() {
            if(et_base.getText().toString()==""){
                tv_tipamount.setText("");
                tv_totalamount.setText("");
                return;
            }
            //Get the value of the base and tip percent
            double baseAmount = Double.parseDouble(et_base .getText().toString());
            int tip_percent= sb_tippercent .getProgress();

            // Compute the tip and total
            double tipAmount = baseAmount * tip_percent / 100 ;
            double totalAmount = baseAmount + tipAmount;

            // Update the result or UI
            tv_tipamount.setText(String.valueOf(tipAmount));
            tv_totalamount.setText(String.valueOf(totalAmount));
        }
}
