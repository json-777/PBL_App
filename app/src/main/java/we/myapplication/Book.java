package we.myapplication;

import android.widget.Switch;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * Created by kazuki on 2016/09/24.
 */
public class Book {
    private String Title = "";
    private ArrayList<String> AuthorList = new ArrayList<String>();
    private ArrayList<String> AuthorListKana = new ArrayList<String>();
    private String ISBN = "";
    private String Publisher = "";
    private String Image_URL;

    public String getTitle(){
        return this.Title;
    }

    public String getISBN(){
        return this.ISBN;
    }

    public String getPublisher(){
        return this.Publisher;
    }

    public String getAuthor(int i){
        return this.AuthorList.get(i);
    }

    public String getAuthorKana(int i){
        return this.AuthorListKana.get(i);
    }

    public int getAuthorLength(){
        return this.AuthorList.size();
    }
    public String getImage_URL(){return Image_URL;}

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setISBN(String isbn) {
        this.ISBN = isbn;
    }

    public void setPublisher(String pub) {
        this.Publisher = pub;
    }

    public void addAuthor(String Author,String kana){
        this.AuthorList.add(Author);
        this.AuthorListKana.add(kana);
    }
    public void setImage_URL(String url){
        this.Image_URL = url;
    }






}
