package me.moon.market.domain.image.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.global.entity.BaseTimeEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "IS_REMOVED")
    private boolean removed = false;

    @Builder
    public Image(Post post, String filePath){
        this.post = post;
        this.filePath = filePath;
    }

    public Image(MultipartFile file){
        this.filePath=file.getOriginalFilename();
    }

    public void delete(){
        this.removed = true;
    }
}
