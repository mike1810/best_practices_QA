import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@NoArgsConstructor
public class DataPoolNowInUpdate<T> {
    {
        dataHashMap = new HashMap<>();
    }

    DataPoolNowInUpdate(String testParameterName, ITestContext testContext, Class<T> dataClass, Object key) {
        fillNewDataPool(testParameterName, testContext, dataClass, key);
    }

    HashMap<Object, Object> dataHashMap;

    public void processDataFile(String filePath, Class<T> dataClass, Object key) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            T data = objectMapper.readValue(new File(filePath), dataClass);
            dataHashMap.put(key, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getData(Object neededKey) {
        Object[][] dataToGet = new Object[1][1];
        dataToGet[0][0] = dataHashMap.get(neededKey);
        return dataToGet;
    }

    public void fillNewDataPool(String testParameterName, ITestContext testContext, Class<T> dataClass, Object key) {
        HashMap<String, String> parameters = new HashMap<>(testContext.getCurrentXmlTest().getAllParameters());
        this.processDataFile(parameters.get(testParameterName), dataClass, key);
    }
}
