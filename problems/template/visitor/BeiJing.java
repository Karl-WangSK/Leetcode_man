package template.visitor;

public class BeiJing extends City {

    public BeiJing(String city){
        super(city);
    }

    public void accept(Visitor visitor) {
        visitor.visitBeijing(this,name);
    }
}
