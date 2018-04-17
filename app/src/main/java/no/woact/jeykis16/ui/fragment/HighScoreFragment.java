package no.woact.jeykis16.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.woact.jeykis16.R;
import no.woact.jeykis16.db.AppDatabase;
import no.woact.jeykis16.db.entity.Player;
import no.woact.jeykis16.ui.adapter.HighScoreAdapter;

public class HighScoreFragment extends Fragment {
    public static final String TAG = "HighScoreFragment";
    @BindView(R.id.highScoreRecyclerView) RecyclerView highScoreRecyclerView;

    public HighScoreFragment() {
        // Required empty public constructor
    }

    public static HighScoreFragment newInstance() {
        return new HighScoreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_high_score, container, false);
        ButterKnife.bind(this, view);

        List<Player> players = AppDatabase.getAppDatabase(view.getContext()).playerDao().getAllSortedByWinsDesc();
        highScoreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        highScoreRecyclerView.setAdapter(new HighScoreAdapter(players));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
