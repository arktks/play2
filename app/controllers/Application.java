package controllers;

import java.util.List;

import models.Member;
import models.Message;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;
import views.html.add2;
import views.html.delete;
import views.html.edit;
import views.html.find;
import views.html.index;
import views.html.item;

public class Application extends Controller {

    public static class SampleForm {
	public String message;
    }

    public static class FindForm {
	@Required
	public String input;
    }

    public static Result index() {
	List<Message> datas = Message.find.all();
	List<Member> datas2 = Member.find.all();
	return ok(index.render("データベースのサンプル", datas, datas2));
    }

    // Message Action =============================

    public static Result add() {
	Form<Message> f = new Form(Message.class);
	return ok(add.render("投稿フォーム", f));
    }

    public static Result create() {
	Form<Message> f = new Form(Message.class).bindFromRequest();
	if (!f.hasErrors()) {
	    Message data = f.get();
	    data.member = Member.findByName(data.name);
	    data.save();
	    return redirect("/");
	} else {
	    return badRequest(add.render("ERROR", f));
	}
    }

    // Member Action =============================
    
    public static Result add2() {
	Form<Member> f = new Form(Member.class);
	return ok(add2.render("投稿フォーム", f));
    }

    public static Result create2() {
	Form<Member> f = new Form(Member.class).bindFromRequest();
	if (!f.hasErrors()) {
	    Member data = f.get();
	    data.save();
	    return redirect("/");
	} else {
	    return badRequest(add2.render("ERROR", f));
	}
    }

    public static Result setitem() {
	Form<Message> f = new Form(Message.class);
	return ok(item.render("IDを入力", f));
    }

    public static Result edit() {
	Form<Message> f = new Form(Message.class).bindFromRequest();
	if (!f.hasErrors()) {
	    Message obj = f.get();
	    Long id = obj.id;
	    obj = Message.find.byId(id);
	    if (obj != null) {
		f = new Form(Message.class).fill(obj);
		return ok(edit.render("ID=" + id + "の投稿を編集", f));
	    } else {
		return ok(item.render("ERROR:IDの投稿が見つかりません", f));
	    }
	} else {
	    return ok(item.render("ERROR:入力に問題があります", f));
	}
    }

    public static Result update() {
	Form<Message> f = new Form(Message.class).bindFromRequest();
	if (!f.hasErrors()) {
	    Message data = f.get();
	    data.update();
	    return redirect("/");
	} else {
	    return ok(edit.render("ERROR:再度入力してください", f));
	}
    }

    public static Result delete() {
	Form<Message> f = new Form(Message.class);
	return ok(delete.render("削除するID", f));
    }

    public static Result remove() {
	Form<Message> f = new Form(Message.class).bindFromRequest();
	if (!f.hasErrors()) {
	    Message obj = f.get();
	    Long id = obj.id;
	    obj = Message.find.byId(id);
	    if (obj != null) {
		obj.delete();
		return redirect("/");
	    } else {
		return ok(delete.render("EORROR:そのIDは見つかりません", f));
	    }
	} else {
	    return ok(delete.render("ERROR:入力にエラーが起こりました", f));
	}
    }

    public static Result find() {
	Form<FindForm> f = new Form(FindForm.class).bindFromRequest();
	List<Message> datas = null;
	if (!f.hasErrors()) {
	    String input = f.get().input;
	    String[] arr = input.split(",");
	    String q = "name like '%" + input + "%'";
	    datas = Message.find.where(q).findList();
	}
	return ok(find.render("投稿の検索", f, datas));
    }
}
