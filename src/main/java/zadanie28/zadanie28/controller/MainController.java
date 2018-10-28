package zadanie28.zadanie28.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zadanie28.zadanie28.model.Post;
import zadanie28.zadanie28.repository.PostRepository;

import javax.transaction.Transactional;

@Controller
public class MainController {
    @Autowired
    PostRepository repository;
    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("name", "Dawid");

        return "index";
    }
    @GetMapping("/addPost")
    public String addPostPag(){
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam(value = "title") String title, @RequestParam(value = "content")
                          String content){

        Post post = new Post(title, content);
        repository.save(post);
        System.out.println("Params:"+title+","+content);

        return "index";
    }
}
