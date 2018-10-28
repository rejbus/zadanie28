package zadanie28.zadanie28.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zadanie28.zadanie28.model.Post;
import zadanie28.zadanie28.repository.PostRepository;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("name", "Daniel");
        return "index";
    }

    @GetMapping("/addPost")
    public String addPostPage() {
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam(value = "title") String title,
                          @RequestParam(value = "content") String content) {
        Post post = new Post(title, content);
        postRepository.save(post);
        System.out.println("Params: " + title + ", " + content);
        return "index";
    }

    @GetMapping("/posts")
    public String postsPage(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts";
    }

    @GetMapping("/posts/{title}")
    public String postsByTitle(@PathVariable String title, Model model) {
        model.addAttribute("posts", postRepository.findAllByTitleContains(title));
        return "posts";
    }

    @GetMapping("/posts/{title}/{sortfield}/{sortDirection}")
    public String postsByTitle(@PathVariable String title,
                               @PathVariable String sortfield,
                               @PathVariable String sortDirection, Model model) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equals(sortDirection)){
            direction= Sort.Direction.DESC;
        }

        List<Post> postList = postRepository.findAllByTitleContains(title,
                Sort.by(Sort.Direction.fromString(sortDirection),sortfield));
        model.addAttribute("posts", postList);
        return "posts";
    }

}