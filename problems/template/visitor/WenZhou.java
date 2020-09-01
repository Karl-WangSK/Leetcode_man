package template.visitor;

public class WenZhou extends City {

    public WenZhou(String name) {
        super(name);
    }

    public void accept(Visitor visitor) {
        visitor.visitWenzhou(this,null);
    }
}
