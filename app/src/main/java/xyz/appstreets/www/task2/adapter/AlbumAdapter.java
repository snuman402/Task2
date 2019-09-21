package xyz.appstreets.www.task2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import xyz.appstreets.www.task2.R;
import xyz.appstreets.www.task2.model.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private Context context;
    private List<Album> albumList;

    public AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {

        final AlbumViewHolder holders = holder;
        Album model =albumList.get(position);

        holders.artist_name.setText(model.getArtist());
        holders.album_name.setText(model.getTitle());

        Glide.with(context).load(model.getThumbnail_image()).into(holders.profile_image);
        Glide.with(context).load(model.getImage()).into(holders.album_image);



    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        private TextView artist_name, album_name;
        private ImageView profile_image, album_image;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            album_name = itemView.findViewById(R.id.txt_album_name);
            artist_name = itemView.findViewById(R.id.txt_artist_name);
            profile_image = itemView.findViewById(R.id.profile_image);
            album_image = itemView.findViewById(R.id.album_image);
        }
    }
}
