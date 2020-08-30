package template.visitor;

import java.io.Serializable;

public abstract class City implements Serializable{
    public String name;


    public City(String city){
        name=city;
    }

    public City(City city){
        name=city.name;
    }

    public void accept(Visitor visitor) {

    }
}
