package movieProject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieDatabaseTest {
    static ArrayList<Movie> testMovies;
    @BeforeAll
    static void setup(){
        testMovies = new ArrayList<>();
        testMovies.add(new Movie("Aladdin", 1992, new String[]{"Animation", "Children", "Comedy", "Musical"}, 3.81, 119));
        testMovies.add(new Movie("Braveheart", 1995, new String[]{"Action", "Drama", "War"}, 4.03, 209));
        testMovies.add(new Movie("Casablanca", 1942, new String[]{"Drama", "Romance", "War"}, 4.22, 95));
        testMovies.add(new Movie("Die Hard", 1988, new String[]{"Action", "Crime", "Thriller"}, 4.01, 174));
        testMovies.add(new Movie("E.T. the Extra-Terrestrial", 1982, new String[]{"Children", "Drama", "Sci-Fi"}, 3.87, 141));
        testMovies.add(new Movie("Forrest Gump", 1994, new String[]{"Comedy", "Drama", "Romance", "War"}, 4.16, 329));
        testMovies.add(new Movie("Gladiator", 2000, new String[]{"Action", "Drama"}, 3.91, 207));
        testMovies.add(new Movie("Home Alone", 1990, new String[]{"Children", "Comedy"}, 3.56, 143));
        testMovies.add(new Movie("Indiana Jones and the Last Crusade", 1989, new String[]{"Action", "Adventure"}, 4.09, 179));
        testMovies.add(new Movie("Jurassic Park", 1993, new String[]{"Action", "Adventure", "Sci-Fi", "Thriller"}, 3.93, 216));
        testMovies.add(new Movie("Kill Bill: Vol. 1", 2003, new String[]{"Action", "Crime", "Thriller"}, 3.96, 162));
        testMovies.add(new Movie("Léon: The Professional", 1994, new String[]{"Action", "Crime", "Drama", "Thriller"}, 4.05, 141));
        testMovies.add(new Movie("Memento", 2000, new String[]{"Mystery", "Thriller"}, 4.15, 157));
        testMovies.add(new Movie("Notting Hill", 1999, new String[]{"Comedy", "Romance"}, 3.55, 93));
        testMovies.add(new Movie("Outbreak", 1995, new String[]{"Action", "Drama", "Sci-Fi", "Thriller"}, 3.32, 67));
        testMovies.add(new Movie("Pulp Fiction", 1994, new String[]{"Comedy", "Crime", "Drama", "Thriller"}, 4.20, 307));
        testMovies.add(new Movie("Rear Window", 1954, new String[]{"Mystery", "Thriller"}, 4.27, 84));
        testMovies.add(new Movie("Schindler's List", 1993, new String[]{"Drama", "War"}, 4.30, 193));
        testMovies.add(new Movie("The Matrix", 1999, new String[]{"Action", "Sci-Fi", "Thriller"}, 4.19, 278));
        testMovies.add(new Movie("Usual Suspects; The", 1995, new String[]{"Crime", "Mystery", "Thriller"}, 4.21, 160));

        MovieDatabase.movies = testMovies;
        MovieDatabase.sortedByRating = testMovies;
    }


    @Test
    void binarySearchByTitle() {
        int end = testMovies.size()-1;
        assertEquals(3,MovieDatabase.binarySearchByTitle(testMovies,"Die Hard",0,end));
        assertEquals(19,MovieDatabase.binarySearchByTitle(testMovies,"Usual Suspects; The",0,end));
        assertEquals(0,MovieDatabase.binarySearchByTitle(testMovies,"Aladdin",0,end));
    }

    @Test
    void searchByTitle() {
        assertEquals("The Matrix", MovieDatabase.searchByTitle("The Matrix").getTitle());
        assertEquals("The Matrix", MovieDatabase.searchByTitle("the matrix").getTitle()); // will fail until case-insensitive fix
        assertEquals("Die Hard", MovieDatabase.searchByTitle("Die").getTitle()); // partial match
        assertNull(MovieDatabase.searchByTitle("The Matrices"));
    }

    @Test
    void getAverageRating(){
        assertEquals(3.9895,MovieDatabase.getAverageRating(),0.001);
    }

    @Test
    void filterByGenre(){
        assertEquals(9, MovieDatabase.filterByGenre("Action").size());
        assertEquals(9, MovieDatabase.filterByGenre("action").size()); // case-insensitive
        assertEquals(0, MovieDatabase.filterByGenre("Western").size()); // genre not in list
        assertEquals("Braveheart", MovieDatabase.filterByGenre("Action").get(0).getTitle()); // first Action movie

    }

    @Test
    void sortByRating() {
        MovieDatabase.sortByRating(testMovies);
        assertEquals("Schindler's List", MovieDatabase.sortedByRating.get(0).getTitle());
        assertEquals("Rear Window", MovieDatabase.sortedByRating.get(1).getTitle());
        assertEquals("Casablanca", MovieDatabase.sortedByRating.get(2).getTitle());
        assertEquals(20, MovieDatabase.sortedByRating.size()); // list size shouldn't change
    }

    @Test
    void getTopRated() {
        // top 3 Action movies by rating
        ArrayList<Movie> result = MovieDatabase.getTopRated(3, "Action");
        assertEquals(3, result.size());
        assertEquals("The Matrix", result.get(0).getTitle());
        assertEquals("Indiana Jones and the Last Crusade", result.get(1).getTitle());
        assertEquals("Léon: The Professional", result.get(2).getTitle());

        // requesting more than available should return all 9
        ArrayList<Movie> allAction = MovieDatabase.getTopRated(20, "Action");
        assertEquals(9, allAction.size());

        // genre with no matches
        assertEquals(0, MovieDatabase.getTopRated(5, "Western").size());
    }
}
