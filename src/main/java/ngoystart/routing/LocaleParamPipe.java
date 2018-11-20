package ngoystart.routing;

import static java.lang.String.format;

import ngoy.core.Inject;
import ngoy.core.LocaleProvider;
import ngoy.core.Pipe;
import ngoy.core.PipeTransform;

@Pipe("localeParam")
public class LocaleParamPipe implements PipeTransform {

	@Inject
	public LocaleProvider locale;

	@Override
	public Object transform(Object url, Object... args) {
		return format("%s?locale=%s", url, locale.get());
	}
}
