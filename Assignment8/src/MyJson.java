import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class MyJson {
	public static void main(String[] args) throws IOException {
		File file = new File("data/out.txt");
		// ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
		// String s = getJsonString(vehicles);
		// System.out.println(s);
		// writeToFile(s, file.getParent());
		jsonFileToStringFile(file);
	}

	private static String[] header = null;

	private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
		ArrayList<Vehicle> res = new ArrayList<>();

		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);

		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			String[] tokens = line.split("~");
			if (header == null || header.length == 0) {
				header = tokens;
			} else {
				res.add(new Vehicle(tokens));
			}
		}

		br.close();
		reader.close();

		return res;
	}

	public static String getJsonString(ArrayList<Vehicle> vehicles) {
		if (vehicles == null || vehicles.size() == 0)
			return null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject res = new JsonObject();
		JsonArray detail = new JsonArray();
		System.out.println(vehicles.size());
		res.add(vehicles.get(0).webId, detail);
		for (Vehicle v : vehicles) {
			JsonObject temp = new JsonObject();
			temp.addProperty(header[0], v.id);
			temp.addProperty(header[2], v.category.toString());
			temp.addProperty(header[3], v.year.toString());
			temp.addProperty(header[4], v.make);
			temp.addProperty(header[5], v.model);
			temp.addProperty(header[6], v.trim);
			temp.addProperty(header[7], v.type);
			temp.addProperty(header[8], v.price);
			temp.addProperty(header[9], v.photo.toString());
			detail.add(temp);
		}

		return gson.toJson(res);
	}

	public static void writeToFile(String inputToWrite, String filePath) throws IOException {
		String path = filePath + "/out.txt";
		File f = new File(path);
		f.createNewFile();

		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(inputToWrite);

		bw.close();
		fw.close();
	}

	// ExtraCredit
	public static void jsonFileToStringFile(File file) throws IOException {
		// get json object from file
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(new FileReader(file));
		JsonObject json = jsonElement.getAsJsonObject();

		// find webid
		if (json == null)
			return;
		Iterator it = json.entrySet().iterator();
		String webId = ((Map.Entry<String, JsonArray>) it.next()).getKey();

		// get list of json objects and header of file
		JsonArray arr = json.getAsJsonArray(webId);
		Type listType = new TypeToken<List<JsonObject>>() {}.getType();
		List<JsonObject> detail = new Gson().fromJson(arr, listType);
		if (detail == null || detail.size() == 0)
			return;
		String[] header = new String[detail.get(0).size() + 1];
		String[] row = new String[detail.get(0).size() + 1];
		
		// print to file
		// print header
		String path = file.getParent() + "/original.txt";
		File f = new File(path);
		f.createNewFile();
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);

		// print content
		for (int j = 0; j < detail.size(); j++) {
			Iterator it2 = detail.get(j).entrySet().iterator();
			int k = 0;
			while (it2.hasNext()) {
				Map.Entry<String, JsonPrimitive> entry = (Map.Entry<String, JsonPrimitive>) it2.next();
				if (k == 1) {
					header[k] = "webId";
					row[k] = webId;
					k++;
				}
				if (j == 0) {
					header[k] = entry.getKey();
				}
				row[k] = entry.getValue().getAsString();
				k++;

			}
			if (j == 0) {
				bw.write(String.join("~", header) + "\n");
			}
			bw.write(String.join("~", row) + "\n");
		}

		bw.close();
		fw.close();

	}
}

class Vehicle {

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

	Vehicle(String[] arr) {
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

enum Category {
	NEW, USED, CERTIFIED;

	public static Category getCategory(String cat) {
		switch (cat) {
		case "used":
			return USED;
		case "new":
			return NEW;
		case "certified":
			return CERTIFIED;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case NEW:
			return "NEW";
		case USED:
			return "USED";
		case CERTIFIED:
			return "CERTIFIED";
		default:
			throw new IllegalArgumentException();
		}
	}
}