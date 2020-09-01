package template.visitor;

public class ShangHai extends City {
    protected String name="上海";

    public void accept(Visitor visitor) {
        BeiJing beiJing = new BeiJing();
        visitor.visitShanghai(this, beiJing);
    }
}
