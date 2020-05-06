import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        URL path = Main.class.getResource("testfile.txt");
        File f = new File(path.getFile());


        try {
            mvpSoultion(parser(f));

            System.out.println("\n\n\n***ADVANCED TASK***\n\n\n");

            stretchGoals(parser(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap parser(File filename) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String buffer = "";

        while (reader.ready()) {
            buffer = buffer.concat(reader.readLine());
        }
        reader.close();
        buffer = buffer.toUpperCase();

        buffer = buffer.replaceAll("[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]", "");

        String[] strArr = buffer.split(" +");

        for (int i = 0; i < strArr.length; i++) {
            if (map.containsKey(strArr[i]))
                map.put(strArr[i], map.get(strArr[i]) + 1);
            else
                map.put(strArr[i], 1);
        }

        return map;
    }

    public static void mvpSoultion(HashMap<String, Integer> map) {

        int count = 0;
        while(count < 50 && !map.isEmpty()) {
            String key =  Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Common word " + key + " occurs " + map.get(key) + " times.");
            map.remove(key);
            count++;
        }
    }

    public static void stretchGoals(HashMap<String, Integer> map) {

        HashMap<String, Integer> buffer = new HashMap<>();

        int count = 0;
        while(count < 50 && !map.isEmpty()) {
            String key = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
            buffer.put(key, map.get(key));
            map.remove(key);
            count++;
        }

        while (!buffer.isEmpty()) {
            String key = Collections.min(buffer.entrySet(), Map.Entry.comparingByKey()).getKey();
            System.out.println("Common word " + key + " occurs " + buffer.get(key) + " times.");
            buffer.remove(key);
            count++;
        }
    }


}
