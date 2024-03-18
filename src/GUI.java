import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class GUI {

    JButton userSubmitButton;
    JTextField userInputField;
    Menu menu = new Menu();
    Simulation simulation = new Simulation(menu);
    JFrame simulationFrame = createFrame("Cinema Simulation");
    ArrayList<JPanel> SELLERS = new ArrayList<>();
    ArrayList<JPanel> CHECKERS_Q1_Q2= new ArrayList<>();
    ArrayList<JPanel> CHECKERS_Q3_Q4 = new ArrayList<>();
    ArrayList<JPanel> Q_EXIT = new ArrayList<>();
    ArrayList<JPanel> Q1_SELLER = new ArrayList<>();
    ArrayList<JPanel> Q1_CHECKER = new ArrayList<>();
    ArrayList<JPanel> Q1_HALL = new ArrayList<>();
    ArrayList<JPanel> Q2_SELLER = new ArrayList<>();
    ArrayList<JPanel> Q2_CHECKER = new ArrayList<>();
    ArrayList<JPanel> Q2_HALL = new ArrayList<>();
    ArrayList<JPanel> Q3_SELLER = new ArrayList<>();
    ArrayList<JPanel> Q3_CHECKER = new ArrayList<>();
    ArrayList<JPanel> Q3_HALL = new ArrayList<>();
    ArrayList<JPanel> Q4_SELLER = new ArrayList<>();
    ArrayList<JPanel> Q4_CHECKER = new ArrayList<>();
    ArrayList<JPanel> Q4_HALL = new ArrayList<>();
    GUI() {
        //createMenu();
        //createFilmList();
        //createMusic();
        startSimulation();
    }



    void startSimulation(){
            simulation.passTime(menu.repertoire.hours * 60000); //metoda trwająca tyle godzin ile poda uzytkownik, puszczająca symulacje
            createSimulation();
            // while true samo w sobie nie jest najlepsza praktyka
            while(true) {
                updateCheckerQ();
                updateSellerQ();
                simulationFrame.repaint();
            }
    }

    void updateSellerQ()
    {
        for(int i = 0; i < simulation.sellerQueue.size(); i++) {
            simulationFrame.add(Q1_SELLER.get(i));
        }
        if(simulation.queueChange)
        {
           simulationFrame.remove(Q1_SELLER.get(simulation.sellerQueue.size() - 1));
           simulation.queueChange = false;
        }
    }

    void updateCheckerQ()
    {
        for(int i = 0; i < simulation.checkerQueue.size(); i++)
        {
            simulationFrame.add(Q1_CHECKER.get(i));
        }
    }

        /*if(simulation.sellerQueue.size() == 0)
        {
            simulationFrame.add(Q1_CHECKER.get(0));
            simulationFrame.remove(Q1_SELLER.get(0));
        }

        if(simulation.sellerQueue.size() == 1)
        {
            simulationFrame.add(Q1_CHECKER.get(1));
            simulationFrame.remove(Q1_SELLER.get(1));
            simulationFrame.add(Q1_SELLER.get(0));
        }
        if(simulation.sellerQueue.size() == 2)
        {
            simulationFrame.remove(Q1_SELLER.get(2));
            simulationFrame.add(Q1_SELLER.get(1));
        }*/

    // poprawa czytelności, ogólnie jak Ci gui coś na żółto podświetla i proponuje gotowa zmianę to w 99% przypadków warto z tego skorzystać
    void updateGUI(){
        Menu.Status status = menu.currentStatus;
        switch (status) {
            case MENU -> createMenu();
            case DAYS -> createDays();
            case HOUR -> createHours();
            case SELLER -> createSellers();
            case CHECKER -> createCheckers();
            case MUSIC -> createMusic();
            case FILMLIST -> createFilmList();
            case SIMULATION -> startSimulation();
            case EXIT -> System.exit(0);
            case ERROR -> {
                menu.error();
                System.exit(0);
            }
        }
    }


    JLabel createLabel(String title, int fontSize){
        JLabel label = new JLabel();
        label.setText(title);
        label.setFont(new Font("MV Boli", Font.PLAIN, fontSize));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
    JFrame createFrame(String title){
        JFrame menuFrame = new JFrame(); // Create frame
        URL logo1 = getClass().getResource("logo.png");
        ImageIcon cinemaLogo2 = new ImageIcon(new ImageIcon(logo1).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        menuFrame.setTitle(title);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(false);
        menuFrame.setSize(600, 600);
        menuFrame.setIconImage(cinemaLogo2.getImage());
        return menuFrame;
    }

    JPanel createPanel(Color color, int x, int y, int width, int height)
    {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(x, y, width, height);
        return panel;
    }


    void createQueue(JFrame frame, JPanel panel,ArrayList<JPanel> list, int xPosition, int yPosition, Color color){
        list.add(panel);
        panel.setBackground(color);
        panel.setBounds(xPosition, yPosition, 20, 20);
        //frame.add(panel);
    }


    void createSimulation() {
        simulationFrame.setLayout(null);

        // Z założenia przyjętego u mnie w zespole kod nie powinien wymagać raczej komentarzy, tutaj np może warto by dodać
        // argument name, który byś tym panelom potem przypiswał, żeby wiedzieć co jest co
        //CONSTANT PANELS - PANELS THAT ARE ALWAYS VISIBLE
        //Entrance_1
        simulationFrame.add(createPanel(Color.green, 550, 50, 50, 150));
        //Entrance_2
        simulationFrame.add(createPanel(Color.green, 550, 250, 50, 150));
        //Exit
        simulationFrame.add(createPanel(Color.red, 550, 450, 35, 75));
        //Exit_2
        simulationFrame.add(createPanel(Color.red, 0, 450, 35, 75));
        //TicketTable_1
        simulationFrame.add(createPanel(Color.gray, 350, 70, 35, 100));
        //TicketTable_2
        simulationFrame.add(createPanel(Color.gray, 350, 270, 35, 100));
        //Check_Table_1
        simulationFrame.add(createPanel(Color.black, 180, 70, 35, 45));
        //Check_Table_2
        simulationFrame.add(createPanel(Color.black, 180, 120, 35, 45));
        //Check_Table_3
        simulationFrame.add(createPanel(Color.black, 180, 270, 35, 45));
        //Check_Table_4
        simulationFrame.add(createPanel(Color.black, 180, 320, 35, 45));
        //Hall_1
        simulationFrame.add(createPanel(Color.blue, 50, 65, 50, 50));
        //Hall_2
        simulationFrame.add(createPanel(Color.blue, 50, 120, 50, 50));
        //Hall_3
        simulationFrame.add(createPanel(Color.blue, 50, 265, 50, 50));
        //Hall_4
        simulationFrame.add(createPanel(Color.blue, 50, 320, 50, 50));

        //EVENT PANELS - PANELS THAT WILL APPEAR AND DISAPPEAR
        int xPositionSeller = 420;
        int xPositionChecker = 230;
        int xPositionHall = 112;
        int xPositionExit = 520;
        int yPositionSeller = 109;
        int yPositionChecker = 87;
        int yPositionChecker2 = 286;
        int xSpacing = 40;
        int ySpacingS = 200;
        int ySpacingC = 45;
        //CHECKERS_Q1_Q2
        for(int i = 0; i < 2; i++)
        {
            //CHECKERS_Q1_Q2
            JPanel checker = new JPanel();
            createQueue(simulationFrame, checker, CHECKERS_Q1_Q2, 153, yPositionChecker, Color.orange);
            yPositionChecker += ySpacingC;
        }
        //CHECKERS_Q3_Q4
        for(int i = 0; i < 2; i++)
        {
            //CHECKERS_Q1_Q2
            JPanel checker2 = new JPanel();
            createQueue(simulationFrame, checker2, CHECKERS_Q3_Q4, 153, yPositionChecker2, Color.orange);
            yPositionChecker2 += ySpacingC;
        }
        //SELLERS
        for(int i = 0; i < 2; i++)
        {
            //SELLERS
            JPanel seller = new JPanel();
            createQueue(simulationFrame, seller, SELLERS, 325, yPositionSeller, Color.orange);
            yPositionSeller += ySpacingS;
        }
        //Q_EXIT
        for(int i = 0; i < 13; i++)
        {
            //QE
            JPanel clientQE = new JPanel();
            createQueue(simulationFrame, clientQE, Q_EXIT, xPositionExit, 475, Color.pink);
            xPositionExit -= xSpacing;
        }
        //Q1_SELLER_CHECKER
        for(int i = 0; i < 3; i++)
        {
            //Q1S
            JPanel clientQ1S = new JPanel();
            createQueue(simulationFrame, clientQ1S, Q1_SELLER, xPositionSeller, 80, Color.pink);
            xPositionSeller += xSpacing;
            //Q1C
            JPanel clientQ1C = new JPanel();
            createQueue(simulationFrame,clientQ1C, Q1_CHECKER, xPositionChecker, 80, Color.pink);
            xPositionChecker += xSpacing;
        }
        //Q1_HALL
        JPanel clientQ1H = new JPanel();
        createQueue(simulationFrame,clientQ1H, Q1_HALL, xPositionHall, 80, Color.pink);
        xPositionSeller = 420; //resetx
        xPositionChecker = 230; //resety
        //Q2_SELLER_CHECKER
        for(int i = 0; i < 3; i++)
        {
            //Q2S
            JPanel clientQ2S = new JPanel();
            createQueue(simulationFrame, clientQ2S, Q2_SELLER, xPositionSeller, 140, Color.pink);
            xPositionSeller += xSpacing;
            //Q2C
            JPanel clientQ2C = new JPanel();
            createQueue(simulationFrame,clientQ2C, Q2_CHECKER, xPositionChecker, 140, Color.pink);
            xPositionChecker += xSpacing;
        }
        //Q2_HALL
        JPanel clientQ2H = new JPanel();
        createQueue(simulationFrame,clientQ2H, Q2_HALL, xPositionHall, 140, Color.pink);
        xPositionSeller = 420; //resetx
        xPositionChecker = 230; //resety
        //Q3_SELLER_CHECKER
        for(int i = 0; i < 3; i++)
        {
            //Q3S
            JPanel clientQ3S = new JPanel();
            createQueue(simulationFrame, clientQ3S, Q3_SELLER, xPositionSeller, 280, Color.pink);
            xPositionSeller += xSpacing;
            //Q3C
            JPanel clientQ3C = new JPanel();
            createQueue(simulationFrame,clientQ3C, Q3_CHECKER, xPositionChecker, 280, Color.pink);
            xPositionChecker += xSpacing;
        }
        //Q3_HALL
        JPanel clientQ3H = new JPanel();
        createQueue(simulationFrame,clientQ3H, Q3_HALL, xPositionHall, 280, Color.pink);
        xPositionSeller = 420; //resetx
        xPositionChecker = 230; //resety
        //Q4_SELLER_CHECKER
        for(int i = 0; i < 3; i++)
        {
            //Q4S
            JPanel clientQ4S = new JPanel();
            createQueue(simulationFrame, clientQ4S, Q4_SELLER, xPositionSeller, 340, Color.pink);
            xPositionSeller += xSpacing;
            //Q4C
            JPanel clientQ4C = new JPanel();
            createQueue(simulationFrame,clientQ4C, Q4_CHECKER, xPositionChecker, 340, Color.pink);
            xPositionChecker += xSpacing;
        }
        //Q4_HALL
        JPanel clientQ4H = new JPanel();
        createQueue(simulationFrame,clientQ4H, Q4_HALL, xPositionHall, 340, Color.pink);

        // Set frame visibility
        simulationFrame.setVisible(true);
    }



    void createMenu() {
        JPanel mainPanel = new JPanel(); // Main panel
        JFrame menuFrame = createFrame("Cinema Simulation"); // Create frame
        JLabel titleLabel = createLabel("MENU", 50); // Title label
        JLabel optionsLabel = createLabel("OPTIONS", 35); // Options label
        JLabel repertoireLabel = createLabel("1 - CREATE REPERTOIRE", 25); // Repertoire label
        JLabel exitLabel = createLabel("0 - EXIT", 25); // Repertoire label

         userInputField = new JTextField();
         userSubmitButton = new JButton("Submit");

        //Register Action Listener
        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int menuAnswer = Integer.parseInt((userInputField.getText()));
                menu.handleAnswerMenu(menuAnswer);

                updateGUI();
                menuFrame.dispose();
            }
        });
        // Set main panel layout
        mainPanel.setLayout(new GridLayout(6, 1, 20, 20));
        // Title
        mainPanel.add(titleLabel);
        // Options
        mainPanel.add(optionsLabel);
        // Repertoire
        mainPanel.add(repertoireLabel); // Add repertoire label to main panel
        // Exit
        mainPanel.add(exitLabel); // Add exit label to main panel
        //Text field
        mainPanel.add(userInputField);
        //Button
        mainPanel.add(userSubmitButton);
        // Add main panel to frame
        menuFrame.add(mainPanel);
        // Set frame visibility
        menuFrame.setVisible(true);
    }

    void createDays()
    {
        JPanel mainPanel = new JPanel();
        JFrame menuFrame = createFrame("Cinema Simulation");
        JLabel repTitleLabel = createLabel("REPERTOIRE MAKING: ", 30);
        JLabel repDaysLabel = createLabel("WHEN START, AND END WORK?", 25);
        JLabel repMndLabel = createLabel("1 - MONDAY", 15);
        JLabel repMndTueLabel = createLabel("2 - MONDAY-TUESDAY", 15);
        JLabel repMndWsnLabel = createLabel("3 - MONDAY-WEDNESDAY", 15);
        JLabel repMndThrLabel = createLabel("4 - MONDAY-THURSDAY", 15);
        JLabel repMndFriLabel = createLabel("5 - MONDAY-FRIDAY", 15);
        JLabel repMndSatLabel = createLabel("6 - MONDAY-SATURDAY", 15);
        JLabel repMndSunLabel =  createLabel("7 - MONDAY-SUNDAY", 15);
        JLabel exitLabel = createLabel("0 - EXIT", 15);

        userInputField = new JTextField();
        userSubmitButton = new JButton("Submit");
        //Register Action Listener
        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int daysAnswer = Integer.parseInt((userInputField.getText()));
                menu.handleAnswerDays(daysAnswer);
                updateGUI();
                menuFrame.dispose();
            }
        });
        // Set main panel layout
        mainPanel.setLayout(new GridLayout(12, 1, 10, 10));
        // Title label settings
        mainPanel.add(repTitleLabel);
        // Question
        mainPanel.add(repDaysLabel);
        // Options
        mainPanel.add(repMndLabel);
        mainPanel.add(repMndTueLabel);
        mainPanel.add(repMndWsnLabel);
        mainPanel.add(repMndThrLabel);
        mainPanel.add(repMndFriLabel);
        mainPanel.add(repMndSatLabel);
        mainPanel.add(repMndSunLabel);
        // Exit
        mainPanel.add(exitLabel);
        //Text field
        mainPanel.add(userInputField);
        //Button
        mainPanel.add(userSubmitButton);
        // Add main panel to frame
        menuFrame.add(mainPanel);
        // Set frame visibility
        menuFrame.setVisible(true);
    }

    void createHours(){
        JPanel mainPanel = new JPanel(); // Main panel
        JFrame menuFrame = createFrame("Cinema Simulation");
        JLabel repTitleLabel = createLabel("REPERTOIRE MAKING: ", 30);
        JLabel repHoursQLabel = createLabel("HOW MANY DAILY WORKING HOURS?", 20);
        JLabel repHoursLabel = createLabel("CHOOSE 4-12", 20);
        JLabel exitLabel = createLabel("0 - EXIT", 25);

        userInputField = new JTextField();
        userSubmitButton = new JButton("Submit");
        //Register Action Listener
        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hoursAnswer = Integer.parseInt((userInputField.getText()));
                menu.handleAnswerHours(hoursAnswer);
                updateGUI();
                menuFrame.dispose();
            }
        });
        // Set main panel layout
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
        // Title label settings
        mainPanel.add(repTitleLabel);
        // Question
        mainPanel.add(repHoursQLabel);
        mainPanel.add(repHoursLabel);
        // Exit
        mainPanel.add(exitLabel);
        //Text field
        mainPanel.add(userInputField);
        //Button
        mainPanel.add(userSubmitButton);
        // Add main panel to frame
        menuFrame.add(mainPanel);
        // Set frame visibility
        menuFrame.setVisible(true);
    }
    void createSellers(){
            JPanel mainPanel = new JPanel(); // Main panel
            JFrame menuFrame = createFrame("Cinema Simulation");
            JLabel repTitleLabel = createLabel("REPERTOIRE MAKING: ", 30);
            JLabel repSellersQLabel = createLabel("HOW MANY TICKET SELLERS?", 20);
            JLabel repSellersLabel = createLabel("CHOOSE 1-2", 20);
            JLabel exitLabel = createLabel("0 - EXIT", 25);

        userInputField = new JTextField();
        userSubmitButton = new JButton("Submit");
        //Register Action Listener
        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sellersAnswer = Integer.parseInt((userInputField.getText()));
                menu.handleAnswerSellers(sellersAnswer);
                updateGUI();
                menuFrame.dispose();
            }
        });
            // Set main panel layout
            mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
            // Title label settings
            mainPanel.add(repTitleLabel);
            // Question
            mainPanel.add(repSellersQLabel);
            mainPanel.add(repSellersLabel);
            // Exit
            mainPanel.add(exitLabel);
            //Text field
            mainPanel.add(userInputField);
            //Button
            mainPanel.add(userSubmitButton);
            // Add main panel to frame
            menuFrame.add(mainPanel);
            // Set frame visibility
            menuFrame.setVisible(true);
    }
    void createCheckers(){
        JPanel mainPanel = new JPanel(); // Main panel
        JFrame menuFrame = createFrame("Cinema Simulation");
        JLabel repTitleLabel = createLabel("REPERTOIRE MAKING: ", 30);
        JLabel repCheckersQLabel = createLabel("HOW MANY TICKET CHECKERS?", 20);
        JLabel repCheckersLabel = createLabel("CHOOSE 1-4", 20);
        JLabel exitLabel = createLabel("0 - EXIT", 25);

        userInputField = new JTextField();
        userSubmitButton = new JButton("Submit");
        //Register Action Listener
        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int checkersAnswer = Integer.parseInt((userInputField.getText()));
                menu.handleAnswerCheckers(checkersAnswer);
                updateGUI();
                menuFrame.dispose();
                userInputField.setText("");
            }
        });

        // Set main panel layout
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
        // Title label settings
        mainPanel.add(repTitleLabel);
        // Question
        mainPanel.add(repCheckersQLabel);
        mainPanel.add(repCheckersLabel);
        // Exit
        mainPanel.add(exitLabel);
        //Text field
        mainPanel.add(userInputField);
        //Button
        mainPanel.add(userSubmitButton);
        // Add main panel to frame
        menuFrame.add(mainPanel);
        // Set frame visibility
        menuFrame.setVisible(true);
    }
    void createMusic(){
        JPanel mainPanel = new JPanel(); // Main panel
        JFrame menuFrame = createFrame("Cinema Simulation");
        JLabel repTitleLabel = createLabel("REPERTOIRE MAKING: ", 30);
        JLabel repMusicQLabel = createLabel("CHOOSE MUSIC THEME", 20);
        JLabel repJazzLabel = createLabel("1 - JAZZ", 20);
        JLabel repDiscoLabel = createLabel("2 - DISCO", 20);
        JLabel repJMetalLabel = createLabel("3 - METAL", 20);
        JLabel exitLabel = createLabel("0 - EXIT", 20);

        userInputField = new JTextField();
        userSubmitButton = new JButton("Submit");
        //Register Action Listener
        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int musicAnswer = Integer.parseInt((userInputField.getText()));
                menu.handleAnswerMusic(musicAnswer);
                updateGUI();
                menuFrame.dispose();
            }
        });
        // Set main panel layout
        mainPanel.setLayout(new GridLayout(8, 1, 10, 10));
        // Title label settings
        mainPanel.add(repTitleLabel);
        // Question
        mainPanel.add(repMusicQLabel);
        //Options
        mainPanel.add(repJazzLabel);
        mainPanel.add(repDiscoLabel);
        mainPanel.add(repJMetalLabel);
        // Exit
        mainPanel.add(exitLabel);
        //Text field
        mainPanel.add(userInputField);
        //Button
        mainPanel.add(userSubmitButton);
        // Add main panel to frame
        menuFrame.add(mainPanel);
        // Set frame visibility
        menuFrame.setVisible(true);
    }
    void createFilmList(){
        JPanel mainPanel = new JPanel(); // Main panel
        JFrame menuFrame = createFrame("Cinema Simulation");
        JLabel repTitleLabel = createLabel("REPERTOIRE MAKING: ", 30);
        JLabel repFilmList = createLabel("Film List: ", 20);
        JLabel repFilm1 = createLabel("1- Scary Ghosts 3, Horror, 17+", 10);
        JLabel repFilm2 = createLabel("2- Big boom, Thriller, 17+", 10);
        JLabel repFilm3 = createLabel("3 - The last Stand, Action, 17+", 10);
        JLabel repFilm4 = createLabel("6 - Love in Paris, Romantic, 17+", 10);
        JLabel repFilm5 = createLabel("4 - Amazing Laugh 2, Komedia, 12+", 10);
        JLabel repFilm6 = createLabel("5 - Galactic Odyssey, Sci-Fi, 12+", 10);
        JLabel repFilm7 = createLabel("7 - Mystery Mansion, Mystery, 12+", 10);
        JLabel repFilm8 = createLabel("8 - Secrets of Hubert, Adventure, 9+", 10);
        JLabel repFilm9 = createLabel("9 - Funny flowers, Comedy, 9+", 10);
        JLabel repFilm10 = createLabel("10 - Journey to the Unknown, Adventure, 9+", 10);
        JLabel save = createLabel("11 - SAVE", 10);
        JLabel exitLabel = createLabel("0 - EXIT", 10);
        userInputField = new JTextField();
        userSubmitButton = new JButton("Submit");

        //Register Action Listener
        userSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filmAnswer = Integer.parseInt((userInputField.getText()));
                menu.handleAnswerFilm(filmAnswer);
                updateGUI();
                menuFrame.dispose();
            }
        });
        // Set main panel layout
        mainPanel.setLayout(new GridLayout(16, 1, 10, 10));
        // Title label settings
        mainPanel.add(repTitleLabel);
        // Question
        mainPanel.add(repFilmList);
        //Options
        mainPanel.add(repFilm1);
        mainPanel.add(repFilm2);
        mainPanel.add(repFilm3);
        mainPanel.add(repFilm4);
        mainPanel.add(repFilm5);
        mainPanel.add(repFilm6);
        mainPanel.add(repFilm7);
        mainPanel.add(repFilm8);
        mainPanel.add(repFilm9);
        mainPanel.add(repFilm10);
        //SAVE
        mainPanel.add(save);
        // Exit
        mainPanel.add(exitLabel);
        //Text field
        mainPanel.add(userInputField);
        //Button
        mainPanel.add(userSubmitButton);
        // Add main panel to frame
        menuFrame.add(mainPanel);
        // Set frame visibility
        menuFrame.setVisible(true);
    }
}