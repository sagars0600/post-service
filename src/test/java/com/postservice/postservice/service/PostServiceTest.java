package com.postservice.postservice.service;

import com.postservice.postservice.Feign.FeignComment;
import com.postservice.postservice.Feign.FeignLike;
import com.postservice.postservice.Feign.FeignUser;
import com.postservice.postservice.enums.BloodGroup;
import com.postservice.postservice.enums.Gender;
import com.postservice.postservice.exception.PostNotFoundException;
import com.postservice.postservice.model.Post;
import com.postservice.postservice.model.PostDTO;
import com.postservice.postservice.model.User;
import com.postservice.postservice.repo.PostRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostServiceTest {
    @InjectMocks
   PostService service;

    @Mock
    PostRepo postRepo;

    @Mock
    FeignUser feignUser;

    @Mock
    FeignLike feignLike;

    @Mock
    FeignComment feignComment;

    @Test
    void savePost() throws ParseException {
        Post post = createPostModel();
        PostDTO postDTO =createPostDTO();

        when(this.postRepo.findById("1")).thenReturn(Optional.of(post));
        Mockito.when(this.postRepo.save(any(Post.class))).thenReturn(post);
        assertThat(this.service.savePost(post).getPost()).isEqualTo(postDTO.getPost());

    }

    private User createUser() throws ParseException {
        User user = new User();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        user.setUserId("123");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("natsu@mail.com");
        user.setDateOfBirth(c);
        user.setEmployeeNumber("12345");
        user.setBloodGroup(BloodGroup.A_NEG);
        user.setGender(Gender.FEMALE);
        return user;
    }

    private Post createPostModel(){
            return new Post("1","1","1",null,null);
    }
    private PostDTO createPostDTO() throws ParseException {
        return new PostDTO("1","1",createUser(),1,1,null,null);
    }

    @Test
    void findById() throws ParseException {

        Post post = createPostModel();
     PostDTO postDTO= createPostDTO();
        when(this.postRepo.findById("1")).thenReturn(Optional.of(post));
        assertThat(this.service.findById("1").getPost()).isEqualTo(post.getPost());
    }

    @Test
    void updatePost() throws ParseException {
        Post post = createPostModel();
        PostDTO postDTO= createPostDTO();
        when(this.postRepo.save(post)).thenReturn(post);
        when(this.postRepo.findById("1")).thenReturn(Optional.of(post));

        assertThat(this.service.updatePost(post,"1").getPost()).isEqualTo(
               post.getPost());
        assertThrows(PostNotFoundException.class,()->this.service.updatePost(post, "12"));
    }

    @Test
    void deleteById() throws ParseException {
        Post commentModel = createPostModel();
       PostDTO commentDTO =createPostDTO();

        doNothing().when(this.postRepo).deleteById("1");
        when(this.postRepo.findById("1")).thenReturn(Optional.of(commentModel));

        assertThat(this.service.deleteById("1"));

    }

    @Test
    void allUser() throws ParseException {
        Post post = createPostModel();
        PostDTO postDTO= createPostDTO();
        List<Post> list = new ArrayList<>();
        list.add(post);

        PageImpl<Post> pageImpl = new PageImpl<Post>(list);
        Pageable firstPage = PageRequest.of(1, 2);
        when(this.postRepo.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, this.service.allUser(1, 2).size());
        verify(this.postRepo).findAll((org.springframework.data.domain.Pageable) any());
    }
}