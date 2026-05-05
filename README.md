# AP Computer Science A — Unit 4 Project
## Movie Recommendation System

---

## Setup

### 1. Fork the Repository
1. Go to the repository link provided by your teacher
2. Click the **Fork** button in the top right corner of the page
3. This creates your own personal copy of the project under your GitHub account

### 2. Open a Codespace
1. From your forked repository, click the green **Code** button
2. Select the **Codespaces** tab
3. Click **Create codespace on main**
4. Wait for the Codespace to finish loading — this may take a minute

### 3. Set Up JUnit
Once your Codespace is open you will need to configure JUnit for unit testing. Follow these steps:
1. Open the **Extensions** panel on the left sidebar (or press `Ctrl+Shift+X`)
2. Search for and install **Extension Pack for Java** by Microsoft if it is not already installed
3. Open the **Testing** panel on the left sidebar
4. Click **Enable Java Tests** and select **JUnit Jupiter** when prompted
5. Maven will automatically download the JUnit dependencies — wait for it to finish

### 4. Verify Your Setup
You should now see `MovieDatabaseTest.java` appear in the Testing panel. If everything is configured correctly you will be able to run the tests from there. Your file structure should look like this:
```
Movie.java
MovieDatabase.java
MovieDatabaseTest.java
movies_clean.csv
README.md
```

---

## Overview

In this project, you will build a **Movie Recommendation System** in Java that reads real movie data from a CSV file and allows users to explore, search, sort, and receive recommendations based on genre and rating. The dataset contains 800 movies sourced from the [MovieLens](https://grouplens.org/datasets/movielens/) dataset, including each movie's title, release year, genres, average rating, and number of ratings.

This project covers the major topics from Unit 4:

| Topic | Where It Appears |
|---|---|
| 4.1 Ethical & Social Issues | Reflection questions |
| 4.2 Introduction to Data Sets | Understanding the CSV structure |
| 4.3–4.5 Arrays & Algorithms | Storing genre lists per movie |
| 4.6 Text Files | Reading `movies_clean.csv` with `Scanner` |
| 4.7 Wrapper Classes | Parsing rating and year strings to numbers |
| 4.8–4.10 ArrayList & Algorithms | Main movie collection and filtering |
| 4.11–4.13 2D Arrays | Genre-rating matrix |
| 4.14 Searching | Linear search by title |
| 4.15 Sorting | Sort by rating or number of ratings |
| 4.16–4.17 Recursion | Binary search and recursive filtering |

---

## The Dataset

The file `movies_clean.csv` has the following columns:

```
title,year,genres,avgRating,numRatings
Forrest Gump,1994,Comedy/Drama/Romance/War,4.16,329
The Shawshank Redemption,1994,Crime/Drama,4.43,317
...
```

- **title** — Movie title (commas replaced with semicolons)
- **year** — Release year as a 4-digit string
- **genres** — Slash-separated list of genres (e.g. `Action/Sci-Fi/Thriller`)
- **avgRating** — Average user rating from 0.5 to 5.0
- **numRatings** — Number of users who rated this movie (minimum 20)

---

## Part 1 — The `Movie` Class

Create a class called `Movie` that represents a single movie from the dataset.

### Requirements

- Instance variables for `title` (String), `year` (int), `genres` (String[]), `avgRating` (double), and `numRatings` (int)
- A constructor that accepts all five fields
- Getters for each instance variable
- A `toString()` method that returns a readable summary, for example:
  ```
  The Shawshank Redemption (1994) | Crime/Drama | ★ 4.43 (317 ratings)
  ```
- A `hasGenre(String genre)` method that returns `true` if the movie belongs to the given genre (case-insensitive)

### Hints
- Genres should be stored as a `String[]`. The `split()` method returns a `String[]` directly, so you do not need a loop to build the array — a single call to `split("/")` on the genres string is enough.
- Use `String.join("/", genres)` in `toString()` to print genres cleanly without brackets.
- Use `equalsIgnoreCase()` in `hasGenre()` so that `"drama"` and `"Drama"` both match.

---

## Part 2 — Reading the CSV

Create a class called `MovieDatabase` that reads `movies_clean.csv` and stores all movies in an `ArrayList<Movie>`.

### Requirements

- Use the `File` and `Scanner` classes from `java.io` and `java.util`
- The first line of the CSV is a header — skip it before your loop begins
- Use `hasNextLine()` and `nextLine()` together to read through the file line by line
- Split each line with `split(",")` to get the individual fields, then use `split("/")` on the genres field to get a `String[]`
- Use `Integer.parseInt()` and `Double.parseDouble()` to convert the year, rating, and numRatings fields
- Add each parsed `Movie` to an `ArrayList<Movie>`
- Add `throws IOException` to your method header
- Print the total number of movies loaded when done

### Expected Output
```
Loaded 800 movies.
```

---

## Part 3 — MovieDatabase Methods

Add the following methods to your `MovieDatabase` class. Read through all of them before you start writing — you may find that implementing some methods before others makes your life significantly easier.

> **Important:** Since `movies` is a `static` variable and `main` is a `static` method, all methods in `MovieDatabase` that access `movies` must also be declared `static`.

### 3a. `sortByRating()`
```java
public static void sortByRating()
```
Sort the `movies` ArrayList in descending order by `avgRating` using selection sort or insertion sort. Do **not** use `Collections.sort()`.

### 3b. `getTopRated(int n, String genre)`
```java
public static ArrayList<Movie> getTopRated(int n, String genre)
```
Returns an `ArrayList<Movie>` containing the top `n` movies of the given genre by average rating. Use `hasGenre()` from your `Movie` class to check genre membership.

### 3c. `filterByGenre(String genre)`
```java
public static ArrayList<Movie> filterByGenre(String genre)
```
Returns an `ArrayList<Movie>` containing all movies that belong to the given genre.

### 3d. `getAverageRating()`
```java
public static double getAverageRating()
```
Returns the average `avgRating` across all movies in the dataset as a `double`.

### 3e. `getMostPopular(ArrayList<Movie> list, int n)`
```java
public static ArrayList<Movie> getMostPopular(ArrayList<Movie> list, int n)
```
Returns an `ArrayList<Movie>` containing the `n` movies with the highest `numRatings` from the given list.

### 3f. `searchByTitle(String query)`
```java
public static Movie searchByTitle(String query)
```
Returns the first `Movie` whose title contains the query string (case-insensitive) using a linear search. Return `null` if no match is found. Note that this should support **partial matches** — for example, searching `"Die"` should return `"Die Hard"`.

### 3g. `binarySearchByTitle(ArrayList<Movie> list, String title, int low, int high)`
```java
public static int binarySearchByTitle(ArrayList<Movie> list, String title, int low, int high)
```
Implement a binary search that returns the index of the movie with the matching title in the given list, or -1 if not found. The list must already be sorted alphabetically by title before calling this method. The binary search itself must be implemented **recursively**.

> **Hints:** The following two lines are not on the AP Exam but are provided for your use:
>
> Create a copy of an ArrayList so the original is not modified:
> ```java
> ArrayList<Movie> sorted = new ArrayList<Movie>(movies);
> ```
> Sort the copy alphabetically by title using a lambda expression:
> ```java
> sorted.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
> ```
> Sort once before your first recursive call, not inside the recursive method itself.

---

## Part 4 — The Genre-Rating Matrix (2D Array)

You will build a 2D array that summarizes movie counts broken down by genre and rating tier.

### Setup

Define the following genres (in this order) as your rows:

```java
String[] genres = {"Action", "Comedy", "Drama", "Horror", "Romance",
                   "Sci-Fi", "Thriller", "Animation", "Crime", "Documentary"};
```

Define the following rating tiers as your columns:

| Index | Tier | Range |
|---|---|---|
| 0 | Poor | rating < 2.5 |
| 1 | Below Average | 2.5 ≤ rating < 3.0 |
| 2 | Average | 3.0 ≤ rating < 3.5 |
| 3 | Good | 3.5 ≤ rating < 4.0 |
| 4 | Excellent | rating ≥ 4.0 |

### Requirements

Create a method `buildGenreMatrix()` that returns an `int[][]` where each cell contains the **count** of movies belonging to that genre and rating tier. For example, `matrix[0][4]` would contain the number of Action movies with an average rating ≥ 4.0.

Then create a method `printGenreMatrix()` that prints the matrix in a readable table format with genre names as row labels and tier names as column headers.

---

## Part 5 — Recursive Recommendation

Add a method `recommend(String genre, double minRating, int index)` to `MovieDatabase` that uses recursion to traverse the `movies` ArrayList and return an `ArrayList<Movie>` of all movies matching the given genre with an average rating at or above `minRating`.

### Requirements

- The method must be recursive — no loops allowed
- `index` is the starting position for the current recursive call
- The base case is when `index` reaches the end of the list
- Each recursive call processes one movie and then recurses on the rest

### Example Call
```java
ArrayList<Movie> results = db.recommend("Drama", 4.0, 0);
```

---

## Part 6 — Reflection

Answer the following questions in a file called `reflection.md`. Write at least 3–5 sentences per question.

1. **Implementation Order** — Look back at the methods you wrote in Part 3. Did the order you implemented them matter? Were there any methods that became easier because you had already written another one? What would have been harder if you had tackled them in a different order?

2. **Data Quality** — The ratings in this dataset were collected from volunteer MovieLens users, not a random sample of the general population. How might this introduce bias into the data? What kinds of movies might be over- or underrepresented?

3. **Privacy** — The original dataset included a `userId` and `timestamp` for every rating. What are the privacy risks of storing this kind of data? What could someone learn about a user from their rating history?

4. **Data Structure Choice** — You used both an `ArrayList` and a 2D array in this project. Explain why each was the right choice for its purpose. When would you switch from one to the other?

5. **Algorithm Choice** — You implemented both linear search and binary search. Under what conditions is each one more appropriate? Use specific examples from this project to support your answer.

6. **Class Design** — `binarySearchByTitle()` takes a list as a parameter rather than using `MovieDatabase.movies` directly, unlike most other methods in the class. Does this method really belong in `MovieDatabase`? Could you imagine a design where search and sort algorithms live in a separate class? What would be the advantages and disadvantages of that approach?

---

## Grading

| Part | Points |
|---|---|
| Part 1 — Movie Class | 15 |
| Part 2 — Reading the CSV | 15 |
| Part 3 — MovieDatabase Methods | 30 |
| Part 4 — Genre-Rating Matrix | 20 |
| Part 5 — Recursive Recommendation | 10 |
| Part 6 — Reflection | 10 |
| **Total** | **100** |

---

## Submission

Submit the following files:
- `Movie.java`
- `MovieDatabase.java`
- `reflection.md`
- Any additional classes you created

Do **not** submit `movies_clean.csv` — it will be provided separately.
