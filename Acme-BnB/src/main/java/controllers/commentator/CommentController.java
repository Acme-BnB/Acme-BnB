package controllers.commentator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CommentService;
import services.LessorService;

import controllers.AbstractController;
import domain.Comment;
import forms.CommentForm;

@Controller
@RequestMapping("/commentator/comment")
public class CommentController extends AbstractController{
	//Services-------------------------
		
		@Autowired
		private CommentService commentService;
		
		@Autowired
		private LessorService lessorService;
	

	//Constructor----------------------

	public CommentController() {
		super();
	}
	
	//Creation-------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int commentableId) {

			ModelAndView result;
			CommentForm commentForm;

			commentForm = commentService.generateForm(commentableId);			
			
			result = createEditModelAndView(commentForm, null);
			return result;

		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid CommentForm commentForm, BindingResult binding) {

			ModelAndView result =  new ModelAndView();		
			Comment comment;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(commentForm, null);
			} else {
				try {
					comment = commentService.reconstruct(commentForm, binding);
					comment = commentService.save(comment);
					int id = comment.getCommentable().getId();
					if(lessorService.findOne(id)!=null){
						result = new ModelAndView("redirect:../../lessor/displayById.do?lessorId="+id);
						String requestURI = "lessor/displayById.do?lessorId="+id;
						result.addObject("requestURI", requestURI);
					}else{
						result = new ModelAndView("redirect:../../tenant/display.do?tenantId="+id);
						String requestURI = "tenant/display.do?tenantId="+id;
						result.addObject("requestURI", requestURI);
					}
					
				} catch (Throwable oops) {
					String msgCode;
					if (oops.getMessage().equals("notCommentator")) {
						msgCode = "comment.notCommentator";
						result = createEditModelAndView(commentForm, msgCode); 
					}
					if (oops.getMessage().equals("notCommentable")) {
						msgCode = "comment.notCommentable";
						result = createEditModelAndView(commentForm, msgCode); 
					}
				}
			}
			return result;
		}

		//Ancillary Methods---------------------------

		protected ModelAndView createEditModelAndView(CommentForm commentForm, String message) {
			ModelAndView result;
		
			result = new ModelAndView("comment/edit");
			result.addObject("commentForm", commentForm);
			result.addObject("message", message);

			return result;

		}
		
}
