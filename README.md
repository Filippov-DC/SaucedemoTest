# Saucedemo Test

Тесты для сайта saucedemo.com с Microsoft Edge

## Структура проекта

```
SaucedemoTest/
├── pom.xml
└── src/test/java/tests/
    ├── BaseTest.java      # Базовый класс
    └── LoginTest.java     # Тесты логина
```

## Тесты

### Аутентификация (6 тестов)
- **checkPositiveLoginTest()** - Успешный логин с валидными данными
- **checkNegativeLoginTest()** - Логин с неверными данными
- **testEmptyUsername()** - Логин с пустым именем пользователя
- **testEmptyPassword()** - Логин с пустым паролем
- **testLockedUser()** - Логин с заблокированным пользователем
- **testLogout()** - Успешный выход из системы

### Основной функционал (2 теста)
- **testAddToCart()** - Добавление товара в корзину
- **testSortProducts()** - Сортировка товаров по цене

## Запуск

1. Откройте `LoginTest.java` в IDE
2. Нажмите зеленую стрелку рядом с нужным тестом
3. Выберите "Run 'checkPositiveLoginTest()'" или "Run 'checkNegativeLoginTest()'"

## Требования

- Microsoft Edge браузер
- Java 17+
