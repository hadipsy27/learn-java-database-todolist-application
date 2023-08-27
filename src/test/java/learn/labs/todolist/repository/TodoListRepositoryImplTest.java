package learn.labs.todolist.repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Todolist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TodoListRepository;
import repository.TodoListRepositoryImpl;
import util.DatabaseUtil;

import javax.sql.DataSource;

public class TodoListRepositoryImplTest {

    private HikariDataSource dataSource;
    private TodoListRepository todoListRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        todoListRepository = new TodoListRepositoryImpl(dataSource);
    }

    @Test
    void testAdd() {
        Todolist todolist = new Todolist();
        todolist.setTodo("Andi");

        todoListRepository.add(todolist);

    }

    @Test
    void testRemove() {
        System.out.println(todoListRepository.remove(1));
        System.out.println(todoListRepository.remove(2));
        System.out.println(todoListRepository.remove(3));
        System.out.println(todoListRepository.remove(4));
    }

    @Test
    void testGetAll() {
        todoListRepository.add(new Todolist("Hadi"));
        todoListRepository.add(new Todolist("Prasetyo"));
        todoListRepository.add(new Todolist("Dika"));

        Todolist[] todolists = todoListRepository.getAll();

        for (var todoList : todolists){
            System.out.println(todoList.getId() + " : " + todoList.getTodo());
        }
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
