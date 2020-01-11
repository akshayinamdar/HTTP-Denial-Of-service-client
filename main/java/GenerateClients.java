import common.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generate HTTP requests for each client after a random period of time
 */
public class GenerateClients extends TimerTask {

    private static final Logger logger = Logger.getLogger(GenerateClients.class.getName());
    private final int clientId;

    //Constructor
    public GenerateClients(int clientId) {
        this.clientId = clientId;
    }

    public static boolean running = true; //public static flag to check if execution needs to be terminated

    public void run() {
        if (running) {
            Timer timer = new Timer();
            int delay = (1 + new Random().nextInt(Constants.UPPER_LIMIT_RANDOM_TIME_IN_SECONDS)) * Constants.SECOND_TO_MILLISECOND;
            timer.schedule(new GenerateClients(this.clientId), delay);
            try {
                URL url = new URL(Constants.URL);
                String query = Constants.QUERY+this.clientId;
                URLConnection connection = url.openConnection();
                //use post mode
                connection.setDoOutput(true);
                connection.setAllowUserInteraction(false);

                //send query
                PrintStream ps = new PrintStream(connection.getOutputStream());
                ps.print(query);
                ps.close();

                //get result
                BufferedReader br = new BufferedReader(new InputStreamReader(connection
                        .getInputStream()));
                String str = null;
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                }
                br.close();
            } catch (MalformedURLException e) {
                logger.log(Level.SEVERE, "URL is incorrect");
                running = false;
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Maximum connections exceeded for clientId "+this.clientId);
            } catch (Exception e) {
                running = false;
                logger.log(Level.SEVERE, "Exception encountered for clientId  "+this.clientId);
            }
        } else {
            logger.log(Level.INFO, "Stopping program.");
            System.exit(0);
        }
    }

}
