package com.netflix.java.refactor.ast

import com.netflix.java.refactor.parse.Parser
import com.netflix.java.refactor.test.AstTest
import org.junit.Test
import kotlin.test.assertTrue

abstract class WhileLoopTest(parser: Parser): AstTest(parser) {
    
    @Test
    fun whileLoop() {
        val a = parse("""
            public class A {
                public void test() {
                    while(true) {
                    }
                }
            }
        """)
        
        val whileLoop = a.classDecls[0].methods[0].body.statements[0] as WhileLoop
        assertTrue(whileLoop.condition.expr is Literal)
        assertTrue(whileLoop.body is Block)
    }
}