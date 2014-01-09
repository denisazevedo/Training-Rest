package user.ws;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by denisazevedo on 09/01/14.
 */
//@JsonIgnoreProperties({"_id", "_rev"})
//@JsonIgnoreProperties({"id", "revision"})
//@JsonIgnoreProperties({"_id"})
//@JsonIgnoreProperties({"_rev"})
//@JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
public class User_1 {

//    @JsonProperty("_id")
//    private String id;

    @JsonProperty("_rev")
    private String revision;

    @JsonProperty("_id")
    private String email;

    private String name;
    private String password;

//    public String get_id() {return this.email;}
    public String getId() {return this.email;}
    public void setId(String _id) {this.email = _id;}
    public void set_id(String _id) {this.email = _id;}

    public String get_rev() {return this.revision;} //TODO Precisa???
    public void set_rev(String _rev) {this.revision = _rev;}

    public User_1() {
    }

    public User_1(String email, String name, String password) {
//        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    @JsonProperty("_id")
    public String getEmail() {
        return email;
    }

    @JsonProperty("_id")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @JsonProperty("_id")
//    public String getId() {
//        return id;
//    }
//
//    @JsonProperty("_id")
//    public void setId(String id) {
//        this.id = id;
//    }

    @JsonProperty("_rev")
    public String getRevision() {
        return revision;
    }
    @JsonProperty("_rev")
    public void setRevision(String revision) {
        this.revision = revision;
    }

    @Override
    public String toString() {
        return "User {" +
                "email='" + email + '\'' +
//                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}'
                +'[' + revision + ']';
    }

    public String toJSON() {
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("user", this.getClass());
        return xstream.toXML(this);
    }
}
