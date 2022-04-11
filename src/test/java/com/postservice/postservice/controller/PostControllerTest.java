package com.postservice.postservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postservice.postservice.enums.BloodGroup;
import com.postservice.postservice.enums.Gender;
import com.postservice.postservice.model.Post;
import com.postservice.postservice.model.PostDTO;
import com.postservice.postservice.model.User;
import com.postservice.postservice.service.PostService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(PostController.class)
class PostControllerTest {

    @MockBean
    PostService postService;

    @Autowired
    MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void savePost() throws Exception {
            Post post=saveTestPost();
            PostDTO postDTO= saveTestDto();
        Mockito.when(postService.savePost(post)).thenReturn(postDTO);

        mockMvc.perform(post("/posts")
                        .content(asJsonString(post))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.postID", Matchers.is("1")));

    }

    private Post saveTestPost(){
        Post post= new Post();
        post.setPost("hello");
        post.setPostID("1");
        post.setPostedBy("roody");
        post.setUpdatedAt(null);
        post.setCreatedAt(null);
        return post;
    }

    private PostDTO saveTestDto() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c = sdf.parse("2015-05-26");
        PostDTO postDTO= new PostDTO();
        postDTO.setPost("bro");
        postDTO.setPostID("1");
        postDTO.setPostedBy(new User("1", "Roody", "Bro",
                "nik", "1023456789",  Gender.MALE,"Chennai",c,
                "123", BloodGroup.A_POS, "roody@gamil.com"));
        postDTO.setLikeCounts(2);
        postDTO.setCommentCounts(1);
        postDTO.setCreatedAt(LocalDateTime.now());
        postDTO.setUpdatedAt(LocalDateTime.now());
return  postDTO;
    }

    @Test
    void findByIdTest() throws Exception {
        PostDTO postDTO= saveTestDto();
        Mockito.when(postService.findById("1")).thenReturn(postDTO);

        mockMvc.perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.postID", Matchers.is("1")));

    }




    @Test
    void updatePost() throws Exception {
        Post post=saveTestPost();
        PostDTO postDTO= saveTestDto();
        Mockito.when(postService.updatePost(post,"1")).thenReturn(postDTO);

        mockMvc.perform(put("/posts/1")
                        .content(asJsonString(post))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.postID", Matchers.is("1")));
    }

    @Test
    void deleteById() throws Exception {
        Mockito.when(postService.deleteById("2")).thenReturn("deleted sucessfully");
        mockMvc.perform(delete("/posts/1"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void allUser() throws Exception {
        List<Post> list =getListPost();
        List<PostDTO> list1=getListPostdTO();
        Mockito.when(postService.allUser(1,2)).thenReturn(list1);

        mockMvc.perform(get("/posts?page=1&pageSize=2"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].postID", Matchers.is("1")));
    }

    private List<Post> getListPost(){
        List<Post> list= new ArrayList<>();
        Post p1=new Post("1","helo","roody",null,null);
        Post p2=new Post("2","helo","roody",null,null);
        list.add(p1);
        list.add(p2);
        return list;
    }

    private List<PostDTO> getListPostdTO() throws ParseException {
        List<PostDTO> list= new ArrayList<>();
        PostDTO p1=new PostDTO("1","1",createOneUser(),1,1,null,null);
        PostDTO p2=new PostDTO("2","2",createOneUser(),1,1,null,null);
        list.add(p1);
        list.add(p2);
        return list;
    }
    private User createOneUser() throws  ParseException {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        User user1 = new User();

        user1.setUserId("123");
        user1.setFirstName("FirstID");
        user1.setMiddleName("J");
        user1.setLastName("S");
        user1.setPhoneNumber("9090909090");
        user1.setEmail("prabhu@mail.com");
        user1.setDateOfBirth(c);
        user1.setEmployeeNumber("12345");
        user1.setBloodGroup(BloodGroup.A_NEG);
        user1.setGender(Gender.MALE);
        user1.setAddress("Pune");
        return user1;
    }
}