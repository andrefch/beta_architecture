package br.com.andrefch.libraries.logger

import br.com.andrefch.libraries.logger.tree.CrashlyticsTree
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import timber.log.Timber
import kotlin.random.Random

class LoggerUtilTest {

    @Before
    fun setup() {
        Timber.uprootAll()
    }

    @Test
    fun `Shouldn't have a tree if LoggerUtil is not initialized`() {
        assertEquals(0, Timber.treeCount())
    }

    @Test
    fun `Should have a tree when LoggerUtil is initialized`() {
        LoggerUtil.initialize()
        assertEquals(TIMBER_DEFAULT_TREE_COUNT, Timber.treeCount())
    }

    @Test
    fun `Should have just one tree when LoggerUtil is initialized multiple times`() {
        val min = MIN_TIMES_TIMBER_INITIALIZED
        val max = Random.nextInt(MIN_TIMES_TIMBER_INITIALIZED, MAX_TIMES_TIMBER_INITIALIZED) + 1

        for (i in min..max) {
            LoggerUtil.initialize()
        }

        assertEquals(TIMBER_DEFAULT_TREE_COUNT, Timber.treeCount())
    }

    @Test
    fun `Should plant a DebugTree instance if DebugMode is true on initialize`() {
        LoggerUtil.initialize(true)
        val tree = Timber.forest().firstOrNull()

        assertNotNull("Tree is null.", tree)
        assertTrue("Tree is not a instance of DebugTree.", tree is Timber.DebugTree)
    }

    @Test
    fun `Should plant a CrashlyticsTree instance if DebugMode is false on initialize`() {
        LoggerUtil.initialize(false)
        val tree = Timber.forest().firstOrNull()

        assertNotNull("Tree is null.", tree)
        assertTrue("Tree is not a instance of CrashlyticsTree.", tree is CrashlyticsTree)
    }

    companion object {
        private const val TIMBER_DEFAULT_TREE_COUNT = 1

        private const val MIN_TIMES_TIMBER_INITIALIZED = 2
        private const val MAX_TIMES_TIMBER_INITIALIZED = 1000
    }
}