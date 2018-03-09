package no.woact.jeykis16.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
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
        replaceFragment(MainMenuFragment.class);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    public void replaceFragment(Class clazz) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) clazz.newInstance();
            Log.d("LALALA", "replaceFragment: REPLACING FRAGMENT TO " + clazz.getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.mainMenuFragmentHolder, fragment).commit();
    }
}
