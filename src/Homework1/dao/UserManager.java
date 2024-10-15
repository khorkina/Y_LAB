package Homework1;

import Homework1.models.User;

import java.util.HashMap;
import java.util.Map;


public class UserManager {
    private static Map<String, User> users = new HashMap<>();

    static {
        User admin = new User("admin", "admin@gmail.com", "admin");
        admin.setAdmin();
        users.put(admin.getEmail(), admin);
    }

    public void registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            System.out.println("Пользователь с таким email уже существует.");
            return;
        }
        User user = new User(name, email, password);
        users.put(email, user);
        System.out.println("Пользователь зарегистрирован.");
    }

    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.isBlocked()) {
            System.out.println("Ваш аккаунт был заблокирован администратором");
            return null;
        }
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Добро пожаловать, " + user.getName() + "!");
            return user;
        } else {
            System.out.println("Неверный email или пароль.");
            return null;
        }
    }

    public void updateUserProfile(User user, String newName, String newEmail, String newPassword) {
        users.remove(user.getEmail(), user);
        user.setName(newName);
        user.setEmail(newEmail);
        user.setPassword(newPassword);
        users.put(newEmail, user);
        System.out.println("Профиль обновлен.");
    }

    public void deleteUser(String email) {
        users.remove(email);
        System.out.println("Аккаунт удален.");
    }

    public User getUserByEmail(String email) {
        User user = users.get(email);
        return user;
    }


    public void blockUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setBlocked(true);
            System.out.println("Пользователь заблокирован.");
        } else {
            System.out.println("Пользователь не найден.");
        }
    }


    public Map<String, User> getAllUsers() {
        return users;
    }
}
