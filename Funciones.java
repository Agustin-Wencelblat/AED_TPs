package aed;
import java.lang.Math;

class Funciones {
    int cuadrado(int x) {
        int res = x*x;
        return res;
    }

    double distancia(double x, double y) {
        double res = Math.sqrt(x*x+y*y);
        return res;
    }

    boolean esPar(int n) {
        if (n % 2 == 0){
            return true;
        }
        else return false;
    }    

    boolean esBisiesto(int n) {
        if (n % 400 == 0){
            return true;
        }
        else if ((n % 4 == 0) && (n % 100 != 0)){
            return true;
        }
        else return false;
    }

    int factorialIterativo(int n) {
        int res = 1;
        for (int i = 2; i <= n; i = i + 1){
            res = res * i;
        }
        return res;
    }   

    int factorialRecursivo(int n) {
        int res = 0;
        if (n == 0){
            res = 1;
        }        
        else res = n * factorialRecursivo(n-1);
        return res;
    }

    boolean esPrimo(int n) {
        boolean res = true;
        if ((n == 0)||(n == 1)){
            res = false;
        }
        else if (n == 2){
            res = true; 
        }
        else for (int i = 2; i < n; i = i + 1){
            if (n % i == 0){
            res = false;
            }
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int i = 0; i < numeros.length; i = i + 1){
            res = numeros[i] + res;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = 0;
        for (int i = 0; i < numeros.length ; i = i + 1){
            if (numeros[i] == buscado){
                res = i;
            }
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        for (int i = 0; i < numeros.length; i = i + 1){
            if (esPrimo(numeros[i])){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int i = 0; i < numeros.length ; i = i + 1){
            if (numeros[i] % 2 != 0){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        if (s1.length() > s2.length()){
            return false;
        }
        else for (int i = 0; i < s1.length(); i = i+1){
            if (s1.charAt(i) != s2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res = true;
        if (s1.length() > s2.length()){
            res = false;
        }
        else {
            int s1Index = s1.length() - 1;
            int s2Index = s2.length() - 1;
            while (s1Index >= 0) {
                if (s1.charAt(s1Index) != s2.charAt(s2Index)) {
                    res = false;
                }
                s1Index--;
                s2Index--;
            }
        }
        return res;
    }
}    