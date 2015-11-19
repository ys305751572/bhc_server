package com.gcs.aol.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "aolchild_relation")
public class AolChildRelation implements Serializable {
	
	private static final long serialVersionUID = -2571308010092743039L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@OneToOne
	@JoinColumn(name = "AOL_USER_ID")
	private AolUser aolUser;
	
	@Column(name = "CHILD_ID")
	private String childId;
	
	@Column(name = "FNOTE")
	private String fnote;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AolUser getAolUser() {
		return aolUser;
	}

	public void setAolUser(AolUser aolUser) {
		this.aolUser = aolUser;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public String getFnote() {
		return fnote;
	}

	public void setFnote(String fnote) {
		this.fnote = fnote;
	}
}
