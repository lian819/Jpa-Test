package jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PROJECT")
public class Project {
	private Long id;
	private String title;
	private List<Geek> geeks = new ArrayList<Geek>();
	private Period projectPeriod;
	private List<Period> billingPeriods = new ArrayList<Period>();
	private ProjectType projectType;

	public enum ProjectType {
		FIXED, TIME_AND_MATERIAL;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToMany
	@JoinTable(name = "T_GEEK_PROJECT", 
			joinColumns = { @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "GEEK_ID", referencedColumnName = "ID") })
	public List<Geek> getGeeks() {
		return geeks;
	}

	public void setGeeks(List<Geek> geeks) {
		this.geeks = geeks;
	}

	@Embedded
	public Period getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(Period projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	@ElementCollection
	@CollectionTable(
			name="T_BILLING_PERION",
			joinColumns=@JoinColumn(name="PROJECT_ID"))
	public List<Period> getBillingPeriods() {
		return billingPeriods;
	}

	public void setBillingPeriods(List<Period> billingPeriods) {
		this.billingPeriods = billingPeriods;
	}

	@Enumerated(EnumType.ORDINAL)
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

}
