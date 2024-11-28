package com.cts.adb.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contactId;
	
	@NotBlank(message = "Full Name is a mandatory field")
	@Size(min=5,max=30,message = "Full Name is expected to be of length between 5 and 30 letters")
	private String fullName;
	
	@NotBlank(message = "Mobile is a mandatory field")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Mobile is expected to be a ten digit number")
	private String mobile;
	
	@NotBlank(message = "MailId is a mandatory field")
	@Email(message = "Expecting a valid email id")
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
