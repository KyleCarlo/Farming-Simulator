package Control;

import Model.Crop;
import Model.Farm;
import Model.Tile;

/**
 * Constructs a FarmControl object with a reference to a Farm and a KeyControl.
 *
 * @param farm       the Farm object to be updated
 * @param keyControl the KeyControl object used to detect user input
 */
public class FarmControl {
    private Farm farm;
    private KeyControl keyControl;

    /**
     * Constructs a FarmControl object with a reference to a Farm and a KeyControl.
     * 
     * @param farm
     * @param keyControl
     */
    public FarmControl(Farm farm, KeyControl keyControl) {
        this.farm = farm;
        this.keyControl = keyControl;
    }

    /**
     * Advances the game state by one day.
     *
     * This method is called by the game loop. It updates the day of the farm,
     * resets watered and blocked tiles, and
     * advances the day of each Crop object.
     */
    public void update() {
        if (keyControl.enterPressed) {
            this.farm.nextDay();
            for (int i = 0; i < this.farm.getRow(); i++) {
                for (int j = 0; j < this.farm.getColumn(); j++) {
                    Tile tile = this.farm.getTile(i, j);
                    tile.setWatered(false);
                    tile.setBlock(false);

                    if (tile.getCrop() != null) {
                        Crop crop = tile.getCrop();
                        crop.nextDay();
                    }
                }
            }
            keyControl.enterPressed = false;
        }
    }
}
