package projekt2;

import java.io.*;
import java.util.HashMap;

public class IDrecorder<T extends Serializable> implements Serializable{
    private static final long serialVersionUID = 1L;
    private HashMap<String,Integer> map;
    private static String nazwapliku = "ID.ser";

    public IDrecorder(){
        this.map = new HashMap<>();
    }

    public void setNextid(Integer id,String klasa){
        map.put(klasa,id);
    }

    public HashMap<String,Integer> getMap(){
        return map;
    }

    public Integer getNextid(String nazwaklasy){
        return map.getOrDefault(nazwaklasy,1);
    }

    public static Integer pobierznextid(String klasa) throws IOException, ClassNotFoundException {
        IDrecorder kontener = deserializuj();
        return kontener.getNextid(klasa);
    }

    public static <U extends Serializable> void zapisznextid(Integer id,String klasa) throws IOException {
        IDrecorder<U> kontener;
        try {
            kontener = deserializuj();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            kontener = new IDrecorder<>();
        } catch (IOException e) {
            throw e;
        }
        kontener.setNextid(id,klasa);
        kontener.serializuj();
    }

    public void serializuj() throws IOException{
        FileOutputStream fileout = new FileOutputStream(nazwapliku);
        ObjectOutputStream out = new ObjectOutputStream(fileout);
        out.writeObject(this);
    }

    @SuppressWarnings("unchecked")
    public static<U extends Serializable> IDrecorder<U> deserializuj() throws IOException,ClassNotFoundException{
        try {
            FileInputStream filein = new FileInputStream(nazwapliku);
            ObjectInputStream in = new ObjectInputStream(filein);
            IDrecorder<U> kontener = (IDrecorder<U>) in.readObject();
            return kontener;
        }catch (FileNotFoundException e){
            return new IDrecorder<>();
        }catch (IOException | ClassNotFoundException e){
            throw e;
        }
    }
}
