public class Menu {

    // konwencja nazewnictwa jest taka, że pownno byc FILM_LIST
    enum Status{
        MENU, DAYS, HOUR, SELLER, CHECKER, MUSIC, FILMLIST, EXIT, ERROR, SIMULATION
    }

    Menu(){

    }

    FilmManager manager = new FilmManager();
    Repertoire repertoire = new Repertoire(manager);
    Status currentStatus = Status.MENU;

    void error() {
        System.out.println("ERROR");
        System.exit(0);
    }
    void handleAnswerMenu(int answer) {
    switch(answer){
        case 1:
            currentStatus = Status.DAYS;
            break;
        case 0:
            currentStatus = Status.EXIT;
            break;
        default:
            error();
            break;
        }
    }
    void handleAnswerDays(int dayChoice) {
        if (dayChoice == 0) {
            currentStatus = Status.EXIT;
        } else if (dayChoice > 0 && dayChoice <= 7) {
            repertoire.dayOfWeek = dayChoice;
            currentStatus = Status.HOUR;
            //System.out.println(repertoire.dayOfWeek);
        } else {
            currentStatus = Status.ERROR;
        }
    }

    void handleAnswerHours(int hourChoice) {
        if (hourChoice == 0) {
            currentStatus = Status.EXIT;
        } else if (hourChoice > 3 && hourChoice <= 12) {
            repertoire.hours = hourChoice;
            currentStatus = Status.SELLER;
           //System.out.println(repertoire.hours);
        } else {
            currentStatus = Status.ERROR;
        }
    }

    void handleAnswerSellers(int sellersNumber)
    {
        if (sellersNumber == 0) {
            currentStatus = Status.EXIT;
        } else if (sellersNumber > 0 && sellersNumber <= 2) {
            repertoire.ticketSeller = sellersNumber;
            currentStatus = Status.CHECKER;
            //System.out.println(repertoire.ticketSeller);
        } else {
            currentStatus = Status.ERROR;
        }
    }

    void handleAnswerCheckers(int checkersAnswer)
    {
        if (checkersAnswer == 0) {
            currentStatus = Status.EXIT;
        } else if (checkersAnswer > 0 && checkersAnswer <= 4) {
            repertoire.ticketChecker = checkersAnswer;
            currentStatus = Status.MUSIC;
            //System.out.println(repertoire.ticketChecker);
        } else {
            currentStatus = Status.ERROR;
        }
    }

    void handleAnswerMusic(int musicAnswer)
    {
        if (musicAnswer == 0) {
            currentStatus = Status.EXIT;
        } else if (musicAnswer > 0 && musicAnswer <= 3) {
            manager.createNewList();
            repertoire.music = musicAnswer;
            currentStatus = Status.FILMLIST;
            //System.out.println(repertoire.music);
        } else {
            currentStatus = Status.ERROR;
        }
    }

    void handleAnswerFilm(int filmAnswer)
    {
        if (filmAnswer > 0 && filmAnswer <= 10) {
            manager.addToList(filmAnswer);
            currentStatus = Status.FILMLIST;
        } else if (filmAnswer == 0) {
            currentStatus = Status.EXIT;;
        } else if (filmAnswer == 11) {
            currentStatus = Status.SIMULATION;
            repertoire.showRepertoire();
        } else {
            currentStatus = Status.ERROR;
        }
    }

    /*
        System.out.println("REPERTOIRE MENU");
        System.out.println("CHOOSE MUSIC THEME");
        System.out.println("1. JAZZ");
        System.out.println("2. DISCO");
        System.out.println("3. METAL");
        System.out.println("0. EXIT");
        int musicNumber = scanner.nextInt();

        if (musicNumber == 0) {
            return;
        } else if (musicNumber > 0 && musicNumber <= 3) {
            repertoire.music = musicNumber;
        } else {
            error();
        }

             manager.createNewList();
            while(true) {
                System.out.println("REPERTOIRE MENU");
                System.out.println("CHOOSE FILMS");
                manager.showFilmList(manager.getFilmList());
                System.out.println("11. SAVE");
                System.out.println("0. EXIT");

                int filmNumber = scanner.nextInt();

                if (filmNumber > 0 && filmNumber <= 10) {
                    manager.addToList(filmNumber);
                } else if (filmNumber == 0) {
                    return;
                } else if (filmNumber == 11) {
                    break;
                } else {
                    error();
                }
            }
                repertoire.setListOfFilms(manager.getNewFilmList());
                repertoire.showRepertoire();
            return;
    }*/

}


