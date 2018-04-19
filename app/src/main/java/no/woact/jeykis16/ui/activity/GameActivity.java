package no.woact.jeykis16.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;

import no.woact.jeykis16.R;
import no.woact.jeykis16.db.entity.Player;
import no.woact.jeykis16.http.WebHelper;
import no.woact.jeykis16.http.entity.CatFact;
import no.woact.jeykis16.ui.fragment.BoardFragment;
import no.woact.jeykis16.ui.fragment.GameStatusFragment;
import no.woact.jeykis16.ui.fragment.dialog.GameOverDialogFragment;
import no.woact.jeykis16.ui.fragment.dialog.PlayDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity implements
        BoardFragment.OnFragmentInteractionListener,
        PlayDialogFragment.OnFragmentInteractionListener,
        GameOverDialogFragment.OnFragmentInteractionListener {
    private GameStatusFragment gameStatusFragment;
    private BoardFragment boardFragment;
    private CatFact catFact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle args = getIntent().getExtras().getBundle("bundle");
        getFragmentManager().beginTransaction()
                .add(R.id.gameFragHolder, gameStatusFragment = GameStatusFragment.newInstance(args))
                .add(R.id.gameFragHolder, boardFragment = BoardFragment.newInstance(args)).commit();

        updateCatFact();
    }

    private void updateCatFact() {
        WebHelper.getCatFactsService().getRandomCatFact().enqueue(new Callback<CatFact>() {
            @Override
            public void onResponse(Call<CatFact> call, Response<CatFact> response) {
                if (response.isSuccessful()) {
                    catFact = response.body();
                }
            }

            @Override
            public void onFailure(Call<CatFact> call, Throwable t) {
                catFact = null;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onGameHasEnded(String winner) {
        GameOverDialogFragment godf = GameOverDialogFragment.newInstance(winner,
                catFact == null ? getString(R.string.play_again) + "?" : catFact.getFact());
        gameStatusFragment.stopTimer();
        gameStatusFragment.incrementScore(winner);
        godf.show(getFragmentManager(), "gameOverFragment");
        updateCatFact();
    }

    @Override
    public void startGame() {
        gameStatusFragment.startTimer();
        boardFragment.startGame();
    }

    @Override
    public void onPlayAgain() {
        int round = boardFragment.newRound();
        gameStatusFragment.swapIcons(round);
    }
}
