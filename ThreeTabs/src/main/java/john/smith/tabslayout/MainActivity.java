package john.smith.tabslayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.baseline_home_24,
            R.drawable.baseline_local_grocery_store_24,
            R.drawable.baseline_local_shipping_24};

    private int selectedIconColor = Color.RED;
    private int selectedTextColor = Color.RED;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SettingsFragment());
        fragmentList.add(new ThirdFragment());

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(this, fragmentList));

        tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // Customize the tab name based on position/index
             //       tab.setText("Tab " + (position + 1));
                }
        ).attach();
        setupTabIcons();
    }

//    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//
//
//    }

    private void setupTabIcons() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.custom_tab_layout); // Replace with your custom tab layout XML
                ImageView iconView = tab.getCustomView().findViewById(R.id.tabIcon);
                TextView textView = tab.getCustomView().findViewById(R.id.tabText);
                iconView.setImageResource(tabIcons[i]);
                if (i== 0) textView.setText("John");
                if (i==1) textView.setText("Smith");
                if (i==2) textView.setText("N11111");
               // textView.setText("Tab " + (i + 1));
                textView.setTextSize(20);
                if (i == 0) { // Initially select the first tab
                    iconView.setColorFilter(selectedIconColor, PorterDuff.Mode.SRC_IN);
                    textView.setTextColor(selectedTextColor);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
