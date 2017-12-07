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
    + [3.1. Le package "fil.coo.plugin"](#31-le-package-filcooplugin)
    + [3.2. Le package "plugins"](#32-le-package-plugins)

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

Lors de ce projet nous avons structuré nos codes sources selon une certaine arborescence. Comme vous pouvez le devinez par l'UML ci-dessous, celle-ci est assez fournis. Nous allons donc découper notre schéma en plusieurs parties pour vous le présenter.

![les packages](http://www.plantuml.com/plantuml/png/POnB2e0m34JtFKLEu9r2YJKOcc1R4QHtL_nLSJNmviqO-X43WKa971EirTc5UfREgyBg0EoZ7QX1MZnPPZtdxoPCQ0Dxb6lHcf9PRlkxoQgIVZxD5FFptxXW37kczrTrTGC0)

Ce procédé à pour but de vous rendre ce diagramme plus facile à lire pour en comprendre son intégralité.

##### 3.1 - Le package "fil.coo.plugin"

![le package fil.coo.plugin](http://www.plantuml.com/plantuml/png/bLBTIiCm5BxlK-Io8kG1P8pTA0Xp27s2itYQHa-JadCw1gFlRjQQjNCgU9No_QTamm9H-fQA3im7M5Ir8sr2KAT2gOnrr5ldHsW6yMYm4nSygzDmnTW8NUCCq9TZPdfnbDNN7Wc1-4QF1_HooPzn0c_JoSkieHYAmdd1M8F9c_NhD7srUixCUZtMt2k9uBaEiIrPFQXAelFsxl8rIYpA5lP8vJUyGUfUa3bjM59fW25WNlmRlGjLRvedHxYTHZzcBga4_HkhTpY0Fbz9pzmqyNTpsRATL__HNA58al0vvF_LFxflkpSfB3sYJm00)

Comme vous avez pu le remarquer ci-dessus, ce package reste  encore imposant. C'est pourquoi nous avons encore fait le choix de décomposer ce diagramme :

+ ***<u>Le sous package `tools`</u> :***

> ![le sous package tools](http://www.plantuml.com/plantuml/png/lLRHQjim57ttLnZsuj8cx7c4KjCvgLcQcDXZR6CCnLPSjR8K92KjA_ttNKcMOpjkryAsfoHNH_SUtNDqbGkfa52xYdhoZh0j4gZoSuga39KIPBrJECuvIyalxB_tl2tAxr29VSKvbVwZv_ikWlUOAGWzjM8KiH8-JVJ9ymXJM6nGxi3ZAQ5uJgJ230j_CedT-lcvINtgRm0G5WKk0WsDz2uJE_4_-7jEYWQqmXNVFm-3ufw7rb89b2jBVfoP8qmcOHrkYZv2HnJ9dto9bIAibD53ZhUAS7OjIyYOGbzOsOMwaxu2ceINdy9PD5skalddMRnu5fPT0MOUBcQ0Jbz6fL6MnOlP7r0lrliOpSElMNmThG00IWst0jQkqNPY4vpLYSwzBbXWL6WX0hiCUxGO2rGPGOQAbrXDbuliPnf_Ym8DcbAE-XJG7gSrZQ1bW55B1OwA3cI8g_62SNXjX9HKMueJkYi9Mwub5djmeubLnj93PlthzcmJibG0gxf4Rj4UZGatJ1XqiqPOBYZNpWiA8dJx077cQqpJS5ri3UanOlrw6drvW_ExEjaxVyDfWLqY6x6rREHGt2hVIX0y0pphvZond8l2TJIWhhKMuEATLWhJnZTjsH2X6TGorOOSqCYYd4v3k5xJmbnVSrVJjQ-l5hfNO-EV7huHJGSJc7fSL51NosM1nYMjsYyL7T7HwR6KZguRJUEv_V8_VDNKp0HYaYB5RRgHdwoM2Un81DzYeGYM9WeFpqv1EDQVa1UZgdlvKvXkyoXRwkbroEcPpOVVWRKLf6Q1fg3fEiErvLRrZcP2mGlG6qqvvH87n-BNm-1IBtyXhE3tzZw_IVZNozwjUXWV-06o5NMjdI9qREBBzIs61-wnkPKcgkzaIx2Zj5UOWXZsD3ss_-fOnuDdjFj-G-2IiX-ZcfStvA8sRDZPNljvRyEFXwjt0Ks4lpw_0G00)

+ ***<u>Le sous package `graphical`</u> :***

> ![le sous package graphical](http://www.plantuml.com/plantuml/png/pLPHRvim47xdLrZQIwekQCzJ59LDovggrIfbUvhwuC2LU3KsiarQDSf_tza6HaAWC4tHdh3lFixVVRuxkDA6AbDaFD1FJEHKqOp4d6eT6QFOkZ2mZALOiLSW7u8Wf_4JJO6aYkOR5bDETW4XbRKm30txO1y4BiGVMFZrnvB0Ym6HQFABRkbBg9-PICER1Hu8BiexOX11FfARx_YC551dp40KdwYozZk_TEP7na5RG-LmLWt6ODYs8-T5oaJRlW6UjuoSYXJpqQjYtVB978JTB0rabSEkdLC14kZqnbngwFI-IfbrEWlHwxPPH7dUilkiRvb6uS5Ak43LgcqprG4GtJZzZkQp5PQ7IDyJ_vmVgpm8UOsyGRq1TtWccB5biM304psvGEnMigJssHXDwoN1YfjSk5KAvlv020SyojF1RW_LQEDELBOF7SM6oRem_AuMjIfysnyblWEvSqpU3Zdn-Y4zZ_M9KeUv1_KeLGR91D3lt_ko1M5gUVR1AFhJwULX9xT5-DReYgEtMPPpm2Osll53-cn2i7J2axd_JGuzfm_Wlf32sFbsVjuT9m_W_7c3bGfbkPwVUD_n9zWpOJ0yZO4OARdsR7JOxAoIZ9tgKP8W3Rigk--GHmLJaCbj7x2pLqyfTLvYdOm6NFtHZFeVaZYcT28BkjP6OQaDc86pMLI2v_DnnLnzLkgn63J6wPX1kglexaUqHtbJZGIlov-BV_19646ZvrwAdnk6zoyUffrD4LmXB_mT_0q0)

<br/>

##### 3.2 - Le package "plugins"

![le package plugins](http://www.plantuml.com/plantuml/png/nPA_QWCn3CPtFuN6XCBHkLFITWdqJ-3k1HIVpcTYoyQIXxRatMiIafRiRT0YVP_Gznkq4iMYDGOZUyyP2qQm0KNMgiNlgjB69kxz9y6zCHdj7Xt1v4DdK-fogCupV1dFIcL2Ix0zImRW3hGWovHAN0WyGDzEibjUkkEA8tt17ON5bJvJoAyaql9-MuTM5uvJlhIq8_DvxXwHMpsbai7772WIg_oW_HNP5SLpUaiwD-kM49iupFJEuMCeLVIM9AUy8Qt7iVUEKMkXV-Kvc1Nns7xz6m00)

<!--
```puml
skinparam classAttributeIconSize 0

package plugins {}
package fil.coo {
  package plugin {
    package exceptions {}
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
  + {static} settingsExceptionMsg : String
  + {static} langagesExceptionMsg : String
  + {static} PACKAGEFORPLUGIN : String
  + {static} PATHFORLANGAGES : String
  + {static} PATHFORSETTINGS : String
  + {static} PATHFORPLUGIN : String
  + {static} DELAYTIMER : int
  + {static} settings : Map<String, String>

  + {static} readFile(String : fileName) : String
  + {static} getFONT_SIZE() : Float
  + {static} saveSettings() : void
  - {static} loadSettings() : Map<String, String>
}



class exceptions.NoSuchFileLangageException extends java.lang.Exception {
  + NoSuchFileLangageException(msg : String)
}

class exceptions.NoSuchSettingsFileException extends java.lang.Exception {
  + NoSuchSettingsFileException(msg : String)
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

class tools.ActionListenerChecker implements java.awt.event.ActionListener {
  + actionPerformed(e : ActionEvent) : void
}
tools.ActionListenerChecker -l-+ tools.FileChecker

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

class events.CloseWindowEvent implements java.awt.event.ActionListener {
  actionPerformed(e : ActionEvent) : void
}

class events.FileEvent extends java.util.EventObject {
  + FileEvent(file : String)
}

class events.HelperWindowActionListener implements java.awt.event.ActionListener {
  actionPerformed(e : ActionEvent) : void
}

class graphical.GUI extends javax.swing.JFrame {
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
  # pluginListener : FileListener
  # langageListener : FileListener
  # langagesMenuItem : Map<String, String>
  # pluginsMenuItem : Map<String, String>
  # pluginHelperMenuItem : Map<String, String>

  # initTextFields() : void
  # initMenu() : void
  + GUI()
  + getPluginListener() : FileListener
  + getLangageListener() : FileListener
}

class graphical.PluginActionMenuItemActionListener extends util.PluginMenuItemActionListener {
  + PluginActionMenuItemActionListener(plugin : Plugin)
  + actionPerformed(e : ActionEvent) : void
}
graphical.PluginActionMenuItemActionListener --+ graphical.GUI

class graphical.OpenMenuItemActionListener implements java.awt.event.ActionListener {
  + actionPerformed(e : ActionEvent) : void
}
graphical.OpenMenuItemActionListener --+ graphical.GUI

class graphical.FontSizeActionListener implements java.awt.event.ActionListener {
  + actionPerformed(e : ActionEvent) : void
}
graphical.FontSizeActionListener --+ graphical.GUI

class graphical.changeLangageActionListener implements java.awt.event.ActionListener {
  + actionPerformed(e : ActionEvent) : void
}
graphical.changeLangageActionListener --+ graphical.GUI

class graphical.PluginListener implements events.FileListener {
  + fileAdded(file : FileEvent) : void
  + fileremoved(file : FileEvent) : void
}
graphical.PluginListener --+ graphical.GUI

class graphical.LangageListener implements events.FileListener {
  + fileAdded(file : FileEvent) : void
  + fileremoved(file : FileEvent) : void
}
graphical.LangageListener --+ graphical.GUI

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
