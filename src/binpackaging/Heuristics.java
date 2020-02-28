package binpackaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Heuristics {

   /** Permute 2 objets entre 2 boites. Attention ! les contraintes de poids
      et de couleur doivent être toujours respectées. */
    public void swapObject(Box box1, Object obj1, Box box2, Object obj2){
        box1.deleteObject(obj1.idObj);
        box2.deleteObject(obj2.idObj);
        if(!box1.addObject(obj2)){
            box1.addObject(obj1);
            box2.addObject(obj2);
        }
        else if(!box2.addObject(obj1)){
            box1.addObject(obj2);
            box1.addObject(obj1);
            box2.addObject(obj2);
        }
    }

    /** Retourne l'objet de poids le plus élevé parmi la liste d'objets. */
    public Object mostHeavy(List<Object> ObjList){
        Object selectObj = ObjList.get(0);
        for (Object obj : ObjList){
            if(obj.weight > selectObj.weight) selectObj = obj;
        }
        return selectObj;
    }

    /** boite dont sa capacité est la plus proche du poids donné */
    public Box nearToCapacity(int weigth, List<Box> boxList){
        Box _box = null;
        int rest = 200;
        for(Box box : boxList){
            if(box.capacity - weigth >= 0 && box.capacity - weigth < rest){
                _box = box;
                rest = box.capacity - weigth;
            }
        }
        return _box;
    }

    /** Trie dans l'ordre décroissant selon leurs poids une liste d'objets. */
    public void sortByWeigth(List<Object> ObjList){
        Collections.sort(ObjList, new Comparator<Object>() {
            public int compare(Object obj1, Object obj2) {
                return obj1.getWeight() - obj2.getWeight();
            }
        });
        Collections.reverse(ObjList);
    }

    /** Trie dans l'ordre décroissant selon leurs poids une liste d'objets. */
    public void sortByCapacity(List<Box> boxList){
        Collections.sort(boxList, new Comparator<Box>() {
            public int compare(Box box1, Box box2) {
                return box1.getCapacity() - box2.getCapacity();
            }
        });
        Collections.reverse(boxList);
    }

    /** Trie les objets de manière aléatoire (au hasard). */
    public void randomSort(List<Object> ObjList){
        Collections.shuffle(ObjList);
    }

    /** Algorithme bête
    * On prend la bîte la plus grande et on met un objet on ferme
    * et on recommence avec d'autre objet */
    public int foolishHeuristic(List<Object> _objList, List<Box> _boxList){
        List<Object> objList = new ArrayList<>(_objList); // copie de liste des objets initiale
        List<Box> boxList =  new ArrayList<>(_boxList);

        List<Box> allBoxUse = new ArrayList<>();

        sortByWeigth(objList);
        sortByCapacity(boxList);
        int i = 1; // index boîte
        //System.out.println(" size départ "+boxList.size()+" "+boxList.get(1).getObjList().get(0));
        for(Object object : objList){
            for (Box _box : boxList){
                Box box = new Box(i,_box.capacity);
                //System.out.println( "obj n°"+ object.idObj+" poids "+ object.weight+" size départ "+box.getObjList().size()+" "+box.getObjList().get(0));
                if( box.addObject(object)){
                    allBoxUse.add(box);
                    i++;
                    break;
                }
            }
        }

        int capResidual = 0;
        for(Box _box : allBoxUse){
            capResidual += _box.capResidual();
        }
        // System.out.println( "nb boîtes : " + allBoxUse.size());
        return capResidual;
    }

    /** Algorithme bête Améliorer
     * On prend la bîte la plus grande et on met un objet on ferme
     * et on recommence avec d'autre objet */
    public int randomFoolishHeuristic(List<Object> _objList, List<Box> _boxList){
        List<Object> objList = new ArrayList<>(_objList); // copie de liste des objets initiale
        List<Box> boxList =  new ArrayList<>(_boxList);

        List<Box> allBoxUse = new ArrayList<>();

        randomSort(objList);
        int i = 1; // index boîte
        //System.out.println(" size départ "+boxList.size()+" "+boxList.get(1).getObjList().get(0));
        for(Object object : objList){
            for (Box _box : boxList){
                Box box = new Box(i,_box.capacity);
                //System.out.println( "obj n°"+ object.idObj+" poids "+ object.weight+" size départ "+box.getObjList().size()+" "+box.getObjList().get(0));
                if( box.addObject(object)){
                    allBoxUse.add(box);
                    i++;
                    break;
                }
            }
        }

        int capResidual = 0;
        for(Box _box : allBoxUse){
            capResidual += _box.capResidual();
        }
        //System.out.println( "nb boîtes : " + allBoxUse.size());
        return capResidual;
    }

    public int improoveFoolishHeuristic(List<Object> _objList, List<Box> _boxList){
        List<Object> objList = new ArrayList<>(_objList); // copie de liste des objets initiale
        List<Box> boxList =  new ArrayList<>(_boxList);

        List<Box> allBoxUse = new ArrayList<>();

        int i = 1; // index boîte
        sortByWeigth(objList);
        sortByCapacity(boxList);
        while(!objList.isEmpty()){
            //System.out.println(" size départ "+boxList.size()+" "+boxList.get(1).getObjList().get(0));
            for(Box _box : boxList){
                Box box = new Box(i,_box.capacity);
                for (Object object : objList){
                    box.addObject(object);
                    if( box.capResidual() == 0)break;
                }
                if(box.objList.size() > 0) {allBoxUse.add(box); i++;};
                // supprimer les objets déja ajouter à la liste
                for (Box box_ : allBoxUse){for(Object obj : box_.objList){objList.remove(obj); } }
            }
            // supprimer les objets déja ajouter à la liste
            for (Box box : allBoxUse){
                for(Object obj : box.objList){
                    objList.remove(obj);
                }
            }

        }


        int capResidual = 0;
        for(Box _box : allBoxUse){
            capResidual += _box.capResidual();
        }
       // System.out.println( "nb boîtes : " + allBoxUse.size());
        return capResidual;
    }

    /** Algorithme bête */
    public int sumObjectAdd(List<Object> objList, List<Box> _boxList){

        List<Object> _objList = new ArrayList<>(objList); // copie de liste des objets initiale
        List<Box> boxList =  new ArrayList<>(_boxList);
        List<Box> allBoxUse = new ArrayList<>();

        int i = 0; //index pour créer une boite
        // ajout objet dans boite si poids objet = capacité de la boite
        for(Object obj : _objList){
            for (Box box : boxList){
                // ajout objet dans boite si poids objet = capacité de la boite
                if(obj.weight == box.capacity){
                    Box _box_ = new Box(i, box.capacity);
                     _box_.addObject(obj);
                    allBoxUse.add(_box_);
                    break;}
            }
        }

        // supprimer les objets déja ajouter à la liste
        for (Box box : allBoxUse){
            for(Object obj : box.objList){
                _objList.remove(obj);
            }
        }

        while(!_objList.isEmpty()){
            int weigthSum = 0; // somme des poids des objets restants
            for(Object obj : _objList){
                weigthSum += obj.weight;
            }
            Box nearBox = nearToCapacity(weigthSum, boxList);
            if(nearBox != null ){
                Box __box__ = new Box(i, nearBox.capacity);
                for(Object obj : _objList){
                    __box__.addObject(obj);
                }
                allBoxUse.add(__box__);

                // supprimer les objets déja ajouter à la liste
                for (Box box : allBoxUse){
                    for(Object obj : box.objList){
                        _objList.remove(obj);
                    }
                }
            }
            else{
                // Algo improoveFoolishHeuristic
                for(Box _box : boxList){
                    Box box = new Box(i,_box.capacity);
                    for (Object object : _objList){
                        box.addObject(object);
                        if( box.capResidual() == 0)break;
                    }
                    if(box.objList.size() > 0) {allBoxUse.add(box); i++;};
                    // supprimer les objets déja ajouter à la liste
                    for (Box box_ : allBoxUse){for(Object obj : box_.objList){_objList.remove(obj); } }
                }
                // supprimer les objets déja ajouter à la liste
                for (Box box : allBoxUse){
                    for(Object obj : box.objList){
                        _objList.remove(obj);
                    }
                }
            }


        }

        int capResidual = 0;
        for(Box _box : allBoxUse){
            capResidual += _box.capResidual();
        }
        //System.out.println( "nb boîtes : " + allBoxUse.size());
        return capResidual;
    }

    /* Même heuristique que précédemment sauf que l'étape de placer
    les objets de taille égale à un capacité d'une boite ; a été retirée
    et les objets sont triés selon leur poids par ordre décroissant.* */
    public int foolishHeuristicWithSort(List<Object> objList, List<Box> _boxList) {
        List<Object> _objList =  new ArrayList<>(objList); // copie de liste des objets initiale
        sortByWeigth(_objList);

        List<Box> boxList =  new ArrayList<>(_boxList);
        List<Box> allBoxUse = new ArrayList<>();

        while(!_objList.isEmpty()){
            int weigthSum = 0; // somme des poids des objets restants
            for(Object obj : _objList){
                weigthSum += obj.weight;
            }
            Box _box_ = nearToCapacity(weigthSum, boxList);
            for(Object obj : _objList) _box_.addObject(obj);
            allBoxUse.add(_box_);

            // supprimer les objets déja ajouter à la liste
            for (Box box : boxList){
                for(Object obj : box.objList){
                    _objList.remove(obj);
                }
            }

        }

        int capResidual = 0
                ;
        for(Box _box : allBoxUse){
            capResidual += _box.capResidual();
        }

        return capResidual;

    }

}
