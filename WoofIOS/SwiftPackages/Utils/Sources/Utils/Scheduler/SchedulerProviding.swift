import CombineSchedulers
import Foundation

public protocol SchedulerProviding {
    var mainScheduler: AnySchedulerOf<DispatchQueue> { get }
    var ioScheduler: AnySchedulerOf<DispatchQueue> { get }
    var computationScheduler: AnySchedulerOf<DispatchQueue> { get }
}
