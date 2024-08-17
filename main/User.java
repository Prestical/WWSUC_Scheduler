import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    String name;
    Map<String,ArrayList<String>> busyDays;

    
    User(String name){
        this.name = name.toLowerCase();
        busyDays = new HashMap<>();
    }

    public void setData(HashMap<String,ArrayList<String>> data){
        // Gets from editPane
        if (busyDays.isEmpty()) {
            busyDays.putAll(data);
            return;
        }

        for (Map.Entry<String,ArrayList<String>> entry : data.entrySet()) {
            if (busyDays.containsKey(entry.getKey())) {
                for (String str : entry.getValue()) {
                    busyDays.get(entry.getKey()).add(str);
                }
            }
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
