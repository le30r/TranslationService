# Translate Application

Тестовое задание для инкубатора Tinkoff.

Представляет собой REST-сервис, выполняющий перевод заданной фразы по отдельному слову.

Пример исходящего запроса:

    {
      "data": "Hello, world!",
        "params": {
          "targetLang": "ru",
          "sourceLang": "ru"
        }    
    }

Пример ответа:

    {
      "translationResult": "Здравствуйте, мир!"
    }

Параметр *sourceLang* может быть опущен, в таком случае сервис перевода попытается определить его сам.

Сборка образа для Docker выполняется с помощью следующей команды

    docker build -t le30r/tinkoff-test .

Запуск выполняется с помощью команды

    docker run -p 8080:8080 -e YANDEX_API_KEY=<Ваш API-KEY> le30r/tinkoff-test

Консоль H2 доступна по адресу localhost:8080/h2-ui. JDBC URL: *jdbc:h2:mem:translatedb*
