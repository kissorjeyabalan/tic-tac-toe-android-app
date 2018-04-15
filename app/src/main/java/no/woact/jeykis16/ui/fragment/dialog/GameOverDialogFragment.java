package no.woact.jeykis16.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import no.woact.jeykis16.R;

public class GameOverDialogFragment extends DialogFragment {
    public static GameOverDialogFragment newInstance(String winner) {
        GameOverDialogFragment gameOverDialogFragment = new GameOverDialogFragment();
        Bundle args = new Bundle();
        args.putString("winner", winner);
        gameOverDialogFragment.setArguments(args);
        return gameOverDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final GameOverDialogFragment.OnFragmentInteractionListener interactionListener =
                (GameOverDialogFragment.OnFragmentInteractionListener) getActivity();

        String winner = getArguments().getString("winner");
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        setCancelable(false);
        adb.setTitle(R.string.game_over);
        if (winner != null) {
            adb.setMessage(winner + getString(R.string.won_the_game));
        } else {
            adb.setMessage(R.string.tie_game);
        }
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
