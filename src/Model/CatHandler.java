package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class CatHandler {
    private static ArrayList<String> breedlist = new ArrayList<>();
    private static int breednum;


    public CatHandler(){
        try{
            initializeBreedList();
        }catch(IOException ioe){
            System.out.println("Unexpected error, list could not be retrieved");
        }
    }


    // API code adapted from CPSC 210 edx page, which in turn was from http://zetcode.com/articles/javareadwebpage/
    // EFFECTS: Initializes a list of breeds with data taken from the API
    private void initializeBreedList() throws IOException {
        BufferedReader br = null;

        try {
            String apikey = "4654f6ca-0eb3-4f4d-b314-6e8623019cf6";
            String catapi = "https://api.thecatapi.com/v1/breeds?api_key=";
            String theURL = catapi + apikey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();



            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());


            }


            JSONParser parser = new JSONParser();


            try {
                Object obj = parser.parse(sb.toString());
                JSONArray array = (JSONArray) obj;

                breednum = array.size();

                for (int i = 0; i < array.size(); i++) {
                    JSONObject obj2 = (JSONObject) array.get(i);
                    breedlist.add(obj2.get("id").toString());
                }


            } catch (ParseException pe) {

                System.out.println("position: " + pe.getPosition());
                System.out.println(pe);
            }


        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    private String selectBreed(){
        Random rng = new Random();
        int no = rng.nextInt(67);
        return breedlist.get(no);
    }

    public  ArrayList<String> getBreedlist() {
        return breedlist;
    }
}
