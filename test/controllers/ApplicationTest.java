package controllers;

import models.Kniha;
import models.Uzivatel;
import org.junit.Test;
import play.data.Form;
import play.libs.F;
import play.mvc.HandlerRef;
import play.mvc.Result;
import play.test.TestBrowser;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;

public class ApplicationTest {

    @Test
    public void testIndex() throws Exception {
        Result result = Application.index();
        assertEquals(status(result),200);
    }

    @Test
    public void testNewBook() throws Exception {
        Result result = Application.newBook();
        assertEquals(status(result),200);
    }

    @Test
    public void testManageDatabase() throws Exception {
        Result result = Application.manageDatabase();
        assertEquals(status(result),200);
    }

    @Test
    public void testNewUser() throws Exception {
        Result result = Application.newUser();
        assertEquals(status(result),200);
    }

    @Test
    public void testEditBook() throws Exception {
        Result result = Application.editBook("name");
        assertEquals(status(result),200);
    }

    @Test
    public void testAddBook() throws Exception {
        assertEquals(200, 100);
    }

    @Test
    public void testGetBooks() throws Exception {
        Result result = Application.getBooks();
        assertEquals(status(result),200);
    }

    @Test
    public void testLogin() throws Exception {
        Result result = Application.login();
        assertEquals(status(result),200);
    }

    @Test
    public void testLogout() throws Exception {
        Result result = Application.logout();
        assertEquals(status(result),200);
    }

    @Test
    public void testAddUser() throws Exception {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Lorem ipsum");
            }
        });



        running(fakeApplication(), new Runnable() {
            public void run() {
                String name = "name";
                String password= "password";

                Map<String, String> userData = new HashMap<String, String>();
                userData.put("jmeno", name);
                userData.put("heslo", password);


                Result r = callAction((HandlerRef) Application.addBook(), fakeRequest()
                        .withFormUrlEncodedBody(Form.form(Uzivatel.class).bind(userData).data()));

                assertEquals(200, status(r));
            }
        });

    }

    @Test
    public void testGetUsers() throws Exception {
        assertEquals(200, 100);
    }

    @Test
    public void testLoginUser() throws Exception {
        assertEquals(200, 100);
    }

    @Test
    public void testDeleteAll() throws Exception {
        Result result = Application.deleteAll();
        assertEquals(status(result),200);
    }

    @Test
    public void testUpdateBook() throws Exception {
        assertEquals(200, 100);
    }

    @Test
    public void testKontrolaPrihlaseni() throws Exception {
        assertEquals(200, 100);
    }

    @Test
    public void testVytvorAdmin() throws Exception {
        assertEquals(200, 100);
    }

    @Test
    public void testKontrolaPridaniUzivatele() throws Exception {
        assertEquals(true,Application.kontrolaPridaniUzivatele("Jmeno1","Heslo1"));


    }

    @Test
    public void testKontrolaPridaniKnihy() throws Exception {
       Kniha kniha = new Kniha();
        kniha.setAutor("Autor");
        kniha.setNakladatelstvi("Nakladatelstvi");
        kniha.setNazev("Nazev");
        kniha.setRokVydani("1234");

        assertEquals(true,Application.kontrolaPridaniKnihy(kniha));

    }
}