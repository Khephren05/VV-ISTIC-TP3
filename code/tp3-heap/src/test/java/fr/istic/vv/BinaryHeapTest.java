package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class TestBinaryHeap {

    private BinaryHeap<Integer> bibi;
    private ArrayList<Integer> initiatedBibi;

    /**
     * Creates new empty heap called bibi for each test
     */
    @BeforeEach
    public void setUp() {
        bibi = new BinaryHeap<>(Integer::compareTo);
    }

    /**
     * Provides a data set for the test of the method push
     * @return Stream of {entry datas, checking datas}
     */
    public static Stream<Arguments> provideHeapVerifications() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList(1)), new ArrayList<>(Arrays.asList(1))),
                Arguments.of(new ArrayList<>(Arrays.asList(1,1)), new ArrayList<>(Arrays.asList(1))),
                Arguments.of(new ArrayList<>(Arrays.asList(5, 1)), new ArrayList<>(Arrays.asList(5, 1))),
                Arguments.of(new ArrayList<>(Arrays.asList(10, 3, 11)), new ArrayList<>(Arrays.asList(11, 3, 10))),
                Arguments.of(new ArrayList<>(Arrays.asList(10, 3, 11, 25)), new ArrayList<>(Arrays.asList(25, 11, 10, 3))),
                Arguments.of(new ArrayList<>(Arrays.asList(10, 3, 11, 25, 12, 9, -45, 14)), new ArrayList<>(Arrays.asList(25,14,10,12,11,9,-45,3)))
        );
    }

    /**
     * Test the push method
     * Excpected : each pushed ints must be well arranged
     * @param intsToAdd set of values to add to the heap
     * @param verificationHeap verification ArrayList to compare with the heap created
     */
    @ParameterizedTest
    @MethodSource("provideHeapVerifications")
    @DisplayName("Test method push")
    public void testPush(ArrayList<Integer> intsToAdd, ArrayList<Integer> verificationHeap) {
        intsToAdd.forEach((n) -> bibi.push(n));
        assertEquals(bibi.getHeap(), verificationHeap);
    }

    /**
     * Test the pop method
     * Expected : Pop method returns the minimal
     */
    @Test
    public void testPopNormal() {
        new ArrayList<>(Arrays.asList(10, 3, 11, 25)).forEach((n) -> bibi.push(n));
        Integer popedInteger = bibi.pop();
        assertEquals(3,popedInteger);
        assertEquals(new ArrayList<>(Arrays.asList(25, 11, 10)), bibi.getHeap());
    }

    @Test
    public void testPopThrowException() {
        assertThrows(NoSuchElementException.class, () -> bibi.pop());
    }

    @Test
    public void testPeekNormal() {
        new ArrayList<>(Arrays.asList(10, 3, 11, 25)).forEach((n) -> bibi.push(n));
        Integer popedInteger = bibi.peek();
        assertEquals(3,popedInteger);
        assertEquals(new ArrayList<>(Arrays.asList(25, 11, 10, 3)), bibi.getHeap());
    }

    @Test
    public void testPeekThrowException() {
        assertThrows(NoSuchElementException.class, () -> bibi.peek());
    }

    @Test
    public void testCount(){
        new ArrayList<>(Arrays.asList(10, 3, 11, 25)).forEach((n) -> bibi.push(n));
        bibi.pop();
        assertEquals(3,bibi.count());
    }

    @Test
    public void testCountEmptyHeap(){
        assertEquals(0,bibi.count());
    }

    @Test
    public void testFindMinHeapIndex(){
        new ArrayList<>(Arrays.asList(25, 10, 7, 5 , 6, 2, 1, 4, 3)).forEach((n) -> bibi.push(n));
        assertEquals(6, bibi.findMinHeapIndex());
        assertEquals(1, bibi.getHeap().get(bibi.findMinHeapIndex()));
    }

    @Test
    public void testFindMinHeapIndex2(){
        new ArrayList<>(Arrays.asList(25, 10, 7, 5 , 1, 2, 6, 4, 3)).forEach((n) -> bibi.push(n));
        assertEquals(4, bibi.findMinHeapIndex());
        assertEquals(1, bibi.getHeap().get(bibi.findMinHeapIndex()));
    }
}