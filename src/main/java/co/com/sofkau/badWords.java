package co.com.sofkau;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class badWords {

    public static void main(String[] args) {

        ArrayList palabras = new ArrayList();

        String salir="";

        while(salir==""){
            System.out.println("ingrese una palabra");
            Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
            String entradaTeclado = entradaEscaner.nextLine();
            palabras.add(entradaTeclado);
            System.out.println("desea salir, escriba cualquier cosa de lo contrario unda enter");
            salir = entradaEscaner.nextLine();
        }


        palabras.add("un");
        palabras.add("dos");
        palabras.add("tre");
        palabras.add("perra");
        palabras.add("puerca");


        Flux.fromIterable(palabras).map(pal -> {

            if (validarPalabra(pal.toString())) {
                return "**";
            }
            return pal;
        }).subscribe();
    }


    public static Boolean validarPalabra(String palabra){
        List<String> malasPalabras = new ArrayList<>();
        malasPalabras.add("perra");
        malasPalabras.add("puta");
        malasPalabras.add("malparida");
        malasPalabras.add("puto");
        malasPalabras.add("gueva");

        Flux<Boolean> mag = Flux.fromIterable(malasPalabras).map(pal -> pal.equals(palabra));

        return mag;

    }
}
