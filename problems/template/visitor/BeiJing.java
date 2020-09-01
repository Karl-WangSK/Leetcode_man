package template.visitor;

public class BeiJing extends City {

    public BeiJing(String name) {
        super(name);
    }

    public void accept(Visitor visitor) {
        WenZhou wenZhou = new WenZhou("瓯海");
        visitor.visitBeijing(this, wenZhou);
    }
}
