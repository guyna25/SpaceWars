import java.awt.Image;
import javax.swing.*;

public class Special extends SpaceShip {

    int WAIT_DURATION = 70;

    int currDuration;

    /**
     * Constructor for the Special class
     */

    Special() {
        currDuration = WAIT_DURATION;
        DISTANCE_THRESHOLD = 0.25;
        ANGLE_THRESHOLD = 0.23;
    }

    /**
     * Does the actions of this special ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */

    @Override
    public void doAction(SpaceWars game) {
        this.setShieldoff();
        if (currDuration == 0) {
            teleport();
            currDuration = WAIT_DURATION;
        }

        this.getPhysics().move(false, directionToClosestShip(game));

        if (this.isAngleCloseEnough(game, ANGLE_THRESHOLD) && this.isDistanceCloseEnough(game, DISTANCE_THRESHOLD)) {
            this.shieldOn();
        }
        this.fire(game);

        this.increaseEnergy();
        this.decreaseCoolDown();
        currDuration--;

    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */

    @Override
    public Image getImage(){

        if (this.getShield()) {
            return new ImageIcon("src/special shield.gif").getImage();
        } else {
            return new ImageIcon("src/special.gif").getImage();
        }
    }
}
