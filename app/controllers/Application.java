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

    public static Result addBook(){
        Form<Kniha> form = Form.form(Kniha.class).bindFromRequest();
        Kniha kniha = form.get();
        kniha.save();
        return redirect(routes.Application.index()); //funguje
    }

    public static Result getBooks(){
        List<Kniha> knihy = new Model.Finder(String.class, Kniha.class).all();
        return ok(toJson(knihy));
    }
}
