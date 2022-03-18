# Réponses Test The Date Class

## Question 1 

### Méthode isValidDate() :

Cette méthode prend en entrée 3 int correspondant au jour, au moi et à l'année. 
Retouvez cette méthode [ici](main/java/Date.java) en ligne 38.

### Prédicats et bocks de code : 
- P1 : if (isMonthValid(month)){
- P2 : switch (month){
- B1 : return day > 0 && day < 32;
- P3 : if(isLeapYear(year)){
- B2 : return day > 0 && day < 30;
- B3 : return day > 0 && day < 29;
- B4 : return day > 0 && day < 31;
- B5 : default: return false;
- B6 : return false;

### Séléction d'un nombre fini de paramètres : 

Dans notre classe de test [ici](test/java/TestDate.java)
Nous avons défini 2 jeux de données d'entrée finis afin de parcourir l'entièreté du graphe d'exécution de la méthode isValidDate : 

Un premier jeu de données pour les dates correctes :

| day | month | year | chemin couvert |
| --- | --- | --- | --- |
| 1 | 5 | 2008 | P1 -> B1 |
| 1 | 5 | 1996 | P1 -> B1 |
| 29 | 2 | 2008 | P1 -> P3 -> B2 |
| 29 | 6 | 2007 | P1 -> B4 |

Un second jeu de données pour les dates incorrectes :

| day | month | year | chemin couvert |
| --- | --- | --- | --- |
| 0 |30 | 56 | P1 -> B6 |
| -1 | 5 | 1326 | P1 -> B1 |
| 40 | 2 | 2008 | P1 --> P3 --> B2 |
| 30 | 2 | 2008 | P1 -> P3 -> B2 |
| 29 | 2 | 2007 | P1 -> P3 -> B3 |
| -1 | 6 | 2007 | P1 -> B4 |
| 31 | 6 | 2007 | P1 -> B4 |

Nous nous sommes alors rendu compte que le block de code B5 n'était jamais accédé et non accéccible car P1 empéchait d'y
accéder. Il semblerait donc que P1 soit inutile car son test est réalisé par le default switch case. 

## Question 2 

La couverture obtenue était de 100% pour les classes et méthodes. En revanche nous notons qu'une ligne de code n'a pas été 
éxécutée. Il s'agit du switch case default de isValidDate. Elle n'est jamais éxécutée car le predicat en isMonthValid().

## Question 3  

Nous n'avons pas plus de 2 opérateurs booléens puisque nous n'avons pas ré-inventé la roue (ce qui est une bonne pratique). 

## Question 4 

Report de PIT en /report.


![img.png](../../Images/DateTestPITReport1.png)

Comme vu sur cette figure, nous retrouvons les 97% de couvertures de lignes de code, mais aussi un score de couverture de mutation de 83%
(40/48) ainsi qu'un score de robustesse de 85% (40/47).

PIT nous propose plus de détail : ![img.png](../../Images/DateTestPITReport2.png)

Nous considérons ces mutant non tués comme étant des mutant équivalents. En effet par exemple si nous prenons le premier
mutant "changed conditional boundary → SURVIVED" produit dans la méthode isValidDate, il est fortement propable qu'il y ait 
une modification d'un < en <= ET un modification du int engendrant produisant un code isoFonctionnel ne pouvant être détecté. 
Nous deplorons que l'interface de report soit si pauvre, il aurait été intéréssant de pouvoir voir quelle modification à été réalisée 
par PIT afin de produire un nouveau teste permettant de tuer le mutant. Le feedback rendu étant trop vague, il est compliqué 
d'y répondre pertinament. Nous avons donc ajouté des cas de tests mais ils n'ont pas permis de tuer les mutants restants. 
