package ngoystart.routing;

import static java.lang.String.format;

import ngoy.core.Inject;
import ngoy.core.LocaleProvider;
import ngoy.core.Pipe;
import ngoy.core.PipeTransform;
import ngoy.router.Route;
import ngoy.translate.TranslateService;

@Pipe("routeTitle")
public class RouteTitlePipe implements PipeTransform {

	@Inject
	public TranslateService translateService;

	@Inject
	public LocaleProvider locale;

	@Override
	public Object transform(Object obj, Object... args) {
		Route route = (Route) obj;
		return translateService.translate(format("MSG_%s", route.getPath()
				.toUpperCase()));
	}
}
