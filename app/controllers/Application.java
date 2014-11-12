package controllers;

import models.Kniha;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is banana2."));
    }

    public static Result addKniha(){
        Form<Kniha> form = Form.form(Kniha.class).bindFromRequest();
        Kniha kniha = form.get();
        kniha.save();
        return redirect(routes.Application.index()); //breci ale funguje
    }
}
