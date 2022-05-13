
# GRE Labo Dijkstra
Luca Coduri

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

Pour avoir une meilleure idée, j'ai aussi calculé la moyenne de temps avec la moyenne du nombre d'itérations pour chacun des algorithmes.

## Mes résultats

Voici un apperçu des 20 premières ligne de mes [résultats](results.md) :

| n°   | source | destination | nb itérations simple | nb itérations bidir | temps simple (ms) | temps bidir (ms) |
|------|--------|-------------|----------------------|---------------------|-------------------|------------------|
| 1    | 7611   | 5959        | 5893                 | 5071                | 116.7668          | 59.7699          |
| 2    | 5024   | 9036        | 3264                 | 2685                | 25.9275           | 44.5268          |
| 3    | 1569   | 5469        | 7601                 | 6230                | 55.7515           | 42.6965          |
| 4    | 3363   | 7911        | 200                  | 121                 | 1.2806            | 1.3767           |
| 5    | 6946   | 4726        | 3128                 | 2164                | 19.985301         | 19.3602          |
| 6    | 9083   | 3706        | 6392                 | 5559                | 92.7549           | 43.661699        |
| 7    | 8743   | 8774        | 7974                 | 5650                | 46.390801         | 34.6377          |
| 8    | 190    | 2756        | 1020                 | 699                 | 5.9805            | 4.990799         |
| 9    | 245    | 2721        | 7830                 | 3991                | 100.4416          | 62.1357          |
| 10   | 8293   | 1964        | 8974                 | 6401                | 45.7753           | 42.279499        |
| 11   | 747    | 7460        | 2700                 | 1662                | 18.7691           | 11.1385          |
| 12   | 5319   | 3339        | 2535                 | 1582                | 14.0507           | 9.123299         |
| 13   | 5336   | 8974        | 1143                 | 917                 | 6.407401          | 5.476601         |
| 14   | 9332   | 7544        | 5513                 | 4458                | 30.4698           | 25.2198          |
| 15   | 8489   | 2014        | 8997                 | 6740                | 98.333601         | 81.691101        |
| 16   | 1287   | 7167        | 693                  | 352                 | 3.392001          | 2.3481           |
| 17   | 9440   | 7626        | 6002                 | 4121                | 34.027699         | 26.0652          |
| 18   | 1887   | 7245        | 4410                 | 3683                | 20.182899         | 18.4772          |
| 19   | 1904   | 9033        | 3137                 | 2333                | 14.600199         | 13.3988          |
| 20   | 7511   | 5946        | 7798                 | 3003                | 33.4839           | 15.338201        |

### Temps moyen bidirectionnel:`17.492473785`
### Temps moyen simple: `22.610268852`
