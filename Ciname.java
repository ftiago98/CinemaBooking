package cinema;

public class Cinema {

    public static void main(String[] args) {

        CinemaRoom cinemaRoom = new CinemaRoom();

        boolean response;
        do {
            response = cinemaRoom.showNavigation();

        } while (response == false);

    }
}
