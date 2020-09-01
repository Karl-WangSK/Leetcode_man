package template.visitor;

public class BeiJing extends City {
    protected String name="北京";

    public void accept(Visitor visitor) {
        WenZhou wenZhou = new WenZhou();
        visitor.visitBeijing(this, wenZhou);
    }
}
