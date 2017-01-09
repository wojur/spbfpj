package fragment;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.spbf.hasee.spbf.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import base.GridViewAdapter;

/**
 * Created by CGS on 2017/1/5.
 */

public class MianFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,SurfaceHolder.Callback{
    private static final String TITLE="title";

    private GridView gridView;
    private GridViewAdapter adapter;
    String []sp_name={"花1","花2","花3","花4","花5","点","阳光","晴天","光头强","百团"};
    int []image={R.drawable.hua1,R.drawable.hua2,R.drawable.hua3,R.drawable.hua4,
            R.drawable.hua5,R.drawable.pont2,R.drawable.sun,R.drawable.tianqibackground,
            R.drawable.guantouqian,R.drawable.people};
    private SurfaceView sv;
    private SurfaceHolder holder;
    private MediaPlayer mediaPlayer;
    private int position;
    private RelativeLayout rl;
    private Timer timer;
    private TimerTask task;
    private SeekBar sbar;
    private ImageView play2;
    private String filepath="http://192.168.1.106:8080/oppo2.3gp";
    private String filepath2="http://192.168.1.106:8080/oppo.mp4";
    private String filepath3="http://120.25.195.79/v/userfiles/video/cms/article/2016/06/socialedu.mp4";
    private String file="";
    int count=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.bfmain_layout,container,false);
        initView(view);
        initDatas(view);
        return view;
    }
    private void initView(View view) {
        gridView= (GridView)view.findViewById(R.id.gridView1);
        sbar= (SeekBar)view.findViewById(R.id.sbar);
        play2= (ImageView)view.findViewById(R.id.play);
        sv= (SurfaceView)view.findViewById(R.id.sv);
        //et= (EditText) findViewById(et);
        //filepath=et.getText().toString().trim();
    }
    private void initDatas(View view) {
        //adapter=new GridViewAdapter(getActivity(),image,sp_name);
        //gridView.setAdapter(adapter);
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(this, "准备播放", Toast.LENGTH_SHORT).show();
                if(count==1){
                    rl.setVisibility(View.INVISIBLE);
                    play(0);
                    setPlayLength();
                    count++;
                    return ;
                }
                if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play2.setImageResource(android.R.drawable.ic_media_play);
                }else{
                    mediaPlayer.start();
                    play2.setImageResource(android.R.drawable.ic_media_pause);
                }

            }
        });
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rl.getVisibility()==View.INVISIBLE){
                    rl.setVisibility(View.VISIBLE);
                    CountDownTimer cdt=new CountDownTimer(3000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            System.out.println(millisUntilFinished);
                        }

                        @Override
                        public void onFinish() {
                            rl.setVisibility(View.INVISIBLE);

                        }
                    };
                    cdt.start();
                }else if(rl.getVisibility()==View.VISIBLE){
                    rl.setVisibility(View.INVISIBLE);
                }
            }
        });
        sbar.setOnSeekBarChangeListener(this);
        timer=new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    int progress=mediaPlayer.getCurrentPosition();
                    int total=mediaPlayer.getDuration();
                    sbar.setMax(total);
                    sbar.setProgress(progress);
                }
            }
        };
        timer.schedule(task,500,500);
        rl= (RelativeLayout)view.findViewById(R.id.rl);
        holder=sv.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback((SurfaceHolder.Callback) this);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                if(rl.getVisibility()==View.INVISIBLE){
//                    rl.setVisibility(View.VISIBLE);
//                    CountDownTimer cdt=new CountDownTimer(3000,1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            System.out.println(millisUntilFinished);
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            rl.setVisibility(View.INVISIBLE);
//
//                        }
//                    };
//                    cdt.start();
//                }else if(rl.getVisibility()==View.VISIBLE){
//                    rl.setVisibility(View.INVISIBLE);
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
    @Override
    public void onDestroy() {
    mediaPlayer.pause();
    timer.cancel();
    task.cancel();
    timer=null;
    task=null;
    super.onDestroy();
    }
    private void setPlayLength() {
        CountDownTimer cdt=new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(getActivity(), "免费试看5秒", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                if(mediaPlayer!=null) {
                    position = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    showDialog1(position);
                }
            }
        };
        cdt.start();
    }
    private void showDialog1(final int p){
        AlertDialog.Builder buidler=new AlertDialog.Builder(getActivity());
        buidler.setMessage("该视频需要收费");
        buidler.setTitle("提示");
        buidler.setPositiveButton("去支付>>", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mediaPlayer.start();
                mediaPlayer.seekTo(p);
            }
        });
        buidler.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mediaPlayer.stop();
                mediaPlayer.seekTo(0);
            }
        });
        buidler.create().show();

    }

    private void play(int position) {
        if(filepath3==null){
            Toast.makeText(getActivity(),"路径为空，播放失败",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(filepath3);
            mediaPlayer.setDisplay(sv.getHolder());
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new PrepareListener(position));
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                }
            });
        } catch (IOException e) {
            Toast.makeText(getActivity(),"播放失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private final class PrepareListener implements MediaPlayer.OnPreparedListener{
        private int position;
        public PrepareListener(int position){
            this.position=position;
        }
        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();
            if(position>0)mediaPlayer.seekTo(position);
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(position>0&&filepath3!=null){
            play(position);
            position=0;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mediaPlayer!=null){
        position=mediaPlayer.getCurrentPosition();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int position=seekBar.getProgress();
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(position);
        }
    }
}
