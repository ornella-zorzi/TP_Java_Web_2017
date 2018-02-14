package entity;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Reserva implements Serializable {
	private Time hora_fin;
	private Time hora_inicio;
	private String estado;
	private String detalle;
	private Persona persona;
	private Elemento elemento;
	private Date fecha;	
	private TipoElemento tipoelemento;
	private int id_res;
	
	public TipoElemento getTipoelemento() {
		return tipoelemento;
	}
	public void setTipoelemento(TipoElemento tipoelemento) {
		this.tipoelemento = tipoelemento;
	}
	public Elemento getElemento() {
		return elemento;
	}
	public int getId_res() {
		return id_res;
	}
	public void setId_res(int id_res) {
		this.id_res = id_res;
	}
	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
		
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Time getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(Time hora_fin) {
		this.hora_fin = hora_fin;
	}
	public Date getFecha() {
		return    fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Time getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Reserva ( Date fecha, Time hora_inicio, Time hora_fin, String detalle, String estado){
		this.setFecha (fecha);
		this.setHora_inicio(hora_inicio);
		this.setHora_fin(hora_fin);
		this.setDetalle(detalle);
		this.setEstado(estado);
		
	}
	public Reserva (){
		
	}
	@Override
	public String toString(){
		return this.getTipoelemento().getNombre_TE();
	}
	
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_res()).hashCode();
	}
	@Override
	public boolean equals(Object r){
		return (r instanceof Reserva) &&
			 (((Reserva)r).getTipoelemento().getNombre_TE().equals(this.getTipoelemento().getNombre_TE()));
	}
}
