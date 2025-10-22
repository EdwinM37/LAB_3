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
        Comparator<Movie> comparador;

        if("rating".equals(attribute)){
            comparador = Comparator.comparingDouble(Movie::getRating);
        } else if("genre".equals(attribute)){
            comparador = Comparator.comparing(Movie::getGenre, String.CASE_INSENSITIVE_ORDER);
        } else if("director".equals(attribute)){
            comparador = Comparator.comparing(Movie::getDirector, String.CASE_INSENSITIVE_ORDER);
        } else if("year".equals(attribute)){
            comparador = Comparator.comparingInt(Movie::getReleaseYear);
        } else{
            comparador = Comparator.comparingDouble(Movie::getRating);
        }

        //Algoritmo seleccionado
        if ("insertionSort".equals(algorithm)) {
            insertionSort(movies, comparador);
        } else if ("mergeSort".equals(algorithm)) {
            movies = mergeSort(movies, comparador);
        } else if ("radixSort".equals(algorithm)) {
            if (!"rating".equals(attribute)) {
                Collections.sort(movies, comparador);
            } else {
                movies = radixSort(movies);
            }
        } else {
            Collections.sort(movies, comparador);
        }
        this.sortedByAttribute = attribute;
    }

    //Insertion Sort
    private void insertionSort(ArrayList<Movie> lista, Comparator<Movie> comp) {
        for (int i = 1; i < lista.size(); i++) {
            Movie m = lista.get(i);
            int j = i - 1;
            while (j >= 0 && comp.compare(lista.get(j), m) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, m);
        }
    }

    // Merge Sort
    private ArrayList<Movie> mergeSort(ArrayList<Movie> lista, Comparator<Movie> comp) {
        if (lista.size() <= 1) return lista;

        int mid = lista.size()/2;
        ArrayList<Movie> left = new ArrayList<>(lista.subList(0, mid));
        ArrayList<Movie> right = new ArrayList<>(lista.subList(mid, lista.size()));

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
    private ArrayList<Movie> radixSort(ArrayList<Movie> lista) {
        ArrayList<Movie> result = new ArrayList<>(lista);
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

    private void countingSortByDigit(ArrayList<Movie> lista, int[] keys, int exp, int factor) {
        int n = lista.size();
        Movie[] out = new Movie[n];
        int[] cont = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = (keys[i] / exp) % 10;
            cont[digit]++;
        }

        for (int i = 1; i < 10; i++) cont[i] += cont[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (keys[i] / exp) % 10;
            out[cont[digit] - 1] = lista.get(i);
            cont[digit]--;
        }

        for (int i = 0; i < n; i++){
            lista.set(i, out[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
    }
}