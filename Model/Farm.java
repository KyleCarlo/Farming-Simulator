package Model;

/**
 * The `Farm` class represents a farm. Simulates the status of the farms
 */
public class Farm {
    // The number of rows and columns of the farm, the number of days that have
    // passed, the tiles of the farm
    private final int row = 5;
    private final int column = 10;
    private final Tile[][] tiles = new Tile[row][column];
    private int days = 1;

    /**
     * The constructor of the `Farm` class
     */
    public Farm() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }

    /**
     * Simulates the next day of the farm
     */
    public void nextDay() {
        this.days++;
    }

    /**
     * Gets the number of rows of the farm
     * 
     * @return The number of rows of the farm
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the number of columns of the farm
     * 
     * @return The number of columns of the farm
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Gets the tile at the specified row and column
     * 
     * @param row
     * @param column
     * @return the tile based on row and colum parameters
     */
    public Tile getTile(int row, int column) {
        return this.tiles[row][column];
    }

    /**
     * Gets the number of days that have passed
     * 
     * @return The number of days that have passed
     */
    public int getDays() {
        return this.days;
    }
}
