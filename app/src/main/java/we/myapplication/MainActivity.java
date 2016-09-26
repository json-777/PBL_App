package we.myapplication;

import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStreamReader;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /** 通信リクエスト */
    protected VolleyXmlRequest mRequest;
    private RequestQueue mQueue;
    private Books booksDatabase;

    private int NumberOfBooks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);

        //booksDatabase.Id = 1;
       // booksDatabase.Title = "はじめての英語論文　引ける・使える　パターン表現＆文例集";
       // booksDatabase.Author = "和田朋子 ";
       // booksDatabase.Image_URL = "http://images-jp.amazon.com/images/P/4798045128.09.THUMBZZZ";
/*
        ArrayList<DisplayItems> displayItemses = new ArrayList<DisplayItems>();
        displayItemses.add(new DisplayItems());
        displayItemses.get(0).setAuthor("和田朋子");
        displayItemses.get(0).setTitle("はじめての英語論文　引ける・使える　パターン表現＆文例集");
        displayItemses.get(0).setUrl("http://images-jp.amazon.com/images/P/4798045128.09.THUMBZZZ");
        mQueue = Volley.newRequestQueue(this);
        DisplayItemsAdapter adapter  = new DisplayItemsAdapter(this,displayItemses,mQueue);
        listView.setAdapter(adapter);*/






    }


    private void startQueue(String url){
        mQueue = Volley.newRequestQueue(this);
        final String requestURL = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&recordSchema=dcndl&onlyBib=true&recordPacking=xml&query=isbn%20%3D%20%229784798045122%22";
        mRequest = new VolleyXmlRequest(
                Request.Method.GET
                ,requestURL
                ,null
                ,null
                ,new Response.Listener<InputStream>() {
                     @Override
                      public void onResponse(InputStream response) {

                    }
                }
                ,new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 通信失敗時の処理を行なう...
                    }

                 }
        );

        mQueue.add(mRequest);
    }

    static String convertInputStreamToString(InputStream is) throws IOException {
        InputStreamReader reader = new InputStreamReader(is);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[512];
        int read;
        while (0 <= (read = reader.read(buffer))) {
            builder.append(buffer, 0, read);
        }
        return builder.toString();
    }

}

