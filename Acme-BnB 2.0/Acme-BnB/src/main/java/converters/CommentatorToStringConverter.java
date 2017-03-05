
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Commentator;

@Component
@Transactional
public class CommentatorToStringConverter implements Converter<Commentator, String> {

	@Override
	public String convert(Commentator commentator) {
		String result;

		if (commentator == null)
			result = null;
		else
			result = String.valueOf(commentator.getId());

		return result;
	}

}
