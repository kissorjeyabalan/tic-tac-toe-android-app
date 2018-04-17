package no.woact.jeykis16.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.woact.jeykis16.R;
import no.woact.jeykis16.db.entity.Player;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHolder> {
    List<Player> players;

    public HighScoreAdapter(List<Player> playerList) {
        players = playerList;
    }

    @Override
    public HighScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_item, parent, false);
        ButterKnife.bind(this, item);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(HighScoreAdapter.ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.userName.setText(player.getUsername());
        holder.wins.setText(Integer.toString(player.getWins()));
        holder.defeats.setText(Integer.toString(player.getDefeats()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, wins, defeats;

        ViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.highScoreUsername);
            wins = (TextView) view.findViewById(R.id.highScoreWins);
            defeats = (TextView) view.findViewById(R.id.highScoreDefeats);
        }
    }
}
