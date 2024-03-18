import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {

    Menu menu;
    Client client = new Client();
    ticketSeller seller = new ticketSeller();
    ticketChecker checker = new ticketChecker();
    Queue<Client> sellerQueue = new LinkedList<>();
    Queue<Client> checkerQueue = new LinkedList<>();
    Queue<Client> movieQueue = new LinkedList<>();

    //TIMER
    Timer timer = new Timer();
    int MINUTES_3 = 3000; //czas obsługiwania klienta (3min symulacji = 3sek czasu rzeczywistego)
    int MINUTES_5 = 5000;
    int MINUTES_8 = 8000;
    int MINUTES_15 = 15000;
    int HOUR_1 = 60000; //czas trwania filmu (1h symulacji = 1min czasu rzeczywistego)

    boolean queueChange = false;

    void passTime(int dayHour){
        spawnClient(); //- pierwszy klient w kolejce
        sellTicket(); //- sprawdza czy klient jest przy kasie, obsluguje go i wysyla do nastepnej kolejki
        //startFilm(); //- startuje film
        //getSimInfo(); //- wczytuje informacje z symulacji
        //endSchedule(dayHour);
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

    void startFilm(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Zacznij film");
            }
        }, 0, HOUR_1);
    }

    void spawnClient() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(sellerQueue.size() < 3) {
                    addClient(1); //- klient w ilosci 1 dodany do kolejki seller
                }
            }
        }, MINUTES_3, getRandomTime());
    }

    void sellTicket()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(checkerQueue.size() < 3) {
                    toCheckerQueue();
                }
            }
        }, 0, MINUTES_8);
    }

    void toCheckerQueue()
    {
                if(!sellerQueue.isEmpty()) {
                    Client client = sellerQueue.peek();
                    checkerQueue.add(sellerQueue.peek());
                    queueChange = true;
                    System.out.println("checkerq client " + checkerQueue);
                    sellerQueue.remove(sellerQueue.peek());
                }
    }



    void addClient(int howMany){
        for(int i = 1; i <= howMany; i++) {
            sellerQueue.add(new Client());
            System.out.println("sellerq client: "+sellerQueue);
        }
    }

      long getRandomTime() {
        Random random = new Random();
        return random.nextInt(MINUTES_5 - MINUTES_3 + 1) + MINUTES_3;
    }

    public Simulation(Menu menu) {
        this.menu = menu;
    }

    void mainSimulation(){
        //przydzielenie wartości pozyskanych z menu
        //ilość dni
        //ilość godzin
        //muzyka
        addTicketSeller(1); //selerzy (ustawia status SELLER_1, SELLER_2 itp. który wyświetla ilość sellerów + kasy na mapie)
        addTicketChecker(1); //chekerzy (ustawia status CHECKER_1, CHECKER_2 itp. który wyświetla ilość checkerow + bramki na mapie)

        while(true) //tu będzie kiedyś czas symulacji w dniach jak ogarne jak to zrobić
        {
            //setDay() //przydziela aktualną zmienną day oraz ustawia status DAY_1, DAY_2 itp. który będzie pokazywać w gui jaki jest aktualnie dzień (prawy górny róg mapy)
            //setMusic() //puszcza muzyczke

            while(true) //tu będzie kiedyś czas symulacji w godzinach jak ogarne jak to zrobic
            {
                //WSZYSTKIE TE EVENTY BEDA MIALY OKRESLONY CZAS TRWANIA ZEBY NIC SIE NIE ZAPCHAŁO, TRWAŁO ZA DŁUGO LUB ZA SZYBKO

                //LISTA EVENTOW

                addClient(1); //pojawiaja sie klienci w losowej kolejce z tych które są otwarte, tu chuj wie jak beda dzialac statusy

                sellTicket(); //pierwszy klient kolejki dostaje bilet i idzie w strone kolejki do bramki

                //checkTicket(); //sprawdzany jest bilet i wiek klienta przy bramce, jezeli git to idzie do kolejki do filmu jezeli nie to wywoluje metode goToExit();

                //watchMovie(); //pierwszy klient z kolejki do filmu wchodzi do sali

                //startMovie(); //jezeli godzina sie zgadza, zaczyna sie timer danego filmu, na końcu pętli wywołuje metode checkIfMovieOver()
                //checkIfMovieOver() //sprawdzenie czy dany film sie skonczyl, jezeli nie to pętla trwa dalej, jezeli tak to wywołuje metode goToExit()
                //goToExit //tworzy kolejke do wyjscia, a nastepnie znika po kolei wszystkich klientow na zawsze

                //checkIfDayOver() to będzie sprawdzać czy daily godziny zostały wyrobione/lub zblizaja sie do wyrobienia, jeżel godziny wyrobione to wywoluje sie metoda dayOver();
                //dayOver(); //kończy dzień, wychodzi z pętli oraz jezeli to mozliwe day++;
            }
            //MusicOver(); wyłącza muzyczke
            //checkIfWeekOver(); sprawdza czy ilość dni została wyrobiona jeśli tak to break;, jeśli nie to powtarza pętle
        }
    }


    void addTicketSeller(int howMany){
        for(int i = 1; i <= howMany; i++) {
            seller = new ticketSeller();
        }
    }

    void addTicketChecker(int howMany){
        for(int i = 1; i <= howMany; i++) {
            checker = new ticketChecker();
        }
    }

    void movieNextQueue(){
        Client client = sellerQueue.peek();
        movieQueue.add(client);
    }

    void showFirstClient(){
      Client firstClient = sellerQueue.peek();
        firstClient.showClient();
        System.out.println("This is first client in current queue");
    }
}

/*

    void showRepertoireUnits()
    {
        System.out.print(menu.repertoire.hours * 60000);
    }

void createQueue(int howMany){
    for(int i = 1; i <= howMany; i++)
    {
        sellerQueue.add(new Client());

        if(sellerQueue.size() < 3) {
            System.out.println();
            System.out.println("Queue:");
            sellerQueue.forEach(client -> client.showClient());
            System.out.println();

            if(!client.ticketStatus)
            {
                //klient kupuje ticket
                Client firstClient = sellerQueue.peek();
                firstClient.ticketStatus = true;
                System.out.println("This client is buing a ticket");
                firstClient.showClient();
                System.out.println("Client now leaves for the next queue");
                sellerQueue.poll();
            }
        }
    }
  }

void addclient

void checkTicket(Client client){
    if(client.ticketStatus)
    {
        SellerQueue.poll();
        //chce wsadzic go do kolejnej kolejki?
    }
    else{

    }
}

    for(int i = 0; i <= howMany; i++)
    {
        clientQueue.add(new Client());
    }
    -----------------------------------------
    clientQueue.add(new Client());
    clientQueue.add(new Client());
    clientQueue.add(new Client());
    System.out.println("Zawartość kolejki:");
    System.out.println(clientQueue);
    clientQueue.forEach(client -> client.showClient());
    ---------------------------------------------------
    Client[] spawnClients(int howMany) {
        Client[] client = new Client[howMany];
        for (int i = 0; i < howMany; i++) {
            client[i] = new Client(getRandomAge(), !ticketStatus);
            System.out.println(client[i]);
        }
        return client;
    }*/

