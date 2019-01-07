/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cambiacadenas;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class CambiaCadenas {
    
    public static HashMap<String, HashMap<String, String>> generaM(){
        HashMap<String, HashMap<String, String>> out = new HashMap<>();
        
        HashMap<String, String> a = new HashMap<>();
        a.put("a", "b"); a.put("b", "b"); a.put("c", "a"); a.put("d", "d");
        out.put("a", a);
        
        HashMap<String, String> b = new HashMap<>();
        b.put("a", "c"); b.put("b", "a"); b.put("c", "d"); b.put("d", "a");
        out.put("b", b);
        
        HashMap<String, String> c = new HashMap<>();
        c.put("a", "b"); c.put("b", "a"); c.put("c", "c"); c.put("d", "c");
        out.put("c", c);
        
        HashMap<String, String> d = new HashMap<>();
        d.put("a", "d"); d.put("b", "c"); d.put("c", "d"); d.put("d", "b");
        out.put("d", d);
        
        return out;
    }
    
    public static void imprimeMatriz(HashMap<String, HashMap<String, String>> M){
        String[] letras = {"a","b","c","d"};
        int i, j;
        
        System.out.print("\t");
        for (i = 0; i < letras.length; i++) System.out.print(letras[i] + "\t");
        System.out.println();
        System.out.print("\t");
        for (i = 0; i < letras.length; i++) System.out.print("|" + "\t");
        System.out.println("\n");
        for (i = 0; i < letras.length; i++){
            System.out.print(letras[i] + "-\t");
            for (j = 0; j < letras.length; j++){
                System.out.print(M.get(letras[i]).get(letras[j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static ArrayList<String> addNotContains(ArrayList<String> S, ArrayList<String> in){
        ArrayList<String> out = (ArrayList<String>) S.clone();
        for (int i = 0; i < in.size(); i++){
            String s = in.get(i);
            if (!S.contains(s)) out.add(s);
        }
        
        return out;
    }
    
    public static ArrayList<String> CambiaCadenas(String s, HashMap<String, HashMap<String, String>> M, String head){
        ArrayList<String> out = new ArrayList<>();
        String head1, head2, tail = "", c;
        
        if (s.length() < 2){ //No hay cadena que analizar
            out.add(s);
            return out;
        } else {
            head1 = s.substring(0+head.length(), 1+head.length());
            head2 = s.substring(1+head.length(), 2+head.length());
            c = M.get(head1).get(head2);
            if (s.length() == 2) tail = s.substring(2);
            
            out = CambiaCadenas(c.concat(tail), M, head);
            out = addNotContains(out, (CambiaCadenas()));//--------- Replantear
        }
        
        return out;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<String, HashMap<String, String>> M = generaM();
        System.out.println("La matriz M está inicializada como:");
        imprimeMatriz(M);
        
        ArrayList<String> salida = CambiaCadenas("acabada", M, new String(""));
        System.out.println(salida.toString());
    }
    
}