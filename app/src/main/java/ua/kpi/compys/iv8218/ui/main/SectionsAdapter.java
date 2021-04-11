package ua.kpi.compys.iv8218.ui.main;
import ua.kpi.compys.iv8218.R;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.screen_1, R.string.screen_2};
    private final Context mContext;
    public SectionsAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public Fragment getItem(int a) {
        switch (a) {
            case 0:
                return new Controller();
            case 1:
                return new GraphDiagramElements();
            default:
                return PlaceholderFragment.newInstance(a + 1);
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int a) {
        return mContext.getResources().getString(TAB_TITLES[a]);
    }
}