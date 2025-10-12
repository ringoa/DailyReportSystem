package com.sutaruhin.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authentication")
public class Authentication {

	public static enum Role{
		一般, 管理者
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;

	@Column(length = 20, nullable = false, unique = true)
	@NotEmpty(message = "社員番号は必須です")
	@Length(max=20, message="社員番号は20字までです")
	private String code;

	@Column(length = 255, nullable = false)
	@NotEmpty(message = "パスワードは必須です")
	@Length(max=225, message="パスワードは225文字までです")
	private String password;

	@Column(length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private Role role;

	@Column(length = 10, nullable = true)
	private LocalDate validDate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="employee_id", referencedColumnName="id")
	@Valid
	private Employee employee;
}
