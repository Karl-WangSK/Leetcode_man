package template.visitor;

public class WenZhou extends City {
    protected String name="温州";

    public void accept(Visitor visitor) {
        visitor.visitWenzhou(this,null);
    }
}
