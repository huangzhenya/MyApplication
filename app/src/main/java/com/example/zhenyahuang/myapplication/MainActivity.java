package com.example.zhenyahuang.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        lv = new ListView(this);

        lv.setAdapter(new ListAdapter(this));
        setContentView(lv);
    }

    public class ListAdapter extends BaseAdapter{

        private LayoutInflater mInflater;
        public ListAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 0;
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
                holder.callButtion = (Button)convertView.findViewById(R.id.callButton);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            return convertView;
        }
    }

    public final class ViewHolder{
        public ImageView photoImg;
        public TextView nameTxt;
        public TextView ageTxt;
        public TextView areaTxt;
        public TextView distanceTxt;
        public Button callButtion;
    }

}
