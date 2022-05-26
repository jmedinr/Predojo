package co.com.sofkau;

import co.com.sofkau.modelos.Correos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class PredojoApplication {
	public static void main(String[] args) {
		SpringApplication.run(PredojoApplication.class, args);
	}

	public List<Correos> generadorDeCorreos(){
		List<Correos> correos = new ArrayList<>();

		correos.add(new Correos("camilobch@gmail.com")); //
		correos.add(new Correos("camilobch@gmail.com"));//
		correos.add(new Correos("in@gmail.com"));
		correos.add(new Correos("sofka@gmail.com"));
		correos.add(new Correos("iabarcae@yahoo.es"));
		correos.add(new Correos("camilobch@gmail.com")); //
		correos.add(new Correos("aespinz@hotmail.com"));
		correos.add(new Correos("patorfebre@hotmail.com")); //
		correos.add(new Correos("hfreitte2618@gmail.com"));
		correos.add(new Correos("pecmor63@gmail.com")); //
		correos.add(new Correos("pecmor63@gmail.com")); //
		correos.add(new Correos("panchop71@hotmail.com"));
		correos.add(new Correos("patorfebre@hotmail.com")); //
		correos.add(new Correos("rickygodoy@gmail.cl"));
		correos.add(new Correos("patorfebre@hotmail.com")); //
		correos.add(new Correos("enrigom@gmail.com")); //
		correos.add(new Correos("enrigom@gmail.com")); //
		correos.add(new Correos("flobato.c@gmail.com"));
		correos.add(new Correos("escobilla3carretes@hotmail.com"));
		correos.add(new Correos("jmartinezcossio@gmail.com"));
		correos.add(new Correos("amarantasol@gmail.com"));
		correos.add(new Correos("juanmaceratta@hotmail.com"));
		correos.add(new Correos("almendrita203@hotmail.com"));
		correos.add(new Correos("cmerinosuarez@gmail.com"));
		correos.add(new Correos("susa.mora@gmail.cosm")); //xxxxxxxxx
		correos.add(new Correos("kneirac@hotmail.com"));
		correos.add(new Correos("ceciliataty@gmail.com"));
		correos.add(new Correos("juanmaceratta@hotmail.com"));
		correos.add(new Correos("aldopastore@hotmail.com"));
		correos.add(new Correos("paulinarocahotmail.com")); //xxxxxxxxxxxxx
		correos.add(new Correos("carola.serran@gmail.com"));
		correos.add(new Correos("in@gmail.com")); //xxxxxxxxxxxx
		correos.add(new Correos("juanmsilva@hotmail.c")); //xxxxxxxxxxxx

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

	public void usandoOperadorDistinct(List<Correos> emails){
		Flux.fromIterable(emails)
				.distinct(email -> email.getCorreo())
				.doOnNext(email -> System.out.println(email))
				.count()
				.subscribe(email -> System.out.println("Cantidad correos: " + email));
	}

	public void usandoOperadorFilter(List<Correos> emails) {
		Flux.fromIterable(emails)
				.groupBy(email -> email.getDominio())
				.flatMap(group -> group.collectList())
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
				.subscribe(email -> System.out.println(email));

		System.out.println("Correos descartados: " + correosDescartados.size());
	}

	public void run(String... args) throws Exception {
		List<Correos> emails = generadorDeCorreos();

		usandoOperadorMap(emails);
		System.out.println("\n\nEliminando Correos Repetidos Y Contandolos");
		usandoOperadorDistinct(emails);

		System.out.println("\n\nAgrupando Correos Por Dominio Y Contandolos");
		usandoOperadorFilter(emails);

		System.out.println("\n\nImprimiendo Correos Válidos Y No Válidos");
		usandoOperadorMap(emails);
	}

}
