package com.example.demo.model.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	@Column
	private Long id;

	@OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
	@JsonProperty
	private User user;

	@ManyToMany
	@JsonProperty
	@Column
	private List<Item> items;

	@Column
	@JsonProperty
	private BigDecimal total;

	//command adds the item to list
	public void addItem(Item item) {
		if(items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
		if(total == null) {
			total = new BigDecimal(0);
		}
		total = total.add(item.getPrice());
	}

	//command removes the item from list
	public void removeItem(Item item) {
		if(items == null) {
			items = new ArrayList<>();
		}
		items.remove(item);
		if(total == null) {
			total = new BigDecimal(0);
		}
		total = total.subtract(item.getPrice());
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
