package com.vorkylele;

import com.vorkylele.database.IOwnersManager;
import com.vorkylele.database.OwnersManager;
import com.vorkylele.database.OwnersManagerNG;
import com.vorkylele.database.domain.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddOwnerTest {

	private IOwnersManager om = new OwnersManagerNG();

	@Test
	void checkAddOwner() throws SQLException {
		open("http://localhost:8080/owners/find");
		$("a.btn").click();
		$("#firstName").setValue("Artem");
		$("#lastName").setValue("Vikhlyantsev");
		$("#address").setValue("Russia");
		$("#city").setValue("Perm");
		$("#telephone").setValue("55555");
		$("button.btn-primary").click();

		Owner actualOwner = om.findByLastName("Vikhlyantsev").get(0);
		assertEquals("Perm", actualOwner.getCity());
	}
}
