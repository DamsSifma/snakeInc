Answer in a dedicated file (you can use markdown) you'll put in your repository under a folder called "questions".**  
(do not focus too much on the UI, this is not the main goal of this course, focus on the logic and OOO principles)

Class : Sorte de schéma pour créer un objet ex: Cell
Object: Instance d'une classe
Primitive type: Type d'objet qui est utilisé pour construire tous les autres

Encapsulation : Niveaux de visibilité des méthodes de la classe private, public, static
Getter et Setter permette accéder / modifier valeur d'une classe
Final = ne sera assigné qu'une fois

Static:  pas besoin d'instancier la classe
Composition: Game a une Grid a une Basket a un Snake
donc possède plusieurs instances d'autre classes

Héritage: Hérite des méthodes de la classe mère, du type *OutOfPlayException* qui hérite de *Exception*
Interface: classe abstraite qui n'est jamais utilisée en tant que telle mais que pour être héritée
Polymorphism: Possible de changer la méthode dans la sous classe ex: GamePanel


Static type: type déclaré  (comme Java)
Dynamic type: déclaré en fonction de la valeur de la variable déclaré (Typescript)


Separation of Concerns : Isoler les responsabilités dans différentes classes / fichier pour que chaque classe gère une et une suele chose

Collections: Groupe d'objets : List, Map etc

Exceptions : Cas d'erreurs gérés

Functional interfaces / Lambda : fonction déclarée en ligne sans nom du type
snake = new Snake((apple, cell) -> basket.removeAppleInCell(apple,cell), grid);

Lombok : lib d'annotations qui permet d'éviter d'écrire certaines choses redondantes (getter / setter, etc...)