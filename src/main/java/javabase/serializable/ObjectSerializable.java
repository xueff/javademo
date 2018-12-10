package javabase.serializable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.vertx.core.json.JsonObject;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//java原生慢
class ObjectSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        long start =  System.currentTimeMillis();
        setSerializableObject();
        System.out.println("java原生序列化时间:" + (System.currentTimeMillis() - start) + " ms" );
        start =  System.currentTimeMillis();
        getSerializableObject();
        System.out.println("java原生反序列化时间:" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void setSerializableObject() throws IOException {

        FileOutputStream fo = new FileOutputStream("D:/file2.bin");

        ObjectOutputStream so = new ObjectOutputStream(fo);

        for (int i = 0; i < 100000; i++) {
            Map<String,Integer> map = new HashMap<String, Integer>(2);
            map.put("zhang0", i);
            map.put("zhang1", i);
            so.writeObject(new Simple("zhang"+i,(i+1),map));
        }
        so.flush();
        so.close();
    }

    public static void getSerializableObject(){
        FileInputStream fi;
        try {
            fi = new FileInputStream("D:/file2.bin");
            ObjectInputStream si = new ObjectInputStream(fi);

            Simple simple =null;
            while((simple=(Simple)si.readObject()) != null){
                //System.out.println(simple.getAge() + "  " + simple.getName());
            }
            fi.close();
            si.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

//
class KyroSerializable {

    public static void main(String[] args) throws IOException {
        long start =  System.currentTimeMillis();
        //setSerializableObject();
        //System.out.println("Kryo 序列化时间:" + (System.currentTimeMillis() - start) + " ms" );
        start =  System.currentTimeMillis();
        getSerializableObject();
        System.out.println("Kryo 反序列化时间:" + (System.currentTimeMillis() - start) + " ms");

    }

    public static void setSerializableObject() throws FileNotFoundException{

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.register(Simple.class);
        Output output = new Output(new FileOutputStream("D:/file1.bin"));
        for (int i = 0; i < 100000; i++) {
            Map<String,Integer> map = new HashMap<String, Integer>(2);
            map.put("zhang0", i);
            map.put("zhang1", i);
            kryo.writeObject(output, new Simple("zhang"+i,(i+1),map));
        }
        output.flush();
        output.close();
    }


    public static void getSerializableObject(){
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        Input input;
        try {
            input = new Input(new FileInputStream("D:/file1.bin"));
            Simple simple =null;
            while((simple=kryo.readObject(input, Simple.class)) != null){
                System.out.println(simple.getAge() + "  " + simple.getName() + "  " + simple.getMap().toString());
            }

            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(KryoException e){

        }
    }

}

//最快，，反序列化快10倍
class Json {

    public static void main(String[] args) throws IOException {
        long start =  System.currentTimeMillis();
        setJsonObject();
        System.out.println("json 序列化时间:" + (System.currentTimeMillis() - start) + " ms" );
        start =  System.currentTimeMillis();
        getJsonObject();
        System.out.println("json 反序列化时间:" + (System.currentTimeMillis() - start) + " ms");

    }

    public static void setJsonObject() throws FileNotFoundException{

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.register(JsonObject.class);
        Output output = new Output(new FileOutputStream("D:/file.bin"));
        for (int i = 0; i < 100000; i++) {
            JsonObject json = new JsonObject().put("name","zhangsan"+i).put("age",i+1).put("map",new JsonObject()
                            .put("zhang0", i)
                            .put("zhang1", i));
            kryo.writeObject(output, json);
        }
        output.flush();
        output.close();
    }


    public static void getJsonObject(){
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        Input input;
        try {
            input = new Input(new FileInputStream("D:/file.bin"));
            JsonObject json =null;
            while((json=kryo.readObject(input, JsonObject.class)) != null){
               // System.out.println(json.getString("name"));
            }

            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(KryoException e){

        }
    }

}
// class Simple  implements Serializable
//{
////    private static final long serialVersionUID = -4914434736682797743L;
//    private String name;
//    private int age;
//    private Map<String,Integer> map;
//    public Simple(){
//
//    }
//    public Simple(String name,int age,Map<String,Integer> map){
//        this.name = name;
//        this.age = age;
//        this.map = map;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public Map<String, Integer> getMap() {
//        return map;
//    }
//
//    public void setMap(Map<String, Integer> map) {
//        this.map = map;
//    }
//
//
//}
