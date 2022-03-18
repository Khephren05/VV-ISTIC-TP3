# Problématique de code absent 

Afin de s'assurer que l'absence de code dans les méthodes n'engendre pas de résultat de test positif, nous avons mis en
place des verify() d'appel des méthodes sur le mock fournit au test. Par concéquent nous somme sûr que le code testé se sert 
des méthodes du mock et donc qu'il n'est pas vide. 