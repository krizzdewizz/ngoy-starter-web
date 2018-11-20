package ngoystart.simple;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ngoy.Ngoy;
import ngoy.core.Context;

@Controller
public class GreetingViewController {

	@GetMapping(path = "/")
	public void greeting(@RequestParam(name = "name", required = false, defaultValue = "world") String name, HttpServletResponse response) throws Exception {

		// simply render a template, bind to variables. No 'app' needed.

		Context ctx = Context.of()
				.variable("name", name);
		Ngoy.renderTemplate("/templates/greeting.html", ctx, response.getOutputStream());
	}
}