package se.itello.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * FileHelper är en hjälp klass som kan göra filoperationer.
 */


public class FileHelper {

	public static List<String> getFileRows(String fileName) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        List<String> paymentRows = new ArrayList<>();
        
        boolean fileEnd = false;
        while (!fileEnd){
            String row = file.readLine();
            if(row == null)
                fileEnd = true;
            else
            	paymentRows.add(row);
        }
        file.close();
        return paymentRows;
    }
	
}
