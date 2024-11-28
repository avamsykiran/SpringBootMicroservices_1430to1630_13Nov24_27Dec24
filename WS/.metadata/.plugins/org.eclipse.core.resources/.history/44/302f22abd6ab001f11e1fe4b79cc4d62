package com.cts.adb.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contactId;
	private String fullName;
	private String mobile;
	private String mailId;
	
	public Contact() {
		super();
	}
	
	public Contact(Long contactId, String fullName, String mobile, String mailId) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.mobile = mobile;
		this.mailId = mailId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactId, fullName, mailId, mobile);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(contactId, other.contactId) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(mailId, other.mailId) && Objects.equals(mobile, other.mobile);
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", fullName=" + fullName + ", mobile=" + mobile + ", mailId="
				+ mailId + "]";
	}
	
}
