package user.db;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;
import user.ws.bean.User;

import java.util.List;

/**
 * Created by denisazevedo on 09/01/2014.
 */
//@View(name = "test_view", file = "test_view.json")
public class UserRepository extends CouchDbRepositorySupport<User> {

    //TODO add Spring to the project
//    @Autowired
//    public UserRepository(@Qualifier("userDatabase") CouchDbConnector db) {
    public UserRepository(CouchDbConnector db) {
        super(User.class, db);
        initStandardDesignDocument();
    }

    @GenerateView
    public List<User> findByName(String name) {
        return queryView("by_name", name);
    }

    //TODO Save this view in a file
    @View(name = "by_name_prefix", map =
            "function(doc) {\n" +
            "    var prefix = doc['name'].match(/[A-Za-z0-9]+/g);\n" +
            "    if (prefix) {\n" +
            "        for(var pre in prefix) {\n" +
            "            emit(prefix[pre].toLowerCase(), doc);\n" + //TODO Should be 'doc' or 'null'?
            "        }\n" +
            "    }\n" +
            "}")
    public List<User> findByNamePrefix(String name) {
        return queryView("by_name_prefix", name.toLowerCase());
    }

//    @View(name = "by_id", map = "function(doc) { if (doc._id) { emit(doc._id, null) } }")
//    public List<User> findById(String id) {
//        return queryView("by_id", id);
//    }

//    @Override
//    @View( name="all", map = "function(doc) { if (doc.password) { emit(doc.name, null) } }")
//    public List<User> getAll() {
//        ViewQuery q = createQuery("all").descending(true);
//        return db.queryView(q, User.class);
//    }

}
