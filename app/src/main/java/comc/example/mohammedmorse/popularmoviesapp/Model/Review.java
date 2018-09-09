package comc.example.mohammedmorse.popularmoviesapp.Model;

/**
 * Created by Mohammed Morse on 29/06/2018.
 */

public class Review {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    private String Review;
    public Review(){}
    public Review(String n, String r){
        this.Name=n;
        this.Review=r;
    }
}
