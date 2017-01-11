package base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spbf.hasee.spbf.R;

import java.util.List;

import bean.VideoBean;

/**
 * Created by CGS on 2016/12/26.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
  private List<VideoBean> mDatas;
    public GridViewAdapter(Context context,List<VideoBean> mDatas ) {
        this.context=context;
        this.mDatas=mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
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
        hodler.tv.setText(mDatas.get(position).getTitle());
        Glide.with(context).load(mDatas.get(position).getImage()).into(hodler.img);
        return convertView;
    }
}

class ViewHodler{
    TextView tv;
    ImageView img;
}
