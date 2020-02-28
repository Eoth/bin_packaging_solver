package binpackaging;

import java.util.ArrayList;
import java.util.List;

public class Object_Box_List {
    List<Object> ObjectList = new ArrayList<>();
    List<Box> TypeBoxList = new ArrayList<>();

    public Object_Box_List(){}

    public Object_Box_List(List<Object> objectList, List<Box> typeBoxList){
        this.ObjectList = objectList;
        this.TypeBoxList = typeBoxList;
    }

    public List<Object> getObjectList() {
        return ObjectList;
    }

    public void setObjectList(List<Object> objectList) {
        ObjectList = objectList;
    }

    public List<Box> getTypeBoxList() {
        return TypeBoxList;
    }

    public void setTypeBoxList(List<Box> typeBoxList) {
        TypeBoxList = typeBoxList;
    }
}
