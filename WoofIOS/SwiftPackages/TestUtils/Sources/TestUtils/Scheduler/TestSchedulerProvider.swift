import CombineSchedulers
import Foundation
import Utils

public final class TestSchedulerProvider: SchedulerProviding {
    public let testScheduler = DispatchQueue.test
    public var mainScheduler: AnySchedulerOf<DispatchQueue> = .immediate
    public var ioScheduler: AnySchedulerOf<DispatchQueue> = .immediate
    public var computationScheduler: AnySchedulerOf<DispatchQueue> = .immediate

    public init() {}

    public func useTestSchedulers() {
        mainScheduler = testScheduler.eraseToAnyScheduler()
        ioScheduler = testScheduler.eraseToAnyScheduler()
        computationScheduler = testScheduler.eraseToAnyScheduler()
    }
}
