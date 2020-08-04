package textdecorators.driver;

public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

    public static void main(String[] args) {

        if ((args.length != REQUIRED_NUMBER_OF_CMDLINE_ARGS) || (args[0].equals("${input}")) || (args[1].equals("${mispelled}"))
                || (args[2].equals("${keywords}")) || (args[3].equals("${output}")) || (args[4].equals("${debug}"))) {
            System.err.println("Error: Incorrect number of arguments. Program accepts "+ REQUIRED_NUMBER_OF_CMDLINE_ARGS+" arguments.");
            System.exit(0);
        }

        System.out.println("In driver");
    }

}