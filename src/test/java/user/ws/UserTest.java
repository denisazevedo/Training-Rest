package user.ws;

import junit.framework.Assert;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import user.ws.bean.User;

/**
 * TODO Delete this class
 * Created by denisazevedo on 09/01/2014.
 */
public class UserTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testJSON() throws Exception {
        User user = new User("denis.azevedo@gmail.com", "Denis C de Azevedo", "1234");
        System.out.println(user);
//        System.out.println(user.toJSON());

        Assert.assertNotNull(user);
//        Assert.assertNotNull(user.toJSON());
    }

    @Test
    public void testCouchDB() throws Exception {
        HttpClient httpClient = new StdHttpClient.Builder()
//                .url("http://127.0.0.1:5984/users/_design/application/_view/by-email")
                .url("http://127.0.0.1:5984/users")
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector("users", dbInstance);

        db.createDatabaseIfNotExists();

//        User user = db.get(User.class, "denis");
        User user = db.get(User.class, "9c514ace98d619cf7cb02501bc0007e7");
        System.out.println(user);
    }
}
