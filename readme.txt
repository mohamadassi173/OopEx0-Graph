First assignment in object oriented programming course


This project is about building an undirected graph to operate some of the algorithms (check if the graph is connected, return the shortest path and shortest path dest).

Data structure - Hashmap:
the key of hashmap the id of the vertex.
the value of hashmap is node_data, each of node_data has neighbours collection.

Algorithms:
is an algorithm for traversing or searching tree or graph data structures. It starts at the tree root (or some arbitrary node of a graph, sometimes referred to as a 'search key'[1]), and explores all of the neighbour nodes at the present depth before moving on to the nodes at the next depth level complexity: O(V+E)
We checked if the graph is connected by doing bfs on the arbitrary vertex and check if the colour of all vertices is black, If true That's mean that can I traverse from every arbitrary vertex to other vertices therefore the graph is connected.
BFS also returns distance array from certain vertices  to other vertices that helped me to return the shortest path between two vertices.


assignment github: 
https://github.com/mohamadassi173/OopEx1-Graph.git