package algorithms;

import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.fileparser.ParsedFile;
import com.abrajner.plagiarismdetector.fileparser.ProgrammingLanguage;
import com.abrajner.plagiarismdetector.fileparser.TokenizedStringSerializer;
import com.abrajner.plagiarismdetector.plagiarismanalysis.binaryresults.BinaryResultInstructionAnalysis;
import com.abrajner.plagiarismdetector.plagiarismanalysis.complexresults.ComplexResultInstructionAnalysis;
import com.abrajner.plagiarismdetector.plagiarismanalysis.functioncomparator.AnalyzeEveryFunction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CTest extends AlgorithmsBaseTest {
    private ParsedFile firstFileParsed;

    private ParsedFile secondFileParsed;

    @Autowired
    BinaryResultInstructionAnalysis binaryResultInstructionAnalysis;

    @Autowired
    ComplexResultInstructionAnalysis complexResultAnalysis;

    @Autowired
    AnalyzeEveryFunction analyzeEveryFunction;

    @Before
    public void before() throws IOException {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final MultipartFile firstFile = new MockMultipartFile("cTest1.c", new FileInputStream(new File(classLoader.getResource("cTest1.c").getFile())));
        final MultipartFile secondFile = new MockMultipartFile("cTest2.c", new FileInputStream(new File(classLoader.getResource("cTest2.c").getFile())));
        this.firstFileParsed = TokenizedStringSerializer.parseFile(firstFile, ProgrammingLanguage.C);
        this.secondFileParsed = TokenizedStringSerializer.parseFile(secondFile, ProgrammingLanguage.C);
    }

    @Test
    public void firstAlgorithm(){
        final ReportEntity report = this.binaryResultInstructionAnalysis.performAnalysis(this.firstFileParsed, this.secondFileParsed);
        System.out.println(report.getCodeSimilarityPercentage());
    }

    @Test
    public void secondAlgorithm(){
        final ReportEntity report = this.complexResultAnalysis.performAnalysis(this.firstFileParsed, this.secondFileParsed);
        System.out.println(report.getCodeSimilarityPercentage());
    }

    @Test
    public void thirdAlgorithm(){
        final ReportEntity report = this.analyzeEveryFunction.performAnalysis(this.firstFileParsed, this.secondFileParsed);
        System.out.println(report.getCodeSimilarityPercentage());
    }
}
