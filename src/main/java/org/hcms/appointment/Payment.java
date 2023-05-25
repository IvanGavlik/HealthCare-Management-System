//****************************************************************PAYMENT-CLASS******************************************************************************//
package org.hcms.appointment;

import java.util.*;

public final class Payment {
	private String name;
	private String number;
	private String expiryDate;
	private String cvcNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvcNumber() {
		return cvcNumber;
	}

	public void setCvcNumber(String cvcNumber) {
		this.cvcNumber = cvcNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Payment payment = (Payment) o;
		return Objects.equals(name, payment.name) && Objects.equals(number, payment.number) &&
				Objects.equals(expiryDate, payment.expiryDate) && Objects.equals(cvcNumber, payment.cvcNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, number, expiryDate, cvcNumber);
	}

	@Override
	public String toString() {
		return "Payment{" +
				"name='" + name + '\'' +
				", number='" + number + '\'' +
				", expiryDate='" + expiryDate + '\'' +
				", cvcNumber='" + cvcNumber + '\'' +
				'}';
	}
}

