package projekt2;

import java.io.*;
import java.util.HashMap;

public class IOPliku<T extends Serializable> implements Serializable{
    private static final long serialVersionUID = 1L;
    private HashMap<Integer,T> map;

    public IOPliku(){
        this.map = new HashMap<>();
    }

    public void dodajdomapy(Integer id, T t){
        map.put(id,t);
    }

    public HashMap<Integer,T> getMap(){
        return map;
    }
    public static <U extends Serializable> void dodajdopliku(String nazwapliku,Integer id, U t) throws IOException,ClassNotFoundException{
        IOPliku<U> kontener;
        try {
            kontener = deserializuj(nazwapliku);
        }catch (FileNotFoundException | ClassNotFoundException e) {
            kontener = new IOPliku<>();
        }catch (IOException e) {
            throw e;
        }
        kontener.dodajdomapy(id, t);
        kontener.serializuj(nazwapliku);

    }

    public void serializuj(String nazwapliku) throws IOException {
        FileOutputStream Fileout = new FileOutputStream(nazwapliku);
        ObjectOutputStream out = new ObjectOutputStream(Fileout);
        out.writeObject(this);
    }

    @SuppressWarnings("unchecked")
    public static<U extends Serializable> IOPliku<U> deserializuj(String nazwapliku) throws IOException,ClassNotFoundException{
        try {

            FileInputStream filein = new FileInputStream(nazwapliku);
            ObjectInputStream in = new ObjectInputStream(filein);
            IOPliku<U> kontener = (IOPliku<U>) in.readObject();
            return kontener;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Nie znaleziono pliku");
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        }
    }
}
