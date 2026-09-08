import CombineExpectations
import Foundation

public extension Recorder {
    func lastValue() -> Input {
        try! availableElements.get().last!
    }
}
