package we.myapplication;

import android.widget.Switch;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * Created by kazuki on 2016/09/24.
 */
public class Book {
    private String Title;
    private String TitleKana;
    private String Author;
    private String AuthorKana;
    private String ISBN;

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    private String Image_URL;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitleKana() {
        return TitleKana;
    }

    public void setTitleKana(String titleKana) {
        TitleKana = titleKana;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getAuthorKana() {
        return AuthorKana;
    }

    public void setAuthorKana(String authorKana) {
        AuthorKana = authorKana;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }






}
