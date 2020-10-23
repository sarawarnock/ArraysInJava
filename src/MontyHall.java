import java.util.Random;

public class MontyHall {

    public static int chooseAnotherDoor (int door1, int door2) {
        Random ran = new Random();
        int newDoor;
        do {
            newDoor = ran.nextInt(3);
        }
        while (newDoor == door1 || newDoor == door2);
        return newDoor;
    }

    public double[] simulate() {
        double[] result = new double[2];
        Random ran = new Random();
        int rounds = 1000;
        int switchWins = 0;
        int stayWins = 0;
        int losses = 0;
        for (int i = 0; i < rounds; i++) {
            //where the winning prize is
            int prize = ran.nextInt(3);
            //user chooses a random door
            int userChoice1 = ran.nextInt(3);
            //host opens the door that the prize is not behind and that the user did not choose
            int hostChoice = chooseAnotherDoor(prize, userChoice1);
            //if user switches - choose another door thats not the first choice and not the hostChoice - and win
            int userChoice2 = chooseAnotherDoor(userChoice1, hostChoice);
            if (userChoice2 == prize) {
                switchWins++;
            }
            //if they stay and win
            else if (userChoice1 == prize) {
                stayWins++;
            }

            int stayPerc = (stayWins/10);
            int switchPerc = (switchWins/10);
            result[0] = switchPerc;
            result[1] = stayPerc;
        }
        return result;
    }

}