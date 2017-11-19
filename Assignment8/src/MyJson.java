import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;

import org.json.simple.*;

public class MyJson {
	public static void main(String[] args) throws IOException{
	    File file = new File("your file path");
	    ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
	    String s = getJsonString(vehicles);
	    writeToFile(s, file.getParent());
	}
	private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
		ArrayList<Vehicle> res = new ArrayList<>();
		
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);

		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			String[] tokens = line.split("~");
			res.add(new Vehicle(tokens));	
		}
		
		br.close();
		reader.close();
		
		return res;
		
	}
	public static String getJsonString(ArrayList<Vehicle> vehicles) {
		JSONObject obj 
	}
	public static void writeToFile(String inputToWrite, String filePath) {
		
	}
}

class Vehicle{

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
       switch (cat){
           case "used": return USED;
           case "new": return NEW;
           case "certified": return CERTIFIED;
           default: throw new IllegalArgumentException();
       }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "NEW";
            case USED: return "USED";
            case CERTIFIED: return "CERTIFIED";
            default: throw new IllegalArgumentException();
        }
    }
}