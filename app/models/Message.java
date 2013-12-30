package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.ValidateWith;
import play.data.validation.Constraints.Validator;
import play.db.ebean.Model;
import play.libs.F;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Message extends Model {

    @Id
    public Long id;
    @Required(message = "必須項目です")
    public String name;
    @Email(message = "メールアドレスを記入下さい")
    public String mail;
    @Required
    //@MinLength(10)
    //@MaxLength(200)
    //@Pattern(message = "半角英数字のみ", value = "[a-zA-Z]+")
    @ValidateWith(value = IsUrl.class, message = "URLのみ")
    public String message;
    @CreatedTimestamp
    public Date postdate;

    @OneToOne(cascade = CascadeType.ALL)
    public Member member;

    public static Finder<Long, Message> find = new Finder<Long, Message>(Long.class, Message.class);

    @Override
    public String toString() {
	return ("[id:" + id + ", member:<" + member.name + ", " + member.mail + ">" + ", name:" + name + ", mail:" + mail + " message:" + message
		+ ", date:" + postdate + "]");
    }

    public static class IsUrl extends Validator<String> {
	public boolean isValid(String s) {
	    return s.toLowerCase().startsWith("http://");
	}

	@Override
	public F.Tuple<String, Object[]> getErrorMessageKey() {
	    return new F.Tuple<String, Object[]>("error.invalid", new String[] {});
	}
    }

    public static Message findByName(String input) {
	return Message.find.where().eq("name", input).findList().get(0);
    }

}
