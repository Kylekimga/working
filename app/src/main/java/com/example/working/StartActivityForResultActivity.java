package com.example.working;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivityForResultActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = StartActivityForResultActivity.class.getSimpleName();
    private EditText mValueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_for_result);
        mValueEditText = findViewById(R.id.value_edit);
        findViewById(R.id.submit_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, TargetActivity.class);
        intent.putExtra("value", mValueEditText.getText().toString());
        //주거니 받거니
        startActivityForResult(intent, 1000);
    }

    //받을 때 호출되는 콜백 매서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult : " + requestCode);
        Log.d(TAG, "onActivityResult: " + resultCode);
        Log.d(TAG, "onActivityResult: " + data);
        String result = data.getStringExtra("result");
        int value = data.getIntExtra("int", -1);
        Toast.makeText(this, result + ", int : " + value + "결과가 잘 넘어옴", Toast.LENGTH_LONG).show();

    }
}
