public class Cell {

    //class data fields
    static final int NORTH = 0;
    static final int SOUTH = 1;
    static final int EAST = 2;
    static final int WEST = 3;

    //class instance data fields
    private boolean neighbors[];
    private boolean onPath;

    public Cell(boolean North, boolean South, boolean East, boolean West) {
        onPath = false;
        neighbors = new boolean[4];
        neighbors[NORTH] = North;
        neighbors[SOUTH] = South;
        neighbors[EAST] = East;
        neighbors[WEST] = West;
    }

    public boolean getNorth() {
        return neighbors[NORTH];
    }

    public boolean getSouth() {
        return neighbors[SOUTH];
    }

    public boolean getEast() {
        return neighbors[EAST];
    }

    public boolean getWest() {
        return neighbors[WEST];
    }

    public boolean isOnPath() {
        return onPath;
    }

    public void setOnPath(boolean onPath) {
        this.onPath = onPath;
    }

    public void setNorth(boolean North) {
        neighbors[NORTH] = North;
    }

    public void setSouth(boolean South) {
        neighbors[SOUTH] = South;
    }

    public void setEast(boolean East) {
        neighbors[EAST] = East;
    }

    public void setWest(boolean West) {
        neighbors[WEST] = West;
    }

    public boolean[] getIsNeighbors() {
        return neighbors;
    }

    public String toString() {
        return "NORTH: " + getNorth() + "\n SOUTH: " + getSouth() + "\n EAST: " + getEast() + "\n WEST: " + getWest();
    }

    //clone method
}
