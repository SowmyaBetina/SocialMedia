package com.prodapt.learning;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.prodapt.learning.*;
import com.prodapt.learning.business.LoggedInUser;
import com.prodapt.learning.business.NeedsAuth;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import com.prodapt.learning.entity.Comment;
import com.prodapt.learning.entity.CommentId;
import com.prodapt.learning.entity.CommentRecord;
import com.prodapt.learning.entity.LikeId;
import com.prodapt.learning.entity.LikeRecord;
import com.prodapt.learning.entity.Post;
import com.prodapt.learning.entity.PostRepository;
import com.prodapt.learning.entity.User;
import com.prodapt.learning.repository.CommentRepository;
import com.prodapt.learning.repository.LikeCRUDRepository;
import com.prodapt.learning.repository.LikeCountRepository;
import com.prodapt.learning.repository.UserRepository;

@Controller
@RequestMapping("/forum")
public class ForumController {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private PostRepository postRepository;
  
  @Autowired
  private LikeCountRepository likeCountRepository;
  
  @Autowired
  private LikeCRUDRepository likeCRUDRepository;
  @Autowired
  private LoggedInUser loggedInUser;
  
  @Autowired
  private CommentRepository commentrepository;
  
  private List<User> userList;
  
  @PostConstruct
  public void init() {
    userList = new ArrayList<>();
  }
  @NeedsAuth(loginPage = "/loginpage")

  @GetMapping("/post/form")
  public String getPostForm(Model model) {
    model.addAttribute("postForm", new AddPostForm());
    userRepository.findAll().forEach(user -> userList.add(user));
    model.addAttribute("userList", userList);
    model.addAttribute("authorid", 0);
    return "forum/postForm";
  }
  
  @PostMapping("/post/add")
  public String addNewPost(@ModelAttribute("postForm") AddPostForm postForm, BindingResult bindingResult, RedirectAttributes attr, Model model) throws ServletException {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getFieldErrors());
      attr.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
      attr.addFlashAttribute("post", postForm);
      return "redirect:/forum/post/form";
    }
    //Optional<User> user = userRepository.findById(postForm.getUserId());
    Optional<User> user = userRepository.findById(loggedInUser.getLoggedInUser().getId());
    if (user.isEmpty()) {
      throw new ServletException("Something went seriously wrong and we couldn't find the user in the DB");
    }
    Post post = new Post();
    post.setAuthor(user.get());
    post.setContent(postForm.getContent());
    postRepository.save(post);
    
    
    return String.format("redirect:/forum/post/%d", post.getId());
  }
  
  @GetMapping("/post/{id}")
  public String postDetail(@PathVariable int id, Model model) throws ResourceNotFoundException {
    Optional<Post> post = postRepository.findById(id);
    if (post.isEmpty()) {
      throw new ResourceNotFoundException("No post with the requested ID");
    }
    
    List<Comment> comments = commentrepository.findByPostId(id);        
    model.addAttribute("commentsList", comments);
    
//    LocalDate localDate = LocalDate.now();
//    LocalDateTime localDateTime = LocalDateTime.now();
    //System.out.println(localDate);
    //LocalDate localDate = LocalDate.now(ZoneId.of("GMT+02:30"));
    //System.out.println(localDate);
    //LocalDateTime localDateTime = LocalDateTime.now();
    //LocalTime localTime = localDateTime.toLocalTime();
//    System.out.println(localTime);
    model.addAttribute("post", post.get());
    model.addAttribute("userList", userList);
    //int numLikes = likeCountRepository.countByPostId(id);
    int numLikes = likeCRUDRepository.countByLikeIdPost(post.get());
    model.addAttribute("likeCount", numLikes);
    //System.out.println(localDateTime);
    
    LocalDateTime postDateTime = LocalDateTime.now();
    model.addAttribute("postDateTime", postDateTime);

    
    return "forum/postDetail";
  }
  
  @PostMapping("/post/{id}/like")
  public String postLike(@PathVariable int id, Integer likerId, RedirectAttributes attr, Model model) {
    LikeId likeId = new LikeId();
    //likeId.setUser(userRepository.findById(likerId).get());
    likeId.setUser(userRepository.findById(loggedInUser.getLoggedInUser().getId()).get());
    likeId.setPost(postRepository.findById(id).get());
    LikeRecord like = new LikeRecord();
    like.setLikeId(likeId);    
    likeCRUDRepository.save(like);
    
    LocalDateTime likeDateTime = LocalDateTime.now();
    model.addAttribute("likeDateTime", likeDateTime);
    
    return String.format("redirect:/forum/post/%d", id);
  }
  /*
  @PostMapping("/post/{id}/comment")
  public String postComment(@PathVariable int id, Integer commenterId, RedirectAttributes attr) {
	  
	  CommentId commentId = new CommentId();
	  commentId.setUser(userRepository.findById(loggedInUser.getLoggedInUser().getId()).get());
	  commentId.setPost(postRepository.findById(id).get());
	  CommentRecord comment = new CommentRecord();
	  comment.setCommentid(commentId);
	  commentrepository.save(comment);
	  return String.format("redirect:/forum/post/%d", id);
	  

  }
  */
  @PostMapping("/post/{id}/comment")
  public String addCommentToPost(String commenterName, HttpServletRequest request, @PathVariable int id, Model model) {



      String content = request.getParameter("content");
      Optional<User> user = userRepository.findByName(commenterName);
      Optional<Post> post = postRepository.findById(id);
      
    	  /*
          Comment comment = new Comment();
          comment.setUser(user.get());
          comment.setPost(post.get());
          comment.setContent(content);
          commentrepository.save(comment);
		*/
    	  Comment comment = new Comment();
          comment.setContent(content);
          comment.setUser(userRepository.findById(loggedInUser.getLoggedInUser().getId()).get());
          comment.setPost(postRepository.findById(id).get());
          commentrepository.save(comment); 
          System.out.println(content);
          
          
          comment.setDateTime(LocalDateTime.now());
          commentrepository.save(comment);
          
          /*
          List<Comment> comments = commentrepository.findByPostId(id);        
          model.addAttribute("commentsList", comments);
          */
          return String.format("redirect:/forum/post/%d", id);
      
      //return "redirect:/forum/post/1";
         

         //return "forum/postDetail :: comments";
          //return "forward:/forum/post/{id}";
  }
  
}