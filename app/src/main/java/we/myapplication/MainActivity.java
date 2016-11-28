package we.myapplication;

import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.activeandroid.query.Select;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import android.view.LayoutInflater;


import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStreamReader;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {
    /** 通信リクエスト */

    private RequestQueue mQueue;

    private int NumberOfBooks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);
        mQueue = Volley.newRequestQueue(this);
        String isnb = "9784800711069";
       //BooksDatabase.getInstance().deleateOfFile(this.getApplicationContext());
        DisplayItemsAdapter adapter = new DisplayItemsAdapter(this.getApplicationContext());

        //ListViewのレイアウト設定
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                //ListViewが持ってるBookを因数を渡す
                DetailDaialogFragment detailDaialogFragment = DetailDaialogFragment.newInstance(BooksDatabase.getInstance().get(position));
                detailDaialogFragment.show(getFragmentManager(),"TAG");
            }
        }



        );
//        int padding = (int) (getResources().getDisplayMetrics().density * 8);
//        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//        View header = inflater.inflate(R.layout.listview_header_footer,listView,false);
//        View footer = inflater.inflate(R.layout.listview_header_footer,listView,false);
//        listView.addHeaderView(header,null,false);
//        listView.addFooterView(footer,null,false);
//        listView.setDivider(null);
//        listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
//        listView.setPadding(padding, 0, padding, 0);
        listView.setAdapter(adapter);
        adapter.addItemsFromISNB("4800256798");
        adapter.addItemsFromISNB(isnb);

    }


}

