package WeatherStationClient;

import java.io.*;
import java.util.Scanner;

public class DatLoader {
    private final String filename;
    public DatLoader(String file){
        this.filename = file;
        if (!new File(this.filename).exists()){
            try {new File(this.filename).createNewFile();} catch (Exception e){e.printStackTrace();}
        }
    }

    public String getContents() {
        try {
            File file = new File(this.filename);
            Scanner scanner = new Scanner(file);
            return scanner.next();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void writeContents(String contents){
        try{
            FileWriter fileWriter = new FileWriter(this.filename);
            fileWriter.write(contents);
            fileWriter.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
