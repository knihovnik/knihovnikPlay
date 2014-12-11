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

import static play.libs.Json.toJson;

public class Application extends Controller {


    public static String logged = null;

    public static Result index() {


            return ok(index.render("Vítejte v systému Knihovník",logged));

    }

    public static Result newBook(){
        return ok(addBook.render("Nova kniha",logged));
    }

    private static Result GO_HOME(){
        return redirect(routes.Application.index());

    }


    /**
     * Prida knihu do databaze.
     */
    public static Result addBook(){
        Form<Kniha> form = Form.form(Kniha.class).bindFromRequest();
        Kniha kniha = form.get();
        kniha.save();
        return newBook();
    }

    /**
     * Vypise knihy.
     */
    public static Result getBooks() {
        List<Kniha> knihy = new Model.Finder(String.class, Kniha.class).all();


        return ok(showBooks.render("Seznam knih",knihy,logged));
    }

    /**
     *Pokud neexistuje admin tak se vytvori
     */
    public static Result login(){
        return ok(login.render("Prihlasit",logged,"Přihlaš se:"));
    }

    public static Result logout(){
        logged=null;
        return ok(login.render("Prihlasit", logged, "Přihlaš se:"));
    }

    public static Result manageDatabase(){
        return ok(manageDatabase.render("Správa databáze",logged));
    }

    public static Result newUser(){
        return ok(addUser.render("Novy uzivatel",logged));
    }

    public static Result addUser(){


        Form<Uzivatel> form = Form.form(Uzivatel.class).bindFromRequest();
        Uzivatel uzivatel = form.get();
        UzivatelDAOImpl dao = new UzivatelDAOImpl();

        dao.create(uzivatel);
        return newUser();


    }
    public static Result getUsers(){
        List<Uzivatel> uzivatele = new Model.Finder(String.class, Uzivatel.class).all();


        return ok(showUsers.render("Seznam uživatelů",uzivatele,logged));

    }

    public static Result loginUser(){
        Form<Uzivatel> form = Form.form(Uzivatel.class).bindFromRequest();
        Uzivatel uzivatel = form.get();

        boolean zpravneHeslo = uzivatel.kontrolaPrihlaseni(uzivatel.getJmeno(),uzivatel.getHeslo());
    if(zpravneHeslo){
        logged = uzivatel.jmeno;
        return GO_HOME();
    }
        else{
        return ok(login.render("Prihlasit",logged,"Špatně zadané jméno nebo heslo, zkuz to znovu:"));
    }


    }



    /**
     * Smaze vsechny knihy z databaze.
     */
    public static Result deleteAll() {
        int databaseSize = Kniha.find.all().size();
        for (int i = 1; i < databaseSize+1 ;i++){
            Kniha.find.all().get(0).delete();

        }
        return GO_HOME();
    }

    /**
     * Zobrazi formular na editovani zaznamu v databazi.
     * id - id zaznamu
     */
    public static Result editBook(String id){
        return ok(editBook.render(id,logged));
    }

    /**
     * Updatne zaznam v databázi.
     * id - id zaznamu
     */
    public static Result updateBook(String id){
        Form<Kniha> computerForm = Form.form(Kniha.class).bindFromRequest();
        computerForm.get().update((Object)id);

        return ok(editBook.render(id,logged));
    }


}
