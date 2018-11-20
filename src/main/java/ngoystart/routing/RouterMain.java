package ngoystart.routing;

import static ngoy.core.Provider.useValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ngoy.Ngoy;
import ngoy.core.LocaleProvider;
import ngoy.router.Location;
import ngoy.router.RouterConfig;
import ngoy.router.RouterModule;
import ngoystart.routing.home.HomeComponent;
import ngoystart.routing.settings.SettingsComponent;

@Controller
@RequestMapping("/router/*")
public class RouterMain implements InitializingBean {

	// must be disabled in production!
	private static final boolean DEV = true;

	private Ngoy<RouterApp> ngoy;

	@Autowired
	private HttpServletRequest request;

	@GetMapping()
	public void home(HttpServletResponse response) throws Exception {
//		ngoy.renderSite(java.nio.file.Paths.get(System.getProperty("java.io.tmpdir"), "ngoy-starter-web-router"));

		if (DEV) {
			createApp();
		}

		ngoy.render(response.getOutputStream());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		createApp();
	}

	private void createApp() {
		RouterConfig routerConfig = RouterConfig //
				.baseHref("/router")
				.location(useValue(Location.class, () -> request.getRequestURI()))
				.route("index", HomeComponent.class)
				.route("settings", SettingsComponent.class)
				.build();

		ngoy = Ngoy.app(RouterApp.class)
				.modules(RouterModule.forRoot(routerConfig))
				.translateBundle("messages")
				.providers(useValue(LocaleProvider.class, () -> LocaleContextHolder.getLocale()))
				.build();
	}
}