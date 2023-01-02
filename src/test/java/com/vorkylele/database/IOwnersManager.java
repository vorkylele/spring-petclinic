package com.vorkylele.database;

import com.vorkylele.database.domain.Owner;

import java.sql.SQLException;
import java.util.List;

public interface IOwnersManager {
	int createOwner(Owner owner);

	void deleteOwner(int id);

	List<Owner> findByLastName(String lastName) throws SQLException;
}
