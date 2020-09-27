public class Aggresive extends SpaceShip{
    /**
     * Constructor for the Human class
     */

    Aggresive() {
        ANGLE_THRESHOLD = 0.21;
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

        if (isAngleCloseEnough(game, ANGLE_THRESHOLD) && !isAngleCloseEnough(game, -ANGLE_THRESHOLD)) {
            this.fire(game);
        }

        this.increaseEnergy();
        this.decreaseCoolDown();
    }
}
