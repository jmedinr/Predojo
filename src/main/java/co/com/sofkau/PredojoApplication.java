package co.com.sofkau;

import co.com.sofkau.modelos.Correos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootApplication
public class PredojoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PredojoApplication.class, args);
	}

	public List<Correos> generadorDeCorreos(){
		List<Correos> correos = new ArrayList<>();

		correos.add(new Correos("camilobch@gmail.com"));
		correos.add(new Correos("camilobch@gmail.com"));
		correos.add(new Correos("in@gmail.com"));
		correos.add(new Correos("sofka@gmail.com"));
		correos.add(new Correos("y@outlook.com"));
		correos.add(new Correos("camilobch@gmail.com"));
		correos.add(new Correos("aespinz@hotmail.com"));
		correos.add(new Correos("patorfebrehotmail.com"));
		correos.add(new Correos("h@gmail.com"));
		correos.add(new Correos("pecmor63@gmail.com"));
		correos.add(new Correos("pecmor63@gmail.com"));
		correos.add(new Correos("op71@hotmail.com"));
		correos.add(new Correos("patorfebre@hotmail.cohm"));
		correos.add(new Correos("rickygodoy@outlook.com"));
		correos.add(new Correos("patorfebre@hotmail.com"));
		correos.add(new Correos("enrigom@gmail.com"));
		correos.add(new Correos("enrigom@gmail.com"));
		correos.add(new Correos("flobato.cgmail.com"));
		correos.add(new Correos("escobilla3carretes@hotmail.com"));
		correos.add(new Correos("jmartinezcossio@gmail.com"));
		correos.add(new Correos("amarantasol@gmail.com"));
		correos.add(new Correos("juanmaceratta@hotmail.com"));
		correos.add(new Correos("almendrita203@hotmail.com"));
		correos.add(new Correos("cmerinosuarez@gmail.com"));
		correos.add(new Correos("susa.moraoutlook.com"));
		correos.add(new Correos("kneirac@hotmail.com"));
		correos.add(new Correos("ceciliataty@gmail.com"));
		correos.add(new Correos("juanmaceratta@hotmail.com"));
		correos.add(new Correos("aldopastore@hotmail.com"));
		correos.add(new Correos("paulinaroca@hotmail.com"));
		correos.add(new Correos("carola.serrangmail.com"));
		correos.add(new Correos("i@gmail.com"));
		correos.add(new Correos("juanmsilva@outlook.com"));

		return correos;
	}

	public boolean validarCorreo(String email){
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		if (mather.find()) {
			return true;
		} else{
			return false;
		}
	}

	public Mono<List<Correos>> usandoOperadorDistinct(List<Correos> emails){
		 return Flux.fromIterable(emails)
				.distinct(Correos::getCorreo)
				.collect(Collectors.toList());
	}

	public void usandoOperadorFilter(List<Correos> emails) {
		// Agrupando Correos Por Dominio Y Contandolos (Los no-válidos se toman en cuenta como dominios distintos)
		List<Correos> emailsParaFilter = emails.stream().filter(email -> email.getDominio().equals("gmail.com") || email.getDominio().equals("hotmail.com") || email.getDominio().equals("outlook.com")).collect(Collectors.toList());

		Flux.fromIterable(emailsParaFilter)
				.groupBy(Correos::getDominio)
				.flatMap(Flux::collectList)
				.subscribe(email -> {
					System.out.println("Correos" + email);
					System.out.println("Cantidad" + email.size());
				});
	}

	public void usandoOperadorMap(List<Correos> emails) {
		List<Correos> correosDescartados = new ArrayList<>();

		Flux.fromIterable(emails)
				.map(email -> {
					if(validarCorreo(email.getCorreo())) {
						return "Correo valido " + email.getCorreo();
					} else {
						correosDescartados.add(email);
						return "Correo no valido " + email.getCorreo();
					}
				})
				.subscribe(System.out::println);

		System.out.println("Correos descartados: " + correosDescartados.size());
	}

	@Override
	public void run(String... args) throws Exception {
		/* List<Correos> emails = generadorDeCorreos();

		System.out.println("\n\nEliminando Correos Repetidos Y Contandolos");
		Mono<List<Correos>> emailsDistinct = usandoOperadorDistinct(emails);
		System.out.println("Numero De Correos: " + emailsDistinct.block().size());

		System.out.println("\n\nLa nueva lista de correos es: " + emailsDistinct.block());

		System.out.println("\n\nAgrupando Correos Por Dominio Y Contandolos");
		usandoOperadorFilter(emailsDistinct.block());

		System.out.println("\n\nMostrando Correos Validos Y No Validos");
		usandoOperadorMap(emails); */

		// Ejecutando La Actividad Dos
		ActividadDos actividadDos = new ActividadDos();
		actividadDos.run(args);
	}
}
