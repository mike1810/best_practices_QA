import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class dataPool<T>{

    Collection<T> data;

    public void processDataFile( String filePath, Class<T> dataClass ){

        data = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat( dateFormat );
        try {
            T user = objectMapper.readValue( new File( filePath ), dataClass );
            data.add( user );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getData() {

        Object[][] dataToGet = new Object[ data.size() ][ 1 ];

        Iterator<T> it = data.iterator();

        int i = 0;
        while( it.hasNext() ) {
            dataToGet[ i ][ 0 ] = it.next();
            i++;
        }

        return dataToGet;
    }
}
