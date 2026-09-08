import Foundation
import Swinject
import SwinjectAutoregistration

public final class TimeAdvancingFactory {
    private let scheduler: TestSchedulerProvider

    public init(_ container: Container) {
        scheduler = container~>
    }

    @discardableResult
    public func withTestSchedulers() -> Self {
        scheduler.useTestSchedulers()
        return self
    }

    public func tick() {
        scheduler.testScheduler.advance(by: .microseconds(1))
    }

    public func advanceMillis(_ amount: Int) {
        scheduler.testScheduler.advance(by: .milliseconds(amount))
    }
}
