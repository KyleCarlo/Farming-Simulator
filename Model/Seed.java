package Model;

/**
 * Seed is a class that contains all the information about a seed.
 */
public class Seed {
    protected String name;
    protected String croptype;
    protected int harvestTime;
    protected int waterNeeds;
    protected int waterBonus;
    protected int fertilizerNeeds;
    protected int fertilizerBonus;
    protected int[] prodRange = new int[2];
    protected int seedCost;
    protected int sellPrice;
    protected double expGain;

    /**
     * Constructor for the Seed class. Sets the default values for the seed based on
     * the given name.
     * 
     * @param name the name of the seed
     */
    public Seed(String name) {
        this.name = name;
        switch (this.name) {
            case "Turnip":
                this.croptype = "root crop";
                this.harvestTime = 2;
                this.waterNeeds = 1;
                this.waterBonus = 2;
                this.fertilizerNeeds = 0;
                this.fertilizerBonus = 1;
                this.prodRange[0] = 1;
                this.prodRange[1] = 2;
                this.seedCost = 5;
                this.sellPrice = 6;
                this.expGain = 5;
                break;

            case "Carrot":
                this.croptype = "root crop";
                this.harvestTime = 3;
                this.waterNeeds = 1;
                this.waterBonus = 2;
                this.fertilizerNeeds = 0;
                this.fertilizerBonus = 1;
                this.prodRange[0] = 1;
                this.prodRange[1] = 2;
                this.seedCost = 10;
                this.sellPrice = 9;
                this.expGain = 7.5;
                break;

            case "Potato":
                this.croptype = "root crop";
                this.harvestTime = 5;
                this.waterNeeds = 3;
                this.waterBonus = 4;
                this.fertilizerNeeds = 1;
                this.fertilizerBonus = 2;
                this.prodRange[0] = 1;
                this.prodRange[1] = 10;
                this.seedCost = 20;
                this.sellPrice = 3;
                this.expGain = 12.5;
                break;

            case "Rose":
                this.croptype = "flower";
                this.harvestTime = 1;
                this.waterNeeds = 1;
                this.waterBonus = 2;
                this.fertilizerNeeds = 0;
                this.fertilizerBonus = 1;
                this.prodRange[0] = 1;
                this.prodRange[1] = 1;
                this.seedCost = 5;
                this.sellPrice = 5;
                this.expGain = 2.5;
                break;

            case "Tulips":
                this.croptype = "flower";
                this.harvestTime = 2;
                this.waterNeeds = 2;
                this.waterBonus = 3;
                this.fertilizerNeeds = 0;
                this.fertilizerBonus = 1;
                this.prodRange[0] = 1;
                this.prodRange[1] = 1;
                this.seedCost = 10;
                this.sellPrice = 9;
                this.expGain = 5;
                break;

            case "Sunflower":
                this.croptype = "flower";
                this.harvestTime = 3;
                this.waterNeeds = 2;
                this.waterBonus = 3;
                this.fertilizerNeeds = 1;
                this.fertilizerBonus = 2;
                this.prodRange[0] = 1;
                this.prodRange[1] = 1;
                this.seedCost = 20;
                this.sellPrice = 19;
                this.expGain = 7.5;
                break;

            case "Mango":
                this.croptype = "fruit tree";
                this.harvestTime = 10;
                this.waterNeeds = 7;
                this.waterBonus = 7;
                this.fertilizerNeeds = 4;
                this.fertilizerBonus = 4;
                this.prodRange[0] = 5;
                this.prodRange[1] = 15;
                this.seedCost = 100;
                this.sellPrice = 8;
                this.expGain = 25;
                break;

            case "Apple":
                this.croptype = "fruit tree";
                this.harvestTime = 10;
                this.waterNeeds = 7;
                this.waterBonus = 7;
                this.fertilizerNeeds = 5;
                this.fertilizerBonus = 5;
                this.prodRange[0] = 10;
                this.prodRange[1] = 15;
                this.seedCost = 200;
                this.sellPrice = 5;
                this.expGain = 25;
                break;
        }
    }

    /**
     * Sets the name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the croptype
     * 
     * @return croptype
     */
    public String getCropType() {
        return this.croptype;
    }

    /**
     * Gets the harvest time
     * 
     * @return harvestTime
     */
    public int getHarvestTime() {
        return this.harvestTime;
    }

    /**
     * Gets the water needs
     * 
     * @return waterNeeds
     */
    public int getWaterNeeds() {
        return this.waterNeeds;
    }

    /**
     * Gets the water bonus
     * 
     * @return water bonus
     */
    public int getWaterBonus() {
        return this.waterBonus;
    }

    /**
     * Gets the fertilizer needs
     * 
     * @return fertilizerNeeds
     */
    public int getFertilizerNeeds() {
        return this.fertilizerNeeds;
    }

    /**
     * Gets the fertilizer limit for a crop
     * 
     * @return fertilizerBonus
     */
    public int getFertilizerBonus() {
        return this.fertilizerBonus;
    }

    /**
     * Gets the production range
     * 
     * @return int[] prodRange
     */
    public int[] getProdRange() {
        return this.prodRange;
    }

    /**
     * Gets the seed cost
     * 
     * @return seedCost
     */
    public int getSeedCost() {
        return this.seedCost;
    }

    /**
     * Gets the sell price
     * 
     * @return sellPrice
     */
    public int getSellPrice() {
        return this.sellPrice;
    }

    /**
     * Gets the experience gain
     * 
     * @return expGain
     */
    public double getExpGain() {
        return this.expGain;
    }
}
