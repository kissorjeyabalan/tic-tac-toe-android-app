package no.woact.jeykis16.ui.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.woact.jeykis16.R;

public class PlayerSelectFragment extends Fragment {

    public PlayerSelectFragment() {
        // Required empty public constructor
    }

    public static PlayerSelectFragment newInstance() {
        return new PlayerSelectFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_select, container, false);
    }
}
