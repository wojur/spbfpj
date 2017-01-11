package bean;

/**
 * Created by hasee on 2016/12/26.
 */

public class VideoBean {
    /**
     * id : dbc5bc1efec34543b9fcecf26f3bc8fe
     * title : 队伍建设
     * keywords :
     * timeLong : 19
     * categoryId : b1689b12f3564bb39d3e7a8c2c99237b
     * categoryName : 队伍建设
     * image : http://120.25.195.79/v/userfiles/thumbs/images/cms/artilce/2016/06/77.jpg
     * url : http://120.25.195.79/v/userfiles/video/cms/article/2016/06/socialedu.mp4
     * createDate : 2016-06-06
     * remarks :
     */
    private String id;
    private String title;
    private String keywords;
    private int timeLong;
    private String categoryId;
    private String categoryName;
    private String image;
    private String url;
    private String createDate;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(int timeLong) {
        this.timeLong = timeLong;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "id"+id;
    }
}
