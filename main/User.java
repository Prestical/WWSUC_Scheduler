import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class User {

    String name;
    Map<String,HashSet<String>> busyDays;

    
    User(String name){
        this.name = name.toLowerCase();
        busyDays = new HashMap<>();
    }

    public void setData(HashMap<String,HashSet<String>> data){
        // Gets from editPane
        if (busyDays.isEmpty()) {
            busyDays.putAll(data);
            return;
        }

        for (Map.Entry<String,HashSet<String>> entry : data.entrySet()) {
            if (busyDays.containsKey(entry.getKey())) {
                for (String str : entry.getValue()) {
                    busyDays.get(entry.getKey()).add(str);
                }
            }
            else{
                busyDays.put(entry.getKey(),entry.getValue());
            }   
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    @Override
    public String toString(){
        return name;
    }
}
