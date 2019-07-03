package br.com.andrefch.libraries.logger

import br.com.andrefch.libraries.logger.tree.CrashlyticsTree
import timber.log.Timber

object LoggerUtil {

    @JvmOverloads
    fun initialize(debugMode: Boolean = BuildConfig.DEBUG) {
        if (Timber.treeCount() > 0) {
            return
        }

        val tree: Timber.Tree = if (debugMode) {
            Timber.DebugTree()
        } else {
            CrashlyticsTree()
        }

        Timber.plant(tree)
    }
}