package ngoystart.routing;

import static ngoy.core.Provider.useValue;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ngoy.Ngoy;
import ngoy.core.LocaleProvider;
import ngoy.core.TemplateCache;
import ngoy.router.Location;
import ngoy.router.RouterConfig;
import ngoy.router.RouterModule;
import ngoystart.routing.home.HomeComponent;
import ngoystart.routing.settings.SettingsComponent;

@Controller
@RequestMapping("/router/*")
public class RouterMain implements InitializingBean {

	private Ngoy<RouterApp> ngoy;

	@Autowired
	private HttpServletRequest request;

	private Locale locale = Locale.getDefault();

	@GetMapping()
	public void home(@RequestParam(name = "locale", required = false, defaultValue = "en") String locale, HttpServletResponse response) throws Exception {
//		ngoy.renderSite(java.nio.file.Paths.get("d:/downloads/router-site"));

		this.locale = new Locale(locale);

		ngoy.render(response.getOutputStream());
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		// do not disable in production
		TemplateCache.DEFAULT.setDisabled(true);

		RouterConfig routerConfig = RouterConfig //
				.baseHref("/router")
				.location(useValue(Location.class, () -> request.getRequestURI()))
				.route("index", HomeComponent.class)
				.route("settings", SettingsComponent.class)
				.build();

		ngoy = Ngoy.app(RouterApp.class)
				.modules(RouterModule.forRoot(routerConfig))
				.translateBundle("messages")
				.providers(useValue(LocaleProvider.class, () -> locale))
				.build();
	}
}