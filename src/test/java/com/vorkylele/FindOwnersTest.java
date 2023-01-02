package com.vorkylele;

import com.vorkylele.database.IOwnersManager;
import com.vorkylele.database.domain.Owner;
import com.vorkylele.database.OwnersManagerNG;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FindOwnersTest {

	private IOwnersManager om = new OwnersManagerNG();
	private int createdOwnerId;

	@BeforeEach
	void addOwner() {
		createdOwnerId = om.createOwner(Owner.builder()
			.firstName("Artem")
			.lastName("Vikhlyantsev")
			.address("Russia")
			.city("Perm")
			.telephone("55555")
			.build());
	}

	@AfterEach
	void releaseOwner() {
		om.deleteOwner(createdOwnerId);
	}

	@RepeatedTest(2)
	void findOwnersTest() {
		System.out.println("### Generated id: " + createdOwnerId);
		open("http://localhost:8080/owners/find");
		$("#lastName").setValue("Vikhlyantsev");
		$("button[type='submit']").click();
		$("table.table").should(visible);
		$$("tr").find(text("Name")).should(text("Artem Vikhlyantsev"));
	}
}
