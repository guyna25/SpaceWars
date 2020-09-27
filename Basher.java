public class Basher extends SpaceShip {

    /**
     * Constructor for the Basher class
     */

    Basher() {
        DISTANCE_THRESHOLD =0.19;
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */

    @Override
    public void doAction(SpaceWars game) {
        this.setShieldoff();

        this.getPhysics().move(true, this.directionToClosestShip(game));

        if (this.isDistanceCloseEnough(game, DISTANCE_THRESHOLD)) {
            this.shieldOn();
        }

        this.increaseEnergy();
        this.decreaseCoolDown();
    }

}
