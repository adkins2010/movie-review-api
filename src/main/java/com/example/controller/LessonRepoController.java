package com.example.controller;


import com.example.domain.Lesson;
import com.example.repository.LessonRepository;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonRepoController
{
    private final LessonRepository repository;

    public LessonRepoController(LessonRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all()
    {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public Lesson one(@PathVariable Long id)
    {
        return this.repository.findOne(id);
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson)
    {
        Lesson response = null;
        //try
        //{
            response = this.repository.save(lesson);
        //}
        //catch(HibernateException h)
        //{ //swallow the exception }
        //finally
        //{  // close anything...  }
        return response;
    }
}
