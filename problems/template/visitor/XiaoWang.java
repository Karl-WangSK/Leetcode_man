package template.visitor;

public class XiaoWang implements Visitor {

    public void visitShanghai(ShangHai shangHai,String name) {
        System.out.println("小王从"+name+"来"+shangHai.name+"了");
        shangHai.accept(this);

    }

    public void visitBeijing(BeiJing beiJing,String name) {
        System.out.println("小王从"+name+"来"+beiJing.name+"了");
    }

    public static void main(String[] args) {
        XiaoWang xiaoWang = new XiaoWang();
        xiaoWang.visitShanghai(new ShangHai("上海"),"温州");

    }
}
