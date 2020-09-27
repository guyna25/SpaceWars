import oop.ex2.*;

public class SpaceShipFactory {

    public static SpaceShip[] createSpaceShips(String[] args) {
        String AGGRESIVE_SHIP = "a";
        String BASHER_SHIP = "b";
        String DRUNKARD_SHIP = "d";
        String HUMAN_SHIP = "h";
        String RUNNER_SHIP = "r";
        String SPECIAL_SHIP = "s";
        SpaceShip[] returnedSpaceShips = new SpaceShip[args.length];
        for (int index = 0; index < args.length; index++ ) {
            if (args[index].equals(AGGRESIVE_SHIP)) {
                returnedSpaceShips[index] = new Aggresive();
            } else if (args[index].equals(BASHER_SHIP)) {
                returnedSpaceShips[index] = new Basher();
            } else if (args[index].equals(DRUNKARD_SHIP)) {
                returnedSpaceShips[index] = new Drunkard();
            } else if (args[index].equals(HUMAN_SHIP)) {
                returnedSpaceShips[index] = new Human();
            } else if (args[index].equals(RUNNER_SHIP)) {
                returnedSpaceShips[index] = new Runner();
            } else if (args[index].equals(SPECIAL_SHIP)) {
                returnedSpaceShips[index] = new Special();
            } else {
                returnedSpaceShips[index] = null;
            }
        }
         return returnedSpaceShips;
    }
}
