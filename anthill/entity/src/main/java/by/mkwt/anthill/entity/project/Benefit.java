package by.mkwt.anthill.entity.project;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.entity.user.Donation;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.NotEmptyString;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Positive;

@Entity
@Table(name = "benefits")
public class Benefit implements Identifiable, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "benefit_id")
	private int id;
	
	@Positive
	@NotNull
	private BigDecimal price;
	
	@NotNull
	@NotEmptyString
	@Column(name = "benefit_description")
	private String benefitDescription;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	@NotNull
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "project_id", attrName = "id", joinName = "project", type = Integer.class)
	})
	private Project project;

	@OneToMany(mappedBy = "benefit")
	@JsonIgnore
	private Set<Donation> donations;
	
	public Benefit(BigDecimal price, String benefitDescription, Project project) {
		this.price = price;
		this.benefitDescription = benefitDescription;
		this.project = project;
	}

	public Benefit(String benefitDescription, Project project) {
		this();
		this.benefitDescription = benefitDescription;
		this.project = project;
	}
	
	protected Benefit() {
		price = new BigDecimal(0);
	}
	
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBenefitDescription() {
		return benefitDescription;
	}

	public void setBenefitDescription(String benefitDescription) {
		this.benefitDescription = benefitDescription;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	@Override
	public String toString() {
		return "Benefit [id=" + id + ", price=" + price + ", benefitDescription=" + benefitDescription + ", project="
				+ project + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((benefitDescription == null) ? 0 : benefitDescription.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benefit other = (Benefit) obj;
		if (benefitDescription == null) {
			if (other.benefitDescription != null)
				return false;
		} else if (!benefitDescription.equals(other.benefitDescription))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}
	
}
