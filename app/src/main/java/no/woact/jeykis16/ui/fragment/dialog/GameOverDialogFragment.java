package no.woact.jeykis16.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Objects;

import no.woact.jeykis16.R;
import no.woact.jeykis16.http.entity.CatFact;

public class GameOverDialogFragment extends DialogFragment {
    public static GameOverDialogFragment newInstance(String winner, String catFact) {
        GameOverDialogFragment gameOverDialogFragment = new GameOverDialogFragment();
        Bundle args = new Bundle();
        args.putString("winner", winner);
        args.putString("catFact", catFact);
        gameOverDialogFragment.setArguments(args);
        return gameOverDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final GameOverDialogFragment.OnFragmentInteractionListener interactionListener =
                (GameOverDialogFragment.OnFragmentInteractionListener) getActivity();

        String winner = getArguments().getString("winner");
        String catFact = getArguments().getString("catFact");
        String msg;

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        setCancelable(false);

        adb.setTitle(R.string.game_over);
        if (winner != null) {
            msg = winner + " " + getString(R.string.won_the_game) + "\n";
        } else {
            msg = getString(R.string.tie_game);
        }

        if (catFact != null) {
            msg = msg.concat("\n" + getString(R.string.did_you_know) + "\n" + catFact);
        }

        adb.setMessage(msg);
        adb.setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                interactionListener.onPlayAgain();
            }
        });
        adb.setNegativeButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });

        return adb.create();
    }

    public interface OnFragmentInteractionListener {
        void onPlayAgain();
    }
}
