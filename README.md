### 📝 Projekt: CertificationErrorProcessing | Project: CertificationErrorProcessing

**🇩🇪 Beschreibung:**  
Dieses Programm liest Benutzerdaten ein (Nachname, Vorname, Patronym, Geburtsdatum, Telefonnummer, Geschlecht), validiert sie und speichert sie in einer Datei.  
- Überprüft Anzahl der Eingaben (genau 6 Werte)  
- Validiert das Format von Geburtsdatum, Telefonnummer und Geschlecht  
- Schreibt die Daten in eine Datei mit dem Nachnamen als Dateiname  

**🇬🇧 Description:**  
This program reads user data (Last Name, First Name, Middle Name, Date of Birth, Phone Number, Gender), validates it, and writes it to a file.  
- Checks number of input values (exactly 6 values)  
- Validates the format of date of birth, phone number, and gender  
- Writes data to a file named after the last name  

**Beispiel-Code / Example Code:**

```java
Scanner scanner = new Scanner(System.in);
System.out.println("Enter data: LastName FirstName MiddleName date_of_birth phone_number gender");
String[] inputData = scanner.nextLine().split(" ");

// validate input, write to file
validateInput(inputData);
writeToOutputFile(inputData);
