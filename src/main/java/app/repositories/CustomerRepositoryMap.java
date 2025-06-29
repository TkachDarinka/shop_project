package app.repositories;

import app.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryMap implements CustomerRepository{

    private Map<Long, Customer> database = new HashMap<>(); //Создаётся приватное поле — карта (словарь), где ключ — id клиента, а значение — объект Customer. Это "имитация" базы данных в оперативной памяти.
    private long currentId = 0; //Переменная для хранения текущего максимального id. Используется для генерации новых id при сохранении клиентов.

    @Override
    public Customer save(Customer customer) { //Метод сохраняет нового клиента:
        customer.setId(++currentId); //Увеличивает currentId на 1 и присваивает его объекту customer
        database.put(currentId, customer); //Кладёт customer в "базу" по новому id.
        return customer; //Возвращает сохранённого клиента.
    }

    @Override
    public List<Customer> findAll() { //Возвращает список всех клиентов из "базы".
        return new ArrayList<>(database.values());
    }

    @Override
    public Customer findById(Long id) { //Находит клиента по id. Если такого id нет — вернёт null.
        return database.get(id);
    }

    @Override
    public Customer update(Customer customer) { //Обновляет данные клиента:
        Long id = customer.getId(); //находим клиента по id
        String newName = customer.getName(); //метод getName() у объекта customer возвращает текущее имя клиента. результат работы метода (имя клиента) сохраняется в новую локальную переменную newName типа String

        Customer oldProduct = findById(id); //Находит клиента по id. Результат работы метода сохраняется в переменную oldProduct типа Customer

        if (oldProduct != null) {
            oldProduct.setName(newName); //если найден, обновляем имя
        }

        return oldProduct; // возвращаем обновлённого клиента или null
    }

    @Override
    public boolean deleteById(Long id) {
        Customer oldProduct = findById(id); // ищем клиента по id

        if (oldProduct == null) { //Проверяется, найден ли клиент. Если не найден (oldProduct == null)
            return false;
        }
        oldProduct.setActivity(false); //Если клиент найден, его "активность" устанавливается в false
        return true;
    }


}
