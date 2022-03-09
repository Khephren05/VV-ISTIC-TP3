# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

L'assert suivant : assertTrue(3 * .4 == 1.2) ne fonctionnera car les types des objets comparés sont des float. De ce fait
leur valeur est nécéssairement une approximation de la valeur. Il ne faut jamais faire de comparaison sur des float sans
prendre en compte cette aproximation.
La manière correcte de produire l'oracle serait plutôt la suivante:
assertEquals(expected, result, delta);
où expected=3*.4, result=1.2 et delta=0.001


2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

assertEquals() réalise une comparaison de la valeur de l'objet tandis que assertSame() réalise une comparaison de l'adresse
mémoire des 2 objets comparés.

Scénario equivalent :

A a = new A();
A b = a;
assertEquals(a,b) rend true;
assertSame(a,b) rend true;

Scénario non equivalent :

Integer x = new Integer(12);
Integer y = new Integer(12);

assertEquals(x,y) rend true;
assertSame(x,y) rend false;



3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

Le second interet du fail serait de le placer sur un test dont le developpement n'est pas terminer afin de s'assurer qu'il
échoue et ne renvoie pas un feedback faussement positif.



4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

L'interet de la version Junit5 est qu'elle permet d'enchainer plusieurs assertThrow à la suite et de réaliser d'autres assert
après. De plus elle permet un meilleur contrôle de l'origine de la levée d'exception.



## Answer

