import SwiftUI

@ViewBuilder
public func viewGuard(_ condition: Bool, @ViewBuilder content: () -> some View) -> some View {
    if condition {
        content()
    }
}

@ViewBuilder
public func viewGuard<T>(_ value: T?, @ViewBuilder content: (T) -> some View) -> some View {
    if let value {
        content(value)
    }
}
