package com.example.zhenyahuang.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

import me.codeboy.common.base.log.CBPrint;
import me.codeboy.common.base.net.CBHttp;
import me.codeboy.common.base.net.constant.CBMethod;
import me.codeboy.common.base.net.core.CBConnection;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ImageView imgButton1;
    private ImageView imgButton2;
    private ImageView imgButton3;
    private ImageView imgButton4;
    private ImageView imgView1;
    private ImageView imgView2;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgButton1 = (ImageView) findViewById(R.id.near_driver);
        imgButton2 = (ImageView) findViewById(R.id.paper);
        imgButton3 = (ImageView) findViewById(R.id.friend);
        imgButton4 = (ImageView) findViewById(R.id.more);

        imgView1 = (ImageView) findViewById(R.id.zhongjian);
        imgView2 = (ImageView) findViewById(R.id.youbian);

        listView = (ListView) findViewById(R.id.list);

        List<Map<String, Object>> data_list = this.getData();

        listView.setAdapter(new ListAdapter(this, data_list));
        listView.setOnItemClickListener(new ListClickListener());

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(MainActivity.this,"dsfsdfdsfsd"+ msg.getData().getString("result"), Toast.LENGTH_SHORT).show();
                super.handleMessage(msg);
            }
        };
    }

    class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub
            //String text = listView.getItemAtPosition(position)+"";
            final String text = "" + position;

            Log.i("tag", text);
            new Thread() {
                @Override
                public void run() {
                    try {
                        String baseURL = "http://47.88.192.36:8080/test/addUser";
                        CBConnection connection = CBHttp.getInstance();
                        CBPrint.println("1");
                        String result = connection.connect(baseURL).method(CBMethod.POST).timeout(5000).data("name", text).execute();
                        CBPrint.println("2"+result);
                        Message msg = new Message();
                        msg.arg1 = 0;
                        msg.getData().putString("result", result);
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                        handler.sendEmptyMessage(1);
                    }
                }
            }.start();


        }
    }


    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "张三" + i);
            map.put("age", "驾龄：5年");
            map.put("birth_area", "籍贯：北京");
            map.put("distance", "距离：1km");
            list.add(map);
        }
        return list;
    }


    public class ListAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater mInflater;
        private List<Map<String, Object>> data;

        public ListAdapter(Context context, List<Map<String, Object>> data) {
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
                holder.photoImg = (ImageView) convertView.findViewById(R.id.photoView);
                holder.nameTxt = (TextView) convertView.findViewById(R.id.nameText);
                holder.ageTxt = (TextView) convertView.findViewById(R.id.ageText);
                holder.areaTxt = (TextView) convertView.findViewById(R.id.areaText);
                holder.distanceTxt = (TextView) convertView.findViewById(R.id.distanceText);
                holder.callButtion = (ImageView) convertView.findViewById(R.id.callButton);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }

    public static class ViewHolder {
        public ImageView photoImg;
        public TextView nameTxt;
        public TextView ageTxt;
        public TextView areaTxt;
        public TextView distanceTxt;
        public ImageView callButtion;
    }

}
