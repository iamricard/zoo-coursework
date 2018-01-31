package internet.famous.animal.zoo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public final class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Get the ViewPager and set it's PagerAdapter so that it can display items
    ViewPager viewPager = findViewById(R.id.viewpager);
    viewPager.setAdapter(new ZooFragmentAdapter(getSupportFragmentManager(), MainActivity.this));

    // Give the TabLayout the ViewPager
    TabLayout tabLayout = findViewById(R.id.sliding_tabs);
    tabLayout.setupWithViewPager(viewPager);
  }
}
