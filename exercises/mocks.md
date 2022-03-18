# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.


# Problématique de code absent

Afin de s'assurer que l'absence de code dans les méthodes n'engendre pas de résultat de test positif, nous avons mis en
place des verify() d'appel des méthodes sur le mock fournit au test. Par concéquent nous somme sûr que le code testé se sert
des méthodes du mock et donc qu'il n'est pas vide. 

[Code présent ici](../code/tp3-ssl/src)