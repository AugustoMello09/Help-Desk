package io.gitHub.AugustoMello09.helpDesk.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String nome;
	
	@Column(unique = true)
	private String email;
	
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usuario_cargo", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "cargo_id"))
	private Set<Cargo> cargos = new HashSet<>();

	public Usuario() {
	}

	public Usuario(UUID id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return cargos.stream().map(x -> new SimpleGrantedAuthority(x.getAuthority()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(Set<Cargo> cargos) {
		this.cargos = cargos;
	}

	
	
}
