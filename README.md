Тест полного заполнения формы https://demoqa.com/automation-practice-form

Есть таски:

**test** - запуск одного успешного теста и одного падающего

**positive_tests** - запуск только успешного

**negative_tests** - запуск только падающего

Браузер задается через командную строку с помощью  **browser** (по умолчанию chrome)

Для запуска в selenoid надо указать путь к хабу в **remote.driver**

Для прикрепления видео теста к отчету в allure надо задать путь к хранилищу видео файлов в **video.storage** 

Чтоб тесты прошли успешно надо что-то задать в **secret.title.for.test**

Логин и пароль к хабу selenoid задается в файле **src/test/resources/config/driver.properties** (пример driver_example.properties)

Пример запуска теста

clean positive_tests -Dbrowser=chrome -Dremote.driver="https://%s:%s@my.selenoid/wd/hub/" -Dvideo.storage="https://my.selenoid/video/" -Dsecret.title.for.test="Посмотри файл генерации данных"