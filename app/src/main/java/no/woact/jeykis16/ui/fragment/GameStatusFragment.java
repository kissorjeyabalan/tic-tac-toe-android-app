package no.woact.jeykis16.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @BindView(R.id.playerVersusName) public TextView tvPlayerVersus;
    private OnFragmentInteractionListener mListener;
    private String playerOne;
    private String playerTwo;
    private boolean isMultiplayer;

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
            this.isMultiplayer = getArguments().getBoolean("multiplayer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_status, container, false);
        ButterKnife.bind(this, view);
        String text = playerOne + " vs " + playerTwo;
        tvPlayerVersus.setText(text);
        return view;
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
