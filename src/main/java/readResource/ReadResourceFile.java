package readResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReadResourceFile {
    public static String read(String filePathInResources) throws IOException
    {
        ClassLoader classLoader = new ReadResourceFile().getClass().getClassLoader();

        File file = new File(classLoader.getResource(filePathInResources).getFile());

        //File is found
        System.out.println("File Found : " + file.exists());

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));

        return content;
    }
}
