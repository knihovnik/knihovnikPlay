package controllers;

import models.Kniha;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;
import views.html.Knihy.editBook;


import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Vítejte v systému Knihovník 0.1"));
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
        return GO_HOME();
    }

    /**
     * Vypise knihy jako Json.
     */
    public static Result getBooks() {
        List<Kniha> knihy = new Model.Finder(String.class, Kniha.class).all();
        return ok(toJson(knihy));
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
        return ok(editBook.render(id));
    }

    /**
     * Updatne zaznam v databázi.
     * id - id zaznamu
     */
    public static Result updateBook(String id){
        Form<Kniha> computerForm = Form.form(Kniha.class).bindFromRequest();
        computerForm.get().update((Object)id);

        return ok(editBook.render(id));
    }


}
