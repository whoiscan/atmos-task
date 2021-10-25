Администратор создается когда spring.sql.init.mode=always в application.properties, далее его нужно заменить на spring.sql.init.mode=never

Логин администратора : admin

Пароль администратора : 12345
____
APIs
____________
1-PAGE Авторизация

URL: /auth/sign/in

METHOD: GET

____________
2-PAGE Регистрация

URL: /auth/sign/up

METHOD: GET
__________
3-PAGE Форма отправки новости

URL: /api/board

METHOD: GET
__________
4-PAGE Список новостей для простого пользователья

URL: /user/all/news

METHOD: GET
________
4-PAGE Список новостей для администратора

URL: /admin/all/news

METHOD: GET