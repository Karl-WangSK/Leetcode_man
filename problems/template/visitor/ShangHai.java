package template.visitor;

public class ShangHai implements City{
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
