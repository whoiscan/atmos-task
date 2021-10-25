package com.example.atmostask.models.custom;

public interface ResponseMessages {
    String USER_NAME_REQ = "Логин не может быть пустым!";
    String EMAIL_REQ = "Адрес электронной почты не может быть пустым!";
    String NOT_VALID_EMAIL_REQ = "Введите действующий адрес электронной почты!";
    String NOT_VALID_PASSWORD_REQ = "Пароль должен содержать от 5 до 16 символов!";

    String ONLY_NUMBERS = "Введите только цифры!";
    String MANY_CHARACTERS = "Ограничение текста 500 символов!!";
    String INVALID_CREDENTIALS = "Неправильный логин или пароль!";
}
