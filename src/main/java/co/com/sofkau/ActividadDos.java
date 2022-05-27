package co.com.sofkau;

import co.com.sofkau.modelos.Chat;
import co.com.sofkau.modelos.MalasPalabras;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
                            mensaje.transformarAPalabraCorrecta(palabra.getMalaPalabra().toLowerCase());
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

        return malasPalabras;
    }

    public List<Chat> generadorDeChat(){
        List<Chat> chat = new ArrayList<>();

        chat.add(new Chat("Resulta y acontece que este vaso está roto"));
        chat.add(new Chat("Darme un tarro de hijueputa"));
        chat.add(new Chat("Hola. hijueputa días"));
        chat.add(new Chat("Realmente, un pirobo y un gonorrea funcionan para lo mismo"));
        chat.add(new Chat("Realmente, un pirobo y un Gonorrea funcionan para lo mismo"));

        return chat;
    }

}
