package adepter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spbf.hasee.spbf.R;

import java.util.List;

import bean.VideoBean;

/**
 * Created by hasee on 2016/12/26.
 */

public class VideoListRecyAdepter extends RecyclerView.Adapter<VideoListRecyAdepter.MyViewHolder> {
    private Context context;
    private List<VideoBean> mData;
    private LayoutInflater inflater;
    public VideoListRecyAdepter(Context context,List<VideoBean> mData){
                 this.context=context;
                  this.mData=mData;
                inflater=LayoutInflater.from(context);
    }

    public interface TvOnClickLitener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public interface ImgOnClickLitener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    private TvOnClickLitener tvOnClickLitener;
    private ImgOnClickLitener imgOnClickLitener;
    public void setTvOnClickLitener(TvOnClickLitener tvOnClickLitener){
        this.tvOnClickLitener=tvOnClickLitener;
    }
    public void setImgOncLickLitener(ImgOnClickLitener imgOncLickLitener){
        this.imgOnClickLitener=imgOncLickLitener;
    }

    @Override
    public VideoListRecyAdepter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.video_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
          holder.tv_videoNname.setText(mData.get(position).getTitle());
        holder.tv_videoTime.setText(mData.get(position).getCreateDate());
        Glide.with(context).load(mData.get(position).getImage()).into(holder.img_video);
        if(tvOnClickLitener!=null){
            holder.tv_videoNname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    tvOnClickLitener.onItemClick(holder.tv_videoNname,pos);
                }
            });
            holder.tv_videoNname.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    tvOnClickLitener.onItemLongClick(holder.tv_videoNname, pos);
                    return true;
                }
            });
        }
        if(imgOnClickLitener!=null){
            holder.img_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    imgOnClickLitener.onItemClick(holder.img_video,pos);
                }
            });

            holder.img_video.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    imgOnClickLitener.onItemLongClick(holder.img_video,pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_video;
        TextView tv_videoNname;
        TextView tv_videoTime;
        public MyViewHolder(View view) {
            super(view);
          img_video= (ImageView) view.findViewById(R.id.img_video);
           tv_videoNname= (TextView) view.findViewById(R.id.tv_videoname);
            tv_videoTime= (TextView) view.findViewById(R.id.tv_videotime);
        }

    }


}
