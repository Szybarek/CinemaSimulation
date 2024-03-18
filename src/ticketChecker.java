public class ticketChecker {


    void checkTicket(Client client){
        if(client.ticketStatus){
            //goToFilm
        }else{
            //goToExit
        }
    }

    void checkAge(Film filmAge, Client clientAge){
        if(filmAge.getFilmAge() <= clientAge.getAge()){
            clientAge.ageStatus = true;
        }else{
            clientAge.ageStatus = false;
        }
    }
}
