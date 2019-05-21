# language: ru

Функция: Простой тест
  Сценарий: Успешный простой тест
    Дано выполняем шаг R1

#    Дано выполняем GET запрос по url "https://petstore.swagger.io/v2/pet/findByStatus?status=available"
#    То код ответа 200-Ok

    Дано выполняем POST запрос по url "https://petstore.swagger.io/v2/pet" с телом запроса в формате XML:
    """
<?xml version="1.0" encoding="UTF-8"?>
<Pet>
	<id>2</id>
	<Category>
		<id>0</id>
		<name>string</name>
	</Category>
	<name>tiger</name>
	<photoUrl>
		<photoUrl>string</photoUrl>
	</photoUrl>
	<tag>
		<Tag>
			<id>0</id>
			<name>string</name>
		</Tag>
	</tag>
	<status>available</status>
</Pet>
    """
    То код ответа 200-Ok