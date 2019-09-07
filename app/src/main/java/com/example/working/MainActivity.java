package com.example.working;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mMinusButton;
    private Button mPlusButton;
    private TextView mQuantityTextView;
    private int mQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //초기화매서드
        initial();
        //레이아웃 표시
        setContentView(R.layout.layout_coffee);
        // 레이아웃에서 특정 id를 인스턴스 변수와 연결
        mMinusButton = (Button) findViewById(R.id.minus_button);
        mPlusButton = (Button) findViewById(R.id.plus_button);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);

        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //debug
                Log.d(TAG, "사용자가 마이너스 버튼 클릭함");
                Toast.makeText(MainActivity.this, "이미 수량이 0개 입니다", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initial() {
        mQuantity = 0;
    }
}
