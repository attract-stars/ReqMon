# ReqMon

ReqMon umfasst die Konzpetion und Implementierung der Runtime Monitors im STARS Projekt.

## Tools Libaries

* xText für die Sprachentwicklung
* Eclipse für RCP/UI Anwendung 
* _Code-Generierung Engine offen_

## Inputs

Der Prozess geht davon aus dass Anforderungen in der definierten Sprache / Algebra definiert worden sind und eine Beschreibung des zu monitorenden Systems vorliegt

* Syntaxbäume der Anforderungen
* Definition relevante Systemdaten (Signale / Datenmodelle / etc.)
* Informationen über Ausführungsumgebung der Monitore

## Outputs
Code / Executable füür
* Abstraction
* Abstract Function + Conformity Oracle
* Situation Monitor + Situation Oracle
* Prozessschritte

## 1. Schritt: Definition Anforderungen
Die Anforderungen werden der dafür vorgesehen Sprache / Algebra definiert und resultieren in einem Syntaxtree.

### Output: Syntaxbaum


## 2. Schritt: Identifikation Objekte / Sprachelemente
Basierend auf den Syntaxbäumen der Anforderungen müssen jetzt die zentralen Sprachelemente identifiziert werden um diese Anforderungen in eine formalen Logik ausdrücken zu können.

* Datenmodell mit allen Objekten in den Anforderungen
** Objekte sind entweder komplex (mit weiteren Eigenschaften) oder primitiv (mit eine Menge von Konkreten Objekten)
** Standarddatentype wir Integer / Float etc.können wiederverwendet werden
* Weitere Sprachelemente der Anforderungen (z.B. Funktionen, Relationen) müssen identifiziert werden und in eine Formale Logik eingebracht werden
* Anforderungen müssen als Formeln über die Logik ausgedrückt werden

###Notwendige Arbeiten/ Entscheidungen:
* Notwendige Sprachkonstrukte und zugehörige formale Sprache (STL, FOL, etc.) identifizieren

## 3. Schritt: Mapping von Anforderungen zu System
Die Sprachelemente und Daten aus den Anforderungen müssen über dem Target-System interüretiert werden, um damit die Abstraktion des Monitoring bilden zu können und die abstrakte Beschreibung der Anforderungen aus den Daten des Target-Systems ableiten zu können.

### Input
* Datenmodell, Logik, und Formeln der Anforderungen
* Beschreibung der Systemdaten und den zugehörigen Zugriff

### Notwendige Arbeiten/ Entscheidungen:
* Beschreibung der Systendaten 
* Import der Systemdaten
* Editor für manuelles Mapping der Logik und Systemdaten 

## 4. Schritt: Code-Generierung der Runtime Monitore
Generierung von Source-Code zur Compilierung der Runtime Monitore für das Target-System von den Formeln der Anforderungen und den Mapping der Sprachelemente zu dem System

### Notwendige Arbeiten/ Entscheidungen:
* Art der Code-Generierung 
* Engine für die Code-Generierung
* Definition und Beschreibung für die Ausführung / Integration der Monitore mit dem System (Timing etc.)