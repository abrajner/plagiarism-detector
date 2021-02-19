package algorithms;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan({
        "com.abrajner.plagiarismdetector.plagiarismanalysis.binaryresults",
        "com.abrajner.plagiarismdetector.plagiarismanalysis.complexresults",
        "com.abrajner.plagiarismdetector.plagiarismanalysis.functioncomparator",
        "com.abrajner.plagiarismdetector.plagiarismanalysis"
})
public class TestConfig {
}
