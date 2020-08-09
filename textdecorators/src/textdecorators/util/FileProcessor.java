package textdecorators.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

//import arrayVisitors.util.MyLogger.DebugLevel;

import java.nio.file.InvalidPathException;

public class FileProcessor {
    private BufferedReader reader;
    //private MyLogger ml;

    /**
     * Constructs a FileProcessor that can stream the contents of the provided input
     * file line by line.
     * 
     * @exception InvalidPathException  On invalid path string.
     * @exception SecurityException     On not having necessary read permissions to
     *                                  the input file.
     * @exception FileNotFoundException On input file not found.
     * @exception IOException           On any I/O errors while reading lines from
     *                                  input file.
     */
    public FileProcessor(String inputFilePath)
            throws InvalidPathException, SecurityException, FileNotFoundException, IOException {
         //ml = MyLogger.getInstance();
         //ml.writeMessage("FileProcessor parameterized constructor", MyLogger.DebugLevel.CO);

        if (!Files.exists(Paths.get(inputFilePath))) {
            throw new FileNotFoundException("invalid input file or input file in incorrect location");
        }

        reader = new BufferedReader(new FileReader(new File(inputFilePath)));
    }

    /**
     * Retrieves and returns the next line in the input file.
     *
     * @return String The next line read from the input file.
     * @exception IOException On error encountered when reading from input file.
     */
    public String poll() throws IOException {
        //ml.writeMessage("calling readline", DebugLevel.FILE_PROCESSOR);
        return reader.readLine();
    }

    /**
     * Close the buffered reader instance.
     *
     * @exception IOException On error encountered when closing the buffered reader.
     */
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public String toString() {
        return "Class: FileProcessor, Data Members: [ reader= " + reader + " ]";
    }
}