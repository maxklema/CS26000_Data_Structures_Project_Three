package Exceptions;

//A custom exception class for when the maze tries to access an invalid location
public class InvalidLocationException extends Exception {

    //
    // Creates a new InvalidLocationException object
    //
    public InvalidLocationException() {} //end InvalidLocationException

    //
    // Returns a new InvalidLocationException with a certain message
    //
    public InvalidLocationException(String message) {
        super(message);
    } //end InvalidLocationException
} //end InvalidLocationException
