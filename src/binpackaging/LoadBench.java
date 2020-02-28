package binpackaging;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadBench {

    public LoadBench(){}

    public Object_Box_List LoadBenchFromFile(String file_name){

        List<Object> _objList = new ArrayList<>(); // liste des objets
        List<Box> _typeOfBox = new ArrayList<>();// listes des types de boîtes

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file_name));

            String line = reader.readLine(); // (====== number of box type et Box capacity =========)
            String[] boxData = line.split(" ");
            int nbBoxtype = Integer.parseInt(boxData[0]);
            for(int i = 0; i < nbBoxtype; i++){
                Box _box_ = new Box(i+1, Integer.parseInt(boxData[i+1]));
                _typeOfBox.add(_box_);
            }

            // read next line (====== number of colors line=========)
            line = reader.readLine();
            String[] nbColorsLine = line.split(" ");
            int nbColors = Integer.parseInt(nbColorsLine[0]);
            // read next line (====== number of object=========)
            line = reader.readLine();
            String[] nbObjectsLine = line.split(" ");
            int nbObjects = Integer.parseInt(nbObjectsLine[0]);

            for (int i =  0; i < nbObjects; i++){
                String _Objectdata = reader.readLine();
                if(_Objectdata.equals("13      58")) _Objectdata = "13\t58";
                String [] ObjectData = _Objectdata.split("\t");
                Object object = new Object(Integer.parseInt(ObjectData[0]), Integer.parseInt(ObjectData[1]), i);
                _objList.add(object);
            }


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object_Box_List object_box = new Object_Box_List(_objList, _typeOfBox);
        System.out.println("nombre de types de boîtes " + _typeOfBox.size());
        System.out.println("nombre d'objets " + _objList.size());
    return object_box;
    }
}
