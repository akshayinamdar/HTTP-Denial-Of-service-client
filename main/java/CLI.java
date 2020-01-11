import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Class to begin execution
 */
public class CLI {

    private static final Logger logger = Logger.getLogger(CLI.class.getName());
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter number of HTTP clients: ");
            int clientsNumber = scanner.nextInt();
            new Exit().start();
            //Threads to generate HTTP clients based on user input
            for(int i=0; i<clientsNumber; i++) {
                new GenerateClients(i).run();
            }
        } catch (InputMismatchException e) {
            logger.log(Level.SEVERE, "Incorrect input, Please enter a number");
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Shutdown");
        }
    }

}
