package Model;

/**
 * The Tool class represents a tool that can be used by a farmer to perform
 * various tasks in a farming game.
 * 
 * A tool has a name, a cost, and an experience gain.
 * 
 * The available tools are Plow, Watering Can, Fertilizer, Pickaxe, and Shovel.
 * 
 * The useTool method is used to apply the tool to a specific Tile on the farm.
 */
public class Tool {
    /**
     * Constructs a new Tool instance with the specified name.
     * 
     * @param name the name of the tool.
     */
    private String name;
    private double cost;
    private double expGain;

    public Tool(String name) {
        this.name = name;
        switch (this.name) {
            case "Plow":
                this.cost = 0;
                this.expGain = 0.5;
                break;
            case "Watering Can":
                this.cost = 0;
                this.expGain = 0.5;
                break;
            case "Fertilizer":
                this.cost = 10;
                this.expGain = 4;
                break;
            case "Pickaxe":
                this.cost = 50;
                this.expGain = 15;
                break;
            case "Shovel":
                this.cost = 7;
                this.expGain = 2;
                break;
        }
    }

    /**
     * Returns the name of the tool.
     * 
     * @return the name of the tool.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the cost of the tool.
     * 
     * @return the cost of the tool.
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Returns the experience gain of the tool.
     * 
     * @return the experience gain of the tool.
     */
    public double getExpGain() {
        return this.expGain;
    }

    /**
     * Applies the tool to the specified Tile on the farm.
     * 
     * @param tile
     * @param farmer
     */
    public void useTool(Tile tile, Farmer farmer) {
        boolean succeed = false;
        if (farmer.getObjectCoins() >= this.cost) {
            if (this.name.equals("Plow") && !tile.getIsRock() && !tile.getIsPlowed()) {
                tile.setPlowed(true);
                succeed = true;
            } else if (this.name.equals("Watering Can") && tile.getIsPlowed() && !tile.getIsWatered()) {
                tile.setWatered(true);
                if (tile.getCrop() != null) {
                    tile.getCrop().water();
                }
                succeed = true;
            } else if (this.name.equals("Fertilizer") && tile.getCrop() != null
                    && tile.getCrop().isFertilized() == false) {
                tile.getCrop().fertilize();
                succeed = true;
            } else if (this.name.equals("Pickaxe") && tile.getIsRock()) {
                tile.setRock(false);
                succeed = true;
            } else if (this.name.equals("Shovel")) {
                tile.setPlowed(false);
                tile.setCrop(null);
                succeed = true;
            }

            if (succeed) {
                farmer.subCoins(this.cost);
                farmer.addExp(this.expGain);
            }
        }
    }
}
