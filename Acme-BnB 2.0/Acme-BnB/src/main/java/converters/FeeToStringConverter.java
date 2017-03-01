
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Fee;

@Component
@Transactional
public class FeeToStringConverter implements Converter<Fee, String> {

	@Override
	public String convert(Fee request) {
		String result;

		if (request == null)
			result = null;
		else
			result = String.valueOf(request.getId());

		return result;
	}

}
