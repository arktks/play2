package controllers;

import java.util.List;

import models.Message;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;
import views.html.index;

public class Application extends Controller {

    public static class SampleForm {
	public String message;
    }

    public static Result index() {
	List<Message> datas = Message.find.all();
	return ok(index.render("データベースのサンプル", datas));
    }

    public static Result add() {
	Form<Message> f = new Form(Message.class);
	return ok(add.render("データベースのサンプル", f));
    }

    public static Result create() {
	Form<Message> f = new Form(Message.class).bindFromRequest();
	return redirect("/");
    }

}
