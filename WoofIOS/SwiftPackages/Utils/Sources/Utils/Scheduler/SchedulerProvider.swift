import CombineSchedulers
import DI
import Foundation

@SingleForProtocol
public final class SchedulerProvider: SchedulerProviding {
    public var mainScheduler: AnySchedulerOf<DispatchQueue> { DispatchQueue.main.eraseToAnyScheduler() }
    public var ioScheduler: AnySchedulerOf<DispatchQueue> { DispatchQueue.global(qos: .utility).eraseToAnyScheduler() }
    public var computationScheduler: AnySchedulerOf<DispatchQueue> { DispatchQueue.global(qos: .userInitiated).eraseToAnyScheduler() }

    public init() {}
}
