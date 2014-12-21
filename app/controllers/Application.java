package controllers;

import DAO.Impl.KnihaDAOImpl;

import models.Kniha;
import models.Uzivatel;
import play.data.Form;
import play.db.ebean.Model;
import DAO.Impl.UzivatelDAOImpl;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Knihy.editBook;
import views.html.Knihy.addBook;
import views.html.Knihy.showBooks;
import views.html.Uzivatele.login;
import views.html.Uzivatele.addUser;
import views.html.Uzivatele.showUsers;
import views.html.Sprava.manageDatabase;
import views.html.index;

import java.util.List;

public class Application extends Controller {

    public static String logged = null;

    public static Result index() {return ok(index.render("Vítejte v systému Knihovník", logged));}

    public static Result newBook() {return ok(addBook.render("Nova kniha", logged, null));}

    public static Result manageDatabase() {return ok(manageDatabase.render("Správa databáze", logged));}

    public static Result newUser() {return ok(addUser.render("Novy uzivatel", logged,null));}

    private static Result GO_HOME() {return redirect(routes.Application.index());}

    public static Result editBook(String id) {List<Kniha> knihy = new Model.Finder(String.class, Kniha.class).all();return ok(editBook.render(id,knihy, logged,null));}

    /**
     * Prida knihu do databaze.
     */
    public static Result addBook() {
        Form<Kniha> form = Form.form(Kniha.class).bindFromRequest();
        Kniha kniha = form.get();

        if(kontrolaPridaniKnihy(kniha)) {
            KnihaDAOImpl dao = new KnihaDAOImpl();
            dao.create(kniha);
            return newBook();
        }
        else{
            return ok(addBook.render("Nova kniha", logged, "Všechna pole musí být vyplněná"));
        }

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

        if(kontrolaPridaniUzivatele(uzivatel.getJmeno(),uzivatel.getHeslo())) {
            UzivatelDAOImpl dao = new UzivatelDAOImpl();
            dao.create(uzivatel);
            return newUser();
        }else{
            return ok(addUser.render("Novy uzivatel", logged,"Jméno je prázdné, nebo již existuje"));
        }

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
        Uzivatel uzivatel=null;
        vytvorAdmin();
        Form<Uzivatel> form = Form.form(Uzivatel.class).bindFromRequest();
        uzivatel = form.get();

        boolean spravneHeslo = kontrolaPrihlaseni(uzivatel.getJmeno(), uzivatel.getHeslo());
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
        int databaseSize = KnihaDAOImpl.find.all().size();
        for (int i = 1; i < databaseSize + 1; i++) {
            KnihaDAOImpl.find.all().get(0).delete();
        }
        return GO_HOME();
    }



    /**
     * Upravi zaznam v databazi.
     * @param name jmeno knihy
     */
    public static Result updateBook(String name) {
        List<Kniha> knihy = new Model.Finder(String.class, Kniha.class).all();
        Form<Kniha> computerForm = Form.form(Kniha.class).bindFromRequest();
        Kniha kniha = computerForm.get();


        if(kontrolaPridaniKnihy(kniha)){
            int id=0;
            for(int i = 0; i < knihy.size();i++){
                if(knihy.get(i).getNazev().equals(name)){
                    id=knihy.get(i).getId();
                }
            }


            computerForm.get().update((Object) id);

            return ok(editBook.render(name,knihy ,logged, null));
        }else{
            return ok(editBook.render(name,knihy ,logged, "Všechna pole musí být vyplněná"));
        }

    }
    /**
     * Kontrola jmena a hesla pri prihlaseni
     * @param jmeno jmeno prihlasovaneho uzivatele
     * @param heslo heslo prihlasovaneho uzivatele
     * @return pokud je jmeno a heslo zadano spravne, vraci se true. Jinak false.
     */
    public static boolean kontrolaPrihlaseni (String jmeno, String heslo){
        List<Uzivatel> uzivatele = new Model.Finder(String.class, Uzivatel.class).all();
        boolean kontrolaPrihlaseni=false;

        for (int i = 0; i < uzivatele.size(); i++) {
            if (uzivatele.get(i).getJmeno() != null) {
                if (uzivatele.get(i).getJmeno().equals(jmeno) && uzivatele.get(i).getHeslo().equals(heslo)) {
                    kontrolaPrihlaseni = true;
                }
            }
        }
        return kontrolaPrihlaseni;

    }
    /**
     * Pokud je databaze uzivatelu prazdna, vytvori se uzivatel admin
     */
    public static void vytvorAdmin(){
        List<Uzivatel> uzivatele = new Model.Finder(String.class, Uzivatel.class).all();
        UzivatelDAOImpl dao = new UzivatelDAOImpl();
        if(uzivatele.isEmpty()){
            Uzivatel admin = new Uzivatel("admin","admin");
            dao.create(admin);
        }
    }

    /**
     * Kontrola jmena a hesla pri pridavani uzivatele
     * @param jmeno jmeno pridavaneho uzivatele
     * @param heslo heslo pridavaneho uzivatele
     * @return true, kdyz je jmeno a heslo v poradu, jinak false.
     */
    public static boolean kontrolaPridaniUzivatele(String jmeno, String heslo){
        List<Uzivatel> uzivatele = new Model.Finder(String.class, Uzivatel.class).all();

        if("".equalsIgnoreCase(jmeno)){return false;}
        if("".equalsIgnoreCase(heslo)){return false;}
        for(int i=0; i< uzivatele.size();i++){
            if(jmeno.equalsIgnoreCase(uzivatele.get(i).getJmeno())){
                return false;
            }

        }
        return true;
    }

    public static boolean kontrolaPridaniKnihy(Kniha kniha){
        if(kniha.getAutor().equalsIgnoreCase("") || kniha.getNakladatelstvi().equalsIgnoreCase("") || kniha.getNazev().equalsIgnoreCase("")
                || kniha.getRokVydani().equalsIgnoreCase("")){
            return false;
        }
        return true;
    }

}
