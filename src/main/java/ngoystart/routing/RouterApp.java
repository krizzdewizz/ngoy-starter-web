package ngoystart.routing;

import static java.lang.String.format;

import java.util.List;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.NgModule;
import ngoy.router.Route;
import ngoy.router.Router;
import ngoy.translate.TranslateService;

@Component(selector = "", templateUrl = "app.component.html")
@NgModule(declarations = { LocaleParamPipe.class })
public class RouterApp {
	public final String appName = "Router";

	@Inject
	public Router router;

	@Inject
	public TranslateService translateService;

	public List<Route> getRoutes() {
		return router.getRoutes();
	}

	public boolean isActiveRoute(Route route) {
		return router.isActive(route);
	}

	public String routeTitle(Route route) {
		return translateService.translate(format("MSG_%s", route.getPath()
				.toUpperCase()));
	}

}
