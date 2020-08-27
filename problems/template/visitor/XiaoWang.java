package template.visitor;

public class XiaoWang implements Visitor {

    public void visit(ShangHai shangHai) {
        System.out.println("小王来上海了");
    }

    public void visit(BeiJing beiJing) {
        System.out.println("小王来北京了");
    }

    public static void main(String[] args) {
        XiaoWang xiaoWang = new XiaoWang();
        ShangHai shangHai = new ShangHai();
        shangHai.accept(xiaoWang);

    }
}
