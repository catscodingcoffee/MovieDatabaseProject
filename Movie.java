public class Movie {
    private String title;
    private int year;
    private double avgRating;
    private String[] genres;
    private int numRatings;

    public Movie(String title, int year,String[] genres,  double avgRating,  int numRatings) {
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.avgRating = avgRating;
        this.numRatings = numRatings;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public boolean hasGenre(String genre){
        for(String g:genres){
            if (g.equalsIgnoreCase(genre)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return title+" ("+year+") | "+String.join("/",genres)+" | ★ "+avgRating+" ("+numRatings+" ratings)";
    }
}
