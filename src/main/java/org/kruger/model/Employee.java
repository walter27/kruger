package org.kruger.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "KRUG_EMPLOYEE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "habdler" })
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID_PR")
	private Long id;

	@Column(name = "EMP_CEDULA")
	@NotEmpty(message = "Cedula is mandatory")
	@Size(min = 10, message = "Cedula should have at least 10 characters")
	@Pattern(regexp = "^[0-9]+$", message = "Cedula invalid")
	private String cedula;

	@Column(name = "EMP_NAMES")
	@NotEmpty(message = "Names is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Names invalid")
	private String names;

	@Column(name = "EMP_LAST_NAMES")
	@NotEmpty(message = "Last names is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Last names invalid")
	private String lastNames;

	@Column(name = "EMP_EMAIL")
	@NotEmpty(message = "Email is mandatory")
	@Email(regexp = "^(.+)@(\\\\S+)$", message = "Email invalid")
	private String email;

	@Column(name = "EMP_BIRTH_DATE")
	@Past(message = " Birth date invalid")
	private Date birthDate;

	@Column(name = "EMP_ADDRESS")
	private String address;

	@Column(name = "EMP_TELEPHONE")
	private String telephone;

	@Column(name = "EMP_VACCINATION")
	private Boolean stateVaccination;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Boolean getStateVaccination() {
		return stateVaccination;
	}

	public void setStateVaccination(Boolean stateVaccination) {
		this.stateVaccination = stateVaccination;
	}

	public Employee() {
		super();
	}
}
