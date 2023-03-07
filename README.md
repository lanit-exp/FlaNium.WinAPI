# FlaNium.WinAPI
Библиотека расширяющая протокол `Selenium REST API` и добавляющая дополнительные возможности по настройке и взаимодействию с `FlaNium драйвером`. Также  данная библиотека позволяет типизировать стандартный `Selenium WebElement` и привести его к компонентам тестируемого приложения, тем самым добавить дополнительные методы взаимодействия характерные определенному типу элемента. 

### Новое в версии 2.0.0

Основные изменения в новой версии:
- Расширен протокол FlaNium драйвера - добавлено несколько новых методов
- Произведен рефакторинг кода библиотеки для упрощения взаимодействия и более быстрого погружения
- Упрощен механизм настройки и запуска драйвера
- Добавлена поддержка Selenium 4

## Основные моменты

Работу с драйвером можно разбить на 4 основных этапа:

1. Подключение к драйверу:

    1.1. Подключение к удаленному или локальному ранее запущенному экземпляру драйвера по {ip}:{port}
    
    1.2. Запуск локального экземпляра драйвера и подключение к нему (http://localhost:{port})

2. Инициализация новой сессии: 

    2.1. С запуском нового процесса тестируемого приложения

    2.2. Подключением к ранее запущенному процессу

3. Поиск необходимого элемента для выполнения взаимодейсвия

4. Взаимодействие с найденным элементом или получение каких-либо данных  


## 1. Настройка и инициализация драйвера

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


Первое что необходимо сделать это создать экземпляр класса `FlaNiumDriver`

Выполнить это можно 2 способами: 

``` Java
   // Используя ранее запущенный экземпляр драйвера:
   FlaNiumDriver driver = new FlaNiumDriver(URL remoteAddress, FlaNiumOptions options);
   
   // Или выполнить инициализацию и запуск нового экземпляра драйвера
   FlaNiumDriver driver = new FlaNiumDriver(FlaNiumDriverService service, FlaNiumOptions options);
   
   // где  URL remoteAddress - адрес запущенного экземпляра FlaNium драйвера
   // FlaNiumOptions options - параметры инициализации и запуска тестируемого процесса приложения
   // FlaNiumDriverService service - параметры инициализации и запуска экземпляра драйвера 
```

### FlaNiumDriverService

`FlaNiumDriverService` - Класс используемый для настройки и запуска экземпляра драйвера.

Пример кода:
``` Java
    String DRIVER_PATH = "src/main/resources/driver/FlaNium.Desktop.Driver/FlaNium.Driver.exe";
    String LOG_PATH = "logs/log.txt";
    int driverPort = 7885;

    FlaNiumDriverService service = new FlaNiumDriverService.Builder()
        // Указание пути до драйвера
        .usingDriverExecutable(new File(DRIVER_PATH).getAbsoluteFile())
        // Установка порта (если не указывать - то по умолчанию 9999; если 0 - то используется случайный свободный)
        .usingPort(driverPort)
        // Включение режима отладки (true - вывод логов в консоль; по умолчанию false - логи не выводятся)
        .withVerbose(true)
        // Отключение логирования (по умолчанию false)
        .withSilent(false)
        // Тайм-аут ожидания запуска сервера драйвера (по умолчанию 20 сек)
        .withTimeout(Duration.ofSeconds(20))
        // Путь файла логирования драйвера
        .withLogFile(new File(LOG_PATH).getAbsoluteFile())
        .build();
```
p.s.

`usingDriverExecutable` - Является единственным обязательным параметром, остальные можно использовать по умолчанию.

`usingPort` - Если не указывать то используется порт 9999; если указать порт 0, то будет использоваться каждый раз случайный свободный порт.

`withVerbose` - Для удобства отладки желательно указывать true.


### FlaNiumOptions -> DesktopOptions

`DesktopOptions` - Класс используемый для инициализации и запуска тестируемого процесса приложения.

Пример кода:
``` Java
    DesktopOptions options = new DesktopOptions()
                    // Путь до тестируемого приложения
                    .setApplicationPath(String appPath)
                    // Аргументы командной строки используемые при запуске приложения
                    .setArguments(String args)
                    // Подключение к ранее запущенному процессу приложения
                    .setDebugConnectToRunningApp(Boolean connectToRunningApp)
                    // Ожидание на запуск приложения (по умолчанию 0)
                    .setLaunchDelay(Integer launchDelay)
                    // Подключение к процессу по имени
                    .setProcessName(String processName)
                    // Тайм-аут ответа драйвера в мс (по умолчанию 300 сек)
                    .setResponseTimeout(Integer responceTimeout)
        
                    // *Опционально, требуется библиотека для инжекта: 
                    // Использование инжекта
                    .setInjectionActivate(Boolean injectionActivate)
                    // Выбор библиотеки для инжекта
                    .setAppType(DesktopOptions.AppType appType);
```
p.s.

`setApplicationPath` - Обязательный параметр, запускает исполняемый файл по указанному пути.

`setArguments` - Опциональный параметр, при необходимости указываются дополнительные параметры командной строки.

`setDebugConnectToRunningApp` - Если true и приложение запущено, то не происходит закрытие и повторный запуск приложения, драйвер использует текущее состояние процесса пиложения. Если false (по умолчанию) - всегда запускается новый процесс приложения (с закрытием текущего, если имеется).

`setLaunchDelay` - Статическое ожидание в мс между запуском приложения и дальнейшими действиями (такими как поиск и инициализация процесса). По умолчанию 0.

`setProcessName` - Если запускаемое приложение порождает несколько процессов или процесс в момент запуска сменяется другим (например запуск java приложений) то можно указать имя процесса к которому необходимо цепляться при запуске драйвера. Так же необходимо указать время ожидания `setLaunchDelay`.

`setResponseTimeout` - Поскольку FlaNium драйвер взаимодействует с внешними библиотеками WinAPI не редки случаи зависания или долгого выполнения запросов. Для этого был добавлен механизм прерывания запроса по таймауту (по умолчанию 300 сек).

`setInjectionActivate` и `setAppType` - Используются при взаимодействии с приложением через технологию инжекта. Требуется dll и библиотека расширения - в открытом доступе пока нет!


## 2. Работа с драйвером

### Поиск

После получения экземпляра `FlaNiumDriver` можно осуществлять поиск контролов приложения и осуществлять взаимодействие с ними через стандартные методы библиотеки Selenium.
``` Java 
WebElement edit = driver.findElement(By.xpath("//*[(@ControlType = 'Edit') and contains(@Name,'Text')]"));

edit.sendKeys("Test text");
```

При использовании `Selenide` необходимо установить полученный ранее экземпляр FlaNium драйвера в качестве текущего драйвера:

``` Java 
WebDriverRunner.setWebDriver(flaniumDriver);

// Затем можно использовать стандартный механизм поиска (вызов метода open() - не требуется)
SelenideElement edit = Selenide.$x("//*[(@ControlType = 'Edit') and contains(@Name,'Text')]");
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

### Расширение возможностей Selenium протокола

      ВАЖНО! 
      Данная библиотека значительно расширяет возможности по взаимодействию с драйвером и тестируемом приложением, а также упрощает это взаимодействие. 
      Поэтому рекомендуется отказаться от работы со стандартным WebElement и WebDriver и использовать экземпляры DesktopElement и FlaNiumDriver.

      DesktopElement и FlaNiumDriver - два основных класса при работе с FlaNium драйвером.


Начиная с версии 2.0.0 появилась возможность приводить WebElement непосредственно к `DesktopElement` - основному узлу по взаимодействию с элементами. 

Теперь,для упрощения поиска, основные методы класса разбиты на группы:

Actions:

   `mouseActions()` - позволяет получить доступ к методам взаимодействия на основе мыши (отдельная реализация, не относится к протоколу Selenium). Например:  dragAndDrop, mouseMove, mouseClick и др.

   `touchActions()` - методы использующие API сенсорного ввода windows. Также поддерживает multitouch. Например: tap, pinch, rotate и др. 

   `screenshotActions()` - методы по работе со скриншотами.

Cast:

   `castTo()` - позволяет привести `DesktopElement` к типизированному элементу. 

``` Java
WebElement edit = driver.findElement(By.xpath("//*[(@ControlType = 'Edit') and contains(@Name,'Text')]"));

DesktopElement editDesktopElement = new DesktopElement(edit);

TextBox editTextBox = editDesktopElement.castTo().toTextBox();

editTextBox.setText("Test text2");
```

   `toCoordinateElement()` - приведение WebElement к "виртуальному" объекту класса CoordinateElement. 


Аналогичная группировка выполнена для методов FlaNiumDriver класса.

## 3. Упрощенный механизм инициализации


