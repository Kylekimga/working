package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.working.R;

import java.util.List;
import java.util.Map;
//Weather 라는 Class를 만들었음!
import models.Weather;

public class WeatherAdapter extends BaseAdapter {

    //필요한 것은 context, 레이아웃(=만들어 놓은 것 가져올 거), 데이터
    private List<Weather> mData;
    private Context mContext;

    public WeatherAdapter(Context context, List<Weather> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }
//id 는 DB를 쓰지 않는한 position하고 동일함
    @Override
    public long getItemId(int i) {
        return i;
    }
//postion번째의 레이아웃!! 리턴이 VIEW다.
    //getView
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // view  : 재사용 되는 뷰
        if(view == null){
            //레이아웃 가져올 때 쓰는 코드(복붙하면 됨)
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_weather, viewGroup, false);
        }

        //레이아웃 들고 오기
        ImageView imageView = view.findViewById(R.id.weather_image);
        TextView temperatureTextView = view.findViewById(R.id.temperature_text);
        TextView locationTextView = view.findViewById(R.id.location_text);

        //데이터
        Weather weather = mData.get(i);
        //레이아웃에 데이터 입력
        imageView.setImageResource(weather.getImageRes());
        locationTextView.setText(weather.getLocation());
        temperatureTextView.setText(weather.getTemperature());

        return view;
    }
}
