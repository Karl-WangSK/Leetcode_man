package template.fanxing;

public class TestEm {
    public enum Em {
        AlWAYS,

        NEVER,

        NONE
    }

    public static void semantic(Em em){
        if (em == Em.AlWAYS){
            System.out.println("always");
        }
    }

    public static void main(String[] args) {
        semantic(Em.AlWAYS);
    }
}