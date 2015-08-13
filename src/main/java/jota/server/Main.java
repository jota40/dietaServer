package jota.server;

import java.math.BigDecimal;

import jota.server.entity.Ingrediente;
import jota.server.service.IngredienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main {
	public static void main(String[] args)  throws Exception {
//		final ApplicationContext context = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	}

	@Autowired
	IngredienteService ingredienteService;

	protected void mainInternal(String[] args) throws Exception {
		/*
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setCoste( BigDecimal.TEN );
		ingrediente.setDescripcion( "probando" );
		ingrediente.setNombre("prueba" );
		ingrediente.setPesoPorRacion( BigDecimal.ONE );
		ingrediente.setPesoPorRacion( BigDecimal.ONE );
		
//		Long ingredienteId = ingredienteService.insert( ingrediente );
//		System.out.println( viviendaService.findByIdUsuario( 1L ) );		
*/
	}
}