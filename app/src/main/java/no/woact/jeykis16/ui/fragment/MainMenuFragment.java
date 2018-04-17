package no.woact.jeykis16.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import no.woact.jeykis16.R;
import no.woact.jeykis16.ui.activity.MainActivity;

public class MainMenuFragment extends Fragment {
    public static final String TAG = "MainMenuFragment";

    public MainMenuFragment() {
        // Required empty public constructor
    }

    public static MainMenuFragment newInstance() {
        MainMenuFragment fragment = new MainMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_high_score)
    public void openHighScore() {
        Log.d(TAG, "openHighScore: called");
        ((MainActivity) getActivity()).replaceFragment(HighScoreFragment.class, true);
    }

    @OnClick(R.id.btn_one_player)
    public void startPlayerVersusAi() {
        setPlayerNames(false);
    }

    @OnClick(R.id.btn_two_player)
    public void startPlayerVersusPlayer() {
        setPlayerNames(true);
    }

    private void setPlayerNames(boolean multiplayer) {
        Log.d(TAG, "setPlayerNames: called");
        Bundle bundle = new Bundle();
        bundle.putBoolean("multiplayer", multiplayer);
        ((MainActivity) getActivity()).replaceFragment(PlayerSelectFragment.class, true, bundle);
    }
}
