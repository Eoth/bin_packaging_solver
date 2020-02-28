package binpackaging;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        Heuristics heuristic = new Heuristics();

        Object obj1 = new Object(19, 0, 1);
        Object obj2 = new Object(29, 1, 2);
        Object obj3 = new Object(16, 2, 3);
        Object obj4 = new Object(7, 0, 4);
        Object obj5 = new Object(11, 3, 5);
        Object obj6 = new Object(8, 3, 6);
        Object obj7 = new Object(10, 4, 7);
        Object obj8 = new Object(1, 4, 8);
        List<Object> objectList = new ArrayList<>();
        objectList.add(obj1); objectList.add(obj2); objectList.add(obj3);
        objectList.add(obj4); objectList.add(obj5); objectList.add(obj6);
        objectList.add(obj7); objectList.add(obj8);

        Box box1 = new Box(1, 15);
        Box box2 = new Box(2, 20);
        Box box3 = new Box(3, 30);
        Box box4 = new Box(4, 11);
        Box box5 = new Box(5, 7);
        Box box6 = new Box(6, 6);
        List<Box> typeBoxList = new ArrayList<>();
        typeBoxList.add(box1); typeBoxList.add(box2); typeBoxList.add(box3);
        typeBoxList.add(box4); typeBoxList.add(box5); typeBoxList.add(box6);

        // System.out.println("Algo foolishHeuristic => Capacité résiduel "+ heuristic.foolishHeuristic(objectList, typeBoxList));
        // System.out.println("Algo randomFoolishHeuristic => Capacité résiduel "+ heuristic.randomFoolishHeuristic(objectList, typeBoxList));
        // System.out.println("Algo improoveFoolishHeuristic => Capacité résiduel "+ heuristic.improoveFoolishHeuristic(objectList, typeBoxList));
        // System.out.println("Algo sumObjectAdd => Capacité résiduel "+ heuristic.sumObjectAdd(objectList, typeBoxList));

       /* box1.addObject(obj1);
        box1.addObject(obj2);
        box1.addObject(obj3);
        box1.addObject(obj4);
        box1.addObject(obj5);

        box1.Contenu();

        box1.deleteObject(2);

        box1.Contenu(); */

        Object_Box_List object_Box_List = new Object_Box_List();
        LoadBench loadBench = new LoadBench(); // classe qui permet de lire les fichiers fournit par le prof

        // parcours de tous les bench fournis par le prof qui se trouve dans le dossier instances
        for (int i = 2; i < 21; i++){
            for(int j = 0; j < 5; j++){

                System.out.print("\n Bench "+i+" _ "+j+"\n\n");

                object_Box_List = loadBench.LoadBenchFromFile("instances/bench_"+i+"_"+j+"");

                System.out.println("Algo foolishHeuristic => Capacité résiduel "+ heuristic.foolishHeuristic(object_Box_List.ObjectList, object_Box_List.TypeBoxList));
                System.out.println("Algo randomFoolishHeuristic => Capacité résiduel "+ heuristic.randomFoolishHeuristic(object_Box_List.ObjectList, object_Box_List.TypeBoxList));
                System.out.println("Algo sumObjectAdd => Capacité résiduel "+ heuristic.sumObjectAdd(object_Box_List.ObjectList, object_Box_List.TypeBoxList));
                System.out.println("Algo improoveFoolishHeuristic => Capacité résiduel "+ heuristic.improoveFoolishHeuristic(object_Box_List.ObjectList, object_Box_List.TypeBoxList));
            }
        }

    }
}
