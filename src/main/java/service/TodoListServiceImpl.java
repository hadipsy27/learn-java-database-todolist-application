package service;

import entity.Todolist;
import repository.TodoListRepository;

public class TodoListServiceImpl implements TodoListService{

    public TodoListRepository todoListRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public void showTodoList() {
        Todolist[] model = todoListRepository.getAll();

        System.out.println("TODO LIST");
        for(var todo : model) {
            System.out.println(todo.getId() + " : " + todo.getTodo());
        }
    }

    @Override
    public void addTodoList(String todo) {
        Todolist todolist = new Todolist(todo);
        todoListRepository.add(todolist);
        System.out.println("Sukses menambah TODO : " + todo);
    }

    @Override
    public void removeTodoList(Integer number) {
        Todolist[] todolist = todoListRepository.getAll();
        boolean success = todoListRepository.remove(number);

        for(var todo : todolist) {
            if(!success){
                System.out.println("Gagal menghapus TODO dengan nomor : " + number);
                break;
            } else {
                if(todo.getId().equals(number)){
                    System.out.println("Sukses menghapus TODO " + todo.getTodo());
                }
            }
        }
    }
}
