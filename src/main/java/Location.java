import Exceptions.InvalidLocationException;

public class Location {

    int row;
    int column;
    Location previous;

    public Location(int row, int column, Location previous) {
        this.row = row;
        this.column = column;
        this.previous = previous;
    }

    public void setPrevious(Location previous) {
        this.previous = previous;
    }

    public Location getPrevious() {
        return previous;
    }

    public Location getLocation(int direction) throws InvalidLocationException {
        switch (direction){

            //if possible, should probably throw an exception if the NESW direction does not exist in the maze
            case 0:
                return new Location(row-1, column, null);
            case 1:
                return new Location(row+1, column, null);
            case 2:
                return new Location(row, column+1, null);
            case 3:
                return new Location(row, column-1, null);
        }

        //invalid location
        throw new InvalidLocationException("You did not provide a valid direction");
    }

    public boolean equals(Object item){
        Location location;
        if (item instanceof Location) {
            location = (Location) item;
            return (location.row == row) && (location.column == column);
        } else {
            return false;
        }
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column){
        this.column = column;
    }

    //clone method
}