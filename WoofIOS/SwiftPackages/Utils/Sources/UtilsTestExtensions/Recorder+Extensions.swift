import CombineExpectations
import Foundation

public extension Recorder {
    var lastElement: Input {
        try! self.availableElements.get().last!
    }
}
