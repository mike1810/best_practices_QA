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
public class DataPoolNowInUpdate<T>{
    {   //new
        dataCollection = new ArrayList<>();
    }

    DataPoolNowInUpdate(String testParameterName , ITestContext testContext, Class<T> dataClass){
        fillNewDataPool(testParameterName, testContext, dataClass);
    }

    Collection<Object> dataCollection;

    public void processDataFile( String filePath, Class<T> dataClass ){

        //dataCollection = new ArrayList<>();    new

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat( dateFormat );
        try {
            T data = objectMapper.readValue( new File( filePath ), dataClass );
            dataCollection.add( data );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getData() {

        Object[][] dataToGet = new Object[ 1 ][ dataCollection.size() ];

        Iterator<Object> it = dataCollection.iterator();

        int i = 0;
        while( it.hasNext() ) {
            dataToGet[ 0 ][ i  ] = it.next();
            i++;
        }

        return dataToGet;
    }

    public void fillNewDataPool(String testParameterName , ITestContext testContext,  Class<T> dataClass){
        HashMap<String,String> parameters = new HashMap<>( testContext.getCurrentXmlTest().getAllParameters());
        this.processDataFile( parameters.get(testParameterName), dataClass );
    }
}
