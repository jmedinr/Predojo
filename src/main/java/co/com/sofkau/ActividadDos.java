package co.com.sofkau;

import co.com.sofkau.modelos.Chat;
import co.com.sofkau.modelos.MalasPalabras;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import reactor.core.publisher.Flux;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActividadDos implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PredojoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<MalasPalabras> malasPalabras = generarListaMalasPalabras();
        List<Chat> chat = generadorDeChat();

        Flux.fromIterable(chat)
                .map(mensaje -> {
                    malasPalabras.forEach(palabra -> {
                        if (mensaje.getChat().toLowerCase().contains(palabra.getMalaPalabra().toLowerCase())) {
                            mensaje.transformarMalasPalabras(palabra.getMalaPalabra().toLowerCase());
                        }
                    });

                    return mensaje;
                })
                .subscribe(mensaje -> System.out.println("Mensaje: " + mensaje));
     }

    public List<MalasPalabras> generarListaMalasPalabras() {
        List<MalasPalabras> malasPalabras = new ArrayList<>();

        malasPalabras.add(new MalasPalabras("pirobo"));
        malasPalabras.add(new MalasPalabras("gonorrea"));
        malasPalabras.add(new MalasPalabras("hijueputa"));
        malasPalabras.add(new MalasPalabras("puta"));
        malasPalabras.add(new MalasPalabras("perra"));
        malasPalabras.add(new MalasPalabras("perro"));
        malasPalabras.add(new MalasPalabras("marica"));
        malasPalabras.add(new MalasPalabras("maricón"));
        malasPalabras.add(new MalasPalabras("maricona"));
        malasPalabras.add(new MalasPalabras("mariconazo"));

        return malasPalabras;
    }

    public List<Chat> generadorDeChat(){
        List<Chat> chat = new ArrayList<>();

        String mensaje = "";
        Integer contador = 1;

        while (!mensaje.equals("salir")) {
            if (contador == 1) {
                System.out.println("Recuerde que para dejar de escribir mensajes escriba 'salir'");
            }

            mensaje = leerMensaje();
            chat.add(new Chat(mensaje));
            contador++;
        }

        return chat;
    }

    public String leerMensaje() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese un mensaje: ");
        return leer.nextLine();
    }
}
