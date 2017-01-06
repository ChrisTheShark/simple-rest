package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.Course;
import com.dyer.frameworks.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Simple data load utility uses a {@link CourseRepository} and a
 * {@link ReviewRepository} instance to load test data.
 */
@Component
public class DatabaseLoader implements ApplicationRunner {

    private final CourseRepository courseRepository;

    @Autowired
    public DatabaseLoader(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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

        List<Course> courses = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzWords[i % buzzWords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www.google.com");
                    c.addReview(new Review(c, i % 5, String.format("Moar %s please!!!",
                            buzzword)));
                    courses.add(c);
                });
        courseRepository.save(courses);
    }

}
