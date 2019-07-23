import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class dataPool<T>{

    Collection<T> dataCollection;

    public void processDataFile( String filePath, Class<T> dataClass ){

        dataCollection = new ArrayList<>();

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

        Object[][] dataToGet = new Object[ dataCollection.size() ][ 1 ];

        Iterator<T> it = dataCollection.iterator();

        int i = 0;
        while( it.hasNext() ) {
            dataToGet[ i ][ 0 ] = it.next();
            i++;
        }

        return dataToGet;
    }
}
