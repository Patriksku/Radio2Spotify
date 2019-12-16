package Rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleObject {

    //We need empty constructor also, for JAX to convert this object to .xml
    public SimpleObject(){
    }

    public SimpleObject(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
