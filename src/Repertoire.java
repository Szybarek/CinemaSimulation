import java.awt.Menu;
import java.util.List;
public class Repertoire{
    // domyślnietakie pola powinny być wszystkie private
    int dayOfWeek;
    int hours;
    int ticketSeller;
    int ticketChecker;
    int music;
    String nameDayOfWeek;
    String nameMusic;
    FilmManager manager;
    Menu menu;

    Repertoire(FilmManager manager){
        this.manager = manager;
    }
    Repertoire(){
    }

    public Repertoire(Menu menu) {
        this.menu = menu;
    }

    // a takie wartości warto sobie dać do jakiegoś enuma albo chociaż zrobić interfejs constants i tam pododawać jako static final
    void showRepertoire(){
        //music
        if(music == 1){
            nameMusic = "Jazz";
        }
        else if(music == 2){
            nameMusic = "Disco";
        }
        else if(music == 3){
            nameMusic = "Metal";
        }
        //dayofweek
        if(dayOfWeek == 1){
            nameDayOfWeek = "MONDAY";
        }
        else if(dayOfWeek == 2){
            nameDayOfWeek = "MONDAY-TUESDAY";
        }
        else if(dayOfWeek == 3){
            nameDayOfWeek = "MONDAY-WEDNESDAY";
        }
        else if(dayOfWeek == 4){
            nameDayOfWeek = "MONDAY-THURSDAY";
        }
        else if(dayOfWeek == 5){
            nameDayOfWeek = "MONDAY-FRIDAY";
        }
        else if(dayOfWeek == 6){
            nameDayOfWeek = "MONDAY-SATURDAY";
        }
        else if(dayOfWeek == 7) {
            nameDayOfWeek = "MONDAY-SUNDAY";
        }
        System.out.println("From: " + nameDayOfWeek + " | "  + hours + " Hours | Ticket Sellers: " + ticketSeller + " | Ticket Checkers: " + ticketChecker + " | Music: " + nameMusic);
        System.out.println("Film list: ");
        FilmManager.showFilmList(manager.getNewFilmList());
    }

}
