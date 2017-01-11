package bean;

import java.util.List;

/**
 * Created by hasee on 2017/1/11.
 */

public class VideoResult {
    /**
     * code : 0
     * count : 2
     * message : success
     * items : [{"id":"dbc5bc1efec34543b9fcecf26f3bc8fe","title":"队伍建设","keywords":"","timeLong":19,"categoryId":"b1689b12f3564bb39d3e7a8c2c99237b","categoryName":"队伍建设","image":"http://120.25.195.79/v/userfiles/thumbs/images/cms/artilce/2016/06/77.jpg","url":"http://120.25.195.79/v/userfiles/video/cms/article/2016/06/socialedu.mp4","createDate":"2016-06-06","remarks":""},{"id":"739d11475f74485791843b713dc95930","title":"党政建设","keywords":"党政建设","timeLong":5,"categoryId":"91ca4cc1fada499ca97e214c5507e127","categoryName":"党政建设","image":"http://120.25.195.79/v/userfiles/_thumbs/images/photo/2015/11/cdbf6c81800a19d817f44f0330fa828ba71e46c5.jpg","url":"http://120.25.195.79/v/userfiles/video/cms/article/2016/05/11.mp4","createDate":"2016-05-31","remarks":""}]
     */
    private int code;
    private int count;
    private String message;
    public List<VideoBean> items;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<VideoBean> getItems() {
        return items;
    }

    public void setItems(List<VideoBean> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "message"+message;
    }
}
