package ngoystart.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ngoy.Ngoy;

@Controller
@RequestMapping("/*")
public class Main implements InitializingBean {

	// must be disabled in production!
	private static final boolean DEV = true;

	private Ngoy<AppComponent> ngoy;

	@Override
	public void afterPropertiesSet() throws Exception {
		createApp();
	}

	private void createApp() {
		ngoy = Ngoy.app(AppComponent.class)
				.translateBundle("messages")
				.build();
	}

	@GetMapping()
	public void home(HttpServletResponse response) throws Exception {
		if (DEV) {
			createApp();
		}
		ngoy.render(response.getOutputStream());
	}
}