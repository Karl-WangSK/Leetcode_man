package template.Singleton;

public class SingletonHunger {
    private static SingletonHunger singleton=new SingletonHunger();

    private SingletonHunger(){}

    //Thread safe
    public static SingletonHunger getInstance(){
        return singleton;
    }

    public void show() {
        System.out.println("start");
    }


    public static void main(String[] args) {
        SingletonHunger instance = SingletonHunger.getInstance();
        instance.show();

    }



}

