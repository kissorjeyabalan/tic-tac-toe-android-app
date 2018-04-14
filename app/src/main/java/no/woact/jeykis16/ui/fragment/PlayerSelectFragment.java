package no.woact.jeykis16.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.woact.jeykis16.R;
import no.woact.jeykis16.ui.activity.GameActivity;

public class PlayerSelectFragment extends Fragment {

    @BindView(R.id.playerOneInputLayout) public TextInputLayout playerOneLayout;
    @BindView(R.id.playerTwoInputLayout) public TextInputLayout playerTwoLayout;
    @BindView(R.id.generalPlayerNameError) TextView generalPlayerNameError;


    public PlayerSelectFragment() {
        // Required empty public constructor
    }

    public static PlayerSelectFragment newInstance() {
        return new PlayerSelectFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_select, container, false);
        ButterKnife.bind(this, view);
        if (!getArguments().getBoolean("multiplayer")) {
            playerTwoLayout.getEditText().setText(R.string.bot_name);
            playerTwoLayout.setEnabled(false);
            playerTwoLayout.setVisibility(View.INVISIBLE);
        }
        startActionDoneListener();
        return view;
    }



    @OnClick(R.id.startGameBtn)
    public void startGame() {
        if (validateFields()) {
            Intent intent = new Intent(getActivity(), GameActivity.class);
            Bundle args = new Bundle();
            args.putBoolean("multiplayer", getArguments().getBoolean("multiplayer"));
            args.putString("playerOne", playerOneLayout.getEditText().getText().toString());
            args.putString("playerTwo", playerTwoLayout.getEditText().getText().toString());
            intent.putExtra("bundle", args);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }

    private boolean validateFields() {
        generalPlayerNameError.setText("");
        playerOneLayout.setErrorEnabled(false);
        playerTwoLayout.setErrorEnabled(false);
        String playerOneName = playerOneLayout.getEditText().getText().toString();
        String playerTwoName = playerTwoLayout.getEditText().getText().toString();
        if (playerOneName.trim().isEmpty()) {
            playerOneLayout.setError(getString(R.string.err_player_one_name));
        } else if (playerTwoName.trim().isEmpty()) {
            playerTwoLayout.setError(getString(R.string.err_player_two_name));
        } else if (playerTwoName.equals(playerOneName)) {
            generalPlayerNameError.setText(R.string.err_player_same_name);
        } else {
            playerOneLayout.setErrorEnabled(false);
            playerTwoLayout.setErrorEnabled(false);
            return true;
        }
        return false;
    }

    private void startActionDoneListener() {
        if (getArguments().getBoolean("multiplayer")) {
            playerTwoLayout.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        startGame();
                        return true;
                    }
                    return false;
                }
            });
        } else {
            playerOneLayout.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        startGame();
                    }
                    return false;
                }
            });
        }
    }
}
