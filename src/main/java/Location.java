/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 *  A location is a simple object that denotes a grid location of the maze using the row and column of a grid cell.
 *
 * CONSTRUCTOR METHODS
 *	Location
 *		Creates a new location object by taking in the row number, column number, and previous location node.
 *
 * INSTANCE AND DATA VARIABLES
 * 	row
 * 		An integer representing the row of the location object.
 *  column
 *      An integer representing the column of the location object.
 *  previous
 *      A Location object of the previous location. This is used in the pathfinding algorithm.
 *
 * INSTANCE METHODS
 * 	setPrevious
 * 		Takes in a location object and set's the current location's previous location to that location.
 *  getPrevious
 * 		returns the previous location object
 *  getLocation
 * 		Takes in a direction integer a returns a new location object representing that new location.
 *  equals
 *      A method that compares one location to another location by comparing row and column numbers.
 *  getRow
 *      Returns the row of the location.
 *  getColumn
 *      Returns the column of the location.
 */

import Exceptions.InvalidLocationException;

public class Location {

    int row; // An integer representing the row of the location object
    int column; // An integer representing the column of the location object
    Location previous; // A Location object of the previous location, used in the pathfinding algorithm

    //
    // Creates a new location object by taking in the row number, column number, and previous location node
    //
    public Location(int row, int column, Location previous) {
        this.row = row;
        this.column = column;
        this.previous = previous;
    } //end Location

    //
    // Takes in a location object and sets the current location's previous location to that location
    //
    public void setPrevious(Location previous) {
        this.previous = previous;
    } //end setPrevious

    //
    // Returns the previous location object
    //
    public Location getPrevious() {
        return previous;
    } //end getPrevious

    //
    // Takes in a direction integer and returns a new location object representing that new location
    //
    public Location getLocation(int direction) throws InvalidLocationException {
        switch (direction) {
            case 0:
                return new Location(row - 1, column, null); // North
            case 1:
                return new Location(row + 1, column, null); // South
            case 2:
                return new Location(row, column + 1, null); // East
            case 3:
                return new Location(row, column - 1, null); // West
        }

        // Invalid location
        throw new InvalidLocationException("You did not provide a valid direction");
    } //end getLocation

    //
    // Compares one location to another location by comparing row and column numbers
    //
    public boolean equals(Object item) {
        Location location;
        if (item instanceof Location) {
            location = (Location) item;
            return (location.row == row) && (location.column == column);
        } else {
            return false;
        }
    } //end equals

    //
    // Returns the row of the location
    //
    public int getRow() {
        return row;
    } //end getRow

    //
    // Returns the column of the location
    //
    public int getColumn() {
        return column;
    } //end getColumn
} //end Location