import java.util.ArrayList;

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

class MovieCatalog{
    ArrayList<Movie> movies;
    String sortedByAttribute;

    public MovieCatalog(ArrayList<Movie> movies){
        this.movies = movies;
        this.sortedByAttribute = null;
    }

    public ArrayList<Movie> getMoviesByRating(double rating){
        ArrayList<Movie> resultado = new ArrayList<>();
        double tolerancia = 0.001;

        //Si está ordenado, se usa busqueda binaria
        if(sortedByAttribute == "rating"){
            int left = 0;
            int right = movies.size()-1;
            while(left <= right){
                int mid = (left+right)/2;
                double midRating = movies.get(mid).getRating();

                if(Math.abs(midRating - rating) <= tolerancia){
                    int i = mid;

                    while(i >= 0 && Math.abs(movies.get(i).getRating() - rating) <= tolerancia){
                        resultado.add(movies.get(i));
                        i--;
                    }
                    i = mid+1;
                    while(i<movies.size() && Math.abs(movies.get(i).getRating() - rating) <= tolerancia){
                        resultado.add(movies.get(i));
                        i++;
                    }
                    break;
                } else if(midRating < rating){
                    left = mid + 1;
                } else{
                    right = mid - 1;
                }
            }
            //Si no, Busqueda lineal
        } else{
            for(int i = 0; i < movies.size(); i++){
                if(Math.abs(movies.get(i).getRating() - rating) <= tolerancia){
                    resultado.add(movies.get(i));
                }
            }
        }
        return resultado;
    }

    public ArrayList<Movie> getMoviesByRatingRange(double lowerRating, double higherRating){
        ArrayList<Movie> resultado = new ArrayList<>();

        //Busqueda Binaria
        if(sortedByAttribute == "rating"){
            int left = 0;
            int right = movies.size()-1;
            int indice = -1;

            while(left <= right){
                int mid = (left+right)/2;
                double midRating = movies.get(mid).getRating();

                if(midRating >= lowerRating){
                    indice = mid;
                    right = mid - 1;
                } else{
                    left = mid + 1;
                }
            } if(indice == -1) {
                return resultado;
            } for(int i = indice; i < movies.size(); i++){
                double r = movies.get(i).getRating();
                if(r<= higherRating){
                    resultado.add(movies.get(i));
                } else {
                    break;
                }
            }
            //Busqueda Lineal
        } else {
            for(int i = 0; i < movies.size(); i++){
                double r = movies.get(i).getRating();
                if(r>= lowerRating && r<= higherRating){
                    resultado.add(movies.get(i));
                }
            }
        } return resultado;
    }

    public ArrayList<Movie> getMoviesByGenre(String genre){
        ArrayList<Movie> resultado = new ArrayList<>();

        //Busqueda Binaria
        if(sortedByAttribute == "genre"){
            int left = 0;
            int right = movies.size()-1;
            int indice = -1;

            while(left <= right){
                int mid = (left+right)/2;
                String midGenre = movies.get(mid).getGenre();
                int comparison = midGenre.compareTo(genre);

                if(comparison == 0){
                    indice = mid;
                    break;
                } else if(comparison < 0){
                    left = mid + 1;
                } else{
                    right = mid - 1;
                }
            } if(indice != -1) {
                int i = indice;
                while(i >= 0 && movies.get(i).getGenre() == genre){
                    resultado.add(movies.get(i));
                    i--;
                } i=indice+1;
                while(i<movies.size() && movies.get(i).getGenre() == genre){
                    resultado.add(movies.get(i));
                    i++;
                }
            }
            //Busqueda Lineal
        } else{
            for(int i = 0; i < movies.size(); i++){
                if(movies.get(i).getGenre() == genre){
                    resultado.add(movies.get(i));
                }
            }
        } return resultado;
    }

    public ArrayList<Movie> getMoviesByDirector(String director){
        ArrayList<Movie> resultado = new ArrayList<>();

        //Busqueda Binaria
        if(sortedByAttribute == "director"){
            int left = 0;
            int right = movies.size()-1;
            int indice = -1;

            while(left <= right){
                int mid = (left+right)/2;
                String midDirector = movies.get(mid).getDirector();
                int comparison = midDirector.compareTo(director);

                if(comparison == 0){
                    indice = mid;
                    break;
                } else if(comparison < 0){
                    left = mid + 1;
                } else{
                    right = mid - 1;
                }
            } if(indice != -1) {
                int i = indice;
                while(i >= 0 && movies.get(i).getDirector() == director){
                    resultado.add(movies.get(i));
                    i--;
                } i=indice+1;
                while(i<movies.size() && movies.get(i).getDirector() == director){
                    resultado.add(movies.get(i));
                    i++;
                }
            }
            //Busqueda Lineal
        } else{
            for(int i = 0; i < movies.size(); i++){
                if(movies.get(i).getDirector() == director){
                    resultado.add(movies.get(i));
                }
            }
        } return  resultado;
    }

    public ArrayList<Movie> getMoviesByYear(int year){
        ArrayList<Movie> resultado = new ArrayList<>();

        if(sortedByAttribute == "year"){
            int left = 0;
            int right = movies.size()-1;
            int indice = -1;

        }
    }
}

public class Main {
    public static void main(String[] args) {
    }
}