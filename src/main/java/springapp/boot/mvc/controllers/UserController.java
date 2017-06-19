package springapp.boot.mvc.controllers;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springapp.boot.mvc.model.User;
import springapp.boot.mvc.repos.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private MongoDbFactory mongoDbFactory;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        DB db = mongoDbFactory.getDb();
        DBCollection userCollection = db.getCollection("users-mock");
        //allUsers = userRepository.findAll();
        DBCursor cursor = userCollection.find();
        Iterator<DBObject> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            DBObject obj = iterator.next();
            User user = new User(String.valueOf(obj.get("first_name")), String.valueOf(obj.get("last_name")), String.valueOf(obj.get("email")), String.valueOf(obj.get("gender")), String.valueOf(obj.get("phone")), String.valueOf(obj.get("comment")));
            allUsers.add(user);
        }
        return allUsers;
    }


}
