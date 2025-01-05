package Control;

import Model.Farmer;
import Model.Farm;
import Model.Seed;

/**
 * The `InfoControl` class represents the information control. It generates the
 * information that will be displayed on the information panel.
 */
public class InfoControl {
    private Farmer farmer;
    private Farm farm;
    private String genInformation;
    private String cropInformation;
    private String otherInformation;

    /**
     * Constructor for InfoControl
     * 
     * @param farmer
     * @param farm
     */
    public InfoControl(Farmer farmer, Farm farm) {
        this.farmer = farmer;
        this.farm = farm;
        this.setCropInfo(this.farmer.getSeed().getName());
    }

    /**
     * Sets the crop information
     * 
     * @param cropName
     */
    public void setCropInfo(String cropName) {
        String tileNeeded = "1x1";
        Seed crop = new Seed(cropName);
        this.cropInformation = "\n    Select Seed [ 1 - 8 ]  |  Plant Seed [ 0 ]\n" +
                "\n    " + cropName.toUpperCase() + ":\n" +
                "    Grow Time: " + crop.getHarvestTime() + " days \n" +
                "    Water Amount: " + crop.getWaterNeeds() + " - " + crop.getWaterBonus() + "\n" +
                "    Fertilizer Amount: " + crop.getFertilizerNeeds() + " - " + crop.getFertilizerBonus() + "\n" +
                "    Product Amount: " + crop.getProdRange()[0] + " - " + crop.getProdRange()[1] + "\n" +
                "    Seed Cost: " + crop.getSeedCost() + "\n" +
                "    Tile Needed: ";

        if (crop.getCropType().equals("fruit tree")) {
            tileNeeded = "3x3 in first day";
        }

        this.cropInformation += tileNeeded + "\n\n";
    }

    /**
     * Sets the general information
     */
    public void setGenInfo() {
        this.genInformation = "Farmer: " + this.farmer.getName() + " | Money: " + this.farmer.getObjectCoins()
                + " | Exp: "
                + this.farmer.getExp() + " | Day: " + this.farm.getDays();
        this.genInformation = " \n\n    Farmer Type: " + this.farmer.getFarmerType() + "\n" +
                "    Level : " + this.farmer.getLevel() + "\n" +
                "    Exp: " + this.farmer.getExp() + "\n" +
                "    Coins: " + this.farmer.getObjectCoins() + "\n" +
                "    Day: " + this.farm.getDays() + "\n\n\n" +
                "    Tools: \n" +
                "    Press J to use Plow \n" +
                "    Press K to use Watering Can \n" +
                "    Press L to use Fertilizer \n" +
                "    Press N to use Pickaxe \n" +
                "    Press M to use Shovel \n\n";
    }

    /**
     * Sets the other informations needed
     */
    public void setOtherInfo() {
        int countCrop = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.farm.getTile(i, j).getCrop() != null) {
                    countCrop++;
                }
            }
        }
        if (this.farmer.getIsHarvesting()) {
            double[] latestEarnings = this.farmer.getLatestEarnings();
            this.otherInformation = "\n    You earned " + latestEarnings[0] + " coins and "
                    + latestEarnings[1] + " exp from harvesting.";
            farmer.setIsHarvesting(false);
        } else if (this.farmer.getObjectCoins() < 5 && countCrop == 0) {
            this.otherInformation = "\n    GAME OVER";
        } else if (this.farmer.getLevel() >= 5 && this.farmer.getFarmerType().equals("farmer")
                && this.farmer.getObjectCoins() >= 200
                && (!this.farmer.getFarmerType().equals("registered")
                        || !this.farmer.getFarmerType().equals("distinguished")
                        || !this.farmer.getFarmerType().equals("legendary"))) {
            this.otherInformation = "\n    You can upgrade! Press U to upgrade.";
        } else if (this.farmer.getLevel() >= 10 && this.farmer.getFarmerType().equals("registered")
                && this.farmer.getObjectCoins() >= 300
                && (!this.farmer.getFarmerType().equals("farmer")
                        || !this.farmer.getFarmerType().equals("distinguished")
                        || !this.farmer.getFarmerType().equals("legendary"))) {
            this.otherInformation = "\n    You can upgrade! Press U to upgrade.";
        } else if (this.farmer.getLevel() >= 15 && this.farmer.getFarmerType().equals("distinguished") &&
                (!this.farmer.getFarmerType().equals("farmer")
                        || !this.farmer.getFarmerType().equals("registered")
                        || !this.farmer.getFarmerType().equals("legendary"))) {
            this.otherInformation = "\n    You can upgrade! Press U to upgrade.";
        }
    }

    /**
     * Gets the general information
     * 
     * @return genInformation
     */
    public String getGenInfo() {
        return this.genInformation;
    }

    /**
     * Gets the crop information
     * 
     * @return cropInformation
     */
    public String getCropInfo() {
        return this.cropInformation;
    }

    /**
     * Gets the other information
     * 
     * @return otherInformation
     */
    public String getOtherInfo() {
        return this.otherInformation;
    }
}
