package com.samir.popularmovies.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.ReviewDetail;
import com.samir.popularmovies.model.TrailerDetail;
import com.samir.popularmovies.service.PersistenceService;
import com.samir.popularmovies.service.ThemoviedbReviewDelegate;
import com.samir.popularmovies.service.ThemoviedbService;
import com.samir.popularmovies.service.ThemoviedbTrailerDelegate;
import com.samir.popularmovies.ui.adapter.ReviewAdapter;
import com.samir.popularmovies.ui.adapter.TrailerAdapter;
import com.samir.popularmovies.util.DateUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class MovieDetailFragment extends Fragment implements ThemoviedbTrailerDelegate, ThemoviedbReviewDelegate {

    public static final String TAG = MovieDetailFragment.class.getSimpleName();
    @BindView(R.id.recycler_trailers)
    RecyclerView recyclerViewTrailers;

    @BindView(R.id.recycler_reviews)
    RecyclerView recyclerViewReview;


    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.poster)
    ImageView poster;

    private ThemoviedbService themoviedbService = new ThemoviedbService();

    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    private Movie movie;
    private PersistenceService service;
    private View view;


    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(MovieDetailActivity.MOVIE)) {

            movie = arguments.getParcelable(MovieDetailActivity.MOVIE);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ButterKnife.bind(this, view);

        if (movie != null) {
            setMovie(movie);
        }


        return view;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;

        final Context context = AplicationPopularMovies.getContext();
        final GridLayoutManager mLayoutManager = new GridLayoutManager(context, 1);

        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailers.setAdapter(trailerAdapter);

        recyclerViewTrailers.setLayoutManager(mLayoutManager);


        final GridLayoutManager mLayoutManagerR = new GridLayoutManager(context, 1);

        reviewAdapter = new ReviewAdapter();
        recyclerViewReview.setAdapter(reviewAdapter);

        recyclerViewReview.setLayoutManager(mLayoutManagerR);

        setTextIntoTexview(movie.original_title, R.id.title);
        setTextIntoTexview(movie.overview, R.id.synopsis);
        setTextIntoTexview(movie.vote_average, R.id.rating);
        setTextIntoTexview(DateUtil.userFriendlyDate(movie.release_date), R.id.release_date);

        loadTrailers(movie);

        loadReviews(movie);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteMovie(movie);
            }
        });

        service = new PersistenceService();
        if (movie.isFavorited || service.isMovieAdded(movie)){
            favorite.setVisibility(GONE);
        }

        String imageUrl = themoviedbService.getBackdrop(movie);

        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(poster);

    }


    public void favoriteMovie(final Movie movie) {
        movie.isFavorited = true;

        final List<TrailerDetail> trailers = trailerAdapter.getTrailers();
        final List<ReviewDetail> reviews = reviewAdapter.getReviews();

        try {
            favorite.setVisibility(GONE);
            service.save(movie, trailers, reviews);
            CharSequence text = getString(R.string.added);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this.getActivity(), text, duration);
            toast.show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


    }


    private void loadTrailers(Movie movie) {

        themoviedbService.loadTrailers(movie, this);

    }

    private void loadReviews(Movie movie) {

        themoviedbService.loadReviews(movie, this);

    }

    public void setTextIntoTexview(final String text, Integer id) {
        final TextView textView = (TextView) view.findViewById(id);
        textView.setText(text);
    }


    @Override
    public void onPreExecute() {
        // progress = ProgressDialog.show(this, getString(R.string.load_title), getString(R.string.load_trailer), true);
    }

    @Override
    public void posExecute() {
        //progress.dismiss();
        trailerAdapter.notifyDataSetChanged();
        reviewAdapter.notifyDataSetChanged();

    }

    @Override
    public void add(TrailerDetail trailer) {

        final boolean trailer1 = trailer.isTrailer();
        if (trailer1) {
            trailerAdapter.addTrailer(trailer);
        }

    }

    @Override
    public void add(ReviewDetail detail) {

        reviewAdapter.addReview(detail);

    }


}
