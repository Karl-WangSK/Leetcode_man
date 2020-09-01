package template.visitor;

public class ShangHai extends City {

    public ShangHai(String name) {
        super(name);
    }

    public void accept(Visitor visitor) {
        BeiJing beiJing = new BeiJing("七里屯");
        visitor.visitShanghai(this, beiJing);
    }
}
