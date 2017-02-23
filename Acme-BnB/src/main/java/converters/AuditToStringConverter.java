
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Audit;

@Component
@Transactional
public class AuditToStringConverter implements Converter<Audit, String> {

	@Override
	public String convert(Audit request) {
		String result;

		if (request == null)
			result = null;
		else
			result = String.valueOf(request.getId());

		return result;
	}

}
