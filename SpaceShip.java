import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */

public abstract class SpaceShip{

    /**
     * The maximal amount of health each ship can have
     */

    private int INITIAL_MAX_HEALTH = 22;

    /**
     * The maximal amount of energy each ship can have
     */

    private int INITIAL_MAX_ENERGY = 210;

    /**
     * The amount of health each ship starts with
     */

    private int INITIAL_HEALTH = 22;

    /**
     * The amount of energy each ship starts with
     */

    private int INITIAL_ENERGY = 190;

    /**
     * The amount of energy needed to fire
     */

    private int FIRE_THRESHOLD = 19;

    /**
     * The amount of energy needed to teleport
     */

    private int TELEPORT_THRESHOLD = 140;

    /**
     * The amount of energy needed to turn shield on
     */

    private int SHIELD_THRESHOLD = 3;

    /**
     * The amount of rounds it takes to cool down
     */
    private int COOL_DOWN_LENGTH = 7;

    /**
     * The maximal distance a ship can be from another ship
     */

    double ANGLE_THRESHOLD;

    /**
     * The maximal angle a ship can be from another ship
     */

    double DISTANCE_THRESHOLD;

    /**
     * The current health of the ship
     */

    private int currentHealth;

    /**
     * The amount of energy the ship currently has
     */

    private int currentEnergy;

    /**
     * The max amount of health the ship currently has
     */

    private int maxCurrentHealth;

    /**
     * The max amount of energy the ship currently has
     */

    private int maxCurrentEnergy;

    /**
     * The state of the ship's shields
     */

    private boolean shieldIsOn;

    /**
     * The ships current cool down
     */

    private int currCoolDown;

    /**
     * The ship's physical state
     */

    private SpaceShipPhysics physicalState;

    /**
     * Constructor for the Spaceship abstract class
     */

    public SpaceShip  () {

        currentHealth = INITIAL_HEALTH;
        currentEnergy = INITIAL_ENERGY;
        maxCurrentHealth = INITIAL_MAX_HEALTH;
        maxCurrentEnergy = INITIAL_MAX_ENERGY;
        shieldIsOn = false;
        physicalState = new SpaceShipPhysics();
        currCoolDown =0;


    }

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shieldIsOn = false;
        this.increaseEnergy();
        this.decreaseCoolDown();
    }

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){

        if (shieldIsOn) {
            currentEnergy += 18;
            maxCurrentEnergy += 18;
        } else {
            if (maxCurrentEnergy < 10) {
                maxCurrentEnergy = 0;
            } else {
                maxCurrentEnergy -= 10;
            }
            if (currentEnergy > maxCurrentEnergy) {
                currentEnergy = maxCurrentEnergy;
            } else {
                currentEnergy -= 10;
            }

            if (currentHealth > 0) {
                currentHealth--;
            }
        }


    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){

        currentHealth = INITIAL_HEALTH;
        currentEnergy = INITIAL_ENERGY;
        maxCurrentHealth = INITIAL_MAX_HEALTH;
        maxCurrentEnergy = INITIAL_MAX_ENERGY;
        shieldIsOn = false;
        physicalState = new SpaceShipPhysics();
        currCoolDown =0;
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */

    public boolean isDead() {

        return currentHealth == 0;

    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */

    public SpaceShipPhysics getPhysics() {
        return physicalState;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldIsOn) {
            if (currentHealth > 0) {
                currentHealth--;
            }
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){

        if (this.shieldIsOn) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if (currentEnergy >= FIRE_THRESHOLD && currCoolDown == 0) {
           game.addShot(physicalState);
           currentEnergy -= FIRE_THRESHOLD;
           currCoolDown = COOL_DOWN_LENGTH;
       }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {

        if (currentEnergy >= SHIELD_THRESHOLD) {
            shieldIsOn = true;
            currentEnergy -= SHIELD_THRESHOLD;
        }
    }

    /**
     * Attempts to teleport.
     */

    public void teleport() {
       if (currentEnergy >= TELEPORT_THRESHOLD) {
            physicalState = new SpaceShipPhysics();
            currentEnergy -= TELEPORT_THRESHOLD;
       }
    }

    /**
     * This is a helper method for ship's doAction, it checks if the ship distance is too close to the closest ship to
     * her
     * @param game the game object to which this ship belongs.
     * @param distanceThreshold the maximal distance from closest ship
     * @return true if the ship's distance is close enough to the closest ship, false otherwise
     */

    public boolean isDistanceCloseEnough(SpaceWars game, double distanceThreshold) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        return this.getPhysics().distanceFrom(closestShip.getPhysics()) <= distanceThreshold;
    }

    /**
     * This is a helper method for ship's doAction, it checks if the ship angle is too close to the closest ship to her
     * @param game the game object to which this ship belongs.
     * @param angleThreshold the maximal angle from closest ship
     * @return true if the ship's angle is close enough to the closest ship, false otherwise
     */

    public boolean isAngleCloseEnough(SpaceWars game, double angleThreshold) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        return this.getPhysics().distanceFrom(closestShip.getPhysics()) <= angleThreshold;
    }

    /**
     * increases the ship's energy level
     */

    public void increaseEnergy() {
        if (currentEnergy < maxCurrentEnergy) {
            currentEnergy++;
        }
    }

    /**
     * reduces the ship's cooldown
     */

    public void decreaseCoolDown() {

        if (currCoolDown > 0) {
            currCoolDown--;
        }
    }

    /**
     * Turns on the shield
     */

    public void setShieldOn() {
        shieldIsOn = true;
    }

    /**
     * Turns off the shield
     */

    public void setShieldoff() {
        shieldIsOn = false;
    }

    /**
     * Gets the state of the shield
     * @return returns the state of the shield: true if on, false if not
     */

    public boolean getShield() {
        return shieldIsOn;
    }

    /**
     * General helper method to know in which direction the closest ship is
     * @param game the game object to which this ship belongs.
     * @return 1 if ship needs to turn left, -1 for right and 0 otherwise
     */

    public int directionToClosestShip(SpaceWars game) {
        double angleToClosestShip = this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics());
        if (angleToClosestShip < 0) {
            return -1;
        }
        if (angleToClosestShip >0) {
            return 1;
        }
        return 0;
    }

}
