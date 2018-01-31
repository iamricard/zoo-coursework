package internet.famous.animal.zoo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import internet.famous.animal.zoo.animal.AnimalFragment;
import internet.famous.animal.zoo.keeper.KeeperFragment;
import internet.famous.animal.zoo.pen.PenFragment;
import internet.famous.animal.zoo.species.SpeciesFragment;

public final class ZooFragmentAdapter extends FragmentPagerAdapter {
  private final String[] tabTitles;
  private final Context context;

  ZooFragmentAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
    tabTitles = context.getResources().getStringArray(R.array.tab_titles);
  }

  @Override
  public int getCount() {
    return tabTitles.length;
  }

  @Override
  public Fragment getItem(int position) {
    if (position == 0) {
      return new AnimalFragment();
    } else if (position == 1) {
      return new KeeperFragment();
    } else if (position == 2) {
      return new PenFragment();
    }
    return new SpeciesFragment();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return tabTitles[position];
  }
}
