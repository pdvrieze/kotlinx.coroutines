/*
 * Copyright 2016-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.coroutines.experimental.internal

import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.*

internal class ScopeOwnerCoroutine<R>(
    parentContext: CoroutineContext
) : AbstractCoroutine<R>(parentContext, true), CoroutineScope {
    /*
     * Always return true, so final exception is in the scope before its completion.
     */
    override fun cancel(cause: Throwable?): Boolean {
        super.cancel(cause)
        return true
    }

    override fun handleJobException(exception: Throwable) {
        /* do nothing -- exception will get reported upstream */
    }
}

internal class ContextScope(context: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext = context
}
