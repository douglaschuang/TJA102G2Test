package com.babymate.member.model;

import java.io.Serializable;
import java.sql.*;
import java.time.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.babymate.cart.model.CartVO;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class MemberVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MemberVO() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, updatable = false)
	private Integer memberId;
	
	@Column(name = "account", nullable = false, unique = true, length = 50)
	private String account; // email
	
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email_verified", nullable = false)
	private byte emailVerified;
	
	@Column(name = "register_date", nullable = false)
	private LocalDateTime registerDate;
	
	@Column(name = "last_login_time", nullable = false)
	private LocalDateTime lastLoginTime;
	
	@Column(name = "account_status", nullable = false)
	private byte accountStatus;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "recipient_name")
	private String recipientName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender; 
	
	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;
	
	@Lob
	@Column(name = "profile_picture", columnDefinition = "LONGBLOB")
	private byte[] profilePicture; 
	
	@Column(name = "email_auth_token")
	private String emailAuthToken; 
	
	@Column(name = "pwd_reset_token")
	private String pwdResetToken;
	
	@Column(name = "pwd_reset_expire")
	private LocalDateTime pwdResetExpire;

	@Column(name = "update_date", nullable = false)
	private LocalDateTime updateDate;
	
//	private Set<CartVO> carts = new HashSet<CartVO>();
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(byte emailVerified) {
		this.emailVerified = emailVerified;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public byte getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(byte accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getEmailAuthToken() {
		return emailAuthToken;
	}

	public void setEmailAuthToken(String emailAuthToken) {
		this.emailAuthToken = emailAuthToken;
	}

	public String getPwdResetToken() {
		return pwdResetToken;
	}

	public void setPwdResetToken(String pwdResetToken) {
		this.pwdResetToken = pwdResetToken;
	}

	public LocalDateTime getPwdResetExpire() {
		return pwdResetExpire;
	}

	public void setPwdResetExpire(LocalDateTime pwdResetExpire) {
		this.pwdResetExpire = pwdResetExpire;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
	
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="cartVO")
//	@OrderBy("memberId asc")
//	//註1:【現在是設定成 cascade="all" lazy="false" inverse="true"之意】
//	//註2:【mappedBy="多方的關聯屬性名"：用在雙向關聯中，把關係的控制權反轉】【deptVO是EmpVO的屬性】
//	//註3:【原預設為@OneToMany(fetch=FetchType.LAZY, mappedBy="deptVO")之意】--> 【是指原為  lazy="true"  inverse="true"之意】
//	//FetchType.EAGER : Defines that data must be eagerly fetched
//	//FetchType.LAZY  : Defines that data can be lazily fetched
//	public Set<CartVO> getCarts() {
//		return this.carts;
//	}
//
//	public void setCarts(Set<CartVO> carts) {
//		this.carts = carts;
//	}

	@Override
	public String toString() {
		int picSize = 0;
		
		try {
			picSize = profilePicture.length;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		return "Member [memberId=" + memberId + ", account=" + account + ", password=" + password + ", name=" + name
				+ ", emailVerified=" + emailVerified + ", registerDate=" + registerDate + ", lastLoginTime="
				+ lastLoginTime + ", accountStatus=" + accountStatus + ", phone=" + phone + ", recipientName="
				+ recipientName + ", address=" + address + ", gender=" + gender + ", birthday=" + birthday
				+ ", profilePicture size=" + picSize /*Arrays.toString(profilePicture)*/ + ", emailAuthToken=" + emailAuthToken
				+ ", pwdResetToken=" + pwdResetToken + ", pwdResetExpire=" + pwdResetExpire + ", updateDate="
				+ updateDate + "]";
	}

	public enum Gender{
		  男,
		  女,
		  其他
		}
}
