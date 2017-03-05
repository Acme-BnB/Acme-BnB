
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentatorRepository;

import domain.Commentator;

@Component
@Transactional
public class StringToCommentatorConverter implements Converter<String, Commentator> {

	@Autowired
	CommentatorRepository	commentatorRepository;


	@Override
	public Commentator convert(String text) {
		Commentator result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = commentatorRepository.findOne(id);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
