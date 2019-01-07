package afd;

import java.io.*;
import java.util.*;


/**
 * @author Jesús Manuel Olivares Ceja 2017 01 08
 *
 */
public class AFD {
    int simbolos = 0;
    int estados = 0;
    ArrayList <Simbolo> simbolo;
    int estado[][];
    boolean edoFinal[];
    String etiqueta[];

    AFD() {
        //CARGA EL CONTENIDO DEL AUTÖMATA
        try {
            BufferedReader entrada = new BufferedReader(new FileReader("lexico.txt"));
            String registro;

            registro = entrada.readLine();
            if (registro.compareTo("AFD") != 0) {
                System.out.println("No cuento con el formato del autómata");
                System.exit(-1);
            }
            
            // TOMA EL ALFABETO
            simbolo = new ArrayList <Simbolo>();
            registro = entrada.readLine();
            if (registro.compareTo("#alfabeto") != 0) {
                System.out.println("No hay indicador del alfabeto del autómata");
                System.exit(-1);
            }
            while( entrada.ready() ){
            Simbolo unSimbolo = new Simbolo();
            
                registro = entrada.readLine();
                if (registro.compareTo("#estados") == 0) 
                    break;
                if( registro.length() > 3 && registro.length() <= 6) // ES UN INTERVALO
                {
                    unSimbolo.min = registro.charAt(1);
                    unSimbolo.max = registro.charAt(4);
                    simbolo.add(unSimbolo);
                    System.out.println("intervalo "+unSimbolo.min+" "
                    +unSimbolo.max);
                }
                else // ES UN SIMBOLO
                {
                    unSimbolo.min = registro.charAt(1);
                    unSimbolo.max = registro.charAt(1);
                    simbolo.add(unSimbolo);
                    System.out.println("solo "+unSimbolo.min+" "
                    +unSimbolo.max);
                }
            }
            
            simbolos = simbolo.size();
            System.out.println("Hay: "+simbolos+ " símbolos");
                    
            registro = entrada.readLine();
            estados = Integer.parseInt(registro);
           System.out.println("Hay: "+estados+ " estados");
                        
            registro = entrada.readLine();
            if (registro.compareTo("#reglas") != 0) {
                System.out.println("No hay indicador de reglas del autómata");
                System.exit(-1);
            }
            
            estado = new int[estados][simbolos];
            
            while( entrada.ready() ){         
                registro = entrada.readLine();
                if (registro.compareTo("#finales") == 0) 
                    break;
            StringTokenizer elemento = new StringTokenizer(registro ,",");
                String sDe = elemento.nextToken();
                String sCon = elemento.nextToken();
                String sA = elemento.nextToken();
                
                int de = Integer.parseInt(sDe);
                int con = Integer.parseInt(sCon);
                int a = Integer.parseInt(sA);
                
                Simbolo uno = simbolo.get(con);
                
                System.out.println("Del estado "+de+" con "+
                uno.min+".."+uno.max+" pasa a "+ a);
                
                estado[de][con] = a;
            }

            etiqueta = new String[estados];
            edoFinal = new boolean[estados];
            for(int i = 0; i < estados; i++ )
                edoFinal[i] = false;
            
            while( entrada.ready() ){         
                registro = entrada.readLine();
                if (registro.compareTo("#fin") == 0) 
                    break;
            StringTokenizer elemento = new StringTokenizer(registro ,",");
                String sFinal = elemento.nextToken();
                String sEtiqueta = elemento.nextToken();
                
                int eFinal = Integer.parseInt(sFinal);
                
                etiqueta[eFinal] = sEtiqueta;
                edoFinal[eFinal] = true;
                
                System.out.println("Final "+eFinal+" "+
                        sEtiqueta);
            }
        } catch (IOException err) {
            System.out.println("ERROR DE LECTURA");
        }
    }

    int getNumSimbolo(char dato) {
        for (int indValor = 0; indValor < simbolos; indValor++ ) {
            Simbolo unSimbolo = simbolo.get(indValor);
            if (unSimbolo.min <= dato && dato <= unSimbolo.max) {
                System.out.println("\tsimbolo: "+indValor);
                return indValor;               
            }
        }
        return -1;
    }
    
    boolean evalua(String entrada) {
        System.out.println("Estás en evalua");
        int estadoActual = 0;
        boolean sigue = true;
        int numSimbolo = 0;

        while (entrada.length() > 0) {
            numSimbolo = getNumSimbolo(entrada.charAt(0));
            estadoActual = estado[estadoActual][numSimbolo];
            entrada = entrada.substring(1);
        }
        if (edoFinal[estadoActual] == true) {
            System.out.println(etiqueta[estadoActual]);
        }
        return edoFinal[estadoActual];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Compilando: " + args[0]);
        if (args.length > 1 ) {
            System.out.println("Salida: " + args[1]);
        } else {
            System.out.println("Salida: resultado.class");
        }

        Scanner scan = new Scanner(System.in);
        //StringBuffer aux = new StringBuffer(50);
        String entrada = null;
        char ch;
        /*try {
        while( (ch = (char)System.in.read()) != '\n' )
            aux.append(ch);
        entrada = aux.toString();
        } 
        catch(IOException e) {}*/
        
        AFD automata = new AFD();
        
        System.out.print("\nLinea de código: ");
        
        while(scan.hasNextLine()){
            
            entrada = scan.nextLine();
            
            if (entrada.equalsIgnoreCase("close")) {
                System.exit(0);
            }
            System.out.println("Proceso: "+entrada);
            if (automata.evalua(entrada) == true) {
                System.out.println("Correcto");
            } else {
                System.out.println("error");
            }
            System.out.println();
            System.out.print("Linea de código: ");
        }
        
        
    }
}

class Simbolo {
    char min;
    char max;

    public Simbolo(){
        min = (char)0;
        max = (char)0;
    }
    
    public Simbolo(char min, char max) {
        this.min = min;
        this.max = max;
    }
}
