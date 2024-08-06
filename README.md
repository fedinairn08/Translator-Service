# Translator
## Сервис-переводчик с упрощенной работой с API яндекс переводчика

Технологии:

- `Java`
- `Spring Framework`
- `H2 database`

Endpoints:
- http://localhost:8081/translator/translate - localhost
- http://localhost:8090/translator/translate - in docker

## Использование
Я передаю запросы через Postman. Для этого нужно:
1) Создать новый запрос
2) Выбрать метод POST
3) Ввести URL
4) Перейти на вкладку "Body, выбрать "raw" и формат JSON
5) Ввести тело запроса, например:
   {
   "text": "пчела",
   "toLanguage": "en",
   "sourceLanguage": "ru"
   }

Для использования приложения нужны IAM-токен и OAuth-токен. IAM-токен обновляется каждые несколько часов. Для удобства, 
он выводится при запуске программы.
Поэтому, если срок действия IAM-токена истек, нужно его перезаписать. OAuth-токен остается постоянным и уже задан в 
проекте

Получение токенов: https://yandex.cloud/ru/docs/iam/operations/iam-token/create
Чтобы получить folderId, создайте Yandex Cloud: https://yandex.cloud/ru/