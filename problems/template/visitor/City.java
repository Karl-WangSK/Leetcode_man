package template.visitor;

import java.io.Serializable;

public interface  City {
    public String name;

    public void accept(Visitor visitor);
}
