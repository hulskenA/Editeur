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
    + [3.1. Le package ""](#31-le-package)

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

class langages.Translator {
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

class events.FileEvent extends java.util.EventObject {
  + FileEvent(file : String)
}

class events.HelperWindowActionListener implements java.awt.event.ActionListener {
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

abstract util.PluginMenuItemActionListener <<Abstract>> implements java.awt.event.ActionListener {
  # plugin : Plugin

  + PluginMenuItemActionListener(plugin : Plugin)
  + {abstract} actionPerformed(e : ActionEvent) : void
}

class util.PluginHelpMenuItemActionListener extends util.PluginMenuItemActionListener {
  + actionPerformed(e : ActionEvent) : void
}



class plugins.BananaCorp implements plugin.Plugin {
  + transform(s : String) : String
  + getLabel() : String
  + helpMessage() : String
}

class plugins.DoNothing implements plugin.Plugin {
  + transform(s : String) : String
  + getLabel() : String
  + helpMessage() : String
}

class plugins.ImTheOnlyTrust implements plugin.Plugin {
  + transform(s : String) : String
  + getLabel() : String
  + helpMessage() : String
}

class plugins.PluginToAddSignature implements plugin.Plugin {
  + transform(s : String) : String
  + getLabel() : String
  + helpMessage() : String
}
```

##### 3.1 - Le package ""

---

### 4 - Conception des Objets

##### 4.1 - Notre code

L'ensemble du cahier des charges a été traité lors de ce projet.
Nous avons également augmenté la porté des fonctionnalité de notre projet par diverses manière:
+ L'implémentation d'un tranducteur (pour le menu uniquement, les extensions et le texte écrit eux ne sont pas touché par cette fonctionnalité) qui pour fonctionné utilise des fichiers `properties` dynamiquement (tel la gestion des plugins).
+ La possibilité de porsonnaliser son propre éditeur dont ses paramètres seront gardé en mémoire pour être utilisé dès sont lancement.

##### 4.2 - Les Design Pattern

Tout au long de ce projet, nous avons essayé de garder à l'esprit l'essentiel de la *Conception Orientée Objet*, Le principe **SOLID**.
