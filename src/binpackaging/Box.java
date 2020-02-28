package binpackaging;

import java.util.ArrayList;
import java.util.List;

public class Box {

    public int idBox;
    public int capacity;
    public List<Integer> colorsList = new ArrayList<>();
    public List<Object> objList = new ArrayList<>();

    public Box(){}

    public Box(int idBox, int capacity){
        this.idBox = idBox;
        this.capacity = capacity;
    }

    public int getIdBox() {
        return idBox;
    }

    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Integer> getColorsList() {
        return colorsList;
    }

    public void setColorsList(List<Integer> colorsList) {
        this.colorsList = colorsList;
    }

    public List<Object> getObjList() {
        return objList;
    }

    public void setObjList(List<Object> objList) {
        this.objList = objList;
    }

    /*Capacité restante dans la boite.*/
    public int capResidual(){
        int capResid = this.capacity;
        for(Object obj : this.objList){
            capResid -= obj.weight;
        }
        return capResid;
    }

    /* Ajoute un objet dans la boite s'il reste suffisamment
    de place et si  la contrainte de 2 couleurs maximum est
    respectée. */
    public boolean addObject(Object obj){
        /* Vérifier capacité de la boite à ajouter cet objet*/
        if(capResidual() - obj.weight < 0){
            //System.out.println(" Impossible d'ajouter l'objet "+obj.idObj+" : pas assez de place");
            return false;
        }
        /* contrainte de coulkeur */
        List<Integer> colList = this.colorsList;
        if(colList.size() >= 2){
            /* deux couleurs présente dans la listes des couleurs des objets*/
            boolean inColorList = false;
            for(int i : colList){
                if (i == obj.color) {
                    objList.add(obj);
                    //System.out.println("Objet "+ obj.idObj +" ajouter .");
                    obj.setBox(this);
                    inColorList = true;break; }
            }
            if(!inColorList)  {
               // System.out.println("Impossible d'ajouter l'objet "+ obj.idObj +": 2 couleurs maximum");
                return false;
            }
        }
        else {
            objList.add(obj);
            obj.setBox(this);
            boolean inColorList = false;
            for(int i : colList){
                if (i == obj.color) { inColorList = true; break;}
            }
            if(!inColorList) this.colorsList.add(obj.color);
            //System.out.println("Objet "+ obj.idObj +" ajouter .");
            return true;
        }
        return false;
    }

    /* Supprimer un objet grâce à son Id*/
    public boolean deleteObject(int id){
        for(Object obj : objList){
            if(obj.idObj == id) {objList.remove(obj); return true;}
        }
        return false;
    }
   // Affiche information de la boite
    public void Contenu() {
        String chaine = "[Box : "+this.idBox+" ] => Capacité : " + this.capacity + " capacité restante : "+ capResidual() +"\n Listes des objets : "+ this.objList.size() +"\n";

        for(Object obj: objList){
            chaine += "     objet id : "  + obj.getIdObj() + " poids : " + obj.getWeight() + " couleur : " + obj.getColor() + "\n";
        }
        System.out.println(chaine);
    }


}
