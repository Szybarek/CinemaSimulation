import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilmManager {
    List<Film> filmList;
    List<Film> newFilmList;

    FilmManager(){
        createList();
    }

    public void createList(){
        Film film1 = new Film(1, "Horror", "Scary Ghosts 3", 17);
        Film film2 = new Film(2, "Thriller", "Big boom", 17);
        Film film3 = new Film(3, "Action", "The last Stand", 17);
        Film film4 = new Film(4, "Comedy", "Amazing laugh 2", 12);
        Film film5 = new Film(5, "Science Fiction", "Galactic Odyssey", 12);
        Film film6 = new Film(6, "Romantic", "Love in Paris", 17);
        Film film7 = new Film(7, "Mystery", "Secrets of Hubert", 12);
        Film film8 = new Film(8, "Adventure", "Cool worm", 9);
        Film film9 = new Film(9, "Comedy", "Funny flowers", 9);
        Film film10 = new Film(10, "Adventure", "Journey to the Unknown", 9);
        filmList = List.of(film1, film2, film3, film4, film5, film6, film7, film8, film9, film10);
    }

    public static void showFilmList(List<Film> list){
        list.forEach(film -> System.out.println(film.getFilmID() + ". Film name: " + film.getFilmName() + " | Film type: " + film.getFilmType() + " | Film age: " + film.getFilmAge() + "+"));
    }

    void createNewList()
    {
        newFilmList = new ArrayList<>();
    }

    List<Film> getNewFilmList(){
        return newFilmList;
    }

    void addToList(int filmNumber){
        newFilmList.addAll(filmList.stream().filter(film -> film.getFilmID() == filmNumber).toList());
    }

    List<Film> getFilmList(){
         return filmList;
    }

}
