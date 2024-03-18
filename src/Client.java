import java.util.Random;
public class Client{

    int age;
    boolean ticketStatus = false;

    boolean ageStatus;

    Random random = new Random();

    Client(){
    this.age = getRandomAge();
    // podwojna inicjalizacja, tutaj możesz użyć "field initialization" czyli zostawić linijkę 5 a wywalić to (14 linijka) z konstruktora
        // bo ta wartość się nie zmienia jakby
    this.ticketStatus = false;
    }

    Client(int age, boolean ticketStatus){
        this.age = age;
        this.ticketStatus = ticketStatus;
    }

    int getRandomAge(){
        int randomAge = random.nextInt(70)+9;
        return randomAge;
    }

    // gettery zazwyczaj sa public + obczaj Lombok
    int getAge(){
        return age;
    }

    // to tak naprawde jest metoda "toString", kazda klasa w Javie taka ma więc zamiast tego wystarczy, że zrobisz:
//    @Override
//    public String toString()
//    {
//        return "age: " + age + " | ticket status: " + ticketStatus;
//    }
    // i będziesz mógł potem robić tak: System.out.println(clientObject) i będzie to samo
    void showClient(){
        System.out.println("age: " + age + " | ticket status: " + ticketStatus);
    }

}
