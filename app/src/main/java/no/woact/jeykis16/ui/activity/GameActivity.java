package no.woact.jeykis16.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import no.woact.jeykis16.R;
import no.woact.jeykis16.db.AppDatabase;
import no.woact.jeykis16.db.entity.Player;
import no.woact.jeykis16.game.PlayerType;
import no.woact.jeykis16.ui.fragment.BoardFragment;
import no.woact.jeykis16.ui.fragment.GameStatusFragment;
import no.woact.jeykis16.ui.fragment.dialog.GameOverDialogFragment;
import no.woact.jeykis16.ui.fragment.dialog.PlayDialogFragment;

public class GameActivity extends AppCompatActivity implements
        GameStatusFragment.OnFragmentInteractionListener,
        BoardFragment.OnFragmentInteractionListener,
        PlayDialogFragment.OnFragmentInteractionListener,
        GameOverDialogFragment.OnFragmentInteractionListener {
    private GameStatusFragment gameStatusFragment;
    private BoardFragment boardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle args = getIntent().getExtras().getBundle("bundle");
        getFragmentManager().beginTransaction()
                .add(R.id.gameFragHolder, gameStatusFragment = GameStatusFragment.newInstance(args))
                .add(R.id.gameFragHolder, boardFragment = BoardFragment.newInstance(args)).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onGameHasEnded(String winner) {
        GameOverDialogFragment godf = GameOverDialogFragment.newInstance(winner);
        gameStatusFragment.stopTimer();
        gameStatusFragment.incrementScore(winner);
        godf.show(getFragmentManager(), "gameOverFragment");
    }

    @Override
    public void startGame() {
        gameStatusFragment.startTimer();
    }

    @Override
    public void onPlayAgain() {
        int round = boardFragment.newRound();
        gameStatusFragment.swapIcons(round);
    }
}
