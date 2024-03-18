import java.util.Random;
public class Client{

    int age;
    boolean ticketStatus = false;

    boolean ageStatus;

    Random random = new Random();

    Client(){
    this.age = getRandomAge();
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

    int getAge(){
        return age;
    }

    void showClient(){
        System.out.println("age: " + age + " | ticket status: " + ticketStatus);
    }

}
