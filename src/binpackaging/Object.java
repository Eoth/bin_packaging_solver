package binpackaging;

public class Object {

    public int weight ;
    public int color ;
    public int idObj;
    public Box box;

    public Object(){}

    public Object(int weight, int color, int idObj){
        this.weight = weight;
        this.color = color;
        this.idObj = idObj;

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getIdObj() {
        return idObj;
    }

    public void setIdObj(int idObj) {
        this.idObj = idObj;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "Boite{" +
                "weight=" + weight +
                ", color=" + color +
                ", idObj=" + idObj +
                '}';
    }
}
