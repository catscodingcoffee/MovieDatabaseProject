import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class MovieDatabase {
    static ArrayList<Movie> movies = new ArrayList<Movie>();
    static ArrayList<Movie> sortedByTitle = new ArrayList<Movie>();
    static ArrayList<Movie> sortedByRating = new ArrayList<Movie>();
    public static void main(String[] args) throws IOException {

        try {
            File f = new File("movieProject/movies_clean.csv");
            Scanner s = new Scanner(f);
            s.nextLine();
            while (s.hasNextLine()){
                String line = s.nextLine();
                String[] parts = line.split(",");
                String title = parts[0];
                int year = Integer.parseInt(parts[1]);
                String[] genres = parts[2].split("/");
                double avgRating = Double.parseDouble(parts[3]);
                int numRatings = Integer.parseInt(parts[4]);
                Movie m = new Movie(title,year,genres,avgRating,numRatings);
                movies.add(m);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.printf("Loaded %d movies%n",movies.size());
        sortedByTitle = new ArrayList<Movie>(movies);
        sortedByTitle.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
        sortedByRating = new ArrayList<Movie>(movies);
        sortByRating(sortedByRating);


    }
    public static ArrayList<Movie> getTopRated(int n, String genre){
        ArrayList<Movie> topMovies = new ArrayList<Movie>();
        ArrayList<Movie> filtered = filterByGenre(genre);
        sortByRating(filtered);
        if(n>filtered.size()){
            n = filtered.size();
        }
        for(int i = 0; i<n;i++){
            topMovies.add(filtered.get(i));
        }

        return topMovies;
    }

    public static ArrayList<Movie> filterByGenre(String genre){
        ArrayList<Movie> filtered = new ArrayList<Movie>();
        for(Movie m:movies){
            if(m.hasGenre(genre)){
                filtered.add(m);
            }
        }
        return filtered;
    }

    public static double getAverageRating(){
        double total = 0.0;
        for(Movie m:movies){
            total += m.getAvgRating();
        }
        return total/movies.size();
    }

    public static ArrayList<Movie> getMostPopular(ArrayList<Movie> movieList,int n){
        ArrayList<Movie> mostPopular = new ArrayList<Movie>();
        sortByRating(movieList);
        if(n>movieList.size()){
            n = movieList.size();
        }
        for(int i = 0; i<n;i++){
            mostPopular.add(movieList.get(i));
        }
        return mostPopular;
    }

    public static Movie searchByTitle(String query){
        for(Movie m:movies){
            String title = m.getTitle();
            if(title.toLowerCase().contains(query.toLowerCase())){
                return m;
            }
        }
        return null;
    }

    public static int binarySearchByTitle(ArrayList<Movie> movieList,String title,int low,int high){
        if (high>=low){
            int mid = low + (high-low)/2;
            String currentTitle = movieList.get(mid).getTitle();
            if(currentTitle.equalsIgnoreCase(title)){
                return mid;
            }
            if(title.compareTo(currentTitle)>0){
                return binarySearchByTitle(movieList,title,mid+1,high);
            }
            return binarySearchByTitle(movieList,title,low,mid-1);
        }

        return -1;
    }

    public static void sortByRating(ArrayList<Movie> movies){
        //uses insertion sort
        for(int i = 1; i<movies.size();i++){
            Movie current = movies.get(i);
            int j = i-1;
            while(j>=0 && movies.get(j).getAvgRating()< current.getAvgRating()){
                movies.set(j+1,movies.get(j));
                j--;
            }
            movies.set(j+1,current);
        }
    }
}
