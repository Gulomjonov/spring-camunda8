---

## Camunda Platform 8

Этот репозиторий содержит ссылки на ресурсы Camunda Platform 8, официальные релизы и файл `docker-compose.yaml` для локального развертывания.

### Документация

* Camunda Platform SaaS
* Руководство по началу работы
* Релизы
* Движок рабочих процессов Zeebe
* Контакты

---

### Использование Docker Compose

Чтобы развернуть полную локальную среду **Camunda Platform 8 Self-Managed**, используйте файл `docker-compose.yaml`, находящийся в этом репозитории.

Среда включает следующие компоненты:

* **Zeebe**
* **Operate**
* **Tasklist**
* **Optimize**
* **Identity**
* **Elasticsearch**
* **Keycloak**

---

### Запуск

Клонируйте репозиторий и выполните команду:

```bash
docker-compose up -d
```

Подождите несколько минут, пока все сервисы будут запущены.
Рекомендуется следить за логами, особенно за контейнером **Keycloak**, чтобы убедиться, что все компоненты успешно стартовали.

После запуска доступны следующие интерфейсы:

| Сервис            | URL                                              |
| ----------------- | ------------------------------------------------ |
| **Zeebe**         | [http://localhost:26500](http://localhost:26500) |
| **Operate**       | [http://localhost:8081](http://localhost:8081)   |
| **Tasklist**      | [http://localhost:8082](http://localhost:8082)   |
| **Optimize**      | [http://localhost:8083](http://localhost:8083)   |
| **Identity**      | [http://localhost:8084](http://localhost:8084)   |
| **Elasticsearch** | [http://localhost:9200](http://localhost:9200)   |
| **Keycloak**      | [http://localhost:18080](http://localhost:18080) |

**Логин и пароль по умолчанию:**

```
demo / demo
```

---

### Остановка окружения

Чтобы полностью остановить и удалить контейнеры:

```bash
docker-compose down -v
```

---

### Облегченный запуск (без Identity, Optimize и Keycloak)

Если вам не нужны **Optimize**, **Identity** и **Keycloak**, используйте упрощённую конфигурацию:

```bash
docker-compose -f docker-compose-core.yml up -d
```

---

Zeebe, Operate, Tasklist и Optimize используют отдельную сеть от Identity — это учтено в `docker-compose` файлах.
Будем рады предложениям и обратной связи!

---
