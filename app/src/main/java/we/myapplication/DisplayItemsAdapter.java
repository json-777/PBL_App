package we.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kazuki on 2016/09/25.
 */
public class DisplayItemsAdapter extends BaseAdapter {
    private Context context;
    private List<DisplayItems> items;
    private ImageLoader imageLoader;

    private static class ViewHolder{
        TextView title;
        TextView author;
        NetworkImageView image;
    }

    public DisplayItemsAdapter(Context context,List<DisplayItems> items,RequestQueue queue){
        this.context = context;
        this.items = items;
        this.imageLoader = new ImageLoader(queue,new LruCacheSample());
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int posison){
        return posison;
    }

    //ListViewの中のアイテムを格納
    @Override
    public View getView(final  int position, View convertView  , ViewGroup parent){
        View view = convertView;
        ViewHolder holder;

        //スクロースした時の高速化を図る
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_layout,parent,false);
            TextView title = (TextView)view.findViewById(R.id.title);
            TextView author = (TextView)view.findViewById(R.id.auther);
            NetworkImageView image = (NetworkImageView)view.findViewById(R.id.image);

            //holderにviewを持たせる
            holder = new ViewHolder();
            holder.title = title;
            holder.author = author;
            holder.image = image;
            view.setTag(holder);
        }else{//表示されたことがあるDisplayItemsだったら
            holder = (ViewHolder)view.getTag();
        }

        //Viewに反映
        holder.title.setText(items.get(position).getTitle());
        holder.author.setText(items.get(position).getAuthor());
        holder.image.setImageUrl(items.get(position).getUrl(),imageLoader);

        return view;
    }

}
