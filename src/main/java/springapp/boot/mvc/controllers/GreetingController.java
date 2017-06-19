package springapp.boot.mvc.controllers;

import com.mongodb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.web.bind.annotation.*;
import springapp.boot.mvc.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(maxAge = 3600)
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    public Greeting greet() {
        DB db = mongoDbFactory.getDb();
        System.out.println(db.getCollection("users-mock").count());
        return new Greeting(counter.incrementAndGet(), "Welcome!!");
    }


}
