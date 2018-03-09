package no.woact.jeykis16.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import no.woact.jeykis16.R;
import no.woact.jeykis16.ui.fragment.MainMenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(MainMenuFragment.class, false);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    public void replaceFragment(Class clazz, boolean addToBackStack) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction().replace(R.id.mainMenuFragmentHolder, fragment);
        if (addToBackStack) {
            tx.addToBackStack(null);
        }
        tx.commit();
    }
}
