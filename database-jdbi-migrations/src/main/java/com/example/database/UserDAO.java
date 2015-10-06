package com.example.database;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserDAO {
	@SqlUpdate("create table user (id int primary key, name varchar(255))")
	void createUserTable();

	@SqlUpdate("insert into user (id, name) values (:id, :name)")
	void insert(@Bind("id") int id, @Bind("name") String name);

	@SqlQuery("select name from user where id = :id")
	String findNameById(@Bind("id") int id);
}
