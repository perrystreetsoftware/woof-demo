import Combine

open class StateDerivingViewModel<ViewModelStateT, ViewModelErrorT>: ErrorProducingViewModel<ViewModelErrorT>, StatefulViewModelImplementing
where ViewModelErrorT: Swift.Error {
    @Published public private(set) var state: ViewModelStateT

    public init(initialValue: ViewModelStateT, source: AnyPublisher<ViewModelStateT, Never>) {
        state = initialValue
        super.init()
        source.assign(to: &$state)
    }
}
