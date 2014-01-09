package user.db;

import junit.framework.Assert;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.testng.annotations.*;
import user.ws.User;

import java.util.List;

/**
 * Created by denisazevedo on 09/01/14.
 */
public class UserDBTest {

    CouchDbConnector db;
    UserRepository repo;

    @BeforeClass
    public void setUp() throws Exception {
        System.out.println("Starting CouchDB connection...");

        HttpClient httpClient = new StdHttpClient.Builder()
//                .url("http://127.0.0.1:5984/users/_design/application/_view/by-email")
                .url("http://127.0.0.1:5984/users")
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        dbInstance.checkIfDbExists("users");
        db = new StdCouchDbConnector("users", dbInstance);

        db.createDatabaseIfNotExists();

        //Create the Repository
        repo = new UserRepository(db);
        System.out.println("Done...\n");
    }

    @AfterClass
    public void tearDown() throws Exception {
    }

//    @Test(enabled = false)
    @Test(dependsOnGroups = "update")
    public void testGetAll() throws Exception {
        List<User> users = repo.getAll();
        System.out.printf("%d users\n", users.size());
        for (User user : users) {
            System.out.println(user);
        }
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }

//    @Test(enabled = false)
    @Test
    public void testGetUser() throws Exception {
//        String id = "9c514ace98d619cf7cb02501bc0007e7";
        String id = "test@couchdb.com";
//        String id = "test@testng.com";
        User user = repo.get(id);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

//    @Test(enabled = false)
    @Test(groups = "add")
    public void testAddUser() throws Exception {
        User user = new User("test@testng.com", "TestNG Test User", "2222");
        repo.add(user);
        System.out.printf("%s added!", user.getId());
    }

//    @Test(dependsOnGroups = "add")
    @Test(dependsOnMethods = "testGetAll")
//    @Test(enabled = false)
    public void testRemoveUser() throws Exception {
        String id = "test@testng.com";

        User user = repo.get(id);
        Assert.assertNotNull("User not found: " + id, user);
        repo.remove(user);
        System.out.println(user);
    }

    @Test(groups = "update", dependsOnGroups = "add")
    public void testUpdateUser() throws Exception {
        User user = repo.get("test@testng.com");
        Assert.assertNotNull(user);
        System.out.println(user);

        user.setName("Name Updated");
        user.setPassword("2223");
        repo.update(user);
        System.out.printf("User %s updated.", user.getId());
    }

    @Test
    public void testContains() throws Exception {

        boolean contains = repo.contains("undefinedUser");
        assert contains == false : "Should not find the user";

        contains = repo.contains("denis.azevedo@gmail.com");
        assert contains : "Should find the user";
    }

    @Test
    public void testView() throws Exception {
        List<User> users = null;

//        users = view.getAll();
//        System.out.printf("%d user(s) found:\n", users.size());
//        assert users != null;
//        assert users.size() > 0;
//        for (User user : users) System.out.println(user);

//        users = repo.findByName("Denis");
//        assert users != null;
//        assert users.size() > 0;
//        System.out.printf("%d user(s) found:\n", users.size());
//        for (User user : users) System.out.println(user);

        users = repo.findByNamePrefix("Denis");
        assert users != null;
        assert users.size() > 0;
        System.out.printf("%d user(s) found:\n", users.size());
        for (User user : users) System.out.println(user);
    }

    @Test
    public void testQueryForObjects() throws Exception {

        ViewQuery query = new ViewQuery()
                .designDocId("_design/User")
//                .viewName("by_name")
//                .key("Denis");
                .viewName("by_name_prefix")
                .key("denis");

        List<User> users = db.queryView(query, User.class);

        assert users != null;
        assert users.size() > 0;
        System.out.printf("%d user(s) found:\n", users.size());
        for (User user : users) System.out.println(user);
    }

    @Test
    public void testScalarQuery() throws Exception {

        ViewQuery query = new ViewQuery()
                .designDocId("_design/User")
                .viewName("by_name_prefix")
                .key("denis");

        ViewResult result = db.queryView(query);
        assert result.getRows().size() > 0;

        for (ViewResult.Row row : result.getRows()) {
            String stringValue = row.getValue();
            int intValue = row.getValueAsInt();

            System.out.printf("String: %s\nint: %d\n\n", stringValue, intValue);
            assert row != null;
        }
    }

}
