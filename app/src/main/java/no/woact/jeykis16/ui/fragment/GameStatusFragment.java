package no.woact.jeykis16.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.woact.jeykis16.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameStatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameStatusFragment extends Fragment {
    @BindView(R.id.playerOneName) public TextView tvPlayerOneName;
    @BindView(R.id.playerTwoName) public TextView tvPlayerTwoName;
    @BindView(R.id.playerOneScore) public TextView tvPlayerOneScore;
    @BindView(R.id.playerTwoScore) public TextView tvPlayerTwoScore;
    @BindView(R.id.playerOneIcon) public ImageView playerOneIcon;
    @BindView(R.id.playerTwoIcon) public ImageView playerTwoIcon;
    @BindView(R.id.gameDurationChronometer) public Chronometer gameDurationChrono;
    private OnFragmentInteractionListener mListener;
    private String playerOne;
    private String playerTwo;

    public GameStatusFragment() {
        // Required empty public constructor
    }


    public static GameStatusFragment newInstance(Bundle args) {
        GameStatusFragment fragment = new GameStatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.playerOne = getArguments().getString("playerOne");
            this.playerTwo = getArguments().getString("playerTwo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_status, container, false);
        ButterKnife.bind(this, view);
        tvPlayerOneName.setText(playerOne);
        tvPlayerTwoName.setText(playerTwo);
        tvPlayerOneScore.setText("0");
        tvPlayerTwoScore.setText("0");

        return view;
    }

    public void startTimer() {
        gameDurationChrono.setBase(SystemClock.elapsedRealtime());
        gameDurationChrono.start();
    }

    public void stopTimer() {
        gameDurationChrono.stop();
    }

    public void swapIcons(int currentRound) {
        if (currentRound == 0) {
            playerOneIcon.setImageResource(R.drawable.cross);
            playerTwoIcon.setImageResource(R.drawable.circle);
        } else {
            playerOneIcon.setImageResource(R.drawable.circle);
            playerTwoIcon.setImageResource(R.drawable.cross);
        }
    }

    public void incrementScore(String winner) {
        if (playerOne.equals(winner)) {
            int currScore = Integer.parseInt(tvPlayerOneScore.getText().toString());
            tvPlayerOneScore.setText(String.valueOf(currScore + 1));
        } else if (playerTwo.equals(winner)) {
            int currScore = Integer.parseInt(tvPlayerTwoScore.getText().toString());
            tvPlayerTwoScore.setText(String.valueOf(currScore + 1));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}
