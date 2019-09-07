package com.example.working;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TargetActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        findViewById(R.id.result_button).setOnClickListener(this);


        mValueTextView = findViewById(R.id.value_text);
        if(getIntent() != null){
            String value = getIntent().getStringExtra("value");
            mValueTextView.setText(value);
        }
    }

    @Override
    public void onClick(View view) {
        // 결과 전달
            Intent intent = new Intent();
            intent.putExtra("result", "이것은 내가 지정한 문구다");
            intent.putExtra("int", 50);

            setResult(RESULT_OK, intent);
            finish();
    }
}
