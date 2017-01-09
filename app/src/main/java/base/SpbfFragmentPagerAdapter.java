package base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by CGS on 2017/1/5.
 */

public class SpbfFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment>fraglist;
    public SpbfFragmentPagerAdapter(FragmentManager fm,List<Fragment>fraglist) {
        super(fm);
        this.fraglist=fraglist;
    }

    @Override
    public Fragment getItem(int position) {
        return fraglist.get(position);
    }

    @Override
    public int getCount() {
        return fraglist.size();
    }
}
