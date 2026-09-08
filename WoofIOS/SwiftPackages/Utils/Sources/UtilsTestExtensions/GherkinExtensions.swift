import Quick

public func Given(_ description: String, closure: () -> Void) {
    QuickSpec.context("Given " + description, closure: closure)
}

public func And(_ description: String, closure: () -> Void) {
    QuickSpec.context("And " + description, closure: closure)
}

public func When(_ description: String, closure: () -> Void) {
    QuickSpec.context("When " + description, closure: closure)
}

public func Then(_ description: String, file: FileString = #file, line: UInt = #line, closure: @escaping () throws -> Void) {
    QuickSpec.it("Then " + description, closure: closure)
}
