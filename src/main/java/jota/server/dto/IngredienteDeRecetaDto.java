package jota.server.dto;

import java.math.BigDecimal;

public class IngredienteDeRecetaDto extends IngredienteDeUsuarioDto {

	private static final long serialVersionUID = -3312146371468281246L;

	private Long idReceta;
	private Integer pax;
	private BigDecimal cantidadPorComensal;
	private BigDecimal cantidad;
	private EscalaDeUnidadDeMedidaDto unidadDeCantidadPorComensal;

	public IngredienteDeRecetaDto() {}

	public IngredienteDeRecetaDto( IngredienteDeUsuarioDto ingredienteDeUsuarioDto, Integer pax, BigDecimal cantidadPorComensal, EscalaDeUnidadDeMedidaDto escalaDeUnidadDeMedidaDto ) {
/*		this.idIngrediente = ingredienteDeUsuarioDto.getId();
		this.ingredienteDto = ingredienteDeUsuarioDto;
		this.pax = pax == null ? 0 : pax;
		this.cantidadPorComensal = cantidadPorComensal == null ? BigDecimal.ZERO : cantidadPorComensal;
		this.escalaDeUnidadDeMedidaDto = escalaDeUnidadDeMedidaDto;
*/	}

	public BigDecimal getCantidadPorComensal() {
		return cantidadPorComensal;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public void setPax(Integer pax) {
		this.pax = pax;
	}

	public BigDecimal calcularCantidad() {
		return cantidadPorComensal.multiply( new BigDecimal( pax ) );
	}

	public BigDecimal calcularTotal() {
		return cantidadPorComensal.multiply( new BigDecimal( pax ) ).multiply( getCoste() );
	}

	public BigDecimal calcularRacionesPorComensal() {
		return cantidadPorComensal.divide( getPesoPorRacion() );
	}

	public void addCantidadPorComensal( BigDecimal incremento ) {
		cantidadPorComensal = cantidadPorComensal.add( incremento );
	}
}