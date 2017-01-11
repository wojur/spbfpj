package com.spbf.hasee.spbf;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import adepter.ListDropDownAdapter;
import adepter.VideoListRecyAdepter;
import base.BaseActivity;
import bean.VideoBean;
import bean.VideoResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import view.DividerGridItemDecoration;
import view.DropDownMenu;
/**
 * Created by hasee on 2016/12/26.
 */

public class VideoList extends BaseActivity implements DropDownMenu.ImgOnClickLitener{


    private String headers[] = {"全部视频"};
    private List<View> popupViews = new ArrayList<>();
    private ListDropDownAdapter dropDownAdapter;
    private String videos[] = {"全部视频", "动漫", "电影", "电视剧"};
    private int videoListPosition = 0;//记录所选
    private DropDownMenu mDropDownMenu;
    //
    private RecyclerView recyclerView;
    private List<VideoBean> mDatas;
    private VideoListRecyAdepter adepter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
              if (msg.what==1){
                  mDatas= (List<VideoBean>) msg.obj;
                 adepter=new VideoListRecyAdepter(VideoList.this,mDatas);
                  recyclerView.setAdapter(adepter);
              }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_list_main);
        mDropDownMenu= (DropDownMenu) findViewById(R.id.dropDownMenu);
        initView();
        getData();
//        recyclerView= (RecyclerView) findViewById(R.id.rcyv_video_list);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
//        mDatas=new ArrayList<VideoBean>();
//        for (int i=0;i<16;i++){
//         VideoBean videoBean=new VideoBean("从零开始的异世界",R.drawable.leimu);
//            mDatas.add(videoBean);
//        }
//        adepter=new VideoListRecyAdepter(this,mDatas);
//        recyclerView.setAdapter(adepter);
    }

   public void  initView(){
       //init campus
       final ListView videosView = new ListView(this);
       videosView.setDividerHeight(0);
       dropDownAdapter = new ListDropDownAdapter(this, Arrays.asList(videos));
       videosView.setAdapter(dropDownAdapter);
       videosView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               dropDownAdapter.setCheckItem(position);
               mDropDownMenu.setTabText(position == 0 ? headers[0] : videos[position]);
               videoListPosition=position;
               // toast(campus[position]);
               mDropDownMenu.closeMenu();
           }
       });
       //填充菜单view
       popupViews.add(videosView);
       //内容区域
       final View contentView = getLayoutInflater().inflate(R.layout.video_list_layout, null);
       recyclerView= (RecyclerView) contentView.findViewById(R.id.rcyv_video_list);
       recyclerView.setLayoutManager(new GridLayoutManager(this,3));
       recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
       mDatas=new ArrayList<VideoBean>();
//       adepter=new VideoListRecyAdepter(VideoList.this,mDatas);
//       recyclerView.setAdapter(adepter);
       mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
       mDropDownMenu.setImgOnClickLitener(this);
    }

    @Override
    public void onItemClick() {
       // Toast.makeText(VideoList.this,"search",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(),"当前是"+messages[(i+messages.length-1)% messages.length],Toast.LENGTH_SHORT).show();
        View popupView = VideoList.this.getLayoutInflater().inflate(R.layout.search_popup_view_layout, null);
        EditText edtView= (EditText) popupView.findViewById(R.id.edt_search);
        ImageView searchImg= (ImageView) popupView.findViewById(R.id.img_pup_search);
        //  2016/5/17 创建PopupWindow对象，指定宽度和高度
        final PopupWindow window = new PopupWindow(popupView,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //  2016/5/17 设置动画
       // window.setAnimationStyle(R.style.popup_window_anim);
        //  2016/5/17 设置背景颜色
        window.setBackgroundDrawable(new ColorDrawable(0x88888888));
        //  2016/5/17 设置可以获取焦点
        window.setFocusable(true);
        //  2016/5/17 设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // 更新popupwindow的状态
        window.update();
        //  2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window.showAtLocation(mDropDownMenu.getView(), Gravity.TOP,0,0);
        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }
    public void getData(){
        String url = "http://120.25.195.79/v/api/video/list";
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("获取apk列表失败");
                Log.d("cwj", e.getMessage());

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
               // System.out.println(result);
                Gson gson = new Gson();
                VideoResult videoResult = gson.fromJson(result,VideoResult.class);
                    Message message=Message.obtain();
                   message.what=1;
                   message.obj=videoResult.items;
                handler.sendMessage(message);
                System.out.println(videoResult.getItems().get(0).getCategoryName());
                Log.d("cwj", videoResult.getItems().get(0).getCategoryName()+videoResult.getItems().get(1).getImage());

            }
        });
    }
}
