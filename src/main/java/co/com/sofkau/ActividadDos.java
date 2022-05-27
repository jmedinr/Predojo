package co.com.sofkau;

import co.com.sofkau.modelos.Chat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActividadDos implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PredojoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> malasPalabras = generarListaMalasPalabras();
        List<Chat> chat = generadorDeChat();

        Flux.fromIterable(chat)
                .map(mensaje -> {
                    malasPalabras.forEach(palabra -> {
                        if (mensaje.getChat().toLowerCase().contains(palabra.toLowerCase())) {
                            mensaje.transformarMalasPalabras(palabra.toLowerCase());
                        }
                    });

                    return mensaje;
                })
                .subscribe(mensaje -> System.out.println("Mensaje: " + mensaje));
     }

    public List<String> generarListaMalasPalabras() {
        List<String> malasPalabras = new ArrayList<>();

        malasPalabras.add("pirobo");
        malasPalabras.add("gonorrea");
        malasPalabras.add("hijueputa");
        malasPalabras.add("puta");
        malasPalabras.add("perra");
        malasPalabras.add("perro");
        malasPalabras.add("marica");
        malasPalabras.add("maricón");
        malasPalabras.add("maricona");
        malasPalabras.add("mariconazo");

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
