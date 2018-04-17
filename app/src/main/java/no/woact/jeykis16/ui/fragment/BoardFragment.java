package no.woact.jeykis16.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.woact.jeykis16.R;
import no.woact.jeykis16.game.GameManager;
import no.woact.jeykis16.game.PlayerType;
import no.woact.jeykis16.ui.activity.MainActivity;
import no.woact.jeykis16.ui.fragment.dialog.PlayDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardFragment extends Fragment {
    @BindView(R.id.boardGrid) public GridLayout boardGrid;
    private OnFragmentInteractionListener mListener;
    private GameManager gameManager;
    private boolean isMultiplayer;
    private ImageButton[][] gridButtons;

    public View.OnClickListener gridImageButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gameManager.makeMove((ImageButton) view, false);
            if (gameManager.isGameFinished()) {
                mListener.onGameHasEnded(gameManager.getWinner());
            }
        }
    };



    public BoardFragment() {
        // Required empty public constructor
    }

    public static BoardFragment newInstance(Bundle args) {
        BoardFragment boardFragment = new BoardFragment();
        boardFragment.setArguments(args);
        return boardFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.isMultiplayer = getArguments().getBoolean("multiplayer");
            String playerOne = getArguments().getString("playerOne");
            String playerTwo = getArguments().getString("playerTwo");
            gameManager = new GameManager(isMultiplayer, playerOne, playerTwo);
            gridButtons = new ImageButton[3][3];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        ButterKnife.bind(this, view);
        newRound();
        return view;
    }

    public int newRound() {
        boardGrid.removeAllViews();
        createImageButtons();
        gameManager.newRound(gridButtons);
        showReadyDialog();
        return gameManager.getCurrentRound();
    }

    private void showReadyDialog() {
        PlayDialogFragment.newInstance(gameManager.getPlayerOneName(), gameManager.getPlayerTwoName())
                .show(getFragmentManager(), "readyDialog");
    }

    private void createImageButtons() {
        float scale = getContext().getResources().getDisplayMetrics().density;
        int btnSize = (int) (100 * scale * 1.1f);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageButton imgBtn = new ImageButton(getContext());
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                lp.height = btnSize;
                lp.width = btnSize;
                lp.rowSpec = GridLayout.spec(i);
                lp.columnSpec = GridLayout.spec(j);
                lp.setMargins(3, 3, 3, 3);
                imgBtn.setClickable(true);
                imgBtn.setOnClickListener(gridImageButtonListener);
                imgBtn.setId(View.generateViewId());
                imgBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imgBtn.setLayoutParams(lp);
                imgBtn.setBackgroundColor(getResources().getColor(R.color.colorBackground, null));
                boardGrid.addView(imgBtn);
                gridButtons[i][j] = imgBtn;
            }
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
        void onGameHasEnded(String winner);
    }
}
