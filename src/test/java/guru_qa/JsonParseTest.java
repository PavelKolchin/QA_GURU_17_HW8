package guru_qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru_qa.model.TVJson;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonParseTest {
    ClassLoader cl = JsonParseTest.class.getClassLoader();


    @Test
    void jsonParse() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        try (
                InputStream resource = cl.getResourceAsStream("homework.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            TVJson tvJson1 = mapper.readValue(reader, TVJson.class);

            assertThat(tvJson1.warranty).isEqualTo(12);
            assertThat(tvJson1.model.name).contains("Samsung");
            assertThat(tvJson1.model.series).contains("QE75QN900BUXCE");
            assertThat(tvJson1.price).isEqualTo(700000);
            assertThat(tvJson1.audioFormat[2]).contains("FLAC");

        }
    }
}
