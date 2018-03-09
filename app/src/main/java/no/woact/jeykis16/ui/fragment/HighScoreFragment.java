package no.woact.jeykis16.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.woact.jeykis16.R;
import no.woact.jeykis16.ui.activity.MainActivity;

public class HighScoreFragment extends Fragment {
    public static final String TAG = "HighScoreFragment";
    @BindView(R.id.one) public TextView tv;
    public HighScoreFragment() {
        // Required empty public constructor
    }

    public static HighScoreFragment newInstance() {
        HighScoreFragment fragment = new HighScoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view =  inflater.inflate(R.layout.fragment_high_score, container, false);
        ButterKnife.bind(this, view);
        tv.setSingleLine(false);
        tv.setText("Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "\"Dolorem expedita quasi dolor.\\nIpsum animi sint quidem suscipit quam fugiat.\\nDeleniti ipsa officiis dolorum ex magni.\\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem."
                    + "Dolorem expedita quasi dolor.\nIpsum animi sint quidem suscipit quam fugiat.\nDeleniti ipsa officiis dolorum ex magni.\nVel velit dolorem.");
        return view;
    }

}
