package no.woact.jeykis16.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import no.woact.jeykis16.R;
import no.woact.jeykis16.ui.fragment.MainMenuFragment;

public class MainActivity extends AppCompatActivity {
    public static final Random RANDOM = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(MainMenuFragment.class, false);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String[] gradients = getResources().getStringArray(R.array.gradients);
        String[] randomGradient = gradients[RANDOM.nextInt(gradients.length)].split("\\|");

        int[] colors = new int[randomGradient.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.parseColor(randomGradient[i]);
        }

        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        getWindow().getDecorView().setBackground(gd);
    }


    public void replaceFragment(Class clazz, boolean addToBackStack) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        replaceFragment(fragment, addToBackStack);
    }

    public void replaceFragment(Class clazz, boolean addToBackStack, Bundle bundle) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) clazz.newInstance();
            fragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        replaceFragment(fragment, addToBackStack);
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction().replace(R.id.mainMenuFragmentHolder, fragment);
        if (addToBackStack) {
            tx.addToBackStack(null);
        }
        tx.commit();
    }
}
