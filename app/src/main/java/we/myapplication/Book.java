package we.myapplication;

import android.widget.Switch;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kazuki on 2016/09/24.
 */
public class Book {
    private String Title;
    private String TitleKana;
    private String Author;
    private String AuthorKana;
    private String ISBN;

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

    // XML の解析
    public void parseXML(InputStream xmlStream){
        XmlPullParserFactory factory = null;
        try {
            factory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        factory.setNamespaceAware(true);

        try {
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(xmlStream,"UTF-8");
            String buffString = "";
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                String tag = null;
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    //開始タグ時
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();
                        if(tag.equals("recordData")){
                          while (eventType != XmlPullParser.END_DOCUMENT){
                              if(xpp.getName() != null && xpp.getName().equals("value")){
                                  this.Title = xpp.nextText();
                                  xpp.next();
                                  xpp.next();
                                  buffString = xpp.getName();
                                  if(xpp.getName() != null && xpp.getName().equals("transcription")){
                                      this.TitleKana = xpp.nextText();
                                  }
                              }else if(xpp.getName() != null && xpp.getName().equals("name")){
                                  this.Author = xpp.nextText();
                                  xpp.next();
                                  xpp.next();
                                  buffString = xpp.getName();
                                  if(xpp.getName() != null && xpp.getName().equals("transcription")){
                                      this.AuthorKana = xpp.nextText();
                                  }
                                  break;
                              }

                              eventType = xpp.next();
                          }
                        }
                        break;
                }
                eventType = xpp.next();
            }



        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
