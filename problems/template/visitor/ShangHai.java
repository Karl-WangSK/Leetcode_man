package template.visitor;

public class ShangHai extends City {
    private BeiJing beiJing;

    public ShangHai(String city){
        super(city);
        beiJing = new BeiJing("北京");
    }

    public ShangHai(City city){
        super(city);
        beiJing = new BeiJing("北京");
    }
    public void accept(Visitor visitor) {
        visitor.visitBeijing(beiJing,name);
    }
}
