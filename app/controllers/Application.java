package controllers;

import models.Kniha;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Vítejte v systému Knihovník 0.1"));
    }

    private static Result GO_HOME(){
        return redirect(routes.Application.index());
    }

    public static Result addBook(){
        Form<Kniha> form = Form.form(Kniha.class).bindFromRequest();
        Kniha kniha = form.get();
        kniha.save();
        return GO_HOME();
    }

    public static Result getBooks(){
        List<Kniha> knihy = new Model.Finder(String.class, Kniha.class).all();
        return ok(toJson(knihy));
    }

    public static Result deleteAll() {
        int databaseSize = Kniha.find.all().size();
        for (int i = 1; i < databaseSize+1 ;i++){
            Kniha.find.all().get(0).delete();
        }
        return GO_HOME();
    }

   /* public static Result delete(String id) {
        Kniha.find.byId(id).delete();
        System.out.println("smazal jsem knihu");
        return redirect(routes.Application.index());
    }*/

}
