package textdecorators;


import java.io.IOException;

/**
     *  AbstractTextDecorator class - Defines the function signature that processes the input 
     * 
     * @author - Rohit Mahendra Dhuri
     * 
     */

public abstract class AbstractTextDecorator {
/**
 * Method processes the input file
 */

    public abstract void processInputDetails() throws IOException;
}