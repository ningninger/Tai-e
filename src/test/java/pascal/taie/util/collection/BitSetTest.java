/*
 * Tai-e: A Static Analysis Framework for Java
 *
 * Copyright (C) 2020-- Tian Tan <tiantan@nju.edu.cn>
 * Copyright (C) 2020-- Yue Li <yueli@nju.edu.cn>
 * All rights reserved.
 *
 * Tai-e is only for educational and academic purposes,
 * and any form of commercial use is disallowed.
 * Distribution of Tai-e is disallowed without the approval.
 */

package pascal.taie.util.collection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BitSetTest {

    @Test
    public void testSet() {
        BitSet s = new BitSet();
        assertTrue(s.set(1));
        assertFalse(s.set(1));
        assertTrue(s.set(10000));
        assertFalse(s.set(10000));
        assertEquals(2, s.cardinality());
        System.out.println(s);
    }

    @Test
    public void testAnd() {
        BitSet s = BitSet.of(1, 2, 3);
        assertFalse(s.and(BitSet.of(1, 2, 3)));
        assertTrue(s.and(BitSet.of(1)));
        assertEquals(1, s.cardinality());
        assertFalse(s.and(BitSet.of(1, 11111, 22222, 33333)));
        assertTrue(s.and(BitSet.of(11111, 22222, 33333)));
        assertTrue(s.isEmpty());
        System.out.println(s);
    }

    @Test
    public void testAndNot() {
        BitSet s = BitSet.of(1, 2, 3);
        s.andNot(BitSet.of(1, 2, 3));
        assertTrue(s.isEmpty());
        s.andNot(BitSet.of(1, 2, 3));
        assertTrue(s.isEmpty());
        s.or(BitSet.of(1, 1, 1));
        s.andNot(BitSet.of(2));
        assertEquals(1, s.cardinality());
        System.out.println(s);
    }

    @Test
    public void testOr() {
        BitSet s = BitSet.of(1, 2, 3);
        assertFalse(s.or(BitSet.of(1, 2, 3)));
        assertFalse(s.or(BitSet.of(1)));
        assertEquals(3, s.cardinality());
        assertTrue(s.or(BitSet.of(1, 11111, 22222, 33333)));
        assertFalse(s.or(BitSet.of(11111, 22222, 33333)));
        assertEquals(6, s.cardinality());
        System.out.println(s);
    }

    @Test
    public void testXor() {
        BitSet s = BitSet.of(1, 2, 300);
        assertTrue(s.xor(new BitSet(s)));
        assertTrue(s.isEmpty());
    }

    @Test
    public void testSetTo() {
        BitSet s = BitSet.of(1, 2, 300);
        s.setTo(BitSet.of());
        assertTrue(s.isEmpty());
        s.setTo(BitSet.of(111, 222, 333));
        assertEquals(s, BitSet.of(111, 222, 333));
        s.setTo(BitSet.of(1));
        assertEquals(s, BitSet.of(1));
        s.setTo(BitSet.of(11111));
        assertEquals(s, BitSet.of(11111));
    }

    @Test
    public void testContains() {
        assertTrue(BitSet.of().contains(BitSet.of()));
        assertTrue(BitSet.of(1, 2, 3).contains(BitSet.of()));
        assertTrue(BitSet.of(1, 2, 3).contains(BitSet.of(1)));
        assertTrue(BitSet.of(1, 2, 3).contains(BitSet.of(1, 2, 3)));
        assertFalse(BitSet.of(1, 2, 3).contains(BitSet.of(11111)));
        assertTrue(BitSet.of(1, 2, 3, 11111).contains(BitSet.of(11111)));
        assertFalse(BitSet.of(1).contains(BitSet.of(1, 2, 3)));
    }
}