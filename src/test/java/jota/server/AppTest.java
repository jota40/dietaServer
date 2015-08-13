package jota.server;

import jota.server.service.IngredienteService;
import jota.server.service.RecetaService;
import jota.server.service.UnidadDeMedidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration( "classpath:/spring/config/BeanLocations.xml" )
public class AppTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	UnidadDeMedidaService unidadDeMedidaService;

	@Autowired
	RecetaService recetaService;
/*
	@Test
	public void insertUnidadDeMedida() throws ServiceException {
		UnidadDeMedida unidadDeMedida = new UnidadDeMedida();
		unidadDeMedida.setAbreviacion("Kg");
		unidadDeMedida.setDescripcion( "Kilogramos" );
		unidadDeMedida.setNombre("Kilogramos" );

		Long idUnidadDeMedida = unidadDeMedidaService.insert( unidadDeMedida );

		//Test
		UnidadDeMedida dev = unidadDeMedidaService.getById( idUnidadDeMedida );
		Assert.assertNotNull( dev );
		Assert.assertEquals( idUnidadDeMedida, dev.getId() );
	}
/*
	@Test
	public void insertIngrediente() throws ServiceException {
		
		UnidadDeMedida unidadDeMedida = new UnidadDeMedida();
		unidadDeMedida.setAbreviacion("Kg");
		unidadDeMedida.setDescripcion( "Kilogramos" );
		unidadDeMedida.setNombre("Kilogramos" );
		
		Long idUnidadDeMedida = unidadDeMedidaService.insert( unidadDeMedida );
		
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setCoste( BigDecimal.TEN );
		ingrediente.setDescripcion( "probando" );
		ingrediente.setNombre("prueba" );
		ingrediente.setPesoPorRacion( BigDecimal.ONE );
		
		Long idIngrediente = ingredienteService.insert( ingrediente, idUnidadDeMedida );

		//Test
		Ingrediente dev = ingredienteService.getById( idIngrediente );
		Assert.assertNotNull( dev );
		Assert.assertEquals( idIngrediente, dev.getId() );
		Assert.assertEquals( idUnidadDeMedida, dev.getUnidadDeMedida().getId() );
	}
*//*
	@Test
	public void insertIngredienteDeReceta() throws ServiceException {
		UnidadDeMedida unidadDeMedida = new UnidadDeMedida();
		unidadDeMedida.setAbreviacion("Kg");
		unidadDeMedida.setDescripcion( "Kilogramos" );
		unidadDeMedida.setNombre("Kilogramos" );

		Long idUnidadDeMedida = unidadDeMedidaService.insert( unidadDeMedida );

		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setCoste( BigDecimal.TEN );
		ingrediente.setDescripcion( "probando" );
		ingrediente.setNombre("prueba" );
		ingrediente.setPesoPorRacion( BigDecimal.ONE );
		Long idIngrediente = ingredienteService.insert( ingrediente, idUnidadDeMedida );

		Receta receta = new Receta();
		receta.setDescripcion( "Descripcion receta" );
		receta.setNombre( "Receta" );
		receta.setPax( 4 );
		Long idReceta = recetaService.insert( receta, null );

		IngredienteDeRecetaId pk = new IngredienteDeRecetaId();
		pk.setIngrediente( ingrediente );
		pk.setReceta( receta );

		IngredienteDeReceta ingredienteDeReceta = new IngredienteDeReceta();
		ingredienteDeReceta.setCantidad( BigDecimal.TEN );

		IngredienteDeRecetaId idIngredienteDeReceta = ingredienteDeRecetaService.insert( ingredienteDeReceta, idIngrediente, idReceta );

		//Test
		IngredienteDeReceta dev = ingredienteDeRecetaService.getById( idIngredienteDeReceta );
		Assert.assertNotNull( dev );
		Assert.assertEquals( idIngrediente, dev.getPk().getIngrediente().getId() );
		Assert.assertEquals( idReceta, dev.getPk().getReceta().getId() );
	}

	@Test
	public void insertRecetaSinIngredientes() throws ServiceException {
		Receta receta = new Receta();
		receta.setDescripcion( "Descripcion receta" );
		receta.setNombre( "Receta" );
		receta.setPax( 4 );

		Long idReceta = recetaService.insert( receta, null );

		//Test
		Receta dev = recetaService.getById( idReceta );
		Assert.assertNotNull( dev );
		Assert.assertEquals( idReceta, dev.getId() );
		Assert.assertNull( dev.getIngredientes() );
	}

	@Test
	public void insertRecetaConIngredientes() throws ServiceException {
		UnidadDeMedida unidadDeMedida = new UnidadDeMedida();
		unidadDeMedida.setAbreviacion("Kg");
		unidadDeMedida.setDescripcion( "Kilogramos" );
		unidadDeMedida.setNombre("Kilogramos" );

		Long idUnidadDeMedida = unidadDeMedidaService.insert( unidadDeMedida );

		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setCoste( BigDecimal.TEN );
		ingrediente.setDescripcion( "probando" );
		ingrediente.setNombre("prueba" );
		ingrediente.setPesoPorRacion( BigDecimal.ONE );
		Long idIngrediente = ingredienteService.insert( ingrediente, idUnidadDeMedida );

		IngredienteDeReceta ingredienteDeReceta = new IngredienteDeReceta();
		ingredienteDeReceta.setCantidad( BigDecimal.TEN );
		ingredienteDeReceta.getPk().setIngrediente( ingrediente );

		Receta receta = new Receta();
		receta.setDescripcion( "Descripcion receta" );
		receta.setNombre( "Receta" );
		receta.setPax( 4 );

		Set<IngredienteDeReceta> ingredientesDeReceta = new HashSet<IngredienteDeReceta>();
		ingredientesDeReceta.add( ingredienteDeReceta );	
		Long idReceta = recetaService.insert( receta, ingredientesDeReceta );

		//Test
		Receta dev = recetaService.getById( idReceta );
		Assert.assertNotNull( dev );
		Assert.assertEquals( idReceta, dev.getId() );
		Assert.assertNotNull( dev.getIngredientes() );
		Assert.assertEquals( 1, dev.getIngredientes().size() );
		Assert.assertEquals( idIngrediente, dev.getIngredientes().iterator().next().getPk().getIngrediente().getId() );
	}*/
}
