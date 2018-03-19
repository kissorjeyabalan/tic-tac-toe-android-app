package no.woact.jeykis16.ui.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.woact.jeykis16.R;

public class HighScoreFragment extends Fragment {
    public static final String TAG = "HighScoreFragment";
    @BindView(R.id.high_score_list) ListView lv;

    public HighScoreFragment() {
        // Required empty public constructor
    }

    public static HighScoreFragment newInstance() {
        HighScoreFragment fragment = new HighScoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_high_score, container, false);
        ButterKnife.bind(this, view);

        String[] mobileArray = {"very","sad","hardcoded","bad",
                "list","oh","well","grr"};
        ArrayAdapter<String> phAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                mobileArray
        );

        lv.setAdapter(phAdapter);
        System.out.println("Tried to make list?");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
