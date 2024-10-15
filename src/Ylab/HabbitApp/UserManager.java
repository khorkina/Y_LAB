package Ylab;

import java.util.HashMap;
import java.util.Map;


public class UserManager {
    private Map<String, User> users = new HashMap<>();

    // Регистрация пользователя
    public void registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            System.out.println("Пользователь с таким email уже существует.");
            return;
        }
        User user = new User(name, email, password);
        users.put(email, user);
        System.out.println("Пользователь зарегистрирован.");
    }

    // Авторизация
    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Добро пожаловать, " + user.getName() + "!");
            return user;
        } else {
            System.out.println("Неверный email или пароль.");
            return null;
        }
    }

    // Изменение профиля
    public void updateUserProfile(User user, String newName, String newEmail, String newPassword) {
        user.setName(newName);
        user.setEmail(newEmail);
        user.setPassword(newPassword);
        users.put(newEmail, user); // Обновляем email в коллекции
        System.out.println("Профиль обновлен.");
    }

    // Удаление аккаунта
    public void deleteUser(String email) {
        users.remove(email);
        System.out.println("Аккаунт удален.");
    }

    public User getUserByEmail(String email) {
        User user = users.get(email);
        return user;
    }
}
