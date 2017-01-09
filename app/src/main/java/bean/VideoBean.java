package bean;

/**
 * Created by hasee on 2016/12/26.
 */

public class VideoBean {

    private String videoName;
    private int videPath;

    public VideoBean (String videoName,int videPath){
        this.videoName=videoName;
        this.videPath=videPath;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public int getVidePath() {
        return videPath;
    }

    public void setVidePath(int videPath) {
        this.videPath = videPath;
    }
}
