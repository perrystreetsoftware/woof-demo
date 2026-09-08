import Combine

public protocol StatefulViewModelImplementing: ObservableObject {
    associatedtype ViewModelStateT

    var state: ViewModelStateT { get }
}
