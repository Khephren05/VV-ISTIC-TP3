# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Nous avons testé category/java/bestpractices.xml/UseAssertEqualsInsteadOfAssertTrue sur les tests de la librarie :
commons-math/commons-math-legacy/src/test/java/
Le fichier de resultat de l'analyse est le suivant : [Resultats](errorBis.html).
Nous avons relevé une "bad smell" dans le fichier AkimaSplineInterpolatorTest.java à la ligne 249. En analysant le resultat 
nous avons remarqué que cette erreur peut être qualifiée de faux positif. En effet l'assert equal utilisé est une méthode 
"maison" du develloper pour prendre en compte l'approximation des floats comparés. 