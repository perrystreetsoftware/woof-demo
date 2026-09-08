import Combine

open class BaseLifecycleViewModel: ObservableObject {
    public var cancellables = Set<AnyCancellable>()
    private var didViewAppear = false

    public init() {}

    public final func onViewAppear() {
        if !didViewAppear {
            onFirstAppear()
            didViewAppear = true
        }
        onEveryAppear()
    }

    open func onFirstAppear() {}

    open func onEveryAppear() {}
}
