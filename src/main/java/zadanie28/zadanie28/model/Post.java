package zadanie28.zadanie28.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date added = new Date();

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post() {

    }

    @OneToMany
    @JoinColumn(name = "postId")
    List<PostComment> comments;
}
