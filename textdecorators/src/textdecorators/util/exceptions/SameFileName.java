package textdecorators.util.exceptions;

public class SameFileName extends Exception{

    /**
     * EmptyFileException class
     * 
     * @author Rohit Mahendra Dhuri
     * 
     */    

        /**
         * EmptyFileException constructor calling the parent class
         * object
         * 
         * @param s - The error message
         */

        public SameFileName(String s){
            super(s);
        }


        @Override
        public String toString() {
            return "Class: SameFileName, Data Members: [ ]";
        }


}