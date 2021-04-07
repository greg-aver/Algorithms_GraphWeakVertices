package org.gregory;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

class SimpleGraphTest {

    @Test
    void evenTrees_NoEdgeForDelete() {
        SimpleGraph tree = new SimpleGraph(5);
        tree.AddVertex(new Vertex(0));
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddEdge(0, 1);
        tree.AddEdge(0, 2);
        assertThat(tree.EvenTrees(), is(empty()));
    }

    @Test
    void evenTrees_1EdgeForDelete() {
        SimpleGraph tree = new SimpleGraph(5);
        tree.AddVertex(new Vertex(0));
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddEdge(0, 1);
        tree.AddEdge(1, 2);
        tree.AddEdge(3, 2);
        assertThat(tree.EvenTrees(), is(List.of(1, 2)));
    }

    @Test
    void evenTrees_NoEdgeForDelete_4Nodes() {
        SimpleGraph tree = new SimpleGraph(5);
        tree.AddVertex(new Vertex(0));
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddEdge(0, 1);
        tree.AddEdge(1, 2);
        tree.AddEdge(3, 1);
        assertThat(tree.EvenTrees(), is(empty()));
    }

    @Test
    void evenTrees_2EdgeForDelete_10Nodes() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));
        tree.AddVertex(new Vertex(10));
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);
        assertThat(tree.EvenTrees(), is(List.of(1, 3, 1, 6)));
    }

    @Test
    void counterVertex_4Vertex() {
        SimpleGraph tree = new SimpleGraph(5);
        tree.AddVertex(new Vertex(0));
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddEdge(0, 1);
        tree.AddEdge(1, 2);
        tree.AddEdge(3, 1);
        try {
            Method counterVertexMethod = tree.getClass().getDeclaredMethod("counterVertex", int.class);
            counterVertexMethod.setAccessible(true);
            counterVertexMethod.invoke(tree, 0);
            assertThat(counterVertexMethod.invoke(tree, 0), is(4));
            assertThat(counterVertexMethod.invoke(tree, 1), is(3));
            assertThat(counterVertexMethod.invoke(tree, 2), is(1));
            assertThat(counterVertexMethod.invoke(tree, 3), is(1));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Test
    void counterVertex_10Vertex() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));
        tree.AddVertex(new Vertex(10));
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);
        try {
            Method counterVertexMethod = tree.getClass().getDeclaredMethod("counterVertex", int.class);
            counterVertexMethod.setAccessible(true);

            assertThat(counterVertexMethod.invoke(tree, 0), is(10));
            assertThat(counterVertexMethod.invoke(tree, 1), is(3));
            assertThat(counterVertexMethod.invoke(tree, 2), is(2));
            assertThat(counterVertexMethod.invoke(tree, 3), is(1));
            assertThat(counterVertexMethod.invoke(tree, 4), is(1));
            assertThat(counterVertexMethod.invoke(tree, 5), is(4));
            assertThat(counterVertexMethod.invoke(tree, 6), is(1));
            assertThat(counterVertexMethod.invoke(tree, 7), is(3));
            assertThat(counterVertexMethod.invoke(tree, 8), is(1));
            assertThat(counterVertexMethod.invoke(tree, 9), is(1));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //Test DepthFirstSearch
    @Test
    void DepthFirstSearch_NoVertex() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));
        tree.AddVertex(new Vertex(10));
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);
        assertThat(tree.DepthFirstSearch(1, 100), is(List.of()));
    }

    @Test
    void DepthFirstSearch_2Vertex() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));
        tree.AddVertex(new Vertex(10));
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);
        assertThat(tree.DepthFirstSearch(0, 2), is(List.of(new Vertex(1), new Vertex(3))));
    }

    @Test
    void DepthFirstSearch_4Vertex() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));
        tree.AddVertex(new Vertex(10));
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);
        assertThat(tree.DepthFirstSearch(0, 9), is(List.of(new Vertex(1), new Vertex(6), new Vertex(8), new Vertex(10))));
    }

    @Test
    void BreadthFirstSearch() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));
        tree.AddVertex(new Vertex(10));
        tree.AddVertex(new Vertex(11));

        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(5, 0);
        tree.AddEdge(1, 6);
        tree.AddEdge(1, 4);
        tree.AddEdge(2, 3);
        tree.AddEdge(5, 7);
        tree.AddEdge(7, 8);
        tree.AddEdge(7, 9);

        assertThat(tree.BreadthFirstSearch(2, 10), is(List.of()));
        assertThat(tree.BreadthFirstSearch(0, 9), is(List.of(new Vertex(1), new Vertex(6), new Vertex(8), new Vertex(10))));
        assertThat(tree.BreadthFirstSearch(2, 5), is(List.of(new Vertex(3), new Vertex(1), new Vertex(6))));
    }

    @Test
    void WeakVertices_9Elements_result2() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));

        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(3, 1);
        tree.AddEdge(3, 2);
        tree.AddEdge(3, 4);
        tree.AddEdge(4, 5);
        tree.AddEdge(5, 7);
        tree.AddEdge(5, 6);
        tree.AddEdge(7, 8);
        tree.AddEdge(1, 2);
        tree.AddEdge(2, 5);
        tree.AddEdge(7, 6);
        // index 4, 8
        assertThat(tree.WeakVertices(), is(List.of(new Vertex(5), new Vertex(9))));
    }

    @Test
    void WeakVertices_10Elements_result3() {
        SimpleGraph tree = new SimpleGraph(12);
        tree.AddVertex(new Vertex(1));
        tree.AddVertex(new Vertex(2));
        tree.AddVertex(new Vertex(3));
        tree.AddVertex(new Vertex(4));
        tree.AddVertex(new Vertex(5));
        tree.AddVertex(new Vertex(6));
        tree.AddVertex(new Vertex(7));
        tree.AddVertex(new Vertex(8));
        tree.AddVertex(new Vertex(9));
        tree.AddVertex(new Vertex(10));

        tree.AddEdge(9, 0);
        tree.AddEdge(2, 0);
        tree.AddEdge(0, 1);
        tree.AddEdge(3, 1);
        tree.AddEdge(3, 2);
        tree.AddEdge(3, 4);
        tree.AddEdge(4, 5);
        tree.AddEdge(5, 7);
        tree.AddEdge(5, 6);
        tree.AddEdge(7, 8);
        tree.AddEdge(1, 2);
        tree.AddEdge(2, 5);
        tree.AddEdge(7, 6);
        // index 4, 8, 9
        assertThat(tree.WeakVertices(), is(List.of(new Vertex(5), new Vertex(9), new Vertex(10))));
    }
}