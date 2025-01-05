package Model;

import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.util.ArrayList;

/**
 * The `Crop` class represents a single crop in the game. It extends the `Seed`
 * class and implements the `Renderable` interface.
 */
public class Crop extends Seed implements Renderable {
    // fields for whether the crop is watered, fertilized, withered, its water and
    // fertilizer amount, and the days it has been planted
    private boolean isWatered;
    private boolean isFertilized;
    private boolean isWithered;
    private int waterAmount;
    private int prodAmount;
    private int fertilizerAmount;
    private int days;

    // reference to the farmer that planted the crop
    private Farmer farmerAttr;

    // list of images representing the different stages of the crop's growth
    private ArrayList<BufferedImage> images;

    /**
     * Constructs a `Crop` with the specified name and `Farmer` that planted it.
     *
     * @param name       the name of the `Crop`
     * @param farmerAttr the `Farmer` that planted the `Crop`
     */
    public Crop(String name, Farmer farmerAttr) {
        super(name);
        this.waterAmount = 0;
        this.fertilizerAmount = 0;
        this.days = 0;
        this.isWatered = false;
        this.isFertilized = false;
        this.isWithered = false;
        this.farmerAttr = farmerAttr;
        this.images = new ArrayList<BufferedImage>();
        this.setImages();
    }

    /**
     * Waters the `Crop` if it is not already watered and there is still water
     * available.
     */
    public void water() {
        if (this.waterAmount + 1 <= this.waterBonus + this.farmerAttr.getWaterBonusIncrease()
                && this.isWatered == false) {
            this.waterAmount++;
            isWatered = true;
        }
    }

    /**
     * Fertilizes the `Crop` if it is not already fertilized and there is still
     * fertilizer available.
     */
    public void fertilize() {
        if (this.fertilizerAmount + 1 <= this.fertilizerBonus + this.farmerAttr.getFertBonusIncrease()
                && this.isFertilized == false) {
            this.fertilizerAmount++;
            this.isFertilized = true;
        }
    }

    /**
     * Updates the farm to the next day. Resets the water and fertilizer status of
     * the crop
     */
    public void nextDay() {
        this.days++;
        this.isFertilized = false;
        this.isWatered = false;
        if (this.days > this.harvestTime || (this.days == this.harvestTime
                && (this.waterAmount < this.waterNeeds || this.fertilizerAmount < this.fertilizerNeeds))) {
            this.isWithered = true;
        }
    }

    /**
     * Sets the fertilizer identifier of the crop.
     */
    public void setIsFertilized(boolean isFertilized) {
        this.isFertilized = isFertilized;
    }

    /**
     * Sets the images needed for the crop.
     */
    public void setImages() {
        try {
            for (int i = 0; i <= this.harvestTime; i++) {
                this.images
                        .add(ImageIO.read(getClass().getResource("/resources/crops/" + this.name + "/" + i + ".png")));
            }
            images.add(ImageIO.read(getClass().getResource("/resources/crops/withered.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns whether the crop is harvestable.
     * 
     * @return true if the crop is harvestable, false otherwise
     */
    public boolean isHarvestable() {
        return this.days == this.harvestTime && this.waterAmount >= this.waterNeeds
                && this.fertilizerAmount >= this.fertilizerNeeds;
    }

    /**
     * Sets the production amount of the crop.
     */
    public void setProdAmount() {
        this.prodAmount = (int) (Math.random() * (this.prodRange[1] - this.prodRange[0] + 1)) + this.prodRange[0];
    }

    /**
     * Computes and gets the selling price of the crop.
     * 
     * @return the selling price of the crop
     */
    public double getSellingPrice() {
        double harvestTotal = this.prodAmount * (this.sellPrice + this.farmerAttr.getBonusEarnPerProd());
        double waterBonus = harvestTotal * 0.2 * (this.waterAmount - 1);
        double fertilizerBonus = harvestTotal * 0.5 * (this.fertilizerAmount);
        double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
        if (this.croptype == "flower") {
            finalHarvestPrice *= 1.1;
        }

        return finalHarvestPrice;
    }

    /**
     * Returns the water status for the day.
     * 
     * @return if crop is watered
     */
    public boolean isWatered() {
        return this.isWatered;
    }

    /**
     * Returns the fertilizer status for the day.
     * 
     * @return if crop is fertilized
     */
    public boolean isFertilized() {
        return this.isFertilized;
    }

    /**
     * Returns the withered status for the day.
     * 
     * @return if crop is withered
     */
    public boolean isWithered() {
        return this.isWithered;
    }

    /**
     * Returns the product amount of the crop.
     * 
     * @return the product amount of the crop
     */
    public int getProdAmount() {
        return this.prodAmount;
    }

    @Override // Override the Renderable interface
    public BufferedImage getDefaultImage(char condition) {
        if (this.days >= this.harvestTime && !isHarvestable()) {
            return this.images.get(this.images.size() - 1);
        }
        return this.images.get(this.days);
    }

    public BufferedImage getFrontImage(int num) {
        return null;
    }

    public BufferedImage getBackImage(int num) {
        return null;
    }

    public BufferedImage getLeftImage(int num) {
        return null;
    }

    public BufferedImage getRightImage(int num) {
        return null;
    }

    public String getDirection() {
        return null;
    }
}
