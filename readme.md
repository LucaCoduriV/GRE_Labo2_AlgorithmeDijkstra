
# GRE Labo Dijkstra
2021-2022 | Luca Coduri

Dans le cadre du cours de graphe et réseau à la HEIG, j'ai dû implémenter en java la version simple et bidirectionnel de Dijkstra.
Ce laboratoire nous permet de mieux comprendre le fonctionnement de l'algorithme ainsi que les avantages et inconvenient de sa version bidirectionnelle.

## Méthode d'analyse
Pour pouvoir comparer les 2 variantes de Dijkstra il est nécessaire de récolter des données.
Il nous est donc demandé de générer 1000 sommets source et target que l'on fourni à nos algorithmes.
On récolte ensuite le nombre d'itérations par couple source-target ainsi que les sommets par les quel il faut passer avec leur distance.
J'ai aussi récolté le temps qu'il faut pour exécuter les algorithmes même si ce n'était pas demandé.
J'ai finalement rempli un tableau avec ces données qui contient ces informations :
- sommet source
- sommet destination
- nombre d'itérations pour Dijkstra simple
- nombre d'itérations pour Dijkstra bidirectionnel
- temps d'exécution en millisecond pour Dijkstra simple
- temps bidirectionnel en millisecond pour Dijkstra bidirectionnel

Pour avoir une meilleure idée et pouvoir plus facilement interpréter les données, j'ai aussi calculé quelques statistiques :
- Nombre de fois que bidirectionnel prend moins de temps
- Nombre de fois que Bidirectionnel à moins d'itérations
- Nombre d'itérations moyen bidirectionnel
- Nombre d'itérations moyen simple
- Temps moyen bidirectionnel
- Temps moyen simple

## Mes résultats

Voici un aperçu des 20 premières lignes de mes [résultats](results.md) :

| n°   | source | destination | nb itérations simple | nb itérations bidir | temps simple (ms) | temps bidir (ms) |
|------|--------|-------------|----------------------|---------------------|-------------------|------------------|
| 1    | 7611   | 5959        | 5893                 | 5071                | 116.875099        | 61.377           |
| 2    | 5024   | 9036        | 3264                 | 2685                | 26.5285           | 45.9114          |
| 3    | 1569   | 5469        | 7601                 | 6230                | 58.801601         | 34.9501          |
| 4    | 3363   | 7911        | 200                  | 121                 | 1.223             | 1.6803           |
| 5    | 6946   | 4726        | 3128                 | 2164                | 17.637899         | 11.776199        |
| 6    | 9083   | 3706        | 6392                 | 5559                | 82.8617           | 34.8346          |
| 7    | 8743   | 8774        | 7974                 | 5650                | 41.2445           | 35.588399        |
| 8    | 190    | 2756        | 1020                 | 699                 | 6.364301          | 5.277901         |
| 9    | 245    | 2721        | 7830                 | 3991                | 86.6617           | 63.7522          |
| 10   | 8293   | 1964        | 8974                 | 6401                | 45.994099         | 37.499699        |
| 11   | 747    | 7460        | 2700                 | 1662                | 17.1476           | 11.1906          |
| 12   | 5319   | 3339        | 2535                 | 1582                | 15.5046           | 11.3302          |
| 13   | 5336   | 8974        | 1143                 | 917                 | 6.839701          | 7.7952           |
| 14   | 9332   | 7544        | 5513                 | 4458                | 36.451601         | 28.935901        |
| 15   | 8489   | 2014        | 8997                 | 6740                | 106.4742          | 88.118099        |
| 16   | 1287   | 7167        | 693                  | 352                 | 4.129801          | 2.2378           |
| 17   | 9440   | 7626        | 6002                 | 4121                | 27.346001         | 20.576           |
| 18   | 1887   | 7245        | 4410                 | 3683                | 20.1724           | 18.658301        |
| 19   | 1904   | 9033        | 3137                 | 2333                | 14.514499         | 13.5449          |
| 20   | 7511   | 5946        | 7798                 | 3003                | 35.702099         | 15.1728          |

**Temps moyen bidirectionnel:`17.492473785`**

**Temps moyen simple: `22.610268852`**

**Nombre de fois que bidirectionnel prend moins de temps : `944/1000`**

**Nombre de fois que Bidirectionnel à moins d'itérations : `998/1000`**

**Nombre d'itération moyen bidirectionnel: `3366.34`**

**Nombre d'itération moyen simple: `5008.845`**

**Temps moyen bidirectionnel: `17.407039388999998 ms`**

**Temps moyen simple: `22.533634783 ms`**

## Conclusion
On remarque que l'algorithme Dijkstra bidirectionnel est le grand vainqueur de ce test.
Ce dernier itère beaucoup moins que la version simple ce qui lui permet en grande majorité d'être plus rapide dans la résolution du chemin le plus court.
Il est possible que malgré qu'il ait moins d'itérations, il prenne plus de temps. Ceci arrive quand la solution se trouve dans les premières itérations.
En effet l'algorithme est plus efficace en nombre d'itérations, mais légèrement plus lent par itération que sa version simple.