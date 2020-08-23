package template.Singleton;

public class SingletonLazy {
    private static SingletonLazy singleton;

    private SingletonLazy(){}

    //Thread not safe
    public static SingletonLazy getUnsafeInstance(){
        if(singleton==null){
            singleton= new SingletonLazy();
        }
        return singleton;
    }
    //Thread safe
    public static synchronized SingletonLazy getSafeInstance(){
        if(singleton==null){
            singleton= new SingletonLazy();
        }
        return singleton;
    }


    public void show() {
        System.out.println("start");
    }

    public static void main(String[] args) {
        SingletonLazy instance = SingletonLazy.getSafeInstance();

        instance.show();


    }


}
