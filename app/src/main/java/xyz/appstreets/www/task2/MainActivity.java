package xyz.appstreets.www.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.appstreets.www.task2.adapter.AlbumAdapter;
import xyz.appstreets.www.task2.api.ApiClient;
import xyz.appstreets.www.task2.api.ApiInterface;
import xyz.appstreets.www.task2.model.Album;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Album> albums = new ArrayList<>();
    private AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        loadData();

    }

    private void loadData(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Album>> call;
        call = apiInterface.getAlbum();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful() && response.body() != null){

                    if (!albums.isEmpty()){
                        albums.clear();
                    }

                    albums = response.body();
                    adapter = new AlbumAdapter(MainActivity.this, albums);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();



                } else {
                    Toast.makeText(MainActivity.this, "No Result", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });


    }
}
