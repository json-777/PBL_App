package we.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 一樹 on 2016/10/27.
 */
public class BooksDatabase {
    private ArrayList<Book> Books ;
    private static BooksDatabase ourInstance = new BooksDatabase();

    public synchronized  static BooksDatabase getInstance() {
        if(ourInstance == null){
            ourInstance = new BooksDatabase();
        }
        return ourInstance;
    }
    private BooksDatabase() {
        Books = new ArrayList<Book>();
    }
    public ArrayList<Book> getBooks(){
        return this.Books;
    }
    public void add(Book book){
        Books.add(book);
    }
    public int size(){ return Books.size();}
    public Book get(int index){return Books.get(index);}
    public Book getLast(){return  Books.get(Books.size() - 1);}

}
