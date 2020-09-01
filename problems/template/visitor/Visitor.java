package template.visitor;

public interface Visitor {

    public void visitShanghai(ShangHai shangHai,City city);

    public void visitBeijing(BeiJing beiJing,City city);

    public void visitWenzhou(WenZhou wenZhou,City city);
}
