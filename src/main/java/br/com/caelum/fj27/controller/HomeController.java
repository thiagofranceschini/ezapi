package br.com.caelum.fj27.controller;
/**
 * @Restcontroller nunca retorna uma pǵina, por isso nao preciso mais do @Responsebody
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String index(){
		System.out.println("Hello World");
		return "Hello Word com SpringBoot Mvc";
	}
}
