package we.myapplication;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.zip.InflaterInputStream;

import javax.xml.parsers.ParserConfigurationException;

import static we.myapplication.XmlParser.*;

/**
 * このクラスはISBNコードの配列を与えるとBookインスタンスの変数全てに値を代入してBookの配列をArrayListとして返します
 * Created by kazuki on 2016/09/26.
 */
 class ListViewChange {
    private static ArrayList<Book> books = new ArrayList<Book>();
    private static DisplayItemsAdapter mAdapter;
    private static Context mContext;
    /**
     * コンストラクタです
     * @param ISBN 書籍情報を取得するためのISBN番号（10桁or13桁）
     */
    public  ListViewChange(Context context,String[] ISBN,int imageSize,DisplayItemsAdapter adapter) {
        mAdapter = adapter;
        mContext = context;
        boolean action = false;
        ArrayList<String> Urls = getBookUrlInfo(ISBN);
        for (int i = 0; i < ISBN.length;i++){
            BooksDatabase.getInstance().add(new Book());
            sendRequest(Urls.get(i));
        }
    }

    public static void sendRequest(String url ){
        VolleyXmlRequest mRequest  = new VolleyXmlRequest(
                Request.Method.GET
                ,url
                , null
                , null
                , new Response.Listener<InputStream>() {
            @Override
            public  void onResponse(InputStream response) {
                XmlParser parser = new XmlParser();
                try {
                    BooksDatabase.getInstance().add( parser.domParse(response));
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                Book b = BooksDatabase.getInstance().getLast();
                String a = b.getTitle();
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 通信失敗時の処理を行なう...
            }

        }
        );
        Singleton.getInstance(mContext).getRequestQueue().add(mRequest);

    }

    /**
     *Bookの配列を返します
     * @return　Bookの配列
     */
    public ArrayList<Book> getBooks(){
        return this.books;
    }
    /**
     * 表紙画像を取得のためのURLを返します
     * @param lv 大きさ指定(小→大：0→3)
     * @return　書籍の表紙情報URL
     */
    public static String getImageURL(String isbn, int lv) {
        isbn = Convert13From10(isbn);
        String imageUrl = "http://images-jp.amazon.com/images/P/";
        switch (lv) {
            case 0:
                imageUrl = String.format("%s%s%s", imageUrl, isbn, ".09.THUMBZZZ.jpg");
                break;
            case 1:
                imageUrl = String.format("%s%s%s", imageUrl, isbn, ".09.TZZZZZZZ.jpg");
                break;
            case 2:
                imageUrl = String.format("%s%s%s", imageUrl, isbn, ".09.MZZZZZZZ.jpg");
                break;
            case 3:
                imageUrl = String.format("%s%s%s", imageUrl, isbn, ".09.LZZZZZZZ.jpg");
                break;
            default:
                return "";
        }
        return imageUrl;
    }

    /**
     *13桁のISBN番号を10桁のISBN番号に変換する
     * @param isbn ISBN番号
     * @return 10桁のISBN番号
     */
    public static String Convert13From10(String isbn){
        if(isbn.length() == 10)
            return isbn;
        int CheckDigit = 11 - Sum(isbn.substring(3,12), 0) % 11;
        return isbn.substring(3,12) + getChekDigit(CheckDigit);
    }

    /**
     *
     * @param isbn ISBN番号
     * @param time 回数
     * @return チェックディジットを求めるため合計値
     */
    private static int Sum(String isbn,int time){
        if(time == 9) return 0;
        return Integer.parseInt(isbn.substring(0,1)) * (10 - time) + Sum(Cut(isbn), time+1);
    }

    /**
     * 文字列の整形
     * @param isbn ISBN番号
     * @return 先頭１ビット削除
     */
    private static String Cut(String isbn){
        return isbn.length() == 1 ? isbn : isbn.substring(1, isbn.length());
    }

    /**
     *チェックディジットの取得
     * @param c チェックディジットの変換
     * @return チェックディジット
     */
    private static String getChekDigit(int c){
        return c == 11 ? "0":(c == 10 ? "X" : String.valueOf(c));
    }

    /**
     *　国会図書館に問い合わせ用URLを取得する
     * @return 国会図書館に問い合わせ用URLを取得する(配列)
     */
    private static ArrayList<String> getBookUrlInfo(String[] ISBN){
        ArrayList<String> ISBNs = new ArrayList<String>();
        for(int i = 0; i < ISBN.length;i++){
            String isbn = ISBN[i];
            isbn = Convert13From10(isbn);
            ISBNs.add("http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&recordSchema=dcndl&onlyBib=true&recordPacking=xml&query=isbn%20%3d%20%22" + isbn + "%22");
        }
        return ISBNs;
        //  "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&recordSchema=dcndl&onlyBib=true&recordPacking=xml&query=isbn%20%3d%20%22" + isbn + "%22";

    }





}
