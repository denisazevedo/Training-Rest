package user.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

/**
 * Created by denisazevedo on 09/01/14.
 */
//@JsonIgnoreProperties({"_id", "_rev"})
//@JsonIgnoreProperties({"id", "revision"})
//@JsonIgnoreProperties({"_id", "_rev"})

//@JsonIgnoreProperties({"_rev"})
//@JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("_rev")
    private String revision;

//    @JsonProperty("_id")
//    private String email;

    private String name;
    private String password;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public User(String id, String name, String password) {
        this.id = id;
        //this.email = email;
        this.name = name;
        this.password = password;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

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

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

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
//                "email='" + email + '\'' +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}'
                +'[' + revision + ']';
    }

//    public String toJSON() {
//        XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
//        xstream.setMode(XStream.NO_REFERENCES);
//        xstream.alias("user", this.getClass());
//        return xstream.toXML(this);
//    }
}
