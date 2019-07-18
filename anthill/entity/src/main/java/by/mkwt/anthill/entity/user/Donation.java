package by.mkwt.anthill.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.entity.project.Benefit;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Positive;

@Entity
@Table(name = "donations")
public class Donation implements Identifiable, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donation_id")
	private int id;
	
	@NotNull
	@Positive
	@Column(name = "donation_value")
	private BigDecimal value;
	
	@NotNull	
	@Column(name = "donation_date")
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name = "benefit_id")
	@NotNull
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "benefit_id", attrName = "id", joinName = "benefit", type = Integer.class)
	})
	private Benefit benefit;
	
	@ManyToOne
	@JoinColumn(name = "user_id")	
	@NotNull
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "user_id", attrName = "id", joinName = "user", type = Integer.class)
	})
	private User user; 
	
	protected Donation() {
		date = LocalDateTime.now();
	}

	public Donation(BigDecimal value, Benefit benefit, User user) {
		this();
		this.value = value;
		this.benefit = benefit;
		this.user = user;
	}
	
	public Donation(Benefit benefit, User user) {
		this(new BigDecimal(0), benefit, user);
	}
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Benefit getBenefit() {
		return benefit;
	}

	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", value=" + value + ", date=" + date + ", benefit=" + benefit + ", user=" + user
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((benefit == null) ? 0 : benefit.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Donation other = (Donation) obj;
		if (benefit == null) {
			if (other.benefit != null)
				return false;
		} else if (!benefit.equals(other.benefit))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
