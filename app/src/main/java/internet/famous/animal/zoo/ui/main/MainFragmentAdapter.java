package internet.famous.animal.zoo.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import internet.famous.animal.zoo.R;

final class MainFragmentAdapter extends FragmentPagerAdapter {
  private final String[] tabTitles;

  MainFragmentAdapter(FragmentActivity activity) {
    super(activity.getSupportFragmentManager());
    tabTitles = activity.getResources().getStringArray(R.array.tab_titles);
  }

  @Override
  public int getCount() {
    return tabTitles.length;
  }

  @Override
  public Fragment getItem(int position) {
    if (position == 0) {
      return AnimalListFragment.newInstance();
    } else if (position == 1) {
      return KeeperListFragment.newInstance();
    } else if (position == 2) {
      return PenListFragment.newInstance();
    }
    return SpeciesListFragment.newInstance();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return tabTitles[position];
  }
}
