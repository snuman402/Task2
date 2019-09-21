package xyz.appstreets.www.task2.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.appstreets.www.task2.model.Album;

public interface ApiInterface {

    @GET("music_albums")
    Call<List<Album>> getAlbum();

}
