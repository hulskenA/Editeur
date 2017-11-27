# PROJET 4 - Conception Orientée Objet<br/>Plugins

<table>
<tbody>
<tr><td rowspan="2"><img src="http://www.fil.univ-lille1.fr/portail/img/logo-FIL-transparent-site.png" width="300"/></td><th>Enseignant responsable du module:<br/>  Romain Rouvoy/Jean-Christophe Routier</th></tr>
<tr><th>Enseignant de travaux dirigés:<br/>Jean-Christophe Routier</th></tr>
<tbody>
</table>

## Auteurs: Alexandre HULSKEN - Martin VASILEV

#### L3S5 - Gr.1

> Ce projet portait sur la conception d'un éditeur de texte. Le langage utilisé ici a été le `Java`.

> Ce README permattra de vous expliquer la structure complète de ce projet ainsi que chacun des choix qui ont été fait tout au long de celui-ci. Il vous indiquera aussi comment le compiler et l'utiliser.

---

## Table des matières

1. [Introduction](#1-introduction)
2. [Utilisation](#2-utilisation)
    + [2.1. Récupération du dépôt](#21-récupération-du-dépôt)
    + [2.2. La Documentation](#22-la-documentation)
    + [2.3. La Génération d'éxecutable et son Execution](#23-la-génération-déxecutable-et-son-execution)

3. [Structure du projet](#3-structure-du-projet)
    + [3.1. Le package "questionnaire"](#31-le-package-questionnaire)
    + [3.2. Le package "ui"](#32-le-package-ui)

4. [Conception des Objets](#4-conception-des-objets)
    + [4.1. Notre code](#41-notre-code)
    + [4.2. Les Design Pattern](#42-les-design-pattern)

<br/>

---

### 1 - Introduction

Comme il a été précisé précédemment, nous avons dû concevoir un petit éditeur textuel. Sa particularité étant que vous pouvez le faire évoluer dynamiquement avec de nouvelles extensions à tout moment.

---

### 2 - Utilisation

**Remarque :** Lors de la suite de l'explication qui va suivre, nous allons vous donner des commandes qui necessitent d'avoir certains programmes déjà installé sur votre machine. Nous vous conseillons donc de télécharger `Maven` et `Git`.

<br/>

##### 2.1 - Récupération du dépôt

Afin de pouvoir utiliser notre travail, vous pourrez récupérer l'ensemble de nos codes en faisant un clône de ce dépôt sur votre machine grâce à cette simple commande :

```git
$ git clone https://gitlab-etu.fil.univ-lille1.fr/hulsken/COO-projet4.git
```

<br/>

##### 2.2 - La Documentation

Comme il est toujours plus utile de lire la documentation d'un objet au lieu d'esssayer de comprendre son fonctionnement en lisant directement son code, vous pouvez le faire également ici. Il vous suffira uniquement de vous placer dans le dossier racine du clône que vous venez de créer et d'utiliser la commande suivante :

```bash
$ mvn javadoc:javadoc
```

Vous pourrez ensuite trouver l'ensemble de la documentation que vous venez de générer dans le dossier *./target/docs*.

<br/>

##### 2.3 - La Génération d'éxecutable et son Execution

Vous pouvez maintenant vous occuper de la partie la plus amusante, en vous situant dans le dossier racine de ce projet, vous pouvez le compiler et créer un éxecutable par la commande :

```bash
$ mvn package
```

A partir de là, vous pouvez lancer le jeu grâce à la commande suivante :

```bash
$ java -jar target/COO-projet4-1.0-SNAPSHOT.jar
```

<br/>

> **Remarque :** Une fois que vous aurez fini, vous pouvez nettoyer votre dossier des fichiers compilés par la commande :
>
>```bash
>$ mvn clean
>```

---

### 3 - Structure du projet

Lors de ce projet nous avons structuré nos codes sources selon une certaine arborescence. Comme celle-ci est assez fournis, nous allons découper notre schéma UML en plusieurs parties pour vous le présenter.

Ce procédé à pour but de vous rendre ce diagramme plus facile à lire pour en comprendre son intégralité.

```puml
skinparam classAttributeIconSize 0

package fil.coo {
  package plugins {}
  package plugin {
    package graphical {
      package util {}
    }
    package tools {
      package events {}
      package langages {}
    }
  }
}



interface plugin.Plugin <<Interface>> {
  + transform(s : String) : String
  + getLabel() : String
  + helpMessage() : String
}

interface events.FileListener <<Interface>> {
  + fileAdded(FileEvent file) : void
  + fileRemoved(FileEvent file) : void
}



abstract tools.Tools <<Abstract>> {
  + {static} PACKAGEFORPLUGIN : String
  + {static} PATHFORLANGAGES : String
  + {static} PATHFORPLUGIN : String
  + {static} DELAYTIMER : int
  + {static} settings : Map<String, String>

  + {static} readFile(String : fileName) : String
  + {static} getFONT_SIZE() : Float
  + {static} saveSettings() : void
  - {static} loadSettings() : Map<String, String>
}



class tools.SimplePluginObserver implements events.FileListener {
  + fileAdded(FileEvent file) : void
  + fileRemoved(FileEvent file) : void
}

class tools.PluginFilter implements java.io.FilenameFilter {
  + accept(dir : File, name : String) : boolean
}

class tools.FileChecker {
  # folder : File
  # filter : FilenameFilter

  + FileChecker(filter : FilenameFilter, folder : File)
  + addListener(listener : FileListener) : void
  + removeListener(listener : FileListener) : void
  + fireFileAdded(String file) : void
  + fireFileRemoved(String file) : void
}

class langages.LangageFilter implements java.io.FilenameFilter {
  + accept(dir : File, name : String) : boolean
}

class Translator {
  - PROP : Properties
  - input : InputStream
  + {static} SINGLETON : Translator

  - Translator()
  + open(langFile : File) : void
  + translate(String str) : String
  + close() : void
}

class events.CloseWindowEvent implements java.axt.event.ActionListener {
  actionPerformed(e : ActionEvent) : void
}

class FileEvent extends java.util.EventObject {
  + FileEvent(file : String)
}

class HelperWindowActionListener implements java.awt.event.ActionListener {
  actionPerformed(e : ActionEvent) : void
}

class graphical.GUI extends javax.swing.JFrame implements events.FileListener {
  # text : JTextArea
  # menuBar : JMenuBar
  # filesMenu : JMenu
  # settingsMenu : JMenu
  # pluginsMenu : JMenu
  # helpMenu : JMenu
  # langagesSubMenu : JMenu
  # openMenuItem : JMenuItem
  # resetMenuItem : JMenuItem
  # closeMenuItem : JMenuItem
  # zoomMenuItem : JMenuItem
  # unzoomMenuItem : JMenuItem
  # helpApp : JMenuItem
  # langagesMenuItem : Map<String, String>
  # pluginsMenuItem : Map<String, String>
  # pluginHelperMenuItem : Map<String, String>

  # initTextFields() : void
  # initMenu() : void
  + GUI()
  + fileAdded(file : FileEvent) : void
  + fileRemoved(file : FileEvent) : void
}

abstract util.PluginMenuItemActionListener implements java.awt.event.ActionListener <<Abstract>> {
  # plugin : Plugin

  + PluginMenuItemActionListener(plugin : Plugin)
  + {abstract} actionPerformed(e : ActionEvent) : void
}

class util.PluginHelpMenuItemActionListener extends util.PluginMenuItemActionListener {
  + actionPerformed(e : ActionEvent) : void
}
```

<!--
##### 3.1 - Le package "questionnaire"

![Le package questionnaire](http://www.plantuml.com/plantuml/png/tLZVJzim47xlNs69brJGQi-ewXGgEdNQ65iBqZJjmIIczKZj43iKjlL_dtydJfosx04XNY3StNS-t_UTuy3kCCbX0TSWoI1ZCUS5lYavcYMKpF5l1DxrUZbCxk0IWL_m0GupI9RWJm_eU73-cA2SOqfwsohm5cV3XDBXVOcOz1682rG3VMtP0JW2Dfg1K_09CpwoOMCJm18gmay19jnOIYp-zB9TpNhAT-oRetxG6iBRo2NYSxbWrAyjgSmMm9GhKEenW6bgJT5zRGq9UA0ujM7iBiwooBCEfBKBjEs5c_YXWFaA9p03w94ZadRrU8D9IZSYtyUfu1mPAxo416NIgXwizQpad19frazDdgunshJ8aiPna0DNeoD3Q8pneapurIngTEOFuv3ZP0jocfUvyiw5DCaot0ItmPmAgfWd4bjlFONQ7s4RL0Xhh1v6xrjbH_TSjDFXJWAG4v3RvVfjXVXr6wV6AfT-h5oNglg6uZ2RqA902GUHMr_db74tb6O8adRiXAvpIX3XEy9TDOkd7BMBnUmQPZWr1Nkz2EOoQzSsk8ip9OefJ3WjdWn_DTwdyMJnvTltsXX8PPdmdfcKKAfANGM273agkgYq1gSSQr3LNeWfyJPjyvaAn1hf56Se4cSUye9FW7_yDGwQXjxzOeayd5eP-yqSOQm_Q_kpk5XDa_YvynmVcivfnk2Dc63HUCEjcOl568n6iN6DnoPM3XrVeTXsVk6ijxHOmopsAQamGfpQLFcCyzYis9eCvm_ASj7frl3cA9Z8q9GeqTekR0qNKgegq0rXAFRmmJu0w5RQWtsG4orAPFlNFF8sPi_gZePxV8Pmqnh7pqSld0P2s1eVsrv8btEypZFKeKxxoephmfPjgwEw5UROE8J5Wzjlzg7BloZNg90ZsYgnuUckSgymgsrKRmwTBQIC0-I0NVAZmrNG6Ft_vb_tRI6eRRMiChwcRdLj_6KRTgOBR_NCix-cjdL2NhPlvqJEeTXxscZT023ZqZTGTLVMMSRLIwm9t_awEwyILXyWDkMppvbdqcRUmrsSlQN1aEGnlzrPeztU9SuJnESourYyF2OhYXFKcgUGkvlqjqDcGAilFBFgzo4NN3OiESuSPvhM6L5Sg4Av-z-7kq5K5Wi37P1epPOjfhTUaVvPF2W7VyVUZKJ0pseCDEy8GS2CSBGqNvorbsCmjdxJRfrl6X6wiGULtmXlo60h_tdaFkK7FrOuJH7v6Vw--mS0)

```puml
skinparam classAttributeIconSize 0

package java.lang {
  class Exception
}

package fil.coo.questionnaire {
  class Questionnaire {
    # questions : List<Question>
    # score : int
    # ui : QuestionnaireUI

    + Questionnaire()
    + Questionnaire(ui : QuestionnaireUI)
    + getScore() : int
    + getQuestions() : List<Question>
    + addQuestion(q : Question) : void
    + askAll() : void
    - ask(q : Question) : void
  }

  class QuestionnaireGraphical extends Questionnaire {
    # window : JFrame
    # aPanel : JPanel
    # aButton : JButton
    # questionsViews : List<QuestionView>

    + QuestionnaireGraphical()
    + constructUI() : void
    + {static} popup() : String
  }

  class Question {
    # points : int
    # question : String
    # answer : Answer<?>

    + Question(qtext : String, answer : Answer<?>, pts : int)
    + getQuestion() : String
    + getAnswer() : Answer
    + getPoints() : int
    + isCorrect (answer : String) : boolean
    + isCorrect (Component : String) : boolean
    + getAnswerType() : String
    + isValidAnswer(text : String) : boolean
    + getCorrectAnswer() : String
  }

  class QuestionnaireFactory {
    + {static} FACTORY : QuestionViewFactory

    - QuestionnaireFactory()
    + createQuestion(text : String, answer : String, points : String, className : String) : Question
    - readFile(fileName : String, questionnaire : Questionnaire) : void
    + createQuestionnire(fileName : String) : Questionnaire
    + createQuestionnaireGraphical(fileName : String) : QuestionnaireGraphical
  }


  package fil.coo.questionnaire.answer {
    abstract class Answer<T> <<Abstract>> {
      # theAnswer : T
      # formalAnswer : String
      # typeString : String

      + Answer(answer : String)
      + getCorrectAnswer() : T
      + getCorrectAnswerString() : String
      + getType() : String
      + {abstract} createView() : Component
      + {abstract} isCorrect (answer : String) : boolean
      + {abstract} isCorrect (answer : Component) : boolean
      + {abstract} validates(answer : String) : boolean
      # {abstract} constructAnswer(answer : String) : T
      # {abstract} constructFormalAnswer(answer : String) : T
      # {abstract} constructTypeString(answer : String) : String
    }

    abstract class SimpleAnswer<T> <<Abstract>> extends Answer {
      + SimpleAnswer(answer : String)
      + isCorrect(answer : String) : boolean
      + validates(answer : String) : boolean
    }

    class NumericalAnswer {
      + NumericalAnswer(answer : String)
      + createView() : Component
      + isCorrect(Component answer) : boolean
      # constructAnswer(answer : String) : int
      # constructFormalAnswer(answer : String) : String
      # constructTypeString(answer : String) : String
    }

    class TextAnswer {
      + TextAnswer(answer : String)
      + createView() : Component
      + isCorrect(Component answer) : boolean
      # constructAnswer(answer : String) : String
      # constructFormalAnswer(answer : String) : String
      # constructTypeString(answer : String) : String
    }

    class BooleanAnswer {
      + BooleanAnswer(answer : String)
      + createView() : Component
      + isCorrect(Component answer) : boolean
      # constructAnswer(answer : String) : boolean
      # constructFormalAnswer(answer : String) : String
      # constructTypeString(answer : String) : String
    }

    class EnumeratedAnswer {
      - enumeration : List<String>

      + EnumeratedAnswer(answer : String)
      + getEnumeration() : List<String>
      + validates(answer : String) : boolean
      + createView() : Component
      + isCorrect(String answer) : boolean
      + isCorrect(Component answer) : boolean
      # constructAnswer(answer : String) : String
      # constructFormalAnswer(answer : String) : String
      # constructTypeString(answer : String) : String
      - setEnumeration(l : List<String>) : void
    }

    class MultipleChoiceAnswer {
      + MultipleChoiceAnswer(answer : String)
      + validates(answer : String) : boolean
      + createView() : Component
      + isCorrect(String answer) : boolean
      + isCorrect(Component answer) : boolean
      # constructAnswer(answer : String) : List<String>
      # constructFormalAnswer(answer : String) : String
      # constructTypeString(answer : String) : String
    }


    package fil.coo.questionnaire.answers.util {
      class InvalidFormatException extends Exception {
        InvalidFormatException(msg : String)
      }
    }


    BooleanAnswer -u-|> SimpleAnswer : Boolean
    NumericalAnswer -u-|> SimpleAnswer : Integer
    TextAnswer -u-|> SimpleAnswer : String

    EnumeratedAnswer -u-|> Answer : String
    MultipleChoiceAnswer -u-|> Answer : List<String>
  }
}

Question -[hidden]- QuestionnaireFactory
```

##### 3.2 - Le package "ui"

![Le package ui](http://www.plantuml.com/plantuml/png/hLJRZjem47tFLrZgIn05zRbYgIY25TKkq8LMgge-J9rPS3UnKzk1tQxuzqukpcMJhK3gI-JCp9bpvcAR1o5Zq10n7e8n4skr-9bON70bD-8FidUU5mD_W1soNt20nw4v2hbZpnxB4UpJ6YI6tgcAknVXa2irJ4GM9gH5VGySsUS4ZHLAIX0QlopOQBHmll4uYsMipm9XuX2URj4OokPlI14HHcRNO-_PGOcWYDC8mKB6YVLJHnv6BXBYf6qio01qG5GYYaECK5hJKf7p3aWdfSaIKYw4g32hn99zJTcRTiD1IjGfTNxo2bqLgz_hBebgAHLVNDIrXdWlE8JdLDMlX_zNEVHjptiOWjoHoHJqA9E8tJZRQ3IZVzIGwYm7pjXS5uUPz8hJrPKx3PY422joPnvK0Ox0RqJs2d-VMPLRcZKKJNLjtMgG9WIhTAbhmDPtgtKsV1MZjW9DwH5fKphs9ULxDfPAv2UsMIolRsRRrP82g_oLyChcLsg9Ilff7xjdaXLKuD0tLZV2QjMbWJnK1lq6_lIlyHq47jtiyhQujVfA7eQF5cNWhdpPfpUCv1opgp1LKQmaBQHNQgXdy7yNFwNKjntOgXSxj9FCdO5x3OBM1Ej4S-3KrQTIOcqeyybqkxhxHhaw0DLiEfor8N4QkCMN9JbCxqNojjQgghEKji8hdIrNjygootdywCEutStN81-L2X5aZZmVvYux1fSYRvFG2dhPfdibE5wANgQqwOjtAN1B-_qQvkGUuQx7Rf1ytuiWGFbZqBfD1EbQp3gaiIf4yXS0)

```puml
skinparam classAttributeIconSize 0

package javax.swing {
  class JPanel
}

package fil.coo.ui {
  interface QuestionnaireUI <<Interface>> {
    + displayMessage(String msg) : void
    + readInput() : String
  }

  class StandardUI implements QuestionnaireUI {
    - in : InputStream
    - out : PrintStream
    - scanner : Scanner

    + StandardUI()
    + displayMessage(msg : String) : void
    + readInput() : String
  }

  class GraphicalUI implements QuestionnaireUI {
    + GraphicalUI()
    + displayMessage(msg : String) : void
    + readInput() : String
  }


  package fil.coo.ui.langages {
    enum Langages <<Enumeration>> {
      Fr
      En

      ==

      - name : String

      --

      - Langages(name : String)
      + toString() : String
    }

    class Translator {
      - PROP : Properties
      - input : InputStream
      + {static} SINGLETON : Translator

      - Translator()
      + open(lang : String) : void
      + translate(str : String) : String
      + close() : void
    }
  }


  package fil.coo.ui.views {
    class QuestionView extends JPanel {
      # answer : Component

      + QuestionView(question : String, answer : Component)
      + getAnswerView() : Component
    }

    class QuestionViewFactory {
      + {static} FACTORY : QuestionViewFactory

      - QuestionViewFactory()
      + createView(question : Question) : QuestionView
    }

    class AnswerViewFactory {
      + {static} FACTORY : AnswerViewFactory

      - AnswerViewFactory()
      + createView(answer : Answer<?>) : Component
      + createView(answer : BooleanAnswer) : Component
      + createView(answer : EnumeratedAnswer) : Component
      + createView(answer : MultipleChoiceAnswer) : Component
      + createView(answer : NumericalAnswer) : Component
      + createView(answer : TextAnswer) : Component
    }
  }

  fil.coo.ui.langages -u[hidden]- fil.coo.ui.views
  QuestionViewFactory -u[hidden]- QuestionView
}
```
-->

---

### 4 - Conception des Objets

##### 4.1 - Notre code

L'ensemble du cahier des charges a été traité lors de ce projet.
Nous avons également augmenté la porté des fonctionnalité de notre projet par diverses manière:
+ L'implémentation d'un tranducteur (pour le menu uniquement, les extensions et le texte écrit eux ne sont pas touché par cette fonctionnalité) qui pour fonctionné utilise des fichiers `properties` dynamiquement (tel la gestion des plugins).
+ La possibilité de porsonnaliser son propre éditeur dont ses paramètres seront gardé en mémoire pour être utilisé dès sont lancement.

##### 4.2 - Les Design Pattern

Tout au long de ce projet, nous avons essayé de garder à l'esprit l'essentiel de la *Conception Orientée Objet*, Le principe **SOLID**.
