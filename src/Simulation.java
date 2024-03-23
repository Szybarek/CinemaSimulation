import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {

    Menu menu;

    int theatreSize = 4;
    Client client = new Client();
    ticketSeller seller = new ticketSeller();
    ticketChecker checker = new ticketChecker();
    Queue<Client> sellerQueue = new LinkedList<>();
    Queue<Client> checkerQueue = new LinkedList<>();
    Queue<Client> filmQueue = new LinkedList<>();
    Queue<Client> watchingMovie = new LinkedList<>();
    Queue<Client> exitQueue = new LinkedList<>();

    //TIMER
    Timer timer = new Timer();

    int MINUTES_1 = 1000;
    int MINUTES_4 = 4000;
    int MINUTES_5 = 5000;
    int MINUTES_8 = 8000;
    int HOUR_1 = 60000; //czas trwania filmu (1h symulacji = 1min czasu rzeczywistego)

    //STATUS
    boolean queueChangeSeller = false;
    boolean queueChangeChecker = false;
    boolean queueChangeFilm = false;
    boolean queueWatchingMovie = false;
    boolean queueLeaveCinema = false;
    boolean clientLeaving = false;
    boolean movieOn = false;

    void passTime(int dayHour){
        //- pierwszy klient w kolejce
        //spawnSeller();
        spawnClient();
        sellTicket();
        checkTicket();
        goToWatchingMovie();
        endMovie();
        goToExitQueue();
        leaveCinema();
        //startFilm(); //- startuje film
        //getSimInfo(); //- wczytuje informacje z symulacji
        //endSchedule(dayHour);
    }

    //SCHEDULE
    void spawnClient() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(sellerQueue.size()  < 3) {
                    addClient(1); //- klient w ilosci 1 dodany do kolejki seller
                }
            }
        }, MINUTES_1, getRandomTime());
    }

    void sellTicket()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(checkerQueue.size() < 3 && !sellerQueue.isEmpty()) {
                        toCheckerQueue();
                        queueChangeSeller = true;
                }
            }
        }, 0, MINUTES_5);
    }

    void checkTicket() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!checkerQueue.isEmpty() && filmQueue.isEmpty() && !movieOn) {
                    //System.out.println("gowno: " + filmQueue);
                    toFilmQueue();
                    queueChangeChecker = true;
                }
            }
        }, 0, MINUTES_8);
    }

    void goToWatchingMovie()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!filmQueue.isEmpty() && watchingMovie.size() <= theatreSize) {
                    toWatchingMovie();
                    queueChangeFilm = true;
                }
            }
        }, 0, MINUTES_1);
    }

    void endMovie()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(watchingMovie.size() == theatreSize) {
                    //zaczyna sie film z ludzmi w sali w ilosci theatreSize
                    System.out.println("koniec filmu");
                    toExitQueue();
                    System.out.println("exit: " + exitQueue);
                }
            }
        }, 0, MINUTES_8);
    }

    void goToExitQueue()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!exitQueue.isEmpty()) {
                 queueLeaveCinema = true;
                }
            }
        }, 0, MINUTES_5); //tu narazie 5min ale to nie jest dokladne (raczej cos w stylu dlugosc filmu)
    }

    void leaveCinema()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!exitQueue.isEmpty()) {
                    exitQueue.poll();
                    clientLeaving = true;
                }
            }
        }, 0, MINUTES_4);
    }

    void endSchedule(int dayHour)
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Koniec dnia");
                timer.cancel();
                menu.repertoire.dayOfWeek--;
            }
        }, dayHour);
    }
    //TO QUEUE
    void toCheckerQueue()
    {
            Client client = sellerQueue.peek();
            checkerQueue.add(sellerQueue.peek());
            System.out.println("checkerq client " + checkerQueue);
            sellerQueue.remove(sellerQueue.peek());
    }

    void toFilmQueue()
    {
            Client client = checkerQueue.peek();
            filmQueue.add(checkerQueue.peek());
            System.out.println("film queue client " + filmQueue);
            checkerQueue.remove(checkerQueue.peek());
    }

    void toWatchingMovie(){
                Client client = filmQueue.peek();
                watchingMovie.add(filmQueue.peek());
                System.out.println("watching movie q: " + watchingMovie);
                filmQueue.remove(filmQueue.peek());
    }

    void toExitQueue()
    {
        exitQueue = new LinkedList<>(watchingMovie);
        watchingMovie.clear(); //clearing watching room, so others can join
        Client client = exitQueue.peek();
        System.out.println("exit queue: " + exitQueue);
    }



    void addClient(int howMany){
        for(int i = 1; i <= howMany; i++) {
            sellerQueue.add(new Client());
            System.out.println("sellerq client: "+sellerQueue);
        }
    }

      long getRandomTime() {
        Random random = new Random();
        return random.nextInt(MINUTES_5 - MINUTES_1 + 1) + MINUTES_1;
    }

    public Simulation(Menu menu) {
        this.menu = menu;
    }

    void showFirstClient(){
      Client firstClient = sellerQueue.peek();
        firstClient.showClient();
        System.out.println("This is first client in current queue");
    }
}
