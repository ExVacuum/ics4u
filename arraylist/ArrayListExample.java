package arraylist;

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args){
        ArrayList<String> citrusFruit = new ArrayList<String>();
        citrusFruit.add("Lemon");
        citrusFruit.add("Orange");

        for(String s: citrusFruit){
            System.out.println(s);
        }

        citrusFruit.remove(citrusFruit.get(0));

        for(String s: citrusFruit){
            System.out.println(s);
        }
    }
}