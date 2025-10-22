import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Movie{
    String title;
    String director;
    String genre;
    int releaseYear;
    double rating;

    public Movie(String title, String director, String genre, int releaseYear, double rating){
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    //Getters
    public String getTitle(){
        return title;
    }
    public String getDirector(){
        return director;
    }
    public String getGenre(){
        return genre;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
    public double getRating(){
        return rating;
    }
}

class MovieCatalog {
    ArrayList<Movie> movies;
    String sortedByAttribute;

    public MovieCatalog(ArrayList<Movie> movies) {
        this.movies = movies;
        this.sortedByAttribute = null;
    }

    public ArrayList<Movie> getMoviesByRating(double rating) {
        ArrayList<Movie> resultado = new ArrayList<>();
        double tolerancia = 0.001;

        //Si está ordenado, se usa busqueda binaria
        if ("rating".equals(sortedByAttribute)){
            int left = 0;
            int right = movies.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                double midRating = movies.get(mid).getRating();

                if (Math.abs(midRating - rating) <= tolerancia) {
                    int i = mid;

                    while (i >= 0 && Math.abs(movies.get(i).getRating() - rating) <= tolerancia) {
                        resultado.add(movies.get(i));
                        i--;
                    }
                    i = mid + 1;
                    while (i < movies.size() && Math.abs(movies.get(i).getRating() - rating) <= tolerancia) {
                        resultado.add(movies.get(i));
                        i++;
                    }
                    break;
                } else if (midRating < rating) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            //Si no, se usa Busqueda lineal
        } else {
            for (int i = 0; i < movies.size(); i++) {
                if (Math.abs(movies.get(i).getRating() - rating) <= tolerancia) {
                    resultado.add(movies.get(i));
                }
            }
        }
        return resultado;
    }

    public ArrayList<Movie> getMoviesByRatingRange(double lowerRating, double higherRating) {
        ArrayList<Movie> resultado = new ArrayList<>();

        //Busqueda Binaria
        if ("rating".equals(sortedByAttribute)){
            int left = 0;
            int right = movies.size() - 1;
            int indice = -1;

            while (left <= right) {
                int mid = (left + right) / 2;
                double midRating = movies.get(mid).getRating();

                if (midRating >= lowerRating) {
                    indice = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int i = indice; i < movies.size(); i++) {
                double r = movies.get(i).getRating();
                if (r <= higherRating) {
                    resultado.add(movies.get(i));
                } else {
                    break;
                }
            }
            //Busqueda Lineal
        } else {
            for (int i = 0; i < movies.size(); i++) {
                double r = movies.get(i).getRating();
                if (r >= lowerRating && r <= higherRating) {
                    resultado.add(movies.get(i));
                }
            }
        }
        return resultado;
    }

    public ArrayList<Movie> getMoviesByGenre(String genre) {
        ArrayList<Movie> resultado = new ArrayList<>();

        //Busqueda Binaria
        if ("genre".equals(sortedByAttribute)){
            int left = 0;
            int right = movies.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                String midGenre = movies.get(mid).getGenre();
                int comparable = midGenre.compareTo(genre);

                if (comparable == 0) {
                    // Encontramos uno, buscar alrededor
                    int i = mid;
                    while (i >= 0 && movies.get(i).getGenre().equalsIgnoreCase(genre)) {
                        resultado.add(movies.get(i));
                        i--;
                    }
                    i = mid + 1;
                    while (i < movies.size() && movies.get(i).getGenre().equalsIgnoreCase(genre)) {
                        resultado.add(movies.get(i));
                        i++;
                    }
                    break;
                } else if (comparable < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            //Busqueda Lineal
        } else {
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getGenre().equals(genre)){
                    resultado.add(movies.get(i));
                }
            }
        }
        return resultado;
    }

    public ArrayList<Movie> getMoviesByDirector(String director) {
        ArrayList<Movie> resultado = new ArrayList<>();

        //Busqueda Binaria
        if ("director".equals(sortedByAttribute)){
            int left = 0;
            int right = movies.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                String midDirector = movies.get(mid).getDirector();
                int comparable = midDirector.compareTo(director);

                if (comparable == 0) {
                    int i = mid;
                    while (i >= 0 && movies.get(i).getDirector().equalsIgnoreCase(director)) {
                        resultado.add(movies.get(i));
                        i--;
                    }
                    i = mid + 1;
                    while (i < movies.size() && movies.get(i).getDirector().equalsIgnoreCase(director)) {
                        resultado.add(movies.get(i));
                        i++;
                    }
                    break;
                } else if (comparable < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            //Busqueda Lineal
        } else {
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getDirector().equals(director)){
                    resultado.add(movies.get(i));
                }
            }
        }
        return resultado;
    }

    public ArrayList<Movie> getMoviesByYear(int year) {
        ArrayList<Movie> resultado = new ArrayList<>();

        //Busqueda Binaria
        if ("year".equals(sortedByAttribute)){
            int left = 0;
            int right = movies.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int midYear = movies.get(mid).getReleaseYear();

                if (midYear == year) {
                    int i = mid;
                    while (i >= 0 && movies.get(i).getReleaseYear() == year) {
                        resultado.add(movies.get(i));
                        i--;
                    }
                    i = mid + 1;
                    while (i < movies.size() && movies.get(i).getReleaseYear() == year) {
                        resultado.add(movies.get(i));
                        i++;
                    }
                    break;
                } else if (midYear < year) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            //Busqueda Lineal
        } else {
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getReleaseYear() == year) {
                    resultado.add(movies.get(i));
                }
            }
        }
        return resultado;
    }

    public void sortByAlgorithm(String algorithm, String attribute) {
        //Comparadores por atributo
        Comparator<Movie> comparator;

        if ("rating".equals(attribute)) {
            comparator = Comparator.comparingDouble(Movie::getRating);
        } else if ("genre".equals(attribute)) {
            comparator = Comparator.comparing(Movie::getGenre, String.CASE_INSENSITIVE_ORDER);
        } else if ("director".equals(attribute)) {
            comparator = Comparator.comparing(Movie::getDirector, String.CASE_INSENSITIVE_ORDER);
        } else if ("year".equals(attribute)) {
            comparator = Comparator.comparingInt(Movie::getReleaseYear);
        } else {
            comparator = Comparator.comparingDouble(Movie::getRating);
        }

        //Algoritmo seleccionado
        if ("insertionSort".equals(algorithm)) {
            insertionSort(movies, comparator);
        } else if ("mergeSort".equals(algorithm)) {
            movies = mergeSort(movies, comparator);
        } else if ("radixSort".equals(algorithm)) {
            if (!"rating".equals(attribute)) {
                // RadixSort solo para rating, sino usar Collections.sort
                Collections.sort(movies, comparator);
            } else {
                movies = radixSort(movies);
            }
        } else {
            Collections.sort(movies, comparator);
        }

        this.sortedByAttribute = attribute;
    }

    //Insertion Sort
    private void insertionSort(ArrayList<Movie> list, Comparator<Movie> comp) {
        for (int i = 1; i < list.size(); i++) {
            Movie key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comp.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Merge Sort
    private ArrayList<Movie> mergeSort(ArrayList<Movie> list, Comparator<Movie> comp) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        ArrayList<Movie> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Movie> right = new ArrayList<>(list.subList(mid, list.size()));

        left = mergeSort(left, comp);
        right = mergeSort(right, comp);

        return merge(left, right, comp);
    }

    private ArrayList<Movie> merge(ArrayList<Movie> left, ArrayList<Movie> right, Comparator<Movie> comp) {
        ArrayList<Movie> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comp.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }

    // Radix Sort
    private ArrayList<Movie> radixSort(ArrayList<Movie> list) {
        ArrayList<Movie> result = new ArrayList<>(list);
        int n = result.size();
        int[] keys = new int[n];
        int factor = 1000;

        for (int i = 0; i < n; i++) {
            keys[i] = (int)(result.get(i).getRating() * factor);
        }

        int max = keys[0];
        for (int k : keys) if (k > max) max = k;

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(result, keys, exp, factor);
            for (int i = 0; i < n; i++) keys[i] = (int)(result.get(i).getRating() * factor);
        }

        return result;
    }

    private void countingSortByDigit(ArrayList<Movie> list, int[] keys, int exp, int factor) {
        int n = list.size();
        Movie[] output = new Movie[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = (keys[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (keys[i] / exp) % 10;
            output[count[digit] - 1] = list.get(i);
            count[digit]--;
        }

        for (int i = 0; i < n; i++){
            list.set(i, output[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
    }
}