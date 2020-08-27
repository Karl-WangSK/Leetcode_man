package template.visitor;

public class BeiJing implements City {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
