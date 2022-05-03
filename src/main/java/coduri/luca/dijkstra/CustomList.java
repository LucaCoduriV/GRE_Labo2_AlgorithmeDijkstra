package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;

public class CustomList {
    VertexImpl[] vertices;
    int position = 0;

    CustomList(VertexImpl[] vertices){
        this.vertices = vertices;
    }

    /**
     * Get and remove a vertex from the list.
     * @param vertexId
     * @return
     */
    VertexImpl remove(int vertexId){
        outOfBoundsCheck(vertexId);
        final var vertex = vertices[vertexId];
        vertices[vertexId] = null;
        return vertex;
    }

    /**
     * Check if the list contains a vertex.
     * @param vertexId
     * @return
     */
    boolean contains(int vertexId){
        outOfBoundsCheck(vertexId);
        return vertices[vertexId] != null;
    }

    /**
     * the first VertexImpl available.
     * @return the first VertexImpl available or null if none is found.
     */
    VertexImpl removeFirstAvailable(){
        VertexImpl vertex;
        do{
            vertex = vertices[position++];
        }while(vertex == null && position < vertices.length);
        return vertex;
    }

    private void outOfBoundsCheck(int vertexId){
        if(vertexId >= vertices.length || vertexId < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}
