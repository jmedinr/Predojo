package co.com.sofkau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class PredojoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PredojoApplication.class, args);
		List<String> correos = new ArrayList<>();

		//Agregando correos
		correos.add("camilobch@gmail.com");
		correos.add("camilobch@gmail.com");
		correos.add("stiwarmejia@gmail.com");
		correos.add("sofka@gmail.com");
		correos.add("iabarcae@yahoo.es");
		correos.add("camilobch@gmail.com");
		correos.add("aespinz@hotmail.com");
		correos.add("patorfebre@hotmail.com");
		correos.add("hfreitte2618@gmail.com");
		correos.add("pecmor63@gmail.com");
		correos.add("pecmor63@gmail.com");
		correos.add("panchop71@hotmail.com");
		correos.add("patorfebre@hotmail.com");
		correos.add("rickygodoy@gmail.cl");
		correos.add("patorfebre@hotmail.com");
		correos.add("enrigom@gmail.com");
		correos.add("enrigom@gmail.com");
		correos.add("flobato.c@gmail.com");
		correos.add("escobilla3carretes@hotmail.com");
		correos.add("jmartinezcossio@gmail.com");
		correos.add("jmartinezcossio@gmail.com");
		correos.add("amarantasol@gmail.com");
		correos.add("amarantasol@gmail.com");
		correos.add("juanmaceratta@hotmail.com");
		correos.add("almendrita203@hotmail.com");
		correos.add("cmerinosuarez@gmail.com");
		correos.add("susa.mora@gmail");
		correos.add("susa.mora@gmail");
		correos.add("kneirac@hotmail.com");
		correos.add("ceciliataty@gmail.com");
		correos.add("juanmaceratta@hotmail.com");

		List<String> correosDistintos = correos.stream().distinct().collect(Collectors.toList());
		System.out.println("Los correos distintos son: "+correosDistintos);

		// filtro de gmail

//		List<String> correosGmail = correosDistintos.stream().filter(correo -> {
//					return correo.contains("gmail");
//		}).collect(Collectors.toList());
//
//		List<String> correosHotmail = correosDistintos.stream().filter(correo -> {
//			return correo.contains("hotmail");
//		}).collect(Collectors.toList());
//
//		List<String> correosOutlook = correosDistintos.stream().filter(correo -> {
//			return correo.contains("outlook");
//		}).collect(Collectors.toList());

		List<String> correosVerficiados =  correos.stream().filter(correo -> {
			if (correo.contains("gmail") || correo.contains("hotmail") || correo.contains("outlook")){
				return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList());

		List<String> correosDescartados = new ArrayList<>();
		List<String> correosCorrectos = new ArrayList<>();

	}

	//Funcion para validar correos

	public static boolean validarCorreo(String email){

		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		if (mather.find()) {
			return true;
		} else{
			return false;
		}
	}

}
