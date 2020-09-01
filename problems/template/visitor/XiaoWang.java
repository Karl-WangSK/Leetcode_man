package template.visitor;

public class XiaoWang implements Visitor {

    public void visit(City city){
        city.accept(this);
    }

    public void visitShanghai(ShangHai shangHai,City city) {
        System.out.println("小王从"+shangHai.name+"去"+city.name+"了");
        city.accept(this);
    }

    public void visitBeijing(BeiJing beiJing,City city) {
        System.out.println("小王从"+beiJing.name+"去"+city.name+"了");
    }

    public void visitWenzhou(WenZhou wenZhou, City city) {
        System.out.println("小王从"+city.name+"去"+wenZhou.name+"了");
    }

    public static void main(String[] args) {
        XiaoWang xiaoWang = new XiaoWang();
        ShangHai shangHai = new ShangHai("闵行");
        xiaoWang.visit(shangHai);
    }
}
