package controllers;

import static play.data.Form.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static class SampleForm {
	public String message;
    }

    public static Result send() {
	Form<SampleForm> f = form(SampleForm.class).bindFromRequest();
	if (!f.hasErrors()) {
	    SampleForm data = f.get();
	    String msg = "you typed: " + data.message;
	    return ok(index.render(msg, f));
	} else {
	    return badRequest(index.render("ERROR", form(SampleForm.class)));
	}
    }

    public static Result index() {
	return ok(index.render("Your new application is ready.", new Form(SampleForm.class)));
    }

}
