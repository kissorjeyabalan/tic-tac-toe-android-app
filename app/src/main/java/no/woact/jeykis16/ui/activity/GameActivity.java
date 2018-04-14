package no.woact.jeykis16.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.woact.jeykis16.R;
import no.woact.jeykis16.ui.fragment.BoardFragment;
import no.woact.jeykis16.ui.fragment.GameStatusFragment;

public class GameActivity extends AppCompatActivity implements
        GameStatusFragment.OnFragmentInteractionListener,
        BoardFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle args = getIntent().getExtras().getBundle("bundle");

        getFragmentManager().beginTransaction()
                .add(R.id.gameFragHolder, GameStatusFragment.newInstance(args))
                .add(R.id.gameFragHolder, BoardFragment.newInstance(args.getBoolean("multiplayer"))).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
