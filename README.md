# FlaNium.WinAPI
Библиотека расширяющая протокол `Selenium REST API` и добавляющая дополнительные возможности по настройке и взаимодействию с `FlaNium драйвером`. Также  данная библиотека позволяет типизировать стандартный `Selenium WebElement` и привести его к компонентам тестируемого приложения, тем самым добавить дополнительные методы взаимодействия характерные определенному типу элемента. 

## Пример настройки и инициализации драйвера:

`pom.xml`
```xml
        ...
        <dependency>
            <groupId>com.github.lanit-exp</groupId>
            <artifactId>FlaNium.WinAPI</artifactId>
            <version>LATEST</version>
        </dependency>
        ...
```

Пример первоначальной настройки и получения экземпляра `FlaNiumDriver`:
```Java 
String DRIVER_PATH = "src/main/resources/driver/FlaNium.Desktop.Driver/FlaNium.Driver.exe";
String APP_PATH = "С:/Test_app.exe";
int driverPort = 9999; // порт по умолчанию 9999

// Инициализация драйвера:
FlaNiumDriverService service = new FlaNiumDriverService.Builder()
        // Указание пути до драйвера
        .usingDriverExecutable(new File(DRIVER_PATH).getAbsoluteFile())
        // Установка порта (по умолчанию 9999)
        .usingPort(driverPort)
        // Включение режима отладки (вывод логов в консоль)
        .withVerbose(true)
        // Отключение логирования
        .withSilent(false)
        .buildDesktopService();

// Инициализация приложения:
DesktopOptions options = new DesktopOptions();
// Указание пути до тестируемого приложения
options.setApplicationPath(new File(APP_PATH));
// Задержка после запуска приложения (сек)
options.setLaunchDelay(5);
// Подключение к ранее запущенному экземпляру приложения
options.setDebugConnectToRunningApp(false);

// Получение экземпляра драйвера приложения
FlaNiumDriver driver = new FlaNiumDriver(service, options); 
```


После получения экземпляра `FlaNiumDriver` можно осуществлять поиск контролов приложения и осуществлять взаимодействие с ними через стандартные методы библиотеки Selenium. 
```Java 
WebElement edit = driver.findElement(By.xpath("//*[(@ControlType = 'Edit') and contains(@Name,'Text')]"));

edit.sendKeys("Test text");
```


Есть возможность поиска элементов по `xpath`, `Name`, `Id (AutomationId)` и `ClassName`, а также поддерживаются 5 параметров поиска с помощью `xpath – AutomationId, Name, ClassName, HelpText, ControlType`.
```Java 
driver.findElement(By.xpath("//*[(@AutomationId = '') or (@Name = '') or (@ClassName = '') or (@HelpText = '') or (@ControlType = '')]"));
driver.findElement(By.name("Checkbox1"));
driver.findElement(By.id("Form1"));
driver.findElement(By.className("MenuItem"));

driver.findElements(By.xpath("#//*"); // символ '#' - позволяет искать элементы относительно Рабочего стола(элементы на всех открытых окнах), а не текущего окна.
```


Для более комфортной работы рекомендую воспользоваться такими инструментами как `FlaUInspect`, `UISpy` и подобными, так как они значительно упрощают написание тестов приложения, позволяя визуально отобразить структуру и параметры элементов приложения, а также позволяют понять, как можно обратиться к тому или иному элементу или какие паттерны поддерживает тот или иной элемент.

Благодаря расширению протокола `Selenium`, есть возможность типизировать любой `WebElement` и получить дополнительные возможности для работы. Для этого необходимо создать экземпляр требуемого класса и передать `WebElement` в качестве параметра:
```Java 
TextBox textBox = new TextBox(edit);
// где edit - WebElement полученный ранее
textBox.setText("Test text2");
```
