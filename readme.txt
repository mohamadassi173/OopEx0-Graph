First assignment in object oriented programming course
This project is about building an undirected graph to operate some of the algorithms (check if the graph is connected, return the shortest path and shortest path dest).

classes list -
node_data :interface that represent the set of operations applicable on a  node (vertex) in an undirectional unweighted graph.
NodeData : class that implements "node_data" interface.
graph: interface that represent an undirectional unweighted graph.
Graph_DS :class that implements "graph" interface.
graph_algorithms:interface represents Graph Theory algorithms.
Graph_Algo: class that implements "graph_algorithms" interface.


Data structures - 
Hashmap<Integer,node_data>:
the key of hashmap the id of the node_data
the value of hashmap is node_data, each of node_data has neighbours collection.
neighbour collection represent in node_data by Collection<node_data>


Algorithms -
BFS is a traversing algorithm where you should start traversing from a selected node (source or starting node) and traverse the graph layerwise thus exploring the neighbour nodes.
and must then move towards the next-level neighbour nodes.

isConnected function: by doing BFS on any vertex in graph,if all vertices is visited that mean the graph is connected if one of vertices not visited the graph is not connected.

shortestpath function: doing bfs on src vertex,then we have a distance array of src vertex to all vertices in graph, 
for printing the the path we use the pred array that present immediate predecessor of the each vertex.

shortestpathdest function: use shortestpath function to print the path and we simply return the size of the path minus 1(because number of edges is equal to number of vertices-1)



github link:
https://github.com/mohamadassi173/OopEx1-Graph.git

