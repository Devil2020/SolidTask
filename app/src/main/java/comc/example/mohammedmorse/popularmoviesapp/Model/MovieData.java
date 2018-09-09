package comc.example.mohammedmorse.popularmoviesapp.Model;

import java.util.ArrayList;

/**
 * Created by Mohammed Morse on 16/06/2018.
 */

public class MovieData {
    private String Name;
    private String PosterMovie;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int Id;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPosterMovie() {

        return PosterMovie;
    }

    public void setPosterMovie(String posterMovie) {
        if(posterMovie.length()<=34){
        PosterMovie = "http://image.tmdb.org/t/p/w185"+posterMovie;
    }
    else
        PosterMovie=posterMovie;
    }

    public String getBackgroundMovie() {
        return BackgroundMovie;
    }

    public void setBackgroundMovie(String backgroundMovie) {
        if(backgroundMovie.length()<=34) {
            BackgroundMovie = "http://image.tmdb.org/t/p/w500" + backgroundMovie;
        }
        else
            BackgroundMovie=backgroundMovie;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    private String BackgroundMovie;
    private int Rate;
    private String Overview;
    private String ReleaseDate;

    public ArrayList<Review> getReviewData() {
        return reviewData;
    }

    public void setReviewData(ArrayList<Review> reviewData) {
        this.reviewData = reviewData;
    }

    private ArrayList<Review> reviewData;

    public ArrayList<Trailer> getTrailerData() {
        return trailerData;
    }

    public void setTrailerData(ArrayList<Trailer> trailerData) {
        this.trailerData = trailerData;
    }

    private ArrayList<Trailer> trailerData;
}
