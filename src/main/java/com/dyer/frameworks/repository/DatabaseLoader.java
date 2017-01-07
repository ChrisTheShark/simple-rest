package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.Course;
import com.dyer.frameworks.domain.Review;
import com.dyer.frameworks.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Simple data load utility uses a {@link CourseRepository} and a
 * {@link ReviewRepository} instance to load test data.
 */
@Component
public class DatabaseLoader implements ApplicationRunner {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] templates = {
                "%s for Beginners",
                "Up and Running with %s",
                "Under the Hood: %s",
                "%s Basics"
        };
        String[] buzzWords = {
                "Spring",
                "Java",
                "Groovy",
                "Hibernate"
        };
        List<User> students = Arrays.asList(
                new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
                new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
                new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
                new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
                new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
                new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
                new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
                new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
                new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
                new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
                new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
                new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"}),
                new User("cdyer", "Chris",  "Dyer", "password", new String[] {"ROLE_USER", "ROLE_ADMIN"})
        );
        userRepository.save(students);

        List<Course> courses = new ArrayList<>(100);
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzWords[i % buzzWords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www.google.com");
                    c.addReview(new Review(c,
                            students.get(i % students.size()),
                            (i % 5) + 1,
                            String.format("Moar %s please!!!",
                            buzzword)));
                    courses.add(c);
                });
        courseRepository.save(courses);
    }

}
