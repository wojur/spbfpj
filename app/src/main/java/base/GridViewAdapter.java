package base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spbf.hasee.spbf.R;

/**
 * Created by CGS on 2016/12/26.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private  int[] image;
    private  String[] sp_name;
    public GridViewAdapter(Context context, int[] image, String[] sp_name) {
        this.context=context;
        this.image=image;
        this.sp_name=sp_name;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return image[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler=null;
        if(convertView==null){
            convertView= View.inflate(context, R.layout.gridview_list_item,null);
            hodler=new ViewHodler();
            hodler.tv= (TextView) convertView.findViewById(R.id.sp_name);
            hodler.img= (ImageView) convertView.findViewById(R.id.imageView1);
            convertView.setTag(hodler);
        }else {
            hodler= (ViewHodler) convertView.getTag();
        }
        hodler.tv.setText(sp_name[position]);
        hodler.img.setImageResource(image[position]);
        return convertView;
    }
}

class ViewHodler{
    TextView tv;
    ImageView img;
}
