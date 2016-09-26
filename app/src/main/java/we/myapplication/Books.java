package we.myapplication;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by kazuki on 2016/09/26.
 */
@Table(name = "Books_tb")//テーブル名
public class Books extends Model {

    @Column(name = "Id",notNull = true,unique = true)
    public int Id;

    @Column(name = "Title")
    public String Title;


    @Column(name = "Author")
    public String Author;

    @Column(name = "Image_URL")
    public String Image_URL;

}
