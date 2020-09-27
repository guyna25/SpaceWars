import java.util.Random;

public class Drunkard extends SpaceShip{

    private int BENDER_DURATION = 100;

    private int currDuration;

    private int currPhase;

    private Random randomValueGenerator = new Random();

    /**
     * Constructor for the Drunkard class
     */

    Drunkard() {
        currDuration = BENDER_DURATION;
        currPhase = randomValueGenerator.nextInt(3);
    }

    /**
     * Does the actions of this Drunkard ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */

    @Override
    public void doAction(SpaceWars game) {
        this.setShieldoff();
        if (currDuration == 0) {
            currDuration = BENDER_DURATION;
            currPhase = randomValueGenerator.nextInt(3);
        }

        switch (currPhase) {
            case 0:
                this.SpinAndShoot(game);
            case 1:
                this.HowDoITeleportAgain(game);
            case 2:
                this.sureICanDrive(game, currDuration);
        }
        this.increaseEnergy();
        this.decreaseCoolDown();
        currDuration--;
    }

    /**
     * The pilot is spinning aimlessly and shooting everywhere, someone should revoke his space license
     * @param game the game object to which this ship belongs.
     */

    private void SpinAndShoot(SpaceWars game) {
        this.getPhysics().move(false, 1);
        this.fire(game);
    }

    /**
     * The pilot is trying to teleport, but he cannot remember how to do that since he was wasted during intergalactic
     * driving lessons, so he's accidentally accelerating and turning on the shield
     * @param game the game object to which this ship belongs.
     */

    private void HowDoITeleportAgain(SpaceWars game) {
        if (randomValueGenerator.nextBoolean()) {
            teleport();
        } else {
            this.shieldOn();
            this.getPhysics().move(true, 0);
        }
    }

    /**
     * The pilot is driving in zigzag, alternating between left and right.
     * @param game the game object to which this ship belongs.
     * @param currDuration the amount of time left for this phase currently
     */

    private void sureICanDrive(SpaceWars game, int currDuration) {
        if (currDuration%2 == 0 ) {
            this.getPhysics().move(true, 1);
        } else {
            this.getPhysics().move(true, -1);
        }
    }
}
