public class Singleton {
    private static volatile Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance==null){
            synchronized(Singleton.class){
                if(instance==null) instance=new Singleton();
            }

        }
        return instance;
    }
    synchronized public void test(){
        System.out.println("test1");

        this.notify();

        System.out.println("test2");
    }
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        instance.test();
    }
}
