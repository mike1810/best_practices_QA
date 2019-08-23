import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.util.*;

@NoArgsConstructor
class DataPool<T> {

    HashMap<Object, Collection<T>> dataHashMap;

    {
        dataHashMap = new HashMap<>();
    }

    DataPool(String testParameterName, ITestContext testContext, Class<T> dataClass, Object key) {
        addNewDataPool(testParameterName, testContext, dataClass, key);
    }


    private void processDataFile(String filePath, Class<T> dataClass, Object key) {

        Collection<T> keyCollection;
        ObjectMapper objectMapper = new ObjectMapper();
        T data;
        keyCollection =
                (dataHashMap.containsKey(key))?
                (dataHashMap.get(key)):
                        (Collection<T>) (new ArrayList<>());

        try {
            data = objectMapper.readValue(new File(filePath), dataClass);
            keyCollection.add(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataHashMap.put(key, keyCollection);

    }

    Object[][] getData(Object neededKey) {

        Object[][] dataToGet = new Object[0][0];
        if (dataHashMap.containsKey(neededKey)) {
            Collection<T> neededKeyCollection = dataHashMap.get(neededKey);

            dataToGet = new Object[neededKeyCollection.size()][1];

            Iterator<T> it = neededKeyCollection.iterator();
            int i = 0;
            while (it.hasNext()) {
                dataToGet[0][i] = it.next();
                i++;
            }
            return dataToGet;
        } else {
            return dataToGet;
        }
    }

    public void addNewDataPool(String testParameterName, ITestContext testContext, Class<T> dataClass, Object key) {
        HashMap<String, String> parameters = new HashMap<>(testContext.getCurrentXmlTest().getAllParameters());
        this.processDataFile(parameters.get(testParameterName), dataClass, key);
    }
}
