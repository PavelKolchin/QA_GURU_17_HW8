package guru_qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileParseTests {

    ClassLoader cl = guru_qa.FileParseTests.class.getClassLoader();

    @Test
    void pdfZipFileCheckTest() throws Exception {

        try (InputStream resource = cl.getResourceAsStream("ForJava.zip");
             ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdfcontent = new PDF(zis);
                    assertThat(pdfcontent.text).contains("Hello World2");
                } else if (entry.getName().contains(".xlsx")) {
                    XLS xlscontent = new XLS(zis);
                    assertThat(xlscontent.excel
                            .getSheetAt(0).getRow(1).getCell(0).getStringCellValue())
                            .contains("Hello World1");
                } else if (entry.getName().contains(".csv")) {
                        CSVReader reader = new CSVReader(new InputStreamReader(zis));
                        List<String[]> csvcontent = reader.readAll();
                        assertThat(csvcontent.get(1)[0])
                                .contains("Hello World1");
                }
            }
        }
    }

}