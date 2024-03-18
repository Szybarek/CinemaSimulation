public class Film {
    //private, protected, public
       private String filmType;
       private String filmName;
       private int filmID;
       private int filmAge;

    Film(int filmID, String filmType, String filmName, int filmAge){
        this.filmID = filmID;
        this.filmType = filmType;
        this.filmName = filmName;
        this.filmAge = filmAge;
        //this.filmImage = filmImage - do GUI
    }

     String getFilmName(){
        return this.filmName;
    }

     String getFilmType(){
        return this.filmType;
    }

     int getFilmAge(){
        return this.filmAge;
    }

     int getFilmID(){
        return this.filmID;
    }

    Film getFilm(Film film)
    {
        return film;
    }

}

