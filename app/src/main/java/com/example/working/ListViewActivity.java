package com.example.working;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {


    private ListView mListView;
    private ArrayList<Map<String, Object>> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        //뷰
        mListView = (ListView) findViewById(R.id.list_view);

        //데이터
        mDataList = new ArrayList<>();
        addItem("커피앱", "CheckBox", MainActivity.class);
        addItem("인탠트연습", "StartActivityForResultActivity", StartActivityForResultActivity.class);


        //어댑터 (ListView에 ArrayList를 삽입시켜주는 작업)
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //      android.R.layout.simple_list_item_1,
        //    mDataList);
        //map으로 되어 있는 데이터는 SimpleAdapter 써야하나봄
        SimpleAdapter adapter = new SimpleAdapter(this,
                mDataList,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "desc"},
                new int[]{android.R.id.text1, android.R.id.text2});

        mListView.setAdapter(adapter);

        //이벤트 - onItemClick을 override하는데, 해당 포지션의 맵을 가져와서, 그 맵의 인탠트를 실행하라는 코드
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Intent intent = (Intent) map.get("intent");
                startActivity(intent);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(ListViewActivity.this, "롱클릭" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    //mDataList는 3가지 속성을 가진 해시맵들의 ArrayList
    private void addItem(String title, String desc, Class cls) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("desc", desc);
        map.put("intent", new Intent(this, cls));
        mDataList.add(map);
    }

    @Override
    public void onBackPressed() {


        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        //intervalTime 은 직전에 누른 시간고 현재 누른 시점 간 얼마만큼이 지났는지를 묻는 것임
        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(this, "한 번 더 뒤로가기를 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }


}
