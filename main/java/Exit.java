import java.util.Scanner;

/**
 * Exit program on user keypress
 */
public class Exit extends Thread {

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press any key + Enter to exit"+System.getProperty("line.separator"));
        //Set the boolean running flag to false to terminate execution
        if(scanner.next()!=null) {
            GenerateClients.running = false;
        }
    }
}