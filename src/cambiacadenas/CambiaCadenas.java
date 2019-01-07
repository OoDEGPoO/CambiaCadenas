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
    
    private static boolean flag_out = false;//si se introduce un número por parámetro 0 -> desactiva salida / otro -> activa salida
    
    public static boolean isInt(String s){
        try {
            int n = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
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
    
    public static ArrayList<String> CambiaCadenas(String s, HashMap<String, HashMap<String, String>> M){
        ArrayList<String> out = new ArrayList<>();
        String head = "", head1, head2, c, tail, cadena = s, aux;
        
        if (s.length() < 2){ //No hay cadena que analizar
            out.add(s);
            return out;
        } else {
            do {
                head1 = cadena.substring(0, 1);
                head2 = cadena.substring(1, 2);
                c = M.get(head1).get(head2);
                tail = cadena.substring(2);
                
                aux = head.concat(c.concat(tail));
                if (flag_out) System.out.println(s + "->" + aux);//para comprobar que funciona
                out = addNotContains(out, CambiaCadenas(aux, M));
                
                head = head.concat(head1);//retiramos la primera en cada iteración
                cadena = cadena.substring(1);
            } while(tail.length() > 0);
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
        String cadena = "";
        
        if (args.length > 0){//para la entrada de parámetros
            for (int i = 0; i < args.length; i++){
                if (isInt(args[i])){//para activar o desactivar la salida del seguimiento del CambiaCadenas
                    flag_out = Integer.parseInt(args[i]) != 0;
                } else {
                    cadena = args[i];//si es letra, se sustituye la cadena
                }
            }
            
            cadena = args[0];
        }
        
        if (cadena.isEmpty()) cadena = "acabada";
        
        System.out.println("Entrada: " + cadena);
        ArrayList<String> salida = CambiaCadenas(cadena, M);
        System.out.println(salida.toString());
    }
    
}
