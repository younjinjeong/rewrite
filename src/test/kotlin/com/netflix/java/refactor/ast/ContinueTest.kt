package com.netflix.java.refactor.ast

import com.netflix.java.refactor.parse.Parser
import com.netflix.java.refactor.test.AstTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

abstract class ContinueTest(parser: Parser): AstTest(parser) {
    
    @Test
    fun continueFromWhileLoop() {
        val a = parse("""
            public class A {
                public void test() {
                    while(true) continue;
                }
            }
        """)
        
        val whileLoop = a.classDecls[0].methods[0].body.statements[0] as WhileLoop
        assertTrue(whileLoop.body is Continue)
        assertNull((whileLoop.body as Continue).label)
    }

    @Test
    fun continueFromLabeledWhileLoop() {
        val a = parse("""
            public class A {
                public void test() {
                    labeled: while(true)
                        continue labeled;
                }
            }
        """)

        val whileLoop = (a.classDecls[0].methods[0].body.statements[0] as Label).statement as WhileLoop
        assertTrue(whileLoop.body is Continue)
        assertEquals("labeled", (whileLoop.body as Continue).label)
    }
}