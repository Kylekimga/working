package com.example.working;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int QUANTITY_MIN = 1;
    public static final int QUANTITY_MAX = 10;
    public static final int COFFEE_PRICE = 3000;
    private Button mMinusButton;
    private Button mPlusButton;
    private Button mOrderButton;
    private Button mCancelButton;
    private TextView mQuantityTextView;
    private TextView mResultTextView;
    private int mQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //레이아웃 표시
        setContentView(R.layout.layout_coffee);
        // 레이아웃에서 특정 id를 인스턴스 변수와 연결
        mMinusButton = (Button) findViewById(R.id.minus_button);
        mPlusButton = (Button) findViewById(R.id.plus_button);
        mOrderButton = (Button) findViewById(R.id.order_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);
        mResultTextView = (TextView) findViewById(R.id.result_text);
        //초기화매서드
        initial();
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //debug
                Log.d(TAG, "사용자가 마이너스 버튼 클릭함");
                if (mQuantity > QUANTITY_MIN) {
                    mQuantity--;
                    mQuantityTextView.setText("" + mQuantity);
                    displayresult();
                } else {
                    initial();
                    mQuantityTextView.setText("" + mQuantity);
                    Toast.makeText(MainActivity.this, "수량이 0개 입니다", Toast.LENGTH_SHORT).show();

                }
            }
        });

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //debug
                Log.d(TAG, "사용자가 플러스 버튼 클릭함");
                mQuantity++;
                if (mQuantity > QUANTITY_MAX) {
                    mQuantity = QUANTITY_MAX;
                    Toast.makeText(MainActivity.this, "수량이 너무 많습니다", Toast.LENGTH_SHORT).show();
                }
                mQuantityTextView.setText("" + mQuantity);
                displayresult();
            }
        });

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mResultTextView.getText().toString();
                Toast.makeText(MainActivity.this, message + "\n주문하겠습니다", Toast.LENGTH_LONG).show();
                String[] addresses = {"kkr0517@naver.com"};
                composeEmail(addresses, "주문요청합니다", message);
                initial();
            }

        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initial();
                Toast.makeText(MainActivity.this, "취소되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void displayresult() {
        String result = "지불 금액 : " + (COFFEE_PRICE * mQuantity)
                + "원 입니다\n감사합니다";
        mResultTextView.setText("" + result);
    }

    private void initial() {
        mQuantity = 0;
        mResultTextView.setText("지불 금액 : ");
        mQuantityTextView.setText("" + mQuantity);
    }

    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //메뉴를 붙이는 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //아이템 메뉴 클릭할 때
        switch (item.getItemId()) {
            case R.id.action_setting:
                Toast.makeText(this, "Not ready yet", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_setting2:
                return true;
            case R.id.action_setting3:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
