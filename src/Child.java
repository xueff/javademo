

public class Child extends Father{

    /**
     * @param args
     */
    public static void main(String[] args) {
        Father fa= new Child();
        fa.fatherMethod();

    }
    @Override
    public void same() {

        System.out.println("same:子");
        
    }

}
