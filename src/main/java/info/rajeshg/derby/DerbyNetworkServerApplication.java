package info.rajeshg.derby;

import org.apache.derby.drda.NetworkServerControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Scanner;

@SpringBootApplication
public class DerbyNetworkServerApplication {

    @Value("#{host}")
    private static String env;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DerbyNetworkServerApplication.class, args);
        InetAddress address = InetAddress.getByName(args[0]);
        final NetworkServerControl server = new NetworkServerControl(address,NetworkServerControl.DEFAULT_PORTNUMBER);
        server.start(new PrintWriter(System.out));
        Runtime.getRuntime().addShutdownHook(shutdownHook(server) );
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Ctrl+C to shutdown");
            scanner.nextLine();
        }
//        server.shutdown();
    }
    private static Thread shutdownHook(final NetworkServerControl server){
        return new Thread() {
            @Override
            public void run() {
                try {
                    server.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
