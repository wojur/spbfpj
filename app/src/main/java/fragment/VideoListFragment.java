package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spbf.hasee.spbf.R;

import java.util.ArrayList;
import java.util.List;

import view.DropDownMenu;

/**
 * Created by hasee on 2016/12/25.
 */

public class VideoListFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.video_list_layout,container,false);
    }

    private String headers[] = {"全部视频"};
    private List<View> popupViews = new ArrayList<>();
   // private ListDropDownAdapter campusAdapter;
    private DropDownMenu mDropDownMenu;

}
