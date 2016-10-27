package we.myapplication;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 一樹 on 2016/10/22.
 */

public class ParseXML {
    public static Book ParseXML(InputStream xmlStream){
        Book book = new Book();
        if(xmlStream == null){
            return null;
        }
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
                                    book.setTitle(xpp.nextText());
                                    xpp.next();
                                    xpp.next();
                                    if(xpp.getName() != null && xpp.getName().equals("transcription")){
                                        book.setTitleKana(xpp.nextText());
                                    }
                                }else if(xpp.getName() != null && xpp.getName().equals("name")){
                                    book.setAuthor(xpp.nextText());
                                    xpp.next();
                                    xpp.next();
                                    if(xpp.getName() != null && xpp.getName().equals("transcription")){
                                        book.setAuthorKana(xpp.nextText());
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
        }finally {
            return  book;
        }
    }
}
