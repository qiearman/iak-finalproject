package com.dunianaya.qmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SectionIndexer;

import com.dunianaya.qmovie.adapter.AdapterMain;
import com.dunianaya.qmovie.api.ApiService;
import com.dunianaya.qmovie.model.Movie;
import com.dunianaya.qmovie.model.Section;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService.IMovieDB mMovieService;
    List<Section> mSections;
    List<Movie> mMovies;

    RecyclerView mRvMain;

    static final int SECTION_POPULAR = 1;
    static final int SECTION_TOP_RATED = 2;
    static final int SECTION_UPCOMING = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvMain = (RecyclerView) findViewById(R.id.rv_Main);

        mMovieService = ApiService.getMovieService();

        Section sectionPopular = createSection("Popular", SECTION_POPULAR);
        Section sectionTopRated = createSection("Top Rated", SECTION_TOP_RATED);
        Section sectionUpcoming = createSection("Upcoming", SECTION_UPCOMING);

        mSections = new ArrayList<Section>();



    }

    Section mSection = null;
    private Section createSection(String sectionName, final int type) {
        mSection = new Section();
        Call<String> call = callFactory(type);
        final String sectionTitle = sectionName;
        call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                        mSection = new Section();
                        mSection.setTitle(sectionTitle);

                        mMovies = new ArrayList<Movie>();
                        try {
                            JSONArray data = Helper.createRoot(response.body()).getJSONArray("results");
                            if(data.length() > 0) {
                               for( int i = 0; i < data.length() ; i++ ) {
                                   JSONObject movieData = data.getJSONObject(i);
                                   Movie movie = new Movie();
                                   movie.setTitle(movieData.getString("title"));
                                   mMovies.add(movie);
                                   Log.d("HANCA", movie.getTitle());

                               }
                            }
                            mSection.setMovies(mMovies);
                            mSections.add(mSection);


                            AdapterMain adapterMain = new AdapterMain(mSections);
                            mRvMain.setAdapter(adapterMain);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
        return mSection;
    }

    private Call<String> callFactory(int type) {
        switch (type) {
            case SECTION_POPULAR:
                return mMovieService.popular(ApiService.API_KEY, 1);
            case SECTION_TOP_RATED:
                return mMovieService.topRated(ApiService.API_KEY, 1);
            case SECTION_UPCOMING:
                return mMovieService.upcoming(ApiService.API_KEY, 1);
            default:
                return null;
        }
    }
}
