package user.ws;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Created by denisazevedo on 09/01/14.
 */
@Path("/user")
public class UserWS {

//    @GET
//    @Produces("application/json")
//    public String listUsers() {
//        return null;
//    }

    @GET
    @Produces("application/json")
    public String getMockUser() {
        User user = new User("john@smith.com", "John Smith", "1234");
//        return user.toJSON();
        return "Mock";
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/user");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}
