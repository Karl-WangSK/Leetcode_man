package template.visitor;

import java.io.Serializable;

public abstract class City implements Serializable{
    public String name;

    public City(String name){
        this.name=name;
    }

    abstract public void accept(Visitor visitor);
}
