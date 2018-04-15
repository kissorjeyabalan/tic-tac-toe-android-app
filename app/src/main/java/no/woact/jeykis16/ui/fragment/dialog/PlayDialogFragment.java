package no.woact.jeykis16.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import no.woact.jeykis16.R;

public class PlayDialogFragment extends DialogFragment {

    public static PlayDialogFragment newInstance(String playerOneName, String playerTwoName) {
        PlayDialogFragment pdf = new PlayDialogFragment();
        Bundle args = new Bundle();
        args.putString("playerOne", playerOneName);
        args.putString("playerTwo", playerTwoName);
        pdf.setArguments(args);
        return pdf;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final OnFragmentInteractionListener interactionListener = (OnFragmentInteractionListener) getActivity();

        String playerOneName = getArguments().getString("playerOne");
        String playerTwoName = getArguments().getString("playerTwo");
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        setCancelable(false);
        adb.setTitle(playerOneName + " vs " + playerTwoName);
        adb.setMessage("Ready to play?");
        adb.setPositiveButton("Okay, let's go!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                interactionListener.startGame();
            }
        });
        adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO: stuff
                getActivity().finish();
            }
        });
        return adb.create();
    }

    public interface OnFragmentInteractionListener {
        void startGame();
    }
}
