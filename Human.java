import javax.swing.*;
import java.awt.*;

/**
 * This class is a spaceship controlled by a human
 *
 * @author Guy Navon
 */

public class Human extends SpaceShip {

    /**
     * Constructor for the Human class
     */

    Human() {
    }

    /**
     * Does the actions of this human ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */

    @Override
    public void doAction(SpaceWars game) {
        this.setShieldoff();
        if (game.getGUI().isTeleportPressed()) {
            this.teleport();
        }

        if (game.getGUI().isUpPressed()) {
            this.getPhysics().move(true, this.turnChecker(game));
        } else {
            this.getPhysics().move(false, this.turnChecker(game));
        }

        if (game.getGUI().isShieldsPressed()) {
            this.shieldOn();
        }

        if (game.getGUI().isShotPressed()) {
            this.fire(game);
        }

        this.increaseEnergy();
        this.decreaseCoolDown();
    }

    /**
     *This method checks in which direction the ship is turning. It is a  helper method for doAction
     *
     * @param game the game object to which this ship belongs.
     * @return the ship turn direction: -1 if it turns right, 1 if it turns left and 0 otherwise
     */

    private int turnChecker(SpaceWars game) {
        if (game.getGUI().isLeftPressed() && (!game.getGUI().isRightPressed())) {
            return 1;
        } else if ((!game.getGUI().isLeftPressed()) && game.getGUI().isRightPressed()) {
            return -1;
        }

        return 0;
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
            return new ImageIcon("src/human symbol shield.gif").getImage();
        } else {
            return new ImageIcon("src/human symbol.gif").getImage();
        }
    }
}
