package aed;

import java.util.Scanner;
import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int largo) {
        float[] vector = new float[largo];
        
        for (int i = 0; i < largo; i++) {
            if (entrada.hasNextFloat()) {
                vector[i] = entrada.nextFloat();
            } 
        }  
        return vector;
    }
    

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] matriz = new float[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (entrada.hasNextFloat()) {
                    matriz[i][j] = entrada.nextFloat();
                } 
            }
        }
        
        return matriz;
    }
    

    void imprimirPiramide(PrintStream salida, int alto) {
        for (int i = 1; i <= alto; i++) {
            for (int j = 1; j <= alto - i; j++) {
                salida.print(" ");
            }
            
            for (int j = 1; j <= 2 * i - 1; j++) {
                salida.print("*");
            }
    
            for (int j = 1; j <= alto - i; j++) {
                salida.print(" ");
            }
            
            salida.println();
        }
    }
    
}
