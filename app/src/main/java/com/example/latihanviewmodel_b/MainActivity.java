package com.example.latihanviewmodel_b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edtWidth, edtHeight, edtLength;
    private Button btnCalculate;
    private TextView tvResult;
    MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String width =  edtWidth.getText().toString();
                String length =  edtLength.getText().toString();
                String height =  edtHeight.getText().toString();


                if (width.isEmpty()){
                    edtWidth.setError("Masih Kosong");
                } else if (height.isEmpty()) {
                    edtHeight.setError("Masih Kosong");
                } else if (length.isEmpty()) {
                    edtLength.setError("Masih Kosong");

                } else {
                    viewModel.calculate(width,height,length);
                }


            }
        });
        displayResult();
    }

    private void displayResult(){
        viewModel.result.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvResult.setText(String.valueOf(integer));

            }
        });
    }

    private void calculate(String width, String length, String height){
        int result = Integer.parseInt(width) * Integer.parseInt(length) * Integer.parseInt(height);
        tvResult.setText(String.valueOf(result));
    }
}