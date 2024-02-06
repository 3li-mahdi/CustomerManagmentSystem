package com.system.managment.customer.endpoint.dto;


import java.time.Instant;
import java.util.Objects;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class CustomerDto extends BaseDto {
		
	@NotNull
	@NotBlank
    private String name;
	private String address;
	
	@Pattern(regexp="(^$|[0-9]{15})")
	private String phoneNum;
	
	@Email(message = "Please provide a valid email address")
	private String email;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String name, String address, String phoneNum, String email, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.email = email;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, address, phoneNum, email);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDto other = (CustomerDto) obj;
		return Objects.equals(name, other.name) && Objects.equals(address, other.address)
				&& Objects.equals(phoneNum, other.phoneNum) && Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", address=" + address + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", fieldsString()=" + fieldsString() + "]";
	}

	
	
	
}
