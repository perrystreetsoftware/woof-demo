package com.perrystreet.woof.presentation.common.viewmodel

import com.perrystreet.woof.presentation.common.viewmodel.base.ErrorProducingViewModel
import com.perrystreet.woof.presentation.common.viewmodel.base.IStatefulViewModel

abstract class StateDerivingViewModel<T : Any> : ErrorProducingViewModel(), IStatefulViewModel<T>
