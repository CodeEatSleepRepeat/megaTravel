package megatravel.bezbednost.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//Tabela sa dva atributa koji su serijski brojevi sertifikata koji mogu medjusobno da komuniciraju.

@Entity
@Table(name="dozvola_komunikacije")
public class CertificateCommunicationModel {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private BigInteger serijskiBroj1;
	
	@NotNull
	private BigInteger serijskiBroj2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getSerijskiBroj1() {
		return serijskiBroj1;
	}

	public void setSerijskiBroj1(BigInteger serijskiBroj1) {
		this.serijskiBroj1 = serijskiBroj1;
	}

	public BigInteger getSerijskiBroj2() {
		return serijskiBroj2;
	}

	public void setSerijskiBroj2(BigInteger serijskiBroj2) {
		this.serijskiBroj2 = serijskiBroj2;
	}
	
	

}
