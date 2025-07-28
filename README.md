# System Zarządzania Zadaniami i Pracownikami (Java Swing)

Ten projekt to prosta aplikacja desktopowa stworzona w języku Java z wykorzystaniem biblioteki Swing. Powstał jako część procesu nauki programowania w Javie, ze szczególnym naciskiem na tworzenie graficznych interfejsów użytkownika (GUI) za pomocą Swing. Aplikacja symuluje podstawowe funkcje systemu zarządzania zadaniami i pracownikami w organizacji.

## Technologie
* **Java**
* **Java Swing** (dla interfejsu użytkownika)
* **Serializacja obiektów** (do zapisu i odczytu danych)

## Główne Funkcjonalności

Aplikacja umożliwia zarządzanie różnymi encjami, w tym:

* **Działami Pracowników (`DzialPracownikow`)**: Tworzenie, edytowanie i usuwanie działów.
* **Pracownikami (`Pracownik`)**: Dodawanie, modyfikowanie i usuwanie pracowników.
* **Użytkownikami (`Uzytkownik`)**: Zarządzanie użytkownikami systemu z rolami dostępu (dziedziczy po `Pracownik`).
* **Brygadami (`Brygada`)**: Tworzenie i zarządzanie brygadami, przypisywanie do nich pracowników i brygadzistów.
* **Brygadzistami (`Brygadzista`)**: Osoby odpowiedzialne za brygady.
* **Zleceniami (`Zlecenie`)**: Definiowanie zleceń z typem, statusem i przypisanymi pracami.
* **Pracami (`Praca`)**: Określanie rodzajów prac i ich czasu trwania.

## Struktura Aplikacji (MVP – Model-View-Presenter w uproszczeniu / MVC w luźnym rozumieniu)

Projekt jest zorganizowany w sposób, który oddziela logikę biznesową od warstwy prezentacji, choć ze względu na etap nauki, niektóre elementy mogą być ze sobą ściślej powiązane niż w dojrzałych architekturach.

### Kluczowe Komponenty:

* **Modele Danych (Encje)**:
    * `Brygada.java`, `Brygadzista.java`, `DzialPracownikow.java`, `Praca.java`, `Pracownik.java`, `Uzytkownik.java`, `Zlecenie.java` - Reprezentują dane i logikę biznesową aplikacji. Wszystkie kluczowe obiekty implementują `Serializable` dla zachowania stanu.

* **Obsługa Danych**:
    * `IDrecorder.java` i `IOPliku.java` - Klasy odpowiedzialne za serializację i deserializację obiektów, co pozwala na zapisywanie i odczytywanie danych z plików.

* **Interfejs Użytkownika (View - Java Swing)**:
    * `Okienko.java` - Główne okno aplikacji (JFrame), które agreguje wszystkie panele i zarządza ich widocznością.
    * `Panellogowania.java` - Panel do obsługi logowania użytkowników.
    * `Gornypanel.java` - Górny pasek z przyciskami akcji (Dodaj, Edytuj, Usuń, PracownicyDzialu).
    * `Lewypanel.java` - Panel nawigacyjny po lewej stronie, pozwalający na wybór wyświetlanych danych (np. Działy, Pracownicy, Zlecenia).
    * `Srodkowypanel.java` - Główny panel wyświetlający listy obiektów (np. `JTable`), dostosowujący zawartość do wybranego widoku.
    * `Dodajobiekt.java`, `Edytujobiekt.java`, `Usunobiekt.java` - Klasy Swing odpowiadające za okna dialogowe do dodawania, edytowania i usuwania obiektów.
    * `ZawartoscObiektu.java` - Wyświetla szczegółowe informacje o wybranym obiekcie.
    * `Guzik.java` - Prosty komponent przycisku używany w interfejsie.
    * `cowyswietlic.java` - Enum definiujący stany widoku aplikacji, np. `DZIALY`, `PRACOWNICY`, `ZLECENIA`.

* **Główna Klasa**:
    * `S34672.java` - Główna klasa uruchamiająca aplikację.

## Jak Uruchomić Projekt

1.  Sklonuj repozytorium:
    git clone https://github.com/brzeznik/Projekt_studia2
2.  Otwórz projekt w swoim ulubionym IDE (np. IntelliJ IDEA, Eclipse, NetBeans).
3.  Upewnij się, że masz skonfigurowane Java Development Kit (JDK) w wersji 8 lub nowszej.
4.  Uruchom klasę `S34672.java`.

## Cele Nauki (Java Swing)

Projekt ten pozwolił mi na:
* Zrozumienie podstawowych komponentów Swing (JFrame, JPanel, JButton, JTable, JTextField, JLabel itd.).
* Tworzenie układów za pomocą menedżerów układu (Layout Managers).
* Obsługę zdarzeń (ActionEvent, MouseEvent itp.).
* Tworzenie dialogów i okien modalnych.
* Serializację i deserializację obiektów w celu trwałego przechowywania danych.
* Zarządzanie stanem aplikacji i przełączanie widoków.
