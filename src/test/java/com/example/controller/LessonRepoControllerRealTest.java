package com.example.controller;


import com.example.domain.Lesson;
import com.example.repository.LessonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonRepoControllerRealTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository lessonRepository;

    Lesson lesson;

    @Before
    public void setup()
    {
        lesson = new Lesson();
        lesson.setTitle("testLessonValue");
        lesson = lessonRepository.save(lesson);
    }

    @After
    public void cleanup()
    {
        lessonRepository.delete(lesson);    // does this do anything?
    }
//
//    @Test public void shouldRespondWithAListOfLessons() throws Exception {
//        Long id = new Random().nextLong();
//        Lesson lesson = new Lesson();
//        lesson.setId(id);
//        lesson.setTitle("java spring boot");
//
//        when(this.lessonRepository.findAll()).thenReturn(Collections.singletonList(lesson));
//
//        MockHttpServletRequestBuilder request = get("/lessons")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id", equalTo(id) ))
//                .andExpect(jsonPath("$[0].title", equalTo("java spring boot") ));
//    }
//
//    @Test public void shouldsendMockPOSTtoLessons() throws Exception {
//        Lesson lesson = new Lesson();
//        lesson.setId(new Random().nextLong());
//        lesson.setTitle("java lesson 2");
//
//        when(this.lessonRepository.save(any(Lesson.class))).thenReturn(lesson);
//
//        MockHttpServletRequestBuilder request = post("/lessons")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(lesson.toString());
//
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", equalTo(lesson.getId())))
//                .andExpect(jsonPath("$.title", equalTo(lesson.getTitle()) ));
//    }
//


    @Test
    @Transactional
    @Rollback
    public void shouldsendPOSTtoLessons() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lesson.toString());

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())));
    }

    @Test
    @Transactional
    @Rollback
    public void shouldRespondWithOneLesson() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/" + lesson.getId())
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
                .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())));
    }
}
