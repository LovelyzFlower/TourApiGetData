
import java.io.*;
import java.util.ArrayList;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class CsvConverter {

    static ArrayList<String[]> getcsv() {
        BufferedReader br = null;
        String path = "contentsID.csv";
        String encoding = "UTF-8";
        String line;
        String cvsSplitBy = ",";
        String[] field=new String[2];
        ArrayList<String[]> list = new ArrayList<String[]>() {};
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
            while ((line = br.readLine()) != null) {
                field = line.split(cvsSplitBy);
                list.add(field);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}