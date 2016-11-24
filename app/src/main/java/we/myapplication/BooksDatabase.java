package we.myapplication;

import java.io.File;
import java.lang.reflect.Array;
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
    public ArrayList<Book> get(){return Books;}
    public Book getLast(){return  Books.get(Books.size() - 1);}

    public synchronized void getBooksFromFile(File file){
        XmlManager manager = new XmlManager(file);
        BookList list = null;
        try {
            list = manager.Deserialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<BookElement>elements = list.getBooks();
        ArrayList<Book> books = new ArrayList<Book>();
        for (int i = 0;i < elements.size();i++){
            Book book = new Book();
            books.add(book);
            book.setTitle(elements.get(i).getTitle());
            book.setImage_URL(elements.get(i).getImage_Url());
            book.setISBN(elements.get(i).getISBN());
            List<Authors> authors =  elements.get(i).getAuthors();
            for(int j = 0; j <authors.size();j++){
                book.addAuthor(authors.get(j).getAuthor(),authors.get(j).getAuthor_Kana());
            }
        }
        for(int i = 0;i < books.size();i++){
            this.add(books.get(i));
        }
    }
    public synchronized void setBooksToFile(File file){
        XmlManager manager = new XmlManager(file);
        BookList list = null;
        list.setVersion(1.0);
        ArrayList<Book> books = BooksDatabase.getInstance().get();
        ArrayList<BookElement> elements = new ArrayList<BookElement>();
        for(int i= 0;i < books.size();i++){
            BookElement element = new BookElement();
            element.setISBN(books.get(i).getISBN());
            element.setImage_Url(books.get(i).getImage_URL());
            element.setTitle(books.get(i).getTitle());
            ArrayList<Authors> authorses = new ArrayList<Authors>();
            for(int j = 0;j < books.get(i).getAuthorLength();j++){
                authorses.add(new Authors());
                authorses.get(j).setAuthor(books.get(i).getAuthor(j));
                authorses.get(j).setAuthor_Kana(books.get(i).getAuthorKana(j));
            }
            element.setAuthors(authorses);
            elements.add(element);
        }
        list.setBooks(elements);
        try {
            manager.Serialize(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
