package controllers;

import DAO.Impl.KnihaDAOImpl;
import models.*;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;
import DAO.Impl.UzivatelDAOImpl;
import views.html.*;
import views.html.Knihy.editBook;
import views.html.Knihy.addBook;
import views.html.Knihy.showBooks;
import views.html.Uzivatele.login;
import views.html.Uzivatele.addUser;
import views.html.Uzivatele.showUsers;
import views.html.Sprava.manageDatabase;

import java.util.List;

public class Application extends Controller {

    public static String logged = null;

    public static Result index() {return ok(index.render("Vítejte v systému Knihovník", logged));}

    public static Result newBook() {return ok(addBook.render("Nova kniha", logged));}

    public static Result manageDatabase() {return ok(manageDatabase.render("Správa databáze", logged));}

    public static Result newUser() {return ok(addUser.render("Novy uzivatel", logged));}

    public static Result editBook(String id) {return ok(editBook.render(id, logged));}

    private static Result GO_HOME() {return redirect(routes.Application.index());}

    /**
     * Prida knihu do databaze.
     */
    public static Result addBook() {
        Form<Kniha> form = Form.form(Kniha.class).bindFromRequest();
        Kniha kniha = form.get();
        kniha.save();
        return newBook();
    }

    /**
     * Vypise seznam knih v databazi.
     */
    public static Result getBooks() {
        List<Kniha> knihy = new Model.Finder(String.class, Kniha.class).all();
        return ok(showBooks.render("Seznam knih", knihy, logged));
    }

    /**
     * Prihlaseni do systemu.
     */
    public static Result login() {
        return ok(login.render("Prihlasit", logged, "Přihlaš se:"));
    }

    /**
     * Odhlaseni ze systemu.
     */
    public static Result logout() {
        logged = null;
        return ok(login.render("Prihlasit", logged, "Přihlaš se:"));
    }

    /**
     * Prida uzivatele do systemu.
     */
    public static Result addUser() {

        Form<Uzivatel> form = Form.form(Uzivatel.class).bindFromRequest();
        Uzivatel uzivatel = form.get();
        UzivatelDAOImpl dao = new UzivatelDAOImpl();
        dao.create(uzivatel);
        return newUser();
    }

    /**
     * Vrati seznam uzivatelu v databazi.
     */
    public static Result getUsers() {
        List<Uzivatel> uzivatele = new Model.Finder(String.class, Uzivatel.class).all();
        return ok(showUsers.render("Seznam uživatelů", uzivatele, logged));
    }


    /**
     * Prihlaseni uzivatele do systemu.
     */
    public static Result loginUser() {
        Form<Uzivatel> form = Form.form(Uzivatel.class).bindFromRequest();
        Uzivatel uzivatel = form.get();

        boolean spravneHeslo = uzivatel.kontrolaPrihlaseni(uzivatel.getJmeno(), uzivatel.getHeslo());
        if (spravneHeslo) {
            logged = uzivatel.jmeno;
            return GO_HOME();
        } else {
            return ok(login.render("Prihlasit", logged, "Špatně zadané jméno nebo heslo, zkuz to znovu:"));
        }
    }

    /**
     * Smaze vsechny knihy z databaze.
     */
    public static Result deleteAll() {
        int databaseSize = Kniha.find.all().size();
        for (int i = 1; i < databaseSize + 1; i++) {
            Kniha.find.all().get(0).delete();
        }
        return GO_HOME();
    }

    /**
     * Upravi zaznam v databazi.
     *
     * @id - identifikacni kod, primarni klic v databazi
     */
    public static Result updateBook(String id) {
        Form<Kniha> computerForm = Form.form(Kniha.class).bindFromRequest();
        computerForm.get().update((Object) id);

        return ok(editBook.render(id, logged));
    }

}
