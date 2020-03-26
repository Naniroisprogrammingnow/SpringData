package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class FileUtilImpl implements FileUtil {
    @Override
    public String[] readFileContent(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Set<String> result = new LinkedHashSet<>();
        String line;
        while ((line = reader.readLine())!=null){
            if (!"".equals(line)){
                result.add(line);
            }
        }
        System.out.println();
        return result.toArray(String[]::new);
    }

    @Override
    public void write(String content, String filePath) throws IOException {
        Files.write(Paths.get(filePath), Collections.singleton(content),
                StandardCharsets.UTF_8);
    }
}
