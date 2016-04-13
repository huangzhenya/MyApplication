package com.example.zhenyahuang.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list);

        List<Map<String, Object>> data_list = this.getData();

        listView.setAdapter(new ListAdapter(this, data_list));
    }


    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("name", "张三"+i);
            map.put("age", "驾龄：5年");
            map.put("birth_area", "籍贯：北京");
            map.put("distance", "距离：1km");
            list.add(map);
        }
        return list;
    }


    public class ListAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater mInflater;
        private List<Map<String, Object>> data;
        public ListAdapter(Context context,List<Map<String, Object>> data){
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.list_item_layout, null);
                holder.photoImg = (ImageView)convertView.findViewById(R.id.photoView);
                holder.nameTxt = (TextView)convertView.findViewById(R.id.nameText);
                holder.ageTxt = (TextView)convertView.findViewById(R.id.ageText);
                holder.areaTxt = (TextView)convertView.findViewById(R.id.areaText);
                holder.distanceTxt = (TextView)convertView.findViewById(R.id.distanceText);
                holder.callButtion = (Button)convertView.findViewById(R.id.callButton);

                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            return convertView;
        }
    }

    public static class ViewHolder{
        public ImageView photoImg;
        public TextView nameTxt;
        public TextView ageTxt;
        public TextView areaTxt;
        public TextView distanceTxt;
        public Button callButtion;
    }

}
