import javax.swing.*;
import java.awt.*;

public class Runner extends SpaceShip{

    /**
     * Constructor for the Runner class
     */

    Runner() {
        DISTANCE_THRESHOLD = 0.25;
        ANGLE_THRESHOLD = 0.23;
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
        if (this.isAngleCloseEnough(game, ANGLE_THRESHOLD) && this.isDistanceCloseEnough(game, DISTANCE_THRESHOLD)) {
            teleport();
        }

        this.getPhysics().move(true, ( this.directionToClosestShip(game) * -1)); //notice i've turned the result
        // so it'd match the Runner's character definition
        this.increaseEnergy();
        this.decreaseCoolDown();
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
            return new ImageIcon("src/runner symbol shield.gif").getImage();
        } else {
            return new ImageIcon("src/runner symbol .gif").getImage();
        }
    }
}
