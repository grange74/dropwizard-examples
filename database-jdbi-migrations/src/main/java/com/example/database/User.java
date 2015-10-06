package com.example.database;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private final Integer id;
	
	@NotEmpty
	private final String name;
	
	@JsonCreator
	public User(@JsonProperty("id") Integer id, @JsonProperty("name") String name)
	{
		this.id = id;
		this.name = name;
	}
	
	@JsonProperty
	public Integer getId()
	{
		return this.id;
	}
	
	@JsonProperty
	public String getName()
	{
		return this.name;
	}
}
